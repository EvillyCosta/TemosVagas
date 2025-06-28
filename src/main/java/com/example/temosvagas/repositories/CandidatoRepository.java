package com.example.temosvagas.repositories;


import com.example.temosvagas.entities.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Optional<Candidato> findByEmail(String email);
}
