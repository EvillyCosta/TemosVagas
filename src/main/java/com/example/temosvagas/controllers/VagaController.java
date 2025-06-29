package com.example.temosvagas.controllers;

import com.example.temosvagas.dtos.VagaRequestDTO;
import com.example.temosvagas.dtos.VagaResponseDTO;
import com.example.temosvagas.services.VagaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vagas")
@RequiredArgsConstructor
public class VagaController {

    @Autowired
    private VagaService vagaService;

    /**
     * Retorna a lista de todas as vagas cadastradas.
     *
     * @return ResponseEntity contendo uma lista de VagaResponseDTO e status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<VagaResponseDTO>> listarTodas() {
        List<VagaResponseDTO> dtos = vagaService.buscarTodasVagas();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Lista todas as vagas em aberto (com data limite futura).
     *
     * @return ResponseEntity contendo lista de VagaResponseDTO e status 200 (OK)
     */
    @GetMapping("/abertas")
    public ResponseEntity<List<VagaResponseDTO>> listarVagasEmAberto() {
        List<VagaResponseDTO> dtos = vagaService.buscarVagasEmAberto();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Busca uma vaga pelo seu identificador único (ID).
     *
     * @param id o identificador da vaga a ser buscada
     * @return ResponseEntity com os dados da vaga encontrada e status 200 (OK)
     * @throws org.springframework.web.server.ResponseStatusException com status 404 (Not Found) se a vaga não for encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<VagaResponseDTO> buscarPorId(@PathVariable Long id) {
        VagaResponseDTO dto = vagaService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Cadastra uma nova vaga com base nos dados fornecidos.
     *
     * @param requestDTO os dados da vaga enviados no corpo da requisição
     * @return ResponseEntity com os dados da vaga criada e status 201 (Created)
     * @throws org.springframework.web.server.ResponseStatusException com status 404 (Not Found) se a filial associada não for encontrada
     */
    @PostMapping
    public ResponseEntity<VagaResponseDTO> criar(@RequestBody @Valid VagaRequestDTO requestDTO) {
        VagaResponseDTO responseDTO = vagaService.criarVaga(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Atualiza os dados de uma vaga existente com base no ID informado.
     *
     * @param id         o identificador único da vaga a ser atualizada
     * @param requestDTO os dados atualizados enviados no corpo da requisição
     * @return ResponseEntity com os dados atualizados da vaga e status 200 (OK)
     * @throws org.springframework.web.server.ResponseStatusException com status 404 (Not Found) se a vaga ou filial não forem encontradas
     */
    @PutMapping("/{id}")
    public ResponseEntity<VagaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid VagaRequestDTO requestDTO) {
        VagaResponseDTO responseDTO = vagaService.atualizarVaga(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Prorroga a data limite de uma vaga, desde que a vaga ainda esteja em aberto.
     *
     * @param id o ID da vaga a ser prorrogada
     * @param novaDataLimite nova data limite desejada
     * @return mensagem de sucesso ou erro
     */
    @PutMapping("/{id}/prorrogar")
    public ResponseEntity<String> prorrogarDataLimite(
            @PathVariable Long id,
            @RequestParam LocalDate novaDataLimite) {
        vagaService.prorrogarDataLimite(id, novaDataLimite);
        return ResponseEntity.ok("Data limite prorrogada com sucesso.");
    }

    /**
     * Remove uma vaga com base no seu identificador.
     *
     * @param id o identificador da vaga a ser deletada
     * @return ResponseEntity com status 204 (No Content) se a exclusão for bem-sucedida
     * @throws org.springframework.web.server.ResponseStatusException com status 404 (Not Found) se a vaga não for encontrada
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }
}