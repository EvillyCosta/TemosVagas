package com.example.temosvagas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filial implements Serializable {
    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 150)
    private String endereco;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL)
    private List<Vaga> vagas;
}
