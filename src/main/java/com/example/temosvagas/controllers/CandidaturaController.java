package com.example.temosvagas.controllers;

import com.example.temosvagas.dtos.CandidaturaRequestDTO;
import com.example.temosvagas.dtos.CandidaturaResponseDTO;
import com.example.temosvagas.services.CandidaturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;

    /**
     * Lista todas as candidaturas registradas.
     *
     * @return ResponseEntity contendo lista de CandidaturaResponseDTO e status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<CandidaturaResponseDTO>> listarTodas() {
        List<CandidaturaResponseDTO> dtos = candidaturaService.listarTodas();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Busca uma candidatura pelo seu ID.
     *
     * @param id o identificador da candidatura
     * @return ResponseEntity contendo os dados da candidatura e status 200 (OK)
     * @throws org.springframework.web.server.ResponseStatusException com status 404 se não encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<CandidaturaResponseDTO> buscarPorId(@PathVariable Long id) {
        CandidaturaResponseDTO dto = candidaturaService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Cria uma nova candidatura -> Aplica para Vaga
     *
     * @param requestDTO os dados da candidatura (candidatoId e vagaId)
     * @return ResponseEntity com os dados da candidatura criada e status 201 (Created)
     * @throws org.springframework.web.server.ResponseStatusException se o candidato ou vaga não existirem
     */
    @PostMapping
    public ResponseEntity<CandidaturaResponseDTO> criar(@RequestBody @Valid CandidaturaRequestDTO requestDTO) {
        CandidaturaResponseDTO responseDTO = candidaturaService.criarCandidatura(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Deleta uma candidatura pelo ID.
     *
     * @param id identificador da candidatura
     * @return ResponseEntity com status 204 (No Content) se a exclusão for bem-sucedida
     * @throws org.springframework.web.server.ResponseStatusException com status 404 se a candidatura não for encontrada
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        candidaturaService.deletarCandidatura(id);
        return ResponseEntity.noContent().build();
    }
}
