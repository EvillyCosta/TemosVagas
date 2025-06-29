package com.example.temosvagas.contexts.relatorio;

import com.example.temosvagas.dtos.CandidatoResponseDTO;
import com.example.temosvagas.entities.Candidatura;
import com.example.temosvagas.entities.Vaga;
import com.example.temosvagas.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private VagaRepository vagaRepository;

    public List<CandidatoResponseDTO> listarCandidatosDaVaga(Long id) {
        Vaga vaga = vagaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        return vaga.getCandidaturas().stream()
                .map(Candidatura::getCandidato)
                .map(CandidatoResponseDTO::toDTO)
                .toList();
    }
}
