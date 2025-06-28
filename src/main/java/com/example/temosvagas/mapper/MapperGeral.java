package com.example.temosvagas.mapper;

import com.example.temosvagas.dtos.EmpresaResponseDTO;
import com.example.temosvagas.entities.Empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapperGeral implements Serializable {

    private static final long serialVersionUID = 1L;

    public static List<EmpresaResponseDTO> toEmpresaResponseList (List<Empresa> empresas) {
        if (empresas == null || empresas.isEmpty()) {
            return Collections.emptyList();
        }

        return empresas.stream()
                .map(EmpresaResponseDTO::toDTO)
                .toList();
    }
}