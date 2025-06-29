package com.example.temosvagas.contexts.autenticacao;

import com.example.temosvagas.contexts.autenticacao.AutenticacaoRequestDTO;
import com.example.temosvagas.contexts.autenticacao.AutenticacaoResponseDTO;
import com.example.temosvagas.entities.Candidato;
import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.repositories.CandidatoRepository;
import com.example.temosvagas.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder; // Importa o PasswordEncoder
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AutenticacaoService {

    @Autowired
    private CandidatoRepository candidatoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Injeta o PasswordEncoder

    public AutenticacaoResponseDTO autenticar(AutenticacaoRequestDTO request) {
        // Verifica se é candidato
        var candidatoOpt = candidatoRepo.findByEmail(request.getEmail());
        if (candidatoOpt.isPresent()) {
            Candidato candidato = candidatoOpt.get();
            // Usa o passwordEncoder.matches para comparar senha em hash
            if (passwordEncoder.matches(request.getSenha(), candidato.getSenha())) {
                return new AutenticacaoResponseDTO(
                        candidato.getId(),
                        candidato.getNome(),
                        candidato.getEmail(),
                        "CANDIDATO"
                );
            }
        }

        // Verifica se é empresa
        var empresaOpt = empresaRepo.findByEmail(request.getEmail());
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            // Usa o passwordEncoder.matches para comparar senha em hash
            if (passwordEncoder.matches(request.getSenha(), empresa.getSenha())) {
                return new AutenticacaoResponseDTO(
                        empresa.getId(),
                        empresa.getNome(),
                        empresa.getEmail(),
                        "EMPRESA"
                );
            }
        }

        // Se não encontrou nada ou senha inválida
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos.");
    }
}
