package com.example.temosvagas.repositories;

import com.example.temosvagas.entities.Filial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilialRepository extends JpaRepository<Filial, Long> {

    List<Filial> findAllByEmpresaId(Long empresaId);
}
