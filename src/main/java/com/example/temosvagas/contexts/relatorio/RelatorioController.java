package com.example.temosvagas.contexts.relatorio;

import com.example.temosvagas.dtos.CandidatoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios/vagas")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    /**
     * Retorna a lista de candidatos inscritos em uma vaga.
     *
     * @param id ID da vaga
     * @return ResponseEntity com lista de candidatos e status 200 (OK)
     */
    @GetMapping("/{id}/candidatos")
    public ResponseEntity<List<CandidatoResponseDTO>> listarCandidatosDaVaga(@PathVariable Long id) {
        List<CandidatoResponseDTO> candidatos = relatorioService.listarCandidatosDaVaga(id);
        return ResponseEntity.ok(candidatos);
    }

}
