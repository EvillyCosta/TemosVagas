package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Filial;
import com.example.temosvagas.entities.Vaga;
import com.example.temosvagas.enums.TipoVaga;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record VagaRequestDTO(
        @NotBlank(message = "O título da vaga é obrigatório")
        @Size(max = 100, message = "O título pode ter no máximo 100 caracteres")
        String titulo,

        @NotNull(message = "O tipo da vaga é obrigatório")
        TipoVaga tipo,

        @Size(max = 255, message = "Os requisitos devem ter no máximo 255 caracteres")
        String requisitosMinimos,

        @NotNull(message = "A data limite é obrigatória")
        @Future(message = "A data limite deve ser futura")
        LocalDate dataLimite,

        String curso,

        String semestreDesejado,

        String anoConclusao,

        @NotNull(message = "O ID da filial é obrigatório")
        Long filialId
){
    public Vaga toEntity(Filial filial) {
        Vaga vaga = new Vaga();
        vaga.setTitulo(titulo);
        vaga.setTipo(tipo);
        vaga.setRequisitosMinimos(requisitosMinimos);
        vaga.setDataLimite(dataLimite);
        vaga.setCurso(curso);
        vaga.setSemestreDesejado(semestreDesejado);
        vaga.setAnoConclusao(anoConclusao);
        vaga.setFilial(filial);
        return vaga;
    }
}