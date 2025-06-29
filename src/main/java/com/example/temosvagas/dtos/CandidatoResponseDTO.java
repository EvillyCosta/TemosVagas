package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Candidato;

import java.io.Serializable;

public record CandidatoResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Boolean cursandoGraduacao,
        Integer anoConclusao,
        String habilidades,
        String curso,
        String semestreAtual,
        String arquivo
) implements Serializable {
    private static final long serialVersionUID = 6L;

    public static CandidatoResponseDTO toDTO(Candidato candidato) {
        return new CandidatoResponseDTO(
                candidato.getId(),
                candidato.getEmail(),
                candidato.getNome(),
                candidato.getTelefone(),
                candidato.getCpf(),
                candidato.getCursandoGraduacao(),
                candidato.getAnoConclusao(),
                candidato.getHabilidades(),
                candidato.getCurso(),
                candidato.getSemestreAtual(),
                candidato.getArquivo()
        );
    }
}