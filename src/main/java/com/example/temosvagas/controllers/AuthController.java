package com.example.temosvagas.controllers;

import com.example.temosvagas.dtos.LoginDTO;
import com.example.temosvagas.dtos.LoginResponseDTO;
import com.example.temosvagas.entities.Candidato;
import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.repositories.CandidatoRepository;
import com.example.temosvagas.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CandidatoRepository candidatoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        //verifica se é candidato
        var candidatoOpt = candidatoRepo.findByEmail(loginDTO.getEmail());
        if (candidatoOpt.isPresent()) {
            Candidato candidato = candidatoOpt.get();
            if (candidato.getSenha().equals(loginDTO.getSenha())) {
                return ResponseEntity.ok(new LoginResponseDTO(
                        candidato.getId(),
                        candidato.getNome(),
                        candidato.getEmail(),
                        "CANDIDATO"
                ));
            }
        }

        //verifica se é empresa
        var empresaOpt = empresaRepo.findByEmail(loginDTO.getEmail());
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            if (empresa.getSenha().equals(loginDTO.getSenha())) {
                return ResponseEntity.ok(new LoginResponseDTO(
                        empresa.getId(),
                        empresa.getNome(),
                        empresa.getEmail(),
                        "EMPRESA"
                ));
            }
        }

        //se não for encontrado ou senha incorreta
        return ResponseEntity.status(401).body("Email ou senha inválidos.");
    }
}