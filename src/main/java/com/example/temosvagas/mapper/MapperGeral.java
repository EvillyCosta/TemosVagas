package com.example.temosvagas.mapper;

import com.example.temosvagas.dtos.CandidatoResponseDTO;
import com.example.temosvagas.dtos.EmpresaResponseDTO;
import com.example.temosvagas.dtos.FilialResponseDTO;
import com.example.temosvagas.dtos.VagaResponseDTO;
import com.example.temosvagas.entities.Candidato;
import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.entities.Filial;
import com.example.temosvagas.entities.Vaga;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class MapperGeral implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static List<EmpresaResponseDTO> toEmpresaResponseList (List<Empresa> empresas) {
        if (empresas == null || empresas.isEmpty()) {
            return Collections.emptyList();
        }

        return empresas.stream()
                .map(EmpresaResponseDTO::toDTO)
                .toList();
    }

    public static List<FilialResponseDTO> toFilialResponseList (List<Filial> filiais) {
        if (filiais == null || filiais.isEmpty()) {
            return Collections.emptyList();
        }

        return filiais.stream()
                .map(FilialResponseDTO::toDTO)
                .toList();
    }

    public static List<CandidatoResponseDTO> toCandidatoResponseList (List<Candidato> candidatos) {
        if (candidatos == null || candidatos.isEmpty()) {
            return Collections.emptyList();
        }

        return candidatos.stream()
                .map(CandidatoResponseDTO::toDTO)
                .toList();
    }

    public static List<VagaResponseDTO> toVagaResponseList (List<Vaga> vagas) {
        if (vagas == null || vagas.isEmpty()) {
            return Collections.emptyList();
        }

        return vagas.stream()
                .map(VagaResponseDTO::toDTO)
                .toList();
    }
}