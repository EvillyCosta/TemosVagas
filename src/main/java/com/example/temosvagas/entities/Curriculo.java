package com.example.temosvagas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Curriculo {

    @Id
    @GeneratedValue
    private Long id;

    // Vagas de Estágio
    @Column
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
}
