package com.escola.Turma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.Turma.model.Aluno;
import com.escola.Turma.service.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(Aluno aluno) {
        Aluno novoAluno = alunoService.cadastrarAluno(aluno);

        return ResponseEntity.ok(novoAluno);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Aluno>> listarAlunosAtivos() {
        List<Aluno> alunosAtivos = alunoService.listarAlunosAtivos();

        return ResponseEntity.ok(alunosAtivos);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodosAlunos() {
        List<Aluno> alunos = alunoService.listarTodosAlunos();

        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Integer id, @RequestBody Aluno detalheAluno) {
        Aluno alunoAtualizado = alunoService.atualizarAluno(detalheAluno, id);

        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAluno(@PathVariable Integer id) {
        alunoService.deletarAluno(id);

        return ResponseEntity.noContent().build();
    }
    
}
