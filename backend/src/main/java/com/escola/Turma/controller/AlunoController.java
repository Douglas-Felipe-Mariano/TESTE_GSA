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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.escola.Turma.dto.AlunoRequestDTO;
import com.escola.Turma.dto.AlunoResponseDTO;
import com.escola.Turma.service.AlunoService;

@RestController //Define que é um controlador REST
@RequestMapping("/api/alunos") //Define a rota que recebera as requisições
@CrossOrigin(origins = "http://localhost:3000") //Configura e informa o CORS de onde viram as requisições
public class AlunoController {

     // Injeta o serviço de alunos  para gerencias alunos com regras de negócio
    @Autowired
    AlunoService alunoService;

    //Método de Criação de aluno
    @PostMapping
    public ResponseEntity<AlunoResponseDTO> cadastrarAluno(@RequestBody AlunoRequestDTO aluno) {
        AlunoResponseDTO novoAluno = alunoService.cadastrarAluno(aluno);

        return ResponseEntity.ok(novoAluno);
    }

    //Método de listagem apenas dos alunos ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunosAtivos() {
        List<AlunoResponseDTO> alunosAtivos = alunoService.listarAlunosAtivos();

        return ResponseEntity.ok(alunosAtivos);
    }

    //Método de listagem de todos os alunos
    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarTodosAlunos() {
        List<AlunoResponseDTO> alunos = alunoService.listarTodosAlunos();

        return ResponseEntity.ok(alunos);
    }

    //Metodo de atualização das alunos
    //@PathVariable define que a variavel sera passada no path, e é parametro essencial para a edição de uma turma
    //@RequestBody define que a entidade deve vir do body da pagina, faz o request do json
    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(@PathVariable Integer id, @RequestBody AlunoRequestDTO detalheAluno) {
        AlunoResponseDTO alunoAtualizado = alunoService.atualizarAluno(detalheAluno, id);

        return ResponseEntity.ok(alunoAtualizado);
    }

     //Método de deleção de Alunos, que realiza soft delete, apenas inativa
    //@PathVariable define que a variavel sera passada no path, e é parametro essencial para a edição de uma turma
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Integer id) {
        alunoService.deletarAluno(id);

        return ResponseEntity.noContent().build();
    }
    
}
