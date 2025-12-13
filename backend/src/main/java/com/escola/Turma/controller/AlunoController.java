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

import com.escola.Turma.dto.AlunoRequestDTO;
import com.escola.Turma.dto.AlunoResponseDTO;
import com.escola.Turma.service.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> cadastrarAluno(AlunoRequestDTO aluno) {
        AlunoResponseDTO novoAluno = alunoService.cadastrarAluno(aluno);

        return ResponseEntity.ok(novoAluno);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunosAtivos() {
        List<AlunoResponseDTO> alunosAtivos = alunoService.listarAlunosAtivos();

        return ResponseEntity.ok(alunosAtivos);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarTodosAlunos() {
        List<AlunoResponseDTO> alunos = alunoService.listarTodosAlunos();

        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(@PathVariable Integer id, @RequestBody AlunoRequestDTO detalheAluno) {
        AlunoResponseDTO alunoAtualizado = alunoService.atualizarAluno(detalheAluno, id);

        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Integer id) {
        alunoService.deletarAluno(id);

        return ResponseEntity.noContent().build();
    }
    
}
