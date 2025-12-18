# Sistema de Gestão Escolar

## 🚀 Funcionalidades

* **Autenticação Segura (JWT)**: Sistema de login com proteção de rotas e persistência de sessão.  
* **Gestão de Alunos**: Listagem, cadastro, edição e exclusão de alunos.  
* **Gestão de Turmas**: Controle completo de turmas escolares.  
* **Dashboard Interativo**: Interface amigável, responsiva e estilizada.  
* **Feedback Visual**: Mensagens de erro e sucesso claras para orientar o usuário.  
* **Busca e Filtros**: Facilidade para encontrar registros.  

---

## 🛠️ Tecnologias Utilizadas

### Backend (API)
* **Java 17+**  
* **Spring Boot 3** (Web, Data JPA, Security, Validation)  
* **Hibernate** (ORM para banco de dados)  
* **MySQL** (Banco de dados para persistência)  
* **Maven** (Gerenciamento de dependências)  

### Frontend (Client)
* **React** (Biblioteca principal)  
* **TypeScript** (Tipagem estática para maior segurança)  
* **Axios** (Comunicação HTTP com a API)  
* **React Router Dom** (Gerenciamento de rotas e navegação)  
* **CSS Modules** (Estilização organizada e limpa)  

---

## ⚙️ Como executar o projeto

Siga os passos abaixo para rodar a aplicação na sua máquina local.

### Pré-requisitos
* Java JDK 17 ou superior instalado.  
* Node.js e npm instalados.  
* Git instalado.  
* MySQL instalado e rodando.

### 1. Clonar o repositório
git clone https://github.com/Douglas-Felipe-Mariano/TESTE_GSA.git

### 2. Configurar a conexão com o banco de dados
No backend, abra o arquivo `src/main/resources/application.properties` e ajuste os parâmetros de conexão com o MySQL:

URL do banco de dados

> ⚠️ **Observação**: Altere o `localhost` de acordo com sua configuração local do MySQL

spring.datasource.url=jdbc:mysql://localhost:3333/escola?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true

A estrutura da URL do banco deve ser 
jdbc:mysql://<host>:<porta>/<nome_do_banco>?<parametros>

O host provavelmente sera localhost e a porta é literalmente a porta que está configurada sua conexão com o banco dedados, o nome do banco não pode ser alterado e os parametros vão de acordo com a configuração da sua conexão.

> ⚠️ **Observação**: Altere `username`, `password` e `url` de acordo com sua configuração local do MySQL.

spring.datasource.username=root
spring.datasource.password=root



> Driver do banco de dados e configuração do JPA | Já está no arquivo, não alterar!

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect


### 3. Rodar o Backend
cd backend

./mvnw spring-boot:run

ou

mvnw spring-boot:run

O servidor iniciará na porta 8080: [http://localhost:8080]

### 4. Rodar o Frontend
cd frontend

npm install

npm start

O frontend será aberto automaticamente no navegador: [http://localhost:3000]

---

## 🔒 Acesso ao Sistema
Para testar as funcionalidades, utilize o usuário administrador padrão que é criado automaticamente no banco de dados, pela DatabaseInitializer dentro do package config no app Spring:

* **Usuário**: admin  
* **Senha**: admin123  

> Caso altere as credenciais padrão, verifique o arquivo `DataInitializer.java` no backend.


# Teste SQL

# O teste SQL está disponivel na pasta database, dentro do bakcend, nomeado como Exercicios.sql

