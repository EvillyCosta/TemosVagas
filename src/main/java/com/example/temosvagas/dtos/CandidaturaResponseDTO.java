package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Candidatura;

import java.time.LocalDate;

import java.io.Serializable;
public record CandidaturaResponseDTO(
        Long id,
        LocalDate dataAplicacao,
        String status,
        Long candidatoId,
        String nomeCandidato,
        Long vagaId,
        String tituloVaga
) implements Serializable {
    private static final long serialVersionUID = 8L;
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
