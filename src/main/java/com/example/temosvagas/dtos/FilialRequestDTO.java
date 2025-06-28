package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.entities.Filial;

public record FilialRequestDTO(
        String nome,
        String endereco,
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
