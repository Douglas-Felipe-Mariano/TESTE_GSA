package com.escola.Turma.dto;

import java.time.LocalDate;

import com.escola.Turma.model.Aluno;

//Utilizado record para tornar implicita a presença de getters e setters
//Dto de resposta para requisições relacionada a aluno que tenham retorno
public record AlunoResponseDTO(
    Integer id,
    String nome,
    LocalDate dataNascimento,
    String endereco,
    boolean ativo,
    String nomeTurma,
    Integer turmaId
) 

//Converte da entidade para dto, util para metodos GET, passando apenas dados essenciais para o frontend.
{
    public static AlunoResponseDTO fromEntity(Aluno aluno) {
        return new AlunoResponseDTO(
            aluno.getId(),
            aluno.getNome(),
            aluno.getDataNascimento(),
            aluno.getEndereco(),
            aluno.isAtivo(),
            aluno.getTurma() != null ? aluno.getTurma().getDescricao() : null,
            aluno.getTurma() != null ? aluno.getTurma().getId() : null
        );
    }
    
}   
