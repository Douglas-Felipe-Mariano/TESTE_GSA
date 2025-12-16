import React, { useEffect, useState } from 'react';
import { ModalNovoAluno } from '../../components/ModalNovoAluno';

import './ListaAlunos.css';
import { AlunoResponseDTO, AlunoService } from '../../services/AlunoService';
import { TurmaDTO, getTurmas } from '../../services/TurmaService';

export const ListaAlunos: React.FC = () => {

    // Gerencia o modal, inicando a pagina com ele fechado e verifica se o aluno está sendo cadastrado ou criado
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [updateAluno, setUpdateAluno] = useState<AlunoResponseDTO | null>(null);

    // Listas populadas pela api
    const [alunos, setAlunos] = useState<AlunoResponseDTO[]>([]);
    const [turmas, setTurmas] = useState<TurmaDTO[]>([]);

    // Filtros
    const [filtroNome, setFiltroNome] = useState("");
    const [filtroTurma, setFiltroTurma] = useState("");
    const [filtroStatus, setFiltroStatus] = useState("");   

    // função de busca dos dados na api
    const carregarDados = async () => {
        try{
            const [responseAlunos, responseTurmas] = await Promise.all([
                AlunoService.getAll(),
                getTurmas()
            ]);

            setAlunos(responseAlunos.data);
            setTurmas(responseTurmas.data);
        } catch (error) {
            console.error("Erro ao carregar dados:", error);
        }
    };

    // Garante que os dados sejam carregados assim que a pagina é aberta
    useEffect (() => {
        carregarDados();
    }, []);

    const alunosFiltrados = alunos.filter((aluno) => {
        // Filtro por nome
        const nomeMatch = aluno.nome.toLowerCase().includes(filtroNome.toLowerCase());

        // Filtro por turma
        const turmaMatch = filtroTurma === "" || String(aluno.turmaId) === filtroTurma;

        // Filtro por Status
        const statusMatch = filtroStatus === "" || String(aluno.ativo) === filtroStatus;

        return nomeMatch && turmaMatch && statusMatch;
    })

    const limparFiltros = () =>{
        setFiltroNome("");
        setFiltroStatus("");
        setFiltroTurma("");
    }

    // Método para buscar o nome da turma, tendo em vista que o DTO de AlunoResponse do backend só retorna o id da turma, sem nome
    const getNomeTurma = (id: number) => {
        const turmaencontrada = turmas.find(t => t.id === id);
        return turmaencontrada ? turmaencontrada.descricao : 'Turma não Encontrada';
    }

    // Função apra abrir o modal
    const handleNovoAluno = () => {     
        setIsModalOpen(true);
        setUpdateAluno(null);
    }

    const handleUpdateAluno = (aluno: AlunoResponseDTO) => {
        setUpdateAluno(aluno);
        setIsModalOpen(true);
    }

    const handleDeleteAluno = async (id: Number) => {
        const confirmou = window.confirm("Tem certeza que deseja excluir este aluno?")
        if (confirmou){
            try{
                await AlunoService.delete(id);
                alert ("Aluno excluido com sucesso");
                carregarDados();
            } catch (error) {
                console.error("Erro ao excluir", error);
                alert("Erro ao excluir. Verifique se o aluno não tem vínculos.");
            }
        }
    };

    // Função para fechar o modal
    const handleCloseModal = () => { 
        setIsModalOpen(false);
    }

    // Função para recarregar os alunos
    const refreshAlunos = () => {
        handleCloseModal();
        carregarDados();
    };

    return (
        <div className="lista-container">
            
            <div className="header-actions">
                <h2>Lista de Alunos</h2>
                <button onClick={handleNovoAluno} className="btn btn-primary">
                    + Novo Aluno
                </button>
            </div>

            
            <div className="filtros-container">
                <div className="filtro-group">
                    <label>Nome do Aluno</label>
                    <input 
                        type="text" 
                        className="form-control" 
                        placeholder="Digite o nome..."  
                        value={filtroNome}
                        onChange={(e) => setFiltroNome(e.target.value)}
                    />
                </div>

                <div className="fil tro-group">
                    <label>Turma</label>
                    <select 
                        className="form-control"
                        value={filtroTurma}
                        onChange={(e) => setFiltroTurma(e.target.value)}
                    >
                        <option value="">Todas as Turmas</option>
                        {turmas.map(turma => (
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
                        value={filtroStatus}
                        onChange={(e) => setFiltroStatus(e.target.value)}
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
                    {alunos.length === 0 ? (
                        <tr>
                            <td colSpan={5} style={{textAlign: 'center', padding: '20px'}}>
                                Nenhum aluno cadastrado.
                            </td>
                        </tr>
                    ) : (
                        alunosFiltrados.map((aluno) => (
                            <tr key={aluno.id}>
                                <td>{aluno.id}</td>
                                <td>{aluno.nome}</td>
                                <td>{getNomeTurma(aluno.turmaId)}</td>
                                <td>{aluno.dataNascimento}</td>
                                <td>
                                    {aluno.ativo ? (
                                        <span className="status-ativo">Ativo</span>
                                    ) : (
                                        <span className="status-inativo">Inativo</span>
                                    )}
                                </td>
                                <td>
                                    <button 
                                        className="btn btn-primary btn-sm mr-10"
                                        onClick={() => handleUpdateAluno(aluno)}    
                                    >
                                        Editar
                                    </button>
                                    <button 
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleDeleteAluno(aluno.id)}
                                    >
                                        Excluir
                                    </button>
                                </td>
                            </tr>
                        ))
                    )}
                </tbody>
            </table>

            <ModalNovoAluno 
                isOpen={isModalOpen} 
                onClose={() => setIsModalOpen(false)} 
                onReload={refreshAlunos}
                updateAluno={updateAluno}
            />

        </div>
    );
};