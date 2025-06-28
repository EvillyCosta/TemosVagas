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

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(length = 150)
    private String url;

    // Vagas de Estágio
    @Column
    private Boolean cursandoGraduacao;

    // Vagas de Trainee
    @Column
    private Integer anoConclusao;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Candidatura> candidaturas;
}