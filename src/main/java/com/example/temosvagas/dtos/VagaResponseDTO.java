package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Vaga;
import com.example.temosvagas.enums.TipoVaga;

import java.io.Serializable;
import java.time.LocalDate;

public record VagaResponseDTO(
        Long id,
        String titulo,
        TipoVaga tipo,
        String requisitosMinimos,
        LocalDate dataLimite,
        String curso,
        String semestreDesejado,
        String anoConclusao,
        Long filialId
) implements Serializable {
    private static final long serialVersionUID = 14L;
    public static VagaResponseDTO toDTO(Vaga vaga) {
        return new VagaResponseDTO(
                vaga.getId(),
                vaga.getTitulo(),
                vaga.getTipo(),
                vaga.getRequisitosMinimos(),
                vaga.getDataLimite(),
                vaga.getCurso(),
                vaga.getSemestreDesejado(),
                vaga.getAnoConclusao(),
                vaga.getFilial().getId()
        );
    }
}
