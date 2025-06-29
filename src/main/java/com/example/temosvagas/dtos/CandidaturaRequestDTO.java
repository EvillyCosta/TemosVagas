package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Candidato;
import com.example.temosvagas.entities.Candidatura;
import com.example.temosvagas.entities.Vaga;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CandidaturaRequestDTO(
        @NotNull(message = "O ID do candidato é obrigatório")
        Long candidatoId,

        @NotNull(message = "O ID da vaga é obrigatório")
        Long vagaId
) implements Serializable {
    private static final long serialVersionUID = 7L;

    public Candidatura toEntity(Candidato candidato, Vaga vaga) {
        Candidatura candidatura = new Candidatura();
        candidatura.setCandidato(candidato);
        candidatura.setVaga(vaga);
        candidatura.setStatus("PENDENTE"); // status inicial padrão
        return candidatura;
    }
}
