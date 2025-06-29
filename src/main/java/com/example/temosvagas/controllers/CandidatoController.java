package com.example.temosvagas.controllers;

import com.example.temosvagas.dtos.CandidatoRequestDTO;
import com.example.temosvagas.dtos.CandidatoResponseDTO;
import com.example.temosvagas.services.CandidatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    /**
     * Retorna a lista de todas os candidatos cadastrados.
     *
     * @return ResponseEntity contendo uma lista de CandidatoResponseDTO e status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<CandidatoResponseDTO>> buscarTodosCandidatos() {
        List<CandidatoResponseDTO> dtos = candidatoService.buscaTodosCandidatos();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Busca um candidato pelo seu identificador único (ID).
     *
     * @param id o identificador do candidato a ser buscado
     * @return ResponseEntity com os dados do candidato encontrado e status 200 (OK)
     * @throws org.springframework.web.server.ResponseStatusException com status 404 (Not Found) se o candidato não for encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<CandidatoResponseDTO> buscarPorId(@PathVariable Long id) {
        CandidatoResponseDTO dto = candidatoService.buscaPorId(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Cadastra um novo candidato com base nos dados fornecidos.
     *
     * @param requestDTO os dados do candidato enviados no corpo da requisição
     * @return ResponseEntity com os dados do candidato criado e status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<CandidatoResponseDTO> criar(@RequestBody @Valid CandidatoRequestDTO requestDTO) {
        CandidatoResponseDTO responseDTO = candidatoService.criaCandidato(requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    /**
     * Atualiza os dados de um candidato existente com base no ID informado.
     *
     * @param id o identificador único do candidato a ser atualizado (vindo da URL)
     * @param requestDTO os dados do candidato enviados no corpo da requisição
     * @return ResponseEntity com os dados atualizados do candidato e status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<CandidatoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid CandidatoRequestDTO requestDTO) {
        CandidatoResponseDTO responseDTO = candidatoService.atualizaCandidato(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Atualiza os dados de um do curriculo de um candidato existente com base no ID informado.
     *
     * @param id o identificador único do candidato a ser atualizado (vindo da URL)
     * @param requestDTO os dados do candidato enviados no corpo da requisição
     * @return ResponseEntity com os dados atualizados do candidato e status 200 (OK)
     */
    @PutMapping("/{id}/curriculo")
    public ResponseEntity<CandidatoResponseDTO> atualizarCurriculo(
            @PathVariable Long id,
            @RequestBody @Valid CandidatoRequestDTO requestDTO) {
        CandidatoResponseDTO responseDTO = candidatoService.atualizaCurriculo(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Deleta o candidato por ID fornecido
     * @param id o identificador único do candidato a ser deletado
     * @return ResponseEntity com status 204 (No Content) se a exclusão for bem-sucedida
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable Long id) {
        candidatoService.deletaCandidato(id);
        return ResponseEntity.noContent().build();
    }
}