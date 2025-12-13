package com.escola.Turma.service;

import java.time.LocalDate;
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
    
        if (aluno.getNome() == null || aluno.getNome().isEmpty()) {
            throw new RuntimeException("O nome do Aluno é obrigatório.");
        }

        if((aluno.getDataNascimento() == null) || (aluno.getDataNascimento().isAfter(LocalDate.now()))){
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
            if (detalheAluno.getDataNascimento().isAfter(LocalDate.now())){
                throw new RuntimeException("Data de Nascimento inválida."); 
            }   
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