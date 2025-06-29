package com.example.temosvagas.contexts.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AutenticacaoResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String tipoUsuario; // "CANDIDATO" ou "EMPRESA"
}