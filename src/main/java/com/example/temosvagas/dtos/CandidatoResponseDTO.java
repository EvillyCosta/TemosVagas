package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Candidato;

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
) {
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