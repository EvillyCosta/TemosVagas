package com.example.temosvagas.controllers;

import com.example.temosvagas.dtos.CandidatoDTO;
import com.example.temosvagas.entities.Candidato;
import com.example.temosvagas.repositories.CandidatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidatos")
@RequiredArgsConstructor
//Cadastro de candidato
public class CandidatoController {

    private final CandidatoRepository candidatoRepository;

//    @PostMapping
//    public Candidato cadastrarCandidato(@RequestBody CandidatoDTO dto) {
//        Candidato candidato = new Candidato(null, dto.getNome(), dto.getEmail(), dto.getSenha(), dto.getTelefone(), dto.getLinkCurriculo(), null, null);
//        return candidatoRepository.save(candidato);
//    }

    @GetMapping("/teste")
    public String teste() {
        return "ok";
    }

}
