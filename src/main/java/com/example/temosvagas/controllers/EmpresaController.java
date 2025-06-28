package com.example.temosvagas.controllers;

import com.example.temosvagas.dtos.EmpresaDTO;
import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
//Cadastro de empresa
public class EmpresaController {

    private final EmpresaRepository empresaRepository;

    @PostMapping
    public Empresa cadastrarEmpresa(@RequestBody EmpresaDTO dto) {
        Empresa empresa = new Empresa(null, dto.getNome(), dto.getEmail(), dto.getSenha());
        return empresaRepository.save(empresa);
    }
}
