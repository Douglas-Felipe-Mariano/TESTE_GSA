package com.escola.Turma.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.Turma.dto.AlunoRequestDTO;
import com.escola.Turma.dto.AlunoResponseDTO;
import com.escola.Turma.model.Aluno;
import com.escola.Turma.model.Turma;
import com.escola.Turma.repository.AlunoRepository;
import com.escola.Turma.repository.TurmaRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository TurmaRepository;    

    public AlunoResponseDTO cadastrarAluno(AlunoRequestDTO alunoDTO) {

        if (alunoDTO.nome() == null || alunoDTO.nome().isEmpty()) {
            throw new RuntimeException("O nome do alunoDTO é obrigatório.");
        }

        if((alunoDTO.dataNascimento() == null) || (alunoDTO.dataNascimento().isAfter(LocalDate.now()))){
            throw new RuntimeException("Data de Nascimento inválida."); 
        }

        if(alunoDTO.turmaId() == null){
            throw new RuntimeException("Turma inválida."); 
        }

        Turma turma = TurmaRepository.findById(alunoDTO.turmaId())
                        .orElseThrow(() -> new RuntimeException("Turma não encontrada."));

        Aluno aluno = alunoDTO.toEntity(turma);

        alunoRepository.save(aluno);

        return AlunoResponseDTO.fromEntity(aluno);
    }

    public List<Aluno> listarAlunosPorNome(String nome) {
        return alunoRepository.findByNomeAndAtivo(nome, true);
    }

    public List<AlunoResponseDTO> listarAlunosAtivos() {
        return alunoRepository.findByAtivo(true)
                              .stream()
                              .map(AlunoResponseDTO::fromEntity)
                              .collect(java.util.stream.Collectors.toList());
    }

    public List<AlunoResponseDTO> listarTodosAlunos() {
        return alunoRepository.findAll()
                              .stream()
                              .map(AlunoResponseDTO::fromEntity)
                              .collect(java.util.stream.Collectors.toList());
    }

    public AlunoResponseDTO atualizarAluno(AlunoRequestDTO detalheAluno, Integer id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));


        if (detalheAluno.nome() != null){
            alunoExistente.setNome(detalheAluno.nome());
        }                                            

        if (detalheAluno.dataNascimento() != null){
            if (detalheAluno.dataNascimento().isAfter(LocalDate.now())){
                throw new RuntimeException("Data de Nascimento inválida."); 
            }   
            alunoExistente.setDataNascimento(detalheAluno.dataNascimento());
        }

        if (detalheAluno.endereco() != null){
            alunoExistente.setEndereco(detalheAluno.endereco());
        }

        if (detalheAluno.turmaId() != null){
            Turma turma = TurmaRepository.findById(detalheAluno.turmaId())
                            .orElseThrow(() -> new RuntimeException("Turma não encontrada."));

            alunoExistente.setTurma(turma);
        }

        alunoRepository.save(alunoExistente);

        return AlunoResponseDTO.fromEntity(alunoExistente);
    }

    public void deletarAluno(Integer id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        alunoExistente.setAtivo(false);
        alunoRepository.save(alunoExistente);
    }
}