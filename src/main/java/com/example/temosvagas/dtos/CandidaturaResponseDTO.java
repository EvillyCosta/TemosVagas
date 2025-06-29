package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Candidatura;

import java.time.LocalDate;

public record CandidaturaResponseDTO(
        Long id,
        LocalDate dataAplicacao,
        String status,
        Long candidatoId,
        String nomeCandidato,
        Long vagaId,
        String tituloVaga
) {
    public static CandidaturaResponseDTO toDTO(Candidatura candidatura) {
        return new CandidaturaResponseDTO(
                candidatura.getId(),
                candidatura.getDataAplicacao(),
                candidatura.getStatus(),
                candidatura.getCandidato().getId(),
                candidatura.getCandidato().getNome(),
                candidatura.getVaga().getId(),
                candidatura.getVaga().getTitulo()
        );
    }
}
