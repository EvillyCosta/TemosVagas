package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Empresa;

public record EmpresaResponseDTO(
        Long id,
        String nome,
        String razaoSocial,
        String cnpj,
        String email
) {
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