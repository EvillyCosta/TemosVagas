package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Candidato;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CandidatoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String senha,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        Boolean cursandoGraduacao,

        Integer anoConclusao,

        String habilidades,

        String curso,

        String semestreAtual,

        @Size(max = 150, message = "O nome do arquivo deve ter no máximo 150 caracteres")
        String arquivo
) {
    public Candidato toEntity() {
        Candidato candidato = new Candidato();
        candidato.setNome(nome);
        candidato.setEmail(email);
        candidato.setSenha(senha);
        candidato.setTelefone(telefone);
        candidato.setCpf(cpf);
        candidato.setCursandoGraduacao(cursandoGraduacao);
        candidato.setAnoConclusao(anoConclusao);
        candidato.setHabilidades(habilidades);
        candidato.setCurso(curso);
        candidato.setSemestreAtual(semestreAtual);
        return candidato;
    }
}