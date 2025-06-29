package com.example.temosvagas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidatura implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDate dataAplicacao;

    @Column(nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    // Define a data atual como data de aplicação no momento da persistência
    @PrePersist
    public void prePersist() {
        this.dataAplicacao = LocalDate.now();
    }
}
