package com.example.temosvagas.contexts.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AutenticacaoResponseDTO implements Serializable {
    private static final long serialVersionUID = 16L;
    private Long id;
    private String nome;
    private String email;
    private String tipoUsuario; // "CANDIDATO" ou "EMPRESA"
}