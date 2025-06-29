package com.example.temosvagas.services;

import com.example.temosvagas.dtos.VagaRequestDTO;
import com.example.temosvagas.dtos.VagaResponseDTO;
import com.example.temosvagas.entities.Filial;
import com.example.temosvagas.entities.Vaga;
import com.example.temosvagas.mapper.MapperGeral;
import com.example.temosvagas.repositories.FilialRepository;
import com.example.temosvagas.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private FilialRepository filialRepository;

    public List<VagaResponseDTO> buscarTodasVagas() {
        List<Vaga> vagas = vagaRepository.findAll();
        List<VagaResponseDTO> dtos = MapperGeral.toVagaResponseList(vagas);
        return dtos;
    }

    public List<VagaResponseDTO> buscarVagasEmAberto() {
        List<Vaga> vagas = vagaRepository.findByDataLimiteAfter(LocalDate.now());
        return vagas.stream()
                .map(VagaResponseDTO::toDTO)
                .toList();
    }

    public VagaResponseDTO buscarPorId(Long id) {
        Vaga vaga = vagaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        return VagaResponseDTO.toDTO(vaga);
    }

    public VagaResponseDTO criarVaga(VagaRequestDTO dto) {
        Filial filial = filialRepository.findById(dto.filialId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Filial não encontrada"));

        if (dto.dataLimite() == null || dto.dataLimite().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data limite deve ser uma data futura.");
        }

        validarTipoVaga(dto);

        Vaga novaVaga = dto.toEntity(filial);
        Vaga salva = vagaRepository.save(novaVaga);
        return VagaResponseDTO.toDTO(salva);
    }

    public VagaResponseDTO atualizarVaga(Long id, VagaRequestDTO dto) {
        Vaga vaga = vagaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        Filial filial = filialRepository.findById(dto.filialId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Filial não encontrada"));

        validarTipoVaga(dto);

        vaga.setTitulo(dto.titulo());
        vaga.setTipo(dto.tipo());
        vaga.setRequisitosMinimos(dto.requisitosMinimos());
        vaga.setDataLimite(dto.dataLimite());
        vaga.setCurso(dto.curso());
        vaga.setSemestreDesejado(dto.semestreDesejado());
        vaga.setAnoConclusao(dto.anoConclusao());
        vaga.setFilial(filial);

        Vaga atualizada = vagaRepository.save(vaga);
        return VagaResponseDTO.toDTO(atualizada);
    }

    public void deletarVaga(Long id) {
        Vaga vaga = vagaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));
        vagaRepository.delete(vaga);
    }

    private void validarTipoVaga(VagaRequestDTO dto) {
        switch (dto.tipo()) {
            case ESTAGIO -> {
                if (dto.curso() == null || dto.curso().isBlank()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O curso é obrigatório para vaga de estágio.");
                }
                if (dto.semestreDesejado() == null || dto.semestreDesejado().isBlank()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O semestre é obrigatório para vaga de estágio.");
                }
            }
            case TRAINEE -> {
                if (dto.anoConclusao() == null || dto.anoConclusao().isBlank()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ano de conclusão é obrigatório para vaga de trainee.");
                }
            }
            case CARGO -> {
                if (dto.requisitosMinimos() == null || dto.requisitosMinimos().isBlank()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os requisitos mínimos são obrigatórios para cargos.");
                }
            }
        }
    }
}
