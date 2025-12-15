import React, { useEffect, useState } from 'react';
import { IAluno } from '../../types/IAluno';
import { MOCK_ALUNOS, MOCK_TURMAS } from '../../services/mockData';

import './ListaAlunos.css';

export const ListaAlunos = () => {
    const [alunosOriginais, setAlunosOriginais] = useState<IAluno[]>([]);

    const [alunosExibidos, setAlunosExibidos] = useState<IAluno[]>([]);

    const [buscaNome, setBuscaNome] = useState<string>('');
    const [buscaTurma, setBuscaTurma] = useState<string>('');
    const [buscaStatus, setBuscaStatus] = useState<string>('todos');

    useEffect(() =>{
        setAlunosOriginais(MOCK_ALUNOS);
        setAlunosExibidos(MOCK_ALUNOS);
    }, []);

    useEffect(() => {
        let resultado = alunosOriginais;

        if (buscaNome) {
            resultado = resultado.filter(aluno =>
                aluno.nome.toLowerCase().includes(buscaNome.toLowerCase())
            );
        }

        if (buscaTurma) {
            resultado = resultado.filter(aluno =>
                aluno.nomeTurma.toLowerCase().includes(buscaTurma.toLowerCase())
            );
        }

        if (buscaStatus !== 'todos') {
            const isAtivo = buscaStatus === 'ativo';
            resultado = resultado.filter(aluno => aluno.ativo === isAtivo);
        }
        
        setAlunosExibidos(resultado);

    }, [buscaNome, buscaTurma, buscaStatus, alunosOriginais]);

    const limparFiltros = () => {
        setBuscaNome('');
        setBuscaTurma('');
        setBuscaStatus('todos');
    }

    return (
        <div className="lista-container">
            
            <div className="header-actions">
                <h2>Lista de Alunos</h2>
                <button className="btn btn-primary">
                    + Novo Aluno
                </button>
            </div>

            {/* ÁREA DE FILTROS */}
            <div className="filtros-container">
                <div className="filtro-group">
                    <label>Nome do Aluno</label>
                    <input 
                        type="text" 
                        className="form-control" 
                        placeholder="Digite o nome..."
                        value={buscaNome}
                        onChange={(e) => setBuscaNome(e.target.value)}
                    />
                </div>

                <div className="filtro-group">
                    <label>Turma</label>
                    <select 
                        className="form-control"
                        value={buscaTurma}
                        onChange={(e) => setBuscaTurma(e.target.value)}
                    >
                        <option value="">Todas as Turmas</option>
                        {MOCK_TURMAS.map(turma => (
                            <option key={turma.id} value={turma.id}>
                                {turma.descricao}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="filtro-group">
                    <label>Status</label>
                    <select 
                        className="form-control"
                        value={buscaStatus}
                        onChange={(e) => setBuscaStatus(e.target.value)}
                    >
                        <option value="todos">Todos</option>
                        <option value="ativo">Ativo</option>
                        <option value="inativo">Inativo</option>
                    </select>
                </div>

                <button className="btn btn-secondary" onClick={limparFiltros} style={{height: '38px', backgroundColor: '#94a3b8', color: 'white'}}>
                    Limpar
                </button>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>RA</th>
                        <th>Nome</th>
                        <th>Turma</th>
                        <th>Nascimento</th>
                        <th>Status</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {alunosExibidos.length > 0 ? (
                        alunosExibidos.map((aluno) => (
                            <tr key={aluno.id}>
                                <td>{aluno.id}</td>
                                <td>{aluno.nome}</td>
                                <td>{aluno.nomeTurma}</td>
                                <td>{aluno.dataNascimento}</td>
                                <td>
                                    {aluno.ativo ? (
                                        <span className="status-ativo">Ativo</span>
                                    ) : (
                                        <span className="status-inativo">Inativo</span>
                                    )}
                                </td>
                                <td>
                                    <button className="btn btn-primary btn-sm mr-10">
                                        Editar
                                    </button>
                                    <button className="btn btn-danger btn-sm">
                                        Excluir
                                    </button>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan={6} style={{textAlign: 'center', color: '#666'}}>
                                Nenhum aluno encontrado com esses filtros.
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};