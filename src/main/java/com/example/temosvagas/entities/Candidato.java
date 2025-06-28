package com.example.temosvagas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidato extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;

    private String telefone;

    private String url;
    private Boolean cursandoGraduacao;  //para vagas de estágio
    private Integer anoConclusao;       //para vagas de trainee

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Candidatura> candidaturas;
}