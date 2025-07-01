package com.example.temosvagas.contexts.carregaArquivo;

import com.example.temosvagas.dtos.CandidatoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/candidatos/arquivoCV")
public class CarregaArquivoController {

    @Autowired
    private CarregaArquivoService carregaArquivoService;

    /**
     * Atualiza os dados de um do curriculo de um candidato existente com base no ID informado.
     *
     * @param id o identificador único do candidato a ser atualizado (vindo da URL)
     * @param arquivo os dados do candidato enviados no corpo da requisição
     * @return ResponseEntity com os dados atualizados do candidato e status 200 (OK)
     */
    @PutMapping(consumes = "multipart/form-data")
    public ResponseEntity<CandidatoResponseDTO> atualizarArquivoCurriculo(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "arquivo") MultipartFile arquivo) {
        CarregaArquivoRequestDTO requestDTO = new CarregaArquivoRequestDTO(arquivo);
        CandidatoResponseDTO responseDTO = carregaArquivoService.atualizaArquivoCurriculo(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}