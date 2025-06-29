package com.example.temosvagas.services;

import com.example.temosvagas.dtos.CandidatoRequestDTO;
import com.example.temosvagas.dtos.CandidatoResponseDTO;
import com.example.temosvagas.entities.Candidato;
import com.example.temosvagas.mapper.MapperGeral;
import com.example.temosvagas.repositories.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public List<CandidatoResponseDTO> buscaTodosCandidatos() {
        List<Candidato> candidatos = candidatoRepository.findAll();
        List<CandidatoResponseDTO> dtos = MapperGeral.toCandidatoResponseList(candidatos);
        return dtos;
    }

    public CandidatoResponseDTO buscaPorId(Long id) {
        Candidato candidato = candidatoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não encontrado"));

        return CandidatoResponseDTO.toDTO(candidato);
    }

    public CandidatoResponseDTO criaCandidato(CandidatoRequestDTO dto) {
        Candidato novoCandidato = dto.toEntity();
        if (candidatoRepository.existsByEmail((dto.email()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado");
        } else if (candidatoRepository.existsByCpf((dto.cpf()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
        }
        Candidato salvo = candidatoRepository.save(novoCandidato);
        return CandidatoResponseDTO.toDTO(salvo);
    }

    public CandidatoResponseDTO atualizaCandidato(Long id, CandidatoRequestDTO dto) {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não encontrado"));

        candidato.setNome(dto.nome());
        candidato.setEmail(dto.email());
        candidato.setTelefone(dto.telefone());
        candidato.setCpf(dto.cpf());

        Candidato candidatoSalvo = this.candidatoRepository.save(candidato);

        return CandidatoResponseDTO.toDTO(candidatoSalvo);
    }

    public void deletaCandidato(Long id) {
        Candidato candidato = candidatoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não encontrado"));
        candidatoRepository.delete(candidato);
    }
}
