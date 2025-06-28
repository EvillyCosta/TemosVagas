package com.example.temosvagas.services;

import com.example.temosvagas.dtos.EmpresaRequestDTO;
import com.example.temosvagas.entities.Empresa;
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

    public List<Empresa> buscaTodasEmpresas() {
        return this.empresaRepository.findAll();
    }

    public Empresa buscaPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
    }

    public Empresa criaEmpresa(EmpresaRequestDTO dto) {
        Empresa novaEmpresa = dto.toEntity();
        return this.empresaRepository.save(novaEmpresa);
    }

    public Empresa atualizaEmpresa(Long id, EmpresaRequestDTO dto) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));

        empresa.setNome(dto.nome());
        empresa.setRazaoSocial(dto.razaoSocial());
        empresa.setCnpj(dto.cnpj());
        empresa.setEmail(dto.email());
        empresa.setSenha(dto.senha());

        return this.empresaRepository.save(empresa);
    }

    public void deletaEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
        empresaRepository.delete(empresa);
    }
}