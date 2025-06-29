package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Filial;

import java.io.Serializable;

public record FilialResponseDTO(
        Long id,
        String nome,
        String endereco,
        Long empresaId
)implements Serializable {
    private static final long serialVersionUID = 12L;

    public static FilialResponseDTO toDTO(Filial filial) {
        return new FilialResponseDTO(
                filial.getId(),
                filial.getNome(),
                filial.getEndereco(),
                filial.getEmpresa().getId()
        );
    }
}
