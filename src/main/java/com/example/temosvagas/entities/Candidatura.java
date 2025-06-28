package com.example.temosvagas.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Candidatura {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDate dataAplicacao;

    @Column(nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    // Adiciona a data da criação da requisição como a data de aplicação
    @PrePersist
    public void prePersist() {
        this.dataAplicacao = LocalDate.now();
    }
}
