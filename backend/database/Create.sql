--CRIA O BANCO DE DADOS
CREATE DATABASE ESCOLA;

--SELECIONA O BANCO DE DADOS NO QUAL VAMOS TRABALHAR
USE ESCOLA;

--CRIA AS TABELAS
CREATE TABLE TB_TURMA (TurmaId    INT           PRIMARY KEY   AUTO_INCREMENT
                      ,Descricao  VARCHAR (255) UNIQUE        NOT NULL
                      );                    
                      
CREATE TABLE TB_ALUNO (AlunoId         INT            PRIMARY KEY   AUTO_INCREMENT
                      ,TurmaId         INT            NOT NULL
                      ,Nome            VARCHAR(255)   NOT NULL
                      ,DataNascimento  DATE           NOT NULL
                      ,Endereco        VARCHAR(255)   NULL
                      ,DataCadastro    DATETIME       NOT NULL      DEFAULT CURRENT_TIMESTAMP
                      ,Ativo           BIT            NOT NULL      DEFAULT 1
                      ,CONSTRAINT FK_TURMA_ALUNO FOREIGN KEY (TurmaId) REFERENCES TB_TURMA(TurmaId)
                      );

CREATE TABLE TB_USUARIO (UsuarioId      INT             PRIMARY KEY     AUTO_INCREMENT
                        ,UsuarioNome    VARCHAR(255)    NOT NULL        UNIQUE
                        ,UsuarioSenha   VARCHAR(255)    NOT NULL)                      

CREATE INDEX IDX_NOME_ALUNO ON TB_ALUNO (Nome);                    