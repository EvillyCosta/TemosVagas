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
     * Atualiza o currículo de um candidato existente com base no ID do candidato.
     * O novo arquivo enviado substitui o currículo anterior, sendo armazenado em um bucket S3.
     *
     * @param id o identificador único do candidato cujo currículo será atualizado (enviado como parâmetro de requisição)
     * @param arquivo o novo arquivo de currículo enviado como multipart/form-data
     * @return ResponseEntity contendo os dados atualizados do candidato e status HTTP 200 (OK)
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