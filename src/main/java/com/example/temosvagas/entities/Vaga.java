package com.example.temosvagas.entities;


import com.example.temosvagas.enums.TipoVaga;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVaga tipo;

    @Column(length = 200)
    private String requisitosMinimos;

    @Column(nullable = false)
    private LocalDate dataLimite;

    @Column(length = 100)
    private String curso;

    @Column
    private String semestreDesejado;

    @Column
    private String anoConclusao;

    @ManyToOne(optional = false)
    @JoinColumn()
    private Filial filial;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidatura> candidaturas = new ArrayList<>();
}