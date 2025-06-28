package com.example.temosvagas.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    private String telefone;

    private String linkCurriculo;

    private Boolean cursandoGraduacao;  //para vagas de estágio
    private Integer anoConclusao;       //para vagas de trainee
}

