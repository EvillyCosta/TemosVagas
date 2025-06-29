package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Empresa;

import java.io.Serializable;

public record EmpresaResponseDTO(
        Long id,
        String nome,
        String razaoSocial,
        String cnpj,
        String email
) implements Serializable {
    private static final long serialVersionUID = 10L;
    public static EmpresaResponseDTO toDTO(Empresa empresa) {
        return new EmpresaResponseDTO(
                empresa.getId(),
                empresa.getNome(),
                empresa.getRazaoSocial(),
                empresa.getCnpj(),
                empresa.getEmail()
        );
    }
}