package com.example.temosvagas.contexts.autenticacao;

import lombok.Data;

import java.io.Serializable;

@Data
public class AutenticacaoRequestDTO implements Serializable {
    private static final long serialVersionUID = 15L;
    private String email;
    private String senha;
}
