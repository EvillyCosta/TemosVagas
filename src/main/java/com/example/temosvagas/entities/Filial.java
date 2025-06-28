package com.example.temosvagas.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Filial {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL)
    private List<Vaga> vagas;
}
