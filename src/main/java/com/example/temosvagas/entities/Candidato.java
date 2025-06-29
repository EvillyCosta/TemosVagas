package com.example.temosvagas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Candidato extends Usuario {

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 10)
    private String cpf;

    // Vagas de Estágio
    @Column()
    private Boolean cursandoGraduacao;

    // Vagas de Trainee
    @Column
    private Integer anoConclusao;

    @Column
    private String habilidades;

    @Column
    private String curso;

    @Column
    private String semestreAtual;

    @Column(length = 150)
    private String arquivo;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Candidatura> candidaturas;
}