package com.example.temosvagas.contexts.carregaArquivo;

import com.amazonaws.services.s3.AmazonS3;
import com.example.temosvagas.dtos.CandidatoResponseDTO;
import com.example.temosvagas.entities.Candidato;
import com.example.temosvagas.repositories.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

@Service
public class CarregaArquivoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.bucket.name}")
    private String awsBucketName;


    public CandidatoResponseDTO atualizaArquivoCurriculo(Long id, CarregaArquivoRequestDTO dto) {
        String arquivoUrl = null;

        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não encontrado"));

        if (dto.arquivo() != null) {
            arquivoUrl = this.carregaArquivo(dto.arquivo());
            candidato.setArquivo(arquivoUrl);
        }

        Candidato candidatoSalvo = this.candidatoRepository.save(candidato);

        return CandidatoResponseDTO.toDTO(candidatoSalvo);
    }

    private String carregaArquivo(MultipartFile arquivo) {
        Random random = new Random();
        String nomeArquivo = random + arquivo.getOriginalFilename();
        try {
            File arq = this.converteMultipartToFile(arquivo);
            s3Client.putObject(awsBucketName, nomeArquivo, arq);
            arq.delete();
            return s3Client.getUrl(awsBucketName, nomeArquivo).toString();
        } catch (Exception ex) {
            System.out.println("Erro ao carregar arquivo: " + ex.getMessage());
            return null;
        }
    }

    private File converteMultipartToFile(MultipartFile arquivo) throws IOException {
        File convFile = new File(Objects.requireNonNull(arquivo.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(arquivo.getBytes());
        fos.close();
        return convFile;
    }
}
