package com.escola.Turma.dto;

import java.time.LocalDate;

import com.escola.Turma.model.Aluno;
import com.escola.Turma.model.Turma;

public record AlunoRequestDTO(
    String nome,
    LocalDate dataNascimento,
    String endereco,
    Integer turmaId
) {

        public Aluno toEntity(Turma turma) {
        Aluno aluno = new Aluno();
        aluno.setNome(this.nome());
        aluno.setDataNascimento(this.dataNascimento());
        aluno.setEndereco(this.endereco());
        aluno.setTurma(turma); 
        aluno.setAtivo(true);  
        return aluno;
    }

}
