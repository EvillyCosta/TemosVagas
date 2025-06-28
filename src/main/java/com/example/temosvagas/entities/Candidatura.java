package com.example.temosvagas.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Candidatura {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate dataAplicacao;
    private String status;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;
}
