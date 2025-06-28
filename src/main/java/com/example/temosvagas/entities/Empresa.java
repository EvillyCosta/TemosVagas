package com.example.temosvagas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa  extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String razaoSocial;

    private String cnpj;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Filial> filiais;
}
