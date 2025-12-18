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

@RestController //Define que é um controlador REST
@RequestMapping("/api/turmas") //Define a rota que recebera as requisições
@CrossOrigin(origins = "http://localhost:3000") //Configura e informa o CORS de onde viram as requisições
public class TurmaController {

     // Injeta o serviço de turmas  para gerencias turmas com regras de negócio
    @Autowired
    TurmaService turmaService;

    //Método de criação de turma
    @PostMapping
    public ResponseEntity<Turma> cadastrarTurma(@RequestBody Turma turma) {
        Turma novaTurma = turmaService.cadastrarTurma(turma);

        return ResponseEntity.ok(novaTurma);
    }

    //Método de listagem de todas as turmas
    @GetMapping
    public ResponseEntity<List<Turma>> listarTurmas() {
        List<Turma> turmas = turmaService.listarTurmas();

        return ResponseEntity.ok(turmas);
    }

    //Metodo de atualização das turmas
    //@PathVariable define que a variavel sera passada no path, e é parametro essencial para a edição de uma turma
    //@RequestBody define que a entidade deve vir do body da pagina, faz o request do json
    @PutMapping("/{id}") //define que a rota de edição tera o id vindo da rota
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Integer id, @RequestBody Turma detalheTurma) {
        Turma turmaAtualizada = turmaService.atualizarTurma(detalheTurma, id);

        return ResponseEntity.ok(turmaAtualizada);
    }

    //Método de deleção de turmas
    //@PathVariable define que a variavel sera passada no path, e é parametro essencial para a edição de uma turma
    @DeleteMapping("/{id}") //define que a rota de edição tera o id vindo da rota
    public ResponseEntity<Void> deletarTurma(@PathVariable Integer id) {
        turmaService.deletarTurma(id);

        return ResponseEntity.noContent().build();
    }

}
