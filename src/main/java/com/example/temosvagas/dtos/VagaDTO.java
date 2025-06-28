package com.example.temosvagas.dtos;

import com.example.temosvagas.enums.TipoVaga;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VagaDTO {
    private String titulo;
    private TipoVaga tipo;
    private String requisitos;
    private LocalDate dataLimite;
    private String curso;
    private Integer semestre;
    private Integer anoConclusao;
    private Long empresaId; // ID da empresa que cadastrou a vaga
}