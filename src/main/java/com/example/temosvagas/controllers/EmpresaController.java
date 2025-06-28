package com.example.temosvagas.controllers;

import com.example.temosvagas.dtos.EmpresaRequestDTO;
import com.example.temosvagas.dtos.EmpresaResponseDTO;
import com.example.temosvagas.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    /**
     * Retorna a lista de todas as empresas cadastradas.
     *
     * @return ResponseEntity contendo uma lista de EmpresaResponseDTO e status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> buscarTodasEmpresas() {
        List<EmpresaResponseDTO> dtos = empresaService.buscaTodasEmpresas();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Busca uma empresa pelo seu identificador único (ID).
     *
     * @param id o identificador da empresa a ser buscada
     * @return ResponseEntity com os dados da empresa encontrada e status 200 (OK)
     * @throws `ResponseStatusException` com status 404 (Not Found) se a empresa não for encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> buscarPorId(@PathVariable Long id) {
        EmpresaResponseDTO dto = empresaService.buscaPorId(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Cadastra uma nova empresa com base nos dados fornecidos.
     *
     * @param requestDTO os dados da empresa enviados no corpo da requisição
     * @return ResponseEntity com os dados da empresa criada e status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> criar(@RequestBody @Valid EmpresaRequestDTO requestDTO) {
        EmpresaResponseDTO responseDTO = empresaService.criaEmpresa(requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    /**
     * Atualiza os dados de uma empresa existente com base no ID informado.
     *
     * @param id o identificador único da empresa a ser atualizada (vindo da URL)
     * @param requestDTO os dados da empresa enviados no corpo da requisição
     * @return ResponseEntity com os dados atualizados da empresa e status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody EmpresaRequestDTO requestDTO) {
        EmpresaResponseDTO responseDTO = empresaService.atualizaEmpresa(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Deleta a empresa por ID fornecido
     * @param id o identificador único da empresa a ser deletada
     * @return ResponseEntity com status 204 (No Content) se a exclusão for bem-sucedida
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> deleta(@PathVariable Long id) {
        empresaService.deletaEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}