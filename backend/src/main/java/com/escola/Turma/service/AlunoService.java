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

    //Autoinstancia os repositories
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository TurmaRepository;    

    //Método para cadastrar aluno
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

    //Método para listar aluno por nome (Filtro)
    public List<Aluno> listarAlunosPorNome(String nome) {
        return alunoRepository.findByNomeAndAtivo(nome, true);
    }

    //Método para listar apenas os alunos ativo (Pensando em escalabilidade, caso os alunos inativos não devam aparecer)
    public List<AlunoResponseDTO> listarAlunosAtivos() {
        return alunoRepository.findByAtivo(true)
                              .stream()
                              .map(AlunoResponseDTO::fromEntity)
                              .collect(java.util.stream.Collectors.toList());
    }

    //Método para listar todos os alunos
    public List<AlunoResponseDTO> listarTodosAlunos() {
        return alunoRepository.findAll()
                              .stream()
                              .map(AlunoResponseDTO::fromEntity)
                              .collect(java.util.stream.Collectors.toList());
    }

    //Método para atualizar o aluno
    public AlunoResponseDTO atualizarAluno(AlunoRequestDTO detalheAluno, Integer id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));


        if (! detalheAluno.nome().isEmpty()){
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

    //Método para deletar o aluno, soft delet, apenas inativa
    public void deletarAluno(Integer id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        alunoExistente.setAtivo(false);
        alunoRepository.save(alunoExistente);
    }
}