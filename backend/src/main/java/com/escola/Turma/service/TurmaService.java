package com.escola.Turma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.Turma.model.Turma;
import com.escola.Turma.repository.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaRepository;

    public Turma cadastrarTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public Turma buscarTurmaPorId(Integer id) {
        return turmaRepository.findById(id).orElse(null);
    }

    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    public Turma atualizarTurma(Turma detalheTurma, Integer id) {
        Turma turmaExistente = turmaRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("Turma não encontrada."));

        if (detalheTurma.getDescricao() != null){
            turmaExistente.setDescricao(detalheTurma.getDescricao());
        }                                            

        return turmaRepository.save(turmaExistente);
    }

    public void deletarTurma(Integer id) {
        Turma turmaExistente = turmaRepository.findById(id)
                                              .orElseThrow(() -> new RuntimeException("Turma não encontrada."));
        turmaRepository.delete(turmaExistente);
    }

}
