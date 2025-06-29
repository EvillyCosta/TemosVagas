# 📘 Vaga API

## Endpoints

### 🔹 GET /vagas
Retorna todas as vagas cadastradas.

### 🔹 GET /vagas/{id}
Busca uma vaga específica pelo ID.

### 🔹 GET /vagas/abertas
Retorna todas as vagas com data limite no futuro.

### 🔹 POST /vagas
Cadastra uma nova vaga.

**Body (JSON):**
```json
{
  "titulo": "Estágio em Java",
  "tipo": "ESTAGIO",
  "dataLimite": "2025-08-01",
  "curso": "Sistemas de Informação",
  "semestreDesejado": "6",
  "anoConclusao": null,
  "requisitosMinimos": null,
  "filialId": 3
}
```

### 🔹 PUT /vagas/{id}
Atualiza os dados de uma vaga.

### 🔹 PUT /vagas/{id}/prorrogar
Prorroga a data limite da vaga (caso ainda esteja em aberto).

**Parâmetro:** `?novaDataLimite=2025-08-20`

### 🔹 DELETE /vagas/{id}
Remove uma vaga com base no ID.

---