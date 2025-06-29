package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.entities.Filial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FilialRequestDTO(
        @NotBlank(message = "O nome da filial é obrigatório")
        @Size(max = 100, message = "O nome da filial deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O endereço da filial é obrigatório")
        @Size(max = 150, message = "O endereço da filial deve ter no máximo 150 caracteres")
        String endereco,

        @NotNull(message = "O ID da empresa é obrigatório")
        Long empresaId
) {
    public Filial toEntity(Empresa empresa) {
        Filial filial = new Filial();
        filial.setNome(nome);
        filial.setEndereco(endereco);
        filial.setEmpresa(empresa);
        return filial;
    }
}
