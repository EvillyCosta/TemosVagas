package com.example.temosvagas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String tipoUsuario; // "CANDIDATO" ou "EMPRESA"
}