package com.example.temosvagas.repositories;

import com.example.temosvagas.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    List<Vaga> findByFilialEmpresaId(Long empresaId);

    List<Vaga> findByDataLimiteAfter(LocalDate data);

}
