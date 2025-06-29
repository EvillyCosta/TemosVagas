package com.example.temosvagas.contexts.autenticacao;

import com.example.temosvagas.entities.Candidato;
import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.repositories.CandidatoRepository;
import com.example.temosvagas.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private CandidatoRepository candidatoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticacaoResquestDTO autenticacaoResquestDTO) {

        //verifica se é candidato
        var candidatoOpt = candidatoRepo.findByEmail(autenticacaoResquestDTO.getEmail());
        if (candidatoOpt.isPresent()) {
            Candidato candidato = candidatoOpt.get();
            if (candidato.getSenha().equals(autenticacaoResquestDTO.getSenha())) {
                return ResponseEntity.ok(new AutenticacaoResponseDTO(
                        candidato.getId(),
                        candidato.getNome(),
                        candidato.getEmail(),
                        "CANDIDATO"
                ));
            }
        }

        //verifica se é empresa
        var empresaOpt = empresaRepo.findByEmail(autenticacaoResquestDTO.getEmail());
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            if (empresa.getSenha().equals(autenticacaoResquestDTO.getSenha())) {
                return ResponseEntity.ok(new AutenticacaoResponseDTO(
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