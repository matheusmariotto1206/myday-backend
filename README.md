# 🌅 MyDay - Habit Tracker

Aplicação fullstack para acompanhamento de hábitos diários, com autenticação JWT, CRUD completo e citações motivacionais.

## 🛠 Tecnologias

### Backend
- Java 17 + Spring Boot 4
- Spring Security + JWT
- Spring Data JPA + H2 (banco em memória)
- Flyway (migrações)
- Lombok
- Gradle

### Frontend
- Angular 18 (standalone components)
- TypeScript
- HttpClient com interceptação JWT

## 📋 Funcionalidades

- ✅ Registro e login de usuários (JWT)
- ✅ CRUD completo de hábitos (Criar, Listar, Editar, Deletar)
- ✅ Check-in diário (marcar/desmarcar hábito como feito)
- ✅ Citações motivacionais aleatórias
- ✅ Migrações automáticas com Flyway

## 🚀 Como executar

### Pré-requisitos
- Java 17+
- Node.js 18+
- Angular CLI (`npm install -g @angular/cli`)

### Backend

```bash
cd myday-backend
.\gradlew bootRun --args="--server.port=8081 --server.error.include-message=always"

👤 Integrantes
Matheus Barbosa Mariotto	rm-560276
João Vinícius	rm-559369


