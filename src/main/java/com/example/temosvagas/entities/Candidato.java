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

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
    private List<Candidatura> candidaturas;
}