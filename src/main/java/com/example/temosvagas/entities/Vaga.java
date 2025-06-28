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

    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoVaga tipo;

    private String requisitosMinimos;

    private LocalDate dataLimite;

    private String curso;

    private Integer semestre;

    private Integer anoConclusao;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL)
    private List<Candidatura> candidaturas;

    // será analisado
    //construtor personalizado
    public Vaga(Long id, String titulo, TipoVaga tipo, String requisitos,
                LocalDate dataLimite, String curso, Integer semestre,
                Integer anoConclusao, Filial filial) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.requisitosMinimos = requisitos;
        this.dataLimite = dataLimite;
        this.curso = curso;
        this.semestre = semestre;
        this.anoConclusao = anoConclusao;
        this.filial = filial;
        this.candidaturas = new ArrayList<>();
    }
}