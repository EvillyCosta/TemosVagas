# 📘 Empresa API

## Endpoints

### 🔹 GET /empresas
Retorna a lista de todas as empresas cadastradas.

### 🔹 GET /empresas/{id}
Retorna os dados de uma empresa específica pelo ID.

### 🔹 POST /empresas
Cadastra uma nova empresa.

**Body (JSON):**
```json
{
  "nome": "Tech Corp",
  "razaoSocial": "Tech Corp Ltda.",
  "cnpj": "12345678000199",
  "email": "contato@techcorp.com",
  "senha": "senha123"
}
```

### 🔹 PUT /empresas/{id}
Atualiza os dados de uma empresa existente.

### 🔹 DELETE /empresas/{id}
Remove uma empresa com base no ID.

---