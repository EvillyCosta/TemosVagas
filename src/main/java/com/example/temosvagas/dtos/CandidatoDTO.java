package com.example.temosvagas.dtos;

import lombok.Data;

@Data
public class CandidatoDTO {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String linkCurriculo;
}