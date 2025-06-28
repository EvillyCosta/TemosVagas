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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curriculo_id", nullable = false, unique = true)
    private Curriculo curriculo;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Candidatura> candidaturas;
}

