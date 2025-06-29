# 📘 Candidato API

## Endpoints

### 🔹 GET /candidatos
Retorna a lista de todos os candidatos cadastrados.

### 🔹 GET /candidatos/{id}
Retorna os dados de um candidato específico pelo ID.

### 🔹 POST /candidatos
Cadastra um novo candidato.

**Body (JSON):**
```json
{
  "nome": "Ana Silva",
  "email": "ana@email.com",
  "senha": "123456",
  "telefone": "11988887777",
  "cpf": "12345678900",
  "cursandoGraduacao": true,
  "anoConclusao": null,
  "habilidades": "Java, Spring Boot",
  "curso": "Engenharia da Computação",
  "semestreAtual": "6º",
  "arquivo": "curriculo_ana_silva.pdf"
}
```

### 🔹 PUT /candidatos/{id}
Atualiza os dados de um candidato existente.

### 🔹 DELETE /candidatos/{id}
Remove um candidato com base no ID.

---