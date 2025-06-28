package com.example.temosvagas.services;

import com.example.temosvagas.dtos.FilialRequestDTO;
import com.example.temosvagas.dtos.FilialResponseDTO;
import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.entities.Filial;
import com.example.temosvagas.mapper.MapperGeral;
import com.example.temosvagas.repositories.EmpresaRepository;
import com.example.temosvagas.repositories.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FilialService {

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<FilialResponseDTO> buscarFiliaisPorEmpresa(Long idEmpresa) {
        List<Filial> filiais = filialRepository.findAllByEmpresaId(idEmpresa);
        List<FilialResponseDTO> dtos = MapperGeral.toFilialResponseList(filiais);
        return dtos;
    }

    public FilialResponseDTO buscarPorId(Long id) {
        Filial filial = filialRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filial não encontrada"));
        return FilialResponseDTO.toDTO(filial);
    }


    public FilialResponseDTO criarFilial(Long idEmpresa, FilialRequestDTO dto) {
        Empresa empresa = empresaRepository.findById(idEmpresa).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));

        Filial filial = dto.toEntity(empresa);
        Filial novaFilial = filialRepository.save(filial);
        return FilialResponseDTO.toDTO(novaFilial);
    }

    public FilialResponseDTO atualizarFilial(Long id, FilialRequestDTO dto) {
        Filial filial = filialRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filial não encontrada"));

        filial.setNome(dto.nome());
        filial.setEndereco(dto.endereco());

        Filial novaFilial = filialRepository.save(filial);
        return FilialResponseDTO.toDTO(novaFilial);
    }

    public void deletarFilial(Long id) {
        Filial filial = filialRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filial não encontrada"));

        filialRepository.delete(filial);
    }
}