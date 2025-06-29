package com.example.temosvagas.contexts.autenticacao;

import com.example.temosvagas.contexts.autenticacao.AutenticacaoRequestDTO;
import com.example.temosvagas.contexts.autenticacao.AutenticacaoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticacaoRequestDTO request) {
        AutenticacaoResponseDTO response = autenticacaoService.autenticar(request);
        return ResponseEntity.ok(response);
    }
}
