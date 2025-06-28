
# 📘 Documentação da API – EmpresaController

**Base URL:** `/empresas`

Controlador responsável por gerenciar operações relacionadas a **empresas**, incluindo criação, leitura, atualização e exclusão.

---

## 🔹 GET /empresas

### ✅ Descrição
Retorna a lista de todas as empresas cadastradas.

### 🔄 Método HTTP
`GET`

### 📤 Resposta (200 OK)

```json
[
  {
    "id": 1,
    "nome": "Tech Ltda",
    "razaoSocial": "Tech Soluções Digitais LTDA",
    "cnpj": "12.345.678/0001-90",
    "email": "contato@tech.com"
  }
]
```

---

## 🔹 GET /empresas/{id}

### ✅ Descrição
Busca uma empresa pelo seu ID.

### 🔄 Método HTTP
`GET`

### 📥 Parâmetro de URL
- `id` (Long): ID da empresa a ser buscada.

### 📤 Resposta (200 OK)

```json
{
  "id": 1,
  "nome": "Tech Ltda",
  "razaoSocial": "Tech Soluções Digitais LTDA",
  "cnpj": "12.345.678/0001-90",
  "email": "contato@tech.com"
}
```

### ⚠️ Erros possíveis
- `404 Not Found`: Se a empresa não for encontrada.

---

## 🔹 POST /empresas

### ✅ Descrição
Cadastra uma nova empresa com os dados fornecidos.

### 🔄 Método HTTP
`POST`

### 📥 Corpo da Requisição (JSON)

```json
{
  "nome": "Tech Ltda",
  "razaoSocial": "Tech Soluções Digitais LTDA",
  "cnpj": "12.345.678/0001-90",
  "email": "contato@tech.com",
  "senha": "senhaSegura123"
}
```

### 📤 Resposta (201 Created)

```json
{
  "id": 1,
  "nome": "Tech Ltda",
  "razaoSocial": "Tech Soluções Digitais LTDA",
  "cnpj": "12.345.678/0001-90",
  "email": "contato@tech.com"
}
```

---

## 🔹 PUT /empresas/{id}

### ✅ Descrição
Atualiza os dados de uma empresa existente.

### 🔄 Método HTTP
`PUT`

### 📥 Parâmetro de URL
- `id` (Long): ID da empresa a ser atualizada.

### 📥 Corpo da Requisição (JSON)

```json
{
  "nome": "Tech Ltda Atualizada",
  "razaoSocial": "Tech Soluções e Sistemas LTDA",
  "cnpj": "12.345.678/0001-90",
  "email": "novo@tech.com",
  "senha": "novaSenha123"
}
```

### 📤 Resposta (200 OK)

```json
{
  "id": 1,
  "nome": "Tech Ltda Atualizada",
  "razaoSocial": "Tech Soluções e Sistemas LTDA",
  "cnpj": "12.345.678/0001-90",
  "email": "novo@tech.com"
}
```

---

## 🔹 DELETE /empresas/{id}

### ✅ Descrição
Remove uma empresa existente do sistema.

### 🔄 Método HTTP
`DELETE`

### 📥 Parâmetro de URL
- `id` (Long): ID da empresa a ser excluída.

### 📤 Resposta (204 No Content)
Nenhum conteúdo é retornado, apenas o status HTTP indicando sucesso.

### ⚠️ Erros possíveis
- `404 Not Found`: Se a empresa não for encontrada.
