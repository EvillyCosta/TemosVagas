package com.example.temosvagas.services;

import com.example.temosvagas.dtos.EmpresaRequestDTO;
import com.example.temosvagas.dtos.EmpresaResponseDTO;
import com.example.temosvagas.entities.Empresa;
import com.example.temosvagas.mapper.MapperGeral;
import com.example.temosvagas.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<EmpresaResponseDTO> buscaTodasEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        List<EmpresaResponseDTO> dtos = MapperGeral.toEmpresaResponseList(empresas);
        return dtos;
    }

    public EmpresaResponseDTO buscaPorId(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));

        return EmpresaResponseDTO.toDTO(empresa);
    }

    public EmpresaResponseDTO criaEmpresa(EmpresaRequestDTO dto) {
        Empresa novaEmpresa = dto.toEntity();
        Empresa salva = empresaRepository.save(novaEmpresa);
        return EmpresaResponseDTO.toDTO(salva);
    }

    public EmpresaResponseDTO atualizaEmpresa(Long id, EmpresaRequestDTO dto) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));

        empresa.setNome(dto.nome());
        empresa.setRazaoSocial(dto.razaoSocial());
        empresa.setCnpj(dto.cnpj());
        empresa.setEmail(dto.email());
        empresa.setSenha(dto.senha());

        Empresa empresaSalva = this.empresaRepository.save(empresa);

        return EmpresaResponseDTO.toDTO(empresaSalva);
    }

    public void deletaEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
        empresaRepository.delete(empresa);
    }
}