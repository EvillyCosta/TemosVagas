# Atualização de Segurança - Projeto TemosVagas

---

## ✅ O que foi implementado?

- Autenticação HTTP Basic com Spring Security.
- Senha dos usuários (candidatos e empresas) salva com hash (BCrypt) no banco de dados para maior segurança.

---

## 🛠️ Arquivos adicionados ou modificados (pasta `config`)

- **SecurityConfig.java**: configura o `BCryptPasswordEncoder` para codificar senhas.
- **SecurityFilterConfig.java**: define as rotas públicas e protegidas + configuração do `UserDetailsService`.
- **UsuarioDetailsServiceImpl.java**: busca o usuário (empresa ou candidato) pelo e-mail no banco.
- **UsuarioDetails.java**: implementa o `UserDetails` do Spring Security com email e senha.

---

## 🔐 Regras de Segurança Aplicadas

- Rotas públicas (não exigem autenticação):
  - `POST /auth/login`
  - `POST /candidatos`
  - `POST /empresas`

- Todas as outras rotas exigem autenticação HTTP Basic  
  Exemplo: cadastro de vaga, cadastro de filial, etc.

---

## ✅ Como testar no Postman

### 1. Cadastro de empresa (sem autenticação)

- **POST:**  
  `http://localhost:8081/TemosVagas/api/empresas`

- **JSON no corpo:**

```json
{
  "nome": "TI Inovação",
  "razaoSocial": "TI Inovação Ltda ME",
  "cnpj": "13.345.678/0001-99",
  "endereco": "Rua Exemplo, 123, São Paulo - SP",
  "telefone": "(11) 98765-4321",
  "email": "contato@tiinovacao.com",
  "senha": "senhaSegura456"
}
```

**Esperado:**  
Status `201 Created`  
No banco, a senha estará salva em formato hash (BCrypt).

Dica: Antes de cadastrar a Vaga, certifique-se de ter a Filial da empresa já cadastrada e ter em mãos seu ID e o ID da empresa.

POST de Filial: `http://localhost:8081/TemosVagas/api/filiais/empresa/{idempresa}`

```json
{
  "nome": "Filial Parelheiros",
  "localizacao": "São Paulo - SP",
  "endereco": "Rua Batolomeu, 123, São Paulo - SP"
}
```
(Necessita de Basic Auth para dar certo, explicado no próximo passo)

## 2. Cadastro de vaga (sem autenticação)

**URL:**  
`POST http://localhost:8081/TemosVagas/api/vagas`

**Body (JSON):**

```json
{
  "titulo": "Desenvolvedor Java Pleno",
  "tipo": "ESTAGIO",
  "requisitosMinimos": "Conhecimento básico em Java e Spring Boot",
  "dataLimite": "2025-12-31",
  "curso": "Ciência da Computação",
  "semestreDesejado": "8",
  "anoConclusao": null,
  "filialId": 2,
  "empresaId": 103
}
```
**Esperado:**  
Status 401 Unauthorized (rota protegida)

---

## 3. Cadastro de vaga (com autenticação HTTP Basic)

No Postman, na aba **Authorization**:

- **Type:** Basic Auth  
- **Username:** contato@tiinovacao.com  
- **Password:** senhaSegura456  

Faça o mesmo POST do cadastro de vaga acima.


**Esperado:**  
Status 201 Created

---

### 🔐 Observações finais

- Mesmo com a senha salva em hash no banco, o Spring Security compara corretamente a senha enviada via Basic Auth com o hash, graças ao `BCryptPasswordEncoder`.
- A senha não precisa ser enviada no corpo da requisição para autenticação, apenas no header Authorization do Basic Auth.
- Todas as configurações de segurança estão organizadas na pasta `config`.
