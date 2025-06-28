package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Filial;

public record FilialResponseDTO(
        Long id,
        String nome,
        String endereco,
        Long empresaId
) {

    public static FilialResponseDTO toDTO(Filial filial) {
        return new FilialResponseDTO(
                filial.getId(),
                filial.getNome(),
                filial.getEndereco(),
                filial.getEmpresa().getId()
        );
    }
}
