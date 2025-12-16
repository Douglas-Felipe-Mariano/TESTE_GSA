package com.escola.Turma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.Turma.model.Turma;
import com.escola.Turma.service.TurmaService;

@RestController
@RequestMapping("/api/turmas")
@CrossOrigin(origins = "http://localhost:3000")
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Turma> cadastrarTurma(@RequestBody Turma turma) {
        Turma novaTurma = turmaService.cadastrarTurma(turma);

        return ResponseEntity.ok(novaTurma);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listarTurmas() {
        List<Turma> turmas = turmaService.listarTurmas();

        return ResponseEntity.ok(turmas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Integer id, @RequestBody Turma detalheTurma) {
        Turma turmaAtualizada = turmaService.atualizarTurma(detalheTurma, id);

        return ResponseEntity.ok(turmaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Integer id) {
        turmaService.deletarTurma(id);

        return ResponseEntity.noContent().build();
    }

}
