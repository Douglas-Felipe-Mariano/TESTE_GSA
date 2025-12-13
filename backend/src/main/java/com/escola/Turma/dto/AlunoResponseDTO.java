package com.escola.Turma.dto;

import java.time.LocalDate;

import com.escola.Turma.model.Aluno;
import com.escola.Turma.model.Turma;

public record AlunoResponseDTO(
    Integer id,
    String nome,
    LocalDate dataNascimento,
    String endereco,
    boolean ativo,
    String nomeTurma,
    Integer turmaId
) 
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
