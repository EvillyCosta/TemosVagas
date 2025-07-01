package com.example.temosvagas.contexts.carregaArquivo;

import org.springframework.web.multipart.MultipartFile;

public record CarregaArquivoRequestDTO(MultipartFile arquivo) {
}
