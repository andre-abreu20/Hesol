# 🌍 HESOL - Plataforma de Monitoramento Ambiental Inteligente

## 📌 Descrição do Projeto

O projeto **HESOL** (Healthy Environment SOLution) tem como objetivo **prever e controlar o impacto ambiental** em locais monitorados por sensores ambientais. Ele foi desenvolvido como parte da Global Solution FIAP 2025, com integração a banco de dados **Oracle** e aplicação backend em **Spring Boot** com segurança baseada em JWT.

A solução permite que **especialistas ambientais** visualizem dados de sensores como temperatura, CO₂, umidade e qualidade do ar (AQI), com cálculo automático de um **índice de impacto climático**, incluindo sugestões para melhoria.

---

## 🧠 Inteligência da Calculadora Ambiental

A pontuação é feita com base nas faixas:

| Parâmetro    | Faixa Ideal       | Pontuação Máxima |
|--------------|-------------------|------------------|
| Temperatura  | 18–25 °C          | 25               |
| CO₂          | < 800 ppm         | 25               |
| Umidade      | 40–70%            | 25               |
| Poluição AQI | 0–50              | 25               |

- Pontuação intermediária ou ruim reduz a nota final.
- A nota final determina a classificação:
    - **85–100**: Excelente 🟢
    - **60–84**: Boa 🟡
    - **40–59**: Moderada 🟠
    - **20–39**: Ruim 🔴
    - **<20**: Péssimo ⚫

---

## 🏗️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA
- Banco de Dados Oracle
- Swagger OpenAPI (documentação das rotas)
- Caffeine Cache (cache de paginação)
- Maven

---

## 📂 Estrutura de Pacotes

```
fiap.com.br.hesol
├── api              # Controllers, DTOs e Mappers
├── domain           # Models, Repositories, Services
├── security         # JWT e configuração de segurança
├── config           # Beans como CacheManager
```

---

## ▶️ Como Rodar o Projeto

### 1. Requisitos
- Java 17+
- Oracle Database (Local ou remoto)
- IDE com suporte a Spring Boot (ex: IntelliJ ou Eclipse)
- Postman ou Swagger para testar as rotas

### 2. Configuração

Configure o `application.properties` com os dados do Oracle:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
```

### 3. Build e execução

```bash
./mvnw clean package
java -jar target/hesol-0.0.1-SNAPSHOT.jar
```

---

## 🔐 Rotas Seguras (JWT)

Você deve gerar um token após login e usá-lo no header `Authorization: Bearer {token}` nas requisições.

Acesse o Swagger UI para testes:
```
http://localhost:8080/swagger-ui.html
```

---

## 📬 Principais Endpoints

### 🔒 Autenticação

| Método | Rota              | Descrição                         |
|--------|-------------------|-----------------------------------|
| POST   | `/auth/login`     | Gera o token JWT                  |
| POST   | `/auth/registrar` | Cria o usuario e gera o token JWT |


---

### 🔒 Rotas Protegidas:

### 👤 Usuário

| Método | Rota           | Descrição                       |
|--------|----------------|---------------------------------|
| GET    | `/usuarios/me` | Retorna dados do usuário logado |
| UPDATE | `/usuarios/id` | Atualiza o usuario              |
| DELETE | `/usuarios/id` | Deleta o usuario                |

---

### 🌡️ Leituras Ambientais

| Método | Rota           | Descrição                                      |
|--------|----------------|-------------------------------------------------|
| POST   | `/leituras`    | Registra uma nova leitura com base nos sensores|
| GET    | `/leituras`    | Lista leituras paginadas do usuário logado     |

Use os parâmetros `?page=0&size=10` para paginação.

---

## 🚀 Cache

A listagem paginada de leituras utiliza Caffeine Cache com expiração de 5 minutos:

```java
@Bean
public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager("leiturasCache");
    cacheManager.setCaffeine(Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(500));
    return cacheManager;
}
```

---

## 👨‍💻 Integrantes

| Nome                         | RM     |
|------------------------------|--------|
| André Luís Mesquita de Abreu | 558159 |
| Maria Eduarda Brigidio       | 558575 |
| Rafael Bompadre Lima         | 556459 |
