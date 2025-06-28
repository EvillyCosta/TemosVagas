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

    private String requisitos;

    private LocalDate dataLimite;

    private String curso;

    private Integer semestre;

    private Integer anoConclusao;

    @ManyToOne
    private Empresa empresa;

    @ManyToMany
    @JoinTable(
            name = "vaga_candidatos",
            joinColumns = @JoinColumn(name = "vaga_id"),
            inverseJoinColumns = @JoinColumn(name = "candidato_id")
    )
    private List<Candidato> candidatos = new ArrayList<>();

    //construtor personalizado
    public Vaga(Long id, String titulo, TipoVaga tipo, String requisitos,
                LocalDate dataLimite, String curso, Integer semestre,
                Integer anoConclusao, Empresa empresa) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.requisitos = requisitos;
        this.dataLimite = dataLimite;
        this.curso = curso;
        this.semestre = semestre;
        this.anoConclusao = anoConclusao;
        this.empresa = empresa;
        this.candidatos = new ArrayList<>();
    }
}
