# 📘 Candidatura API

## Endpoints

### 🔹 GET /candidaturas
Lista todas as candidaturas.

### 🔹 GET /candidaturas/{id}
Busca uma candidatura específica pelo ID.

### 🔹 POST /candidaturas
Registra uma nova candidatura.

**Body (JSON):**
```json
{
  "candidatoId": 1,
  "vagaId": 2
}
```

Validações:
- A data da vaga deve ser futura.
- Um candidato não pode se candidatar duas vezes à mesma vaga.
- Aplicações para estágios exigem `cursandoGraduacao = true`.
- Aplicações para trainees exigem `anoConclusao`.

### 🔹 DELETE /candidaturas/{id}
Remove uma candidatura pelo ID.

---