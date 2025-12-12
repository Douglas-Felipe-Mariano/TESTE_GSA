------------------------------------------------------------------------
-- SELECIONA TODOS OS ALUNOS QUE ESTÃO NA TURMA 'A'
------------------------------------------------------------------------
SELECT A.* FROM TB_ALUNO A
INNER JOIN TB_TURMA T
ON A.TurmaId = T.TurmaId
WHERE T.Descricao = 'A';

------------------------------------------------------------------------
-- SELECIONA TODOS OS ALUNOS QUE TENHAM O NOME 'ALVES'
------------------------------------------------------------------------
SELECT * FROM TB_ALUNO 
WHERE Nome LIKE '%Alves%';

------------------------------------------------------------------------
-- SELECIONA TODOS OS ALUNOS QUE TENHAM NASCIDO ANTES DE 2013
------------------------------------------------------------------------
SELECT * FROM TB_ALUNO
WHERE YEAR(DataNascimento) < 2013;

-- OU 

SELECT * FROM TB_ALUNO
WHERE DataNascimento < '2013-01-01';   

-- YEAR(DataNascimento) extrai o ano de uma data e não permite indexação
-- Já a comparação direta com a data permite o uso de índices, se existirem

------------------------------------------------------------------------
-- ATUALIZA A DATA DE NASCIMENTO DE TODOS OS ALUNOS PARA A DATA ATUAL
------------------------------------------------------------------------
UPDATE TB_ALUNO
SET DataNascimento = CURDATE();

-- OU 

UPDATE TB_ALUNO
SET DataNascimento = NOW();

-- NOW() retorna data e hora atual 
-- CURDATE() retorna apenas a data atual (mais apropriado para a situaçâo com o campo DATE)

------------------------------------------------------------------------
-- INSERE TRÊS NOVAS TURMAS DE FORMA NOMINAL
------------------------------------------------------------------------
INSERT INTO TB_TURMA (Descricao)
VALUES ('1° A')
      ,('1° B')
      ,('1° C');
 
-- OU

-- INSERE DUAS NOVAS TURMAS DE FORMA POSICIONAL
INSERT INTO TB_TURMA 
VALUES (NULL,'2° A')
      ,(NULL,'2° B')
      ,(NULL,'2° C');

------------------------------------------------------------------------
-- DELETA TODOS OS ALUNOS CUJOS IDs ESTEJAM ENTRE 13 E 22
------------------------------------------------------------------------
DELETE FROM TB_ALUNO
WHERE AlunoId BETWEEN 13 AND 22;    