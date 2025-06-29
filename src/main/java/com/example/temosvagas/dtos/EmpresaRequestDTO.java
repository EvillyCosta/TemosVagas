package com.example.temosvagas.dtos;

import com.example.temosvagas.entities.Empresa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record EmpresaRequestDTO(
        @NotBlank
        @Size(max = 100)
        String nome,

        @NotBlank
        @Size(max = 150)
        String razaoSocial,

        @NotBlank
        @Size(max = 25)
        String cnpj,

        @NotBlank @Email
        String email,

        @NotBlank
        @Size(min = 6, max = 100)
        String senha
) implements Serializable {
        private static final long serialVersionUID = 9L;
        public Empresa toEntity() {
                Empresa empresa = new Empresa();
                empresa.setNome(this.nome);
                empresa.setRazaoSocial(this.razaoSocial);
                empresa.setCnpj(this.cnpj);
                empresa.setEmail(this.email);
                empresa.setSenha(this.senha);
                return empresa;
        }
}