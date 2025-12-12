package com.escola.Turma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escola.Turma.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Optional<Aluno> findByNomeIgnoreCase(String nome);

    List<Aluno> findByAtivo(boolean ativo);

    List<Aluno> findByNomeAndAtivo(String nome, boolean ativo);

    //Busca qualquer aluno que tenha as iniciais do nome iguais ao parâmetro nome, ignorando maiúsculas e minúsculas
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
