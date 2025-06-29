# 📘 Filial API

## Endpoints

### 🔹 GET /filiais/{id}
Busca uma filial específica pelo ID.

### 🔹 GET /empresas/{empresaId}/filiais
Lista todas as filiais vinculadas a uma empresa.

### 🔹 POST /filiais/empresa/{idEmpresa}
Cadastra uma nova filial para a empresa especificada.

**Body (JSON):**
```json
{
  "nome": "Unidade Campinas",
  "localizacao": "Campinas - SP"
}
```

### 🔹 PUT /filiais/{id}
Atualiza os dados de uma filial existente.

### 🔹 DELETE /filiais/{id}
Remove uma filial com base no ID.

---