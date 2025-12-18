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
git clone https://github.com/SEU-USUARIO/sistema-escolar.git

cd sistema-escolar

### 2. Configurar a conexão com o banco de dados
No backend, abra o arquivo `src/main/resources/application.properties` e ajuste os parâmetros de conexão com o MySQL:

URL do banco de dados
spring.datasource.url=jdbc:mysql://localhost:3333/escola?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true

Usuário e senha
spring.datasource.username=root
spring.datasource.password=root

Driver e JPA
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

perl
Copiar código

> ⚠️ **Observação**: Altere `username`, `password` e `url` de acordo com sua configuração local do MySQL.

### 3. Rodar o Backend
cd backend
./mvnw spring-boot:run

O servidor iniciará na porta 8080: [http://localhost:8080]

### 4. Rodar o Frontend
cd frontend
npm install
npm start

O frontend será aberto automaticamente no navegador: [http://localhost:3000]

---

## 🔒 Acesso ao Sistema
Para testar as funcionalidades, utilize o usuário administrador padrão:

* **Usuário**: admin  
* **Senha**: admin123  

> Caso altere as credenciais padrão, verifique o arquivo `DataInitializer.java` no backend.