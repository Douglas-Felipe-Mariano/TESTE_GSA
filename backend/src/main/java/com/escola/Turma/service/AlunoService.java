package com.escola.Turma.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.Turma.model.Aluno;
import com.escola.Turma.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno cadastrarAluno(Aluno aluno) {
        List<Aluno> novoAluno = alunoRepository.findByNomeAndAtivo(aluno.getNome(), true);

        if (novoAluno.isEmpty()){
            throw new RuntimeException("O nome do Aluno é obrigatório.");
        }

        if((novoAluno.get(0).getDataNascimento() == null) || (novoAluno.get(0).getDataNascimento().isAfter(LocalDateTime.now())) || (novoAluno.get(0).getDataNascimento().isEqual(LocalDateTime.now()))){
            throw new RuntimeException("Data de Nascimento inválida.");
        }
        
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunosPorNome(String nome) {
        return alunoRepository.findByNomeAndAtivo(nome, true);
    }

    public List<Aluno> listarAlunosAtivos() {
        List<Aluno> alunosAtivos = alunoRepository.findByAtivo(true);
        return alunosAtivos;
    }

    public List<Aluno> listarTodosAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();

        return alunos;
    }

    public Aluno atualizarAluno(Aluno detalheAluno, Integer id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));


        if (detalheAluno.getNome() != null){
            alunoExistente.setNome(detalheAluno.getNome());
        }                                            

        if (detalheAluno.getDataNascimento() != null){
            alunoExistente.setDataNascimento(detalheAluno.getDataNascimento());
        }

        if (detalheAluno.getEndereco() != null){
            alunoExistente.setEndereco(detalheAluno.getEndereco());
        }

        return alunoRepository.save(alunoExistente);
    }

    public void deletarAluno(Integer id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        alunoExistente.setAtivo(false);
        alunoRepository.save(alunoExistente);
    }
}