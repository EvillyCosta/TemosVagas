package com.example.temosvagas.services;

import com.example.temosvagas.dtos.CandidaturaRequestDTO;
import com.example.temosvagas.dtos.CandidaturaResponseDTO;
import com.example.temosvagas.entities.Candidato;
import com.example.temosvagas.entities.Candidatura;
import com.example.temosvagas.entities.Vaga;
import com.example.temosvagas.repositories.CandidatoRepository;
import com.example.temosvagas.repositories.CandidaturaRepository;
import com.example.temosvagas.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class CandidaturaService {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private VagaRepository vagaRepository;

    public List<CandidaturaResponseDTO> listarTodas() {
        List<Candidatura> candidaturas = candidaturaRepository.findAll();
        return candidaturas.stream()
                .map(CandidaturaResponseDTO::toDTO)
                .toList();
    }

    public CandidaturaResponseDTO buscarPorId(Long id) {
        Candidatura candidatura = candidaturaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidatura não encontrada"));

        return CandidaturaResponseDTO.toDTO(candidatura);
    }

    /**
     * Realiza a candidatura de um candidato a uma vaga, aplicando todas as regras de negócio necessárias.
     * <p>
     * Regras aplicadas:
     * <ul>
     *     <li>Valida existência do candidato e da vaga</li>
     *     <li>Impede inscrição após a data limite da vaga</li>
     *     <li>Impede múltiplas inscrições do mesmo candidato na mesma vaga</li>
     *     <li>Validações específicas por tipo de vaga:
     *         <ul>
     *             <li><b>ESTÁGIO</b>: apenas candidatos cursando graduação</li>
     *             <li><b>TRAINEE</b>: apenas candidatos com curso superior concluído</li>
     *             <li><b>CARGO</b>: sem validações adicionais</li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * @param dto DTO contendo os IDs do candidato e da vaga
     * @return CandidaturaResponseDTO com os dados da candidatura criada
     *
     * @throws org.springframework.web.server.ResponseStatusException com status 404 se o candidato ou vaga não forem encontrados
     * @throws org.springframework.web.server.ResponseStatusException com status 400 para qualquer violação das regras de negócio
     */

    public CandidaturaResponseDTO criarCandidatura(CandidaturaRequestDTO dto) {
        Candidato candidato = candidatoRepository.findById(dto.candidatoId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não encontrado"));

        Vaga vaga = vagaRepository.findById(dto.vagaId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        if (vaga.getDataLimite().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prazo para aplicação encerrado.");
        }

        boolean jaInscrito = vaga.getCandidaturas().stream()
                .anyMatch(c -> c.getCandidato().getId().equals(candidato.getId()));

        if (jaInscrito) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Candidato já inscrito nesta vaga.");
        }

        switch (vaga.getTipo()) {
            case ESTAGIO -> {
                if (Boolean.FALSE.equals(candidato.getCursandoGraduacao())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta vaga é para estágio. Apenas candidatos cursando graduação podem se inscrever.");
                }
            }
            case TRAINEE -> {
                if (candidato.getAnoConclusao() == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta vaga é para trainee. Apenas candidatos com curso superior concluído podem se inscrever.");
                }
            }
            case CARGO -> {
                // Nenhuma regra adicional
            }
        }

        Candidatura candidatura = dto.toEntity(candidato, vaga);
        Candidatura salva = candidaturaRepository.save(candidatura);
        return CandidaturaResponseDTO.toDTO(salva);
    }

    public void deletarCandidatura(Long id) {
        Candidatura candidatura = candidaturaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidatura não encontrada"));

        candidaturaRepository.delete(candidatura);
    }
}
