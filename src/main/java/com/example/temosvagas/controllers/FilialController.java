package com.example.temosvagas.controllers;

import com.example.temosvagas.dtos.FilialRequestDTO;
import com.example.temosvagas.dtos.FilialResponseDTO;
import com.example.temosvagas.services.FilialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filiais")
public class FilialController {

    @Autowired
    private FilialService filialService;

    /**
     * Retorna todas as filiais vinculadas a uma empresa específica.
     *
     * @param idEmpresa o identificador único da empresa
     * @return ResponseEntity contendo a lista de FilialResponseDTO e status 200 (OK)
     *
     * Exemplo de requisição:
     * GET /empresas/empresa/1
     *
     * Caso a empresa não possua filiais, retorna uma lista vazia.
     * Caso o ID da empresa não exista, o serviço interno deverá lançar exceção apropriada.
     */
    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<List<FilialResponseDTO>> buscarFiliaisPorEmpresa(@PathVariable Long idEmpresa) {
        List<FilialResponseDTO> dtos = filialService.buscarFiliaisPorEmpresa(idEmpresa);
        return ResponseEntity.ok(dtos);
    }

    /**
     * Busca uma filial pelo seu identificador único (ID).
     *
     * @param idFilial o identificador da filial a ser buscada
     * @return ResponseEntity contendo os dados da filial encontrada e status 200 (OK)
     * @throws org.springframework.web.server.ResponseStatusException com status 404 (Not Found) se a filial não for encontrada
     *
     * Exemplo de requisição:
     * GET /filiais/5
     */
    @GetMapping("/{idFilial}")
    public ResponseEntity<FilialResponseDTO> buscarPorId(@PathVariable Long idFilial) {
        FilialResponseDTO dto = filialService.buscarPorId(idFilial);
        return ResponseEntity.ok(dto);
    }

    /**
     * Cria uma nova filial associada a uma empresa específica.
     *
     * @param idEmpresa o identificador único da empresa à qual a filial será vinculada
     * @param requestDTO os dados da nova filial enviados no corpo da requisição
     * @return ResponseEntity contendo os dados da filial criada e status 201 (Created)
     *
     * @throws org.springframework.web.server.ResponseStatusException com status 404 (Not Found) caso a empresa não exista
     *
     * Exemplo de requisição:
     * POST /filiais/empresa/3
     * Body:
     * {
     *   "nome": "Unidade Campinas",
     *   "localizacao": "Campinas - SP"
     * }
     */
    @PostMapping("/empresa/{idEmpresa}")
    public ResponseEntity<FilialResponseDTO> criar(
            @PathVariable Long idEmpresa,
            @RequestBody @Valid FilialRequestDTO requestDTO) {
        FilialResponseDTO responseDTO = filialService.criarFilial(idEmpresa, requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    /**
     * Atualiza os dados de uma filial existente com base no seu identificador.
     *
     * @param idFilial o identificador único da filial a ser atualizada
     * @param requestDTO os novos dados da filial enviados no corpo da requisição
     * @return ResponseEntity contendo os dados atualizados da filial e status 200 (OK)
     *
     * @throws org.springframework.web.server.ResponseStatusException com status 404 (Not Found) caso a filial não exista
     *
     * Exemplo de requisição:
     * PUT /filiais/12
     * Body:
     * {
     *   "nome": "Filial Rio Claro",
     *   "endereco": "Rua das Rosas, 100 - Rio Claro"
     * }
     */
    @PutMapping("/{idFilial}")
    public ResponseEntity<FilialResponseDTO> atualizar(
            @PathVariable Long idFilial,
            @RequestBody @Valid FilialRequestDTO requestDTO) {
        FilialResponseDTO responseDTO = filialService.atualizarFilial(idFilial, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Remove uma filial existente com base no seu identificador.
     *
     * @param id o identificador único da filial a ser deletada
     * @return ResponseEntity com status 204 (No Content) se a exclusão for bem-sucedida
     *
     * @throws org.springframework.web.server.ResponseStatusException com status 404 (Not Found) caso a filial não exista
     *
     * Exemplo de requisição:
     * DELETE /filiais/12
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        filialService.deletarFilial(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
