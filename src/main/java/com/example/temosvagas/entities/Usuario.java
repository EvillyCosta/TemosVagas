package com.example.temosvagas.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String senha;
}
