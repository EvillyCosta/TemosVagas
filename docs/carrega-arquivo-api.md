# 📘 Carrega Arquivo API

## Endpoints

### 🔹 POST /carrega-arquivo
Realiza o upload de um arquivo (como currículo) para o sistema.

**Requer autenticação**: Sim (usuário candidato autenticado)

**Headers:**
```
Content-Type: multipart/form-data
Authorization: Bearer <token>
```

**Form Data:**
- `file`: O arquivo a ser enviado (formatos aceitos: PDF, DOCX, etc.)

**Exemplo com cURL:**
```bash
curl -X POST http://localhost:8080/carrega-arquivo \
  -H "Authorization: Bearer <token>" \
  -F "file=@/caminho/do/arquivo/curriculo.pdf"
```

**Resposta de Sucesso:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "telefone": "(11) 91234-5678",
  "cpf": "123.456.789-00",
  "cursandoGraduacao": true,
  "anoConclusao": null,
  "habilidades": "Java, Spring Boot, SQL",
  "curso": "Sistemas de Informação",
  "semestreAtual": "5º",
  "arquivo": "https://temos-vagas.s3.amazonaws.com/nome-do-arquivo.pdf"
}

```

**Respostas de Erro:**
- `400 Bad Request` – Arquivo ausente ou inválido.
- `500 Internal Server Error` – Erro ao tentar salvar o arquivo no servidor.

---
