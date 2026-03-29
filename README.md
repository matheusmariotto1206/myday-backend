# MyDay

[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/ehojonv/myday)

MyDay é um serviço backend para uma aplicação de acompanhamento de hábitos. Ele fornece autenticação de usuários, gerenciamento de hábitos, acompanhamento diário de progresso e citações motivacionais para ajudar os usuários a construir e manter hábitos positivos.

## Funcionalidades

* **Autenticação de Usuário**: Registro e login seguros utilizando JWT (JSON Web Tokens).
* **Gerenciamento de Hábitos**: Criar, listar e deletar hábitos pessoais.
* **Acompanhamento Diário**: Registrar a conclusão de hábitos no dia atual.
* **Citações Motivacionais**: Obtém uma citação motivacional aleatória de uma API externa.
* **Migrações de Banco de Dados**: Utiliza Flyway para gerenciar o esquema do banco de dados.

## Tecnologias

* **Backend**: Java, Spring Boot
* **Segurança**: Spring Security, JWT
* **Banco de Dados**: Spring Data JPA, H2 (em memória), Flyway
* **Ferramenta de Build**: Gradle
* **Utilitários**: Lombok

## Como Executar

### Pré-requisitos

* JDK 17 ou superior
* Gradle

### Executando a Aplicação

1. **Clone o repositório:**

   ```sh
   git clone https://github.com/ehojonv/myday.git
   cd myday
   ```

2. **Execute a aplicação usando o Gradle Wrapper:**

   * No macOS/Linux:

     ```sh
     ./gradlew bootRun
     ```
   * No Windows:

     ```sh
     gradlew.bat bootRun
     ```

A aplicação será iniciada em `http://localhost:8080`.

O console do banco H2 está habilitado e pode ser acessado em `http://localhost:8080/h2-console`.

* **JDBC URL**: `jdbc:h2:mem:myday`
* **Usuário**: `sa`
* **Senha**: (deixe em branco)

## Endpoints da API

Todos os endpoints, exceto `/auth/**`, exigem um token `Bearer` no header `Authorization`.

### Autenticação

**Registrar um novo usuário**

```http
POST /auth/register
```

**Body da Requisição:**

```json
{
    "email": "user@example.com",
    "password": "suasenha"
}
```

**Login e obtenção de JWT**

```http
POST /auth/login
```

**Body da Requisição:**

```json
{
    "email": "user@example.com",
    "password": "suasenha"
}
```

**Resposta de Sucesso:**

```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNjE2NDY...
```

### Hábitos

**Listar todos os hábitos do usuário autenticado**

```http
GET /habits
```

**Resposta de Sucesso:**

```json
[
    {
        "id": 1,
        "name": "Ler um livro",
        "description": "Ler por 20 minutos todos os dias."
    },
    {
        "id": 2,
        "name": "Exercitar",
        "description": "30 minutos de cardio."
    }
]
```

**Criar um novo hábito**

```http
POST /habits
```

**Body da Requisição:**

```json
{
    "name": "Beber água",
    "description": "Beber 8 copos de água por dia."
}
```

**Deletar um hábito**

```http
DELETE /habits/{id}
```

**Parâmetros:**

* `id` (Long): ID do hábito a ser deletado.

### Registros de Hábitos

**Marcar um hábito como concluído hoje**

```http
POST /records/{habitId}
```

**Parâmetros:**

* `habitId` (Long): ID do hábito a ser marcado como concluído.

### Citações

**Obter uma citação motivacional aleatória**

```http
GET /quotes
```

**Resposta de Sucesso:**

```
"O segredo de começar é começar."
```
