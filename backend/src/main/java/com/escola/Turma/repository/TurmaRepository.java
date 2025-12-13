package com.escola.Turma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escola.Turma.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    //Busca qualquer turma que tenha as iniciais da descrição iguais ao parâmetro descrição, ignorando maiúsculas e minúsculas
    List<Turma> findByDescricaoContainingIgnoreCase(String descricao);

    Optional<Turma> findByDescricaoIgnoreCase(String descricao);
}
