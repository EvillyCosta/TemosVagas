package com.example.temosvagas.controllers;

import com.example.temosvagas.dtos.VagaDTO;
import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.entities.Vaga;
import com.example.temosvagas.enums.TipoVaga;
import com.example.temosvagas.repositories.CandidatoRepository;
import com.example.temosvagas.repositories.EmpresaRepository;
import com.example.temosvagas.repositories.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vagas")
@RequiredArgsConstructor
public class VagaController {
    private final CandidatoRepository candidatoRepository;
    private final VagaRepository vagaRepository;
    private final EmpresaRepository empresaRepository;

    @PostMapping
    //Cadastro de vaga pela empresa
    public ResponseEntity<?> cadastrarVaga(@RequestBody VagaDTO dto) {
        Empresa empresa = empresaRepository.findById(dto.getEmpresaId()).orElse(null);
        if (empresa == null) {
            return ResponseEntity.badRequest().body("Empresa não encontrada.");
        }

        //Valida se a data limite é futura
        if (dto.getDataLimite() == null || dto.getDataLimite().isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("A data limite deve ser uma data futura.");
        }

        //Valida campos obrigatórios por tipo de vaga
        switch (dto.getTipo()) {
            case ESTAGIO -> {
                if (dto.getCurso() == null || dto.getCurso().isBlank()) {
                    return ResponseEntity.badRequest().body("O curso é obrigatório para vaga de estágio.");
                }
                if (dto.getSemestre() == null) {
                    return ResponseEntity.badRequest().body("O semestre é obrigatório para vaga de estágio.");
                }
            }
            case TRAINEE -> {
                if (dto.getAnoConclusao() == null) {
                    return ResponseEntity.badRequest().body("O ano de conclusão é obrigatório para vaga de trainee.");
                }
            }
            case CARGO -> {
                if (dto.getRequisitos() == null || dto.getRequisitos().isBlank()) {
                    return ResponseEntity.badRequest().body("Os requisitos mínimos são obrigatórios para cargos.");
                }
            }
        }

        Vaga vaga = new Vaga(
                null,
                dto.getTitulo(),
                dto.getTipo(),
                dto.getRequisitos(),
                dto.getDataLimite(),
                dto.getCurso(),
                dto.getSemestre(),
                dto.getAnoConclusao(),
                empresa
        );

        return ResponseEntity.ok(vagaRepository.save(vaga));
    }

    @GetMapping
    // Lista apenas vagas com data limite no futuro
    // Atende ao requisito de "visualizar vagas em aberto"
    public List<VagaDTO> listarVagasEmAberto() {
        return vagaRepository.findByDataLimiteAfter(LocalDate.now()).stream().map(vaga -> {
            VagaDTO dto = new VagaDTO();
            dto.setTitulo(vaga.getTitulo());
            dto.setTipo(vaga.getTipo());
            dto.setRequisitos(vaga.getRequisitos());
            dto.setDataLimite(vaga.getDataLimite());
            dto.setCurso(vaga.getCurso());
            dto.setSemestre(vaga.getSemestre());
            dto.setAnoConclusao(vaga.getAnoConclusao());
            dto.setEmpresaId(vaga.getEmpresa().getId());
            return dto;
        }).toList();
    }

    @PostMapping("/{id}/aplicar")
    public ResponseEntity<?> aplicarVaga(@PathVariable Long id, @RequestParam Long idCandidato) {
        var vagaOpt = vagaRepository.findById(id);
        var candidatoOpt = candidatoRepository.findById(idCandidato);

        if (vagaOpt.isEmpty() || candidatoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var vaga = vagaOpt.get();
        var candidato = candidatoOpt.get();

        //impede inscrição após o prazo
        if (vaga.getDataLimite().isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Prazo para aplicação encerrado.");
        }

        //impede inscrição duplicada
        if (vaga.getCandidatos().contains(candidato)) {
            return ResponseEntity.badRequest().body("Candidato já inscrito nesta vaga.");
        }

        //testes de consistência com base no tipo da vaga
        switch (vaga.getTipo()) {
            case ESTAGIO -> {
                if (candidato.getCursandoGraduacao() == null || !candidato.getCursandoGraduacao()) {
                    return ResponseEntity.badRequest().body("Esta vaga é para estágio. Apenas candidatos cursando graduação podem se inscrever.");
                }
            }
            case TRAINEE -> {
                if (candidato.getAnoConclusao() == null) {
                    return ResponseEntity.badRequest().body("Esta vaga é para trainee. Apenas candidatos com curso superior concluído podem se inscrever.");
                }
            }
            //vagas CARGO não precisam de validação extra no momento
        }

        vaga.getCandidatos().add(candidato);
        vagaRepository.save(vaga);

        return ResponseEntity.ok("Inscrição realizada com sucesso.");
    }

    @GetMapping("/{id}/candidatos")
    //Empresa visualiza os candidatos inscritos em uma vaga
    public ResponseEntity<?> listarCandidatosDaVaga(@PathVariable Long id) {
        var vagaOpt = vagaRepository.findById(id);
        if (vagaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var candidatos = vagaOpt.get().getCandidatos();
        return ResponseEntity.ok(candidatos);
    }

    @PutMapping("/{id}/prorrogar")
    //Empresa prorroga data antes do encerramento
    public ResponseEntity<?> prorrogarDataLimite(@PathVariable Long id, @RequestParam LocalDate novaDataLimite) {
        var vagaOpt = vagaRepository.findById(id);
        if (vagaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var vaga = vagaOpt.get();

        //Impede prorrogar se o prazo atual já expirou
        if (vaga.getDataLimite().isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Não é possível prorrogar uma vaga cujo prazo já expirou.");
        }

        //Impede definir uma nova data no passado
        if (novaDataLimite.isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("A nova data limite deve ser uma data futura.");
        }

        vaga.setDataLimite(novaDataLimite);
        vagaRepository.save(vaga);

        return ResponseEntity.ok("Data limite prorrogada com sucesso.");
    }
}
