package com.example.temosvagas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Empresa  extends Usuario {

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 150)
    private String razaoSocial;

    @Column(nullable = false, length = 25, unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Filial> filiais;
}