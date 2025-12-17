import React, { useEffect, useState } from 'react';
import { ModalNovaTurma } from '../../components/ModalNovaTurma';

import './ListaTurmas.css';
import { TurmaDTO,  TurmaService } from '../../services/TurmaService';
import { AlunoResponseDTO, AlunoService } from '../../services/AlunoService';
import { count } from 'console';

export const ListaTurmas: React.FC = () => {

    // Gerencia o modal, inicando a pagina com ele fechado e verifica qual é a requisição put ou post
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [updateTurma, setUpdateTurma] = useState<TurmaDTO | null>(null);

    // Listas populadas pela api
    const [alunos, setAlunos] = useState<AlunoResponseDTO[]>([]);
    const [turmas, setTurmas] = useState<TurmaDTO[]>([]);

    // Armazena a quantidade de alunos por turma
    const [alunosPorTurma, setAlunosPorTurma] = useState<{[key:number] : number}>({});

    // Filtros
    const [filtroTurma, setFiltroTurma] = useState("");
    

    // função de busca dos dados na api
    const carregarDados = async () => {
        try{
            const [responseAlunos, responseTurmas] = await Promise.all([
                AlunoService.getAll(),
                TurmaService.getAll()
            ]);

            setAlunos(responseAlunos.data);
            setTurmas(responseTurmas.data);

            contarAlunosPorTurma(responseAlunos.data, responseTurmas.data);
        } catch (error) {
            console.error("Erro ao carregar dados:", error);
        }
    };

    const contarAlunosPorTurma = (alunos: AlunoResponseDTO[], turmas: TurmaDTO[]) => {
        const contador: {[key: number]: number} = {};

        alunos.forEach((aluno) => {
            const turmaId = aluno.turmaId;
            contador[turmaId] = (contador[turmaId] || 0) + 1;
        });

        setAlunosPorTurma(contador);
    }

    // Garante que os dados sejam carregados assim que a pagina é aberta
    useEffect (() => {
        carregarDados();
    }, []);

    const turmaFiltrada = turmas.filter((turmas) => {
        // Filtro por turma
        const turmaMatch = filtroTurma === "" || String(turmas.id) === filtroTurma;

        return turmaMatch;
    })

    const limparFiltros = () =>{
        setFiltroTurma(""); 
    }

    // Método para buscar o nome da turma, tendo em vista que o DTO de AlunoResponse do backend só retorna o id da turma, sem nome
    const getNomeTurma = (id: number) => {
        const turmaencontrada = turmas.find(t => t.id === id);
        return turmaencontrada ? turmaencontrada.descricao : 'Turma não Encontrada';
    }

    // Função apra abrir o modal
    const handleNovaTurma = () => { 
        setUpdateTurma(null);
        setIsModalOpen(true);
    }

    // Função para abrir o modal com os campos prenchidos, em caso de edição de registro
    const handleUpdateTurma = (turma: TurmaDTO) => { 
        setUpdateTurma(turma);
        setIsModalOpen(true);
    }

    const handleDeleteTurma = async (id: number) => {
        if (alunosPorTurma[id] && alunosPorTurma[id] > 0){
            alert ("Não é possivel excluir uma turma que possui alunos matriculados!")
            return;
        }
        if (window.confirm("Deseja realmente excluir está turma?"))
            try{
                await TurmaService.delete(id);
                alert("Turma Excluida!");
                carregarDados();
            } catch (error) {
                console.log("error");
                alert ("Erro ao excluir!")
            }
    }

    // Função para fechar o modal
    const handleCloseModal = () => { 
        setIsModalOpen(false);
    }

    // Função para recarregar os alunos
    const refreshTurma = () => {
        handleCloseModal();
        setUpdateTurma(null);
        carregarDados();
    };

    return (
        <div className="lista-container">
            
            <div className="header-actions">
                <h2>Gerenciamento de Turmas</h2>
                <button onClick={handleNovaTurma} className="btn btn-primary">
                    + Nova Turma
                </button>
            </div>

            
            <div className="filtros-container">
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

                <button className="btn btn-secondary" onClick={limparFiltros} style={{height: '38px', backgroundColor: '#94a3b8', color: 'white'}}>
                    Limpar
                </button>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome da Turma</th>
                        <th>Qtd. de Alunos</th>
                        <th>Açoes</th>
                    </tr>
                </thead>
                <tbody>
                    {turmas.length === 0 ? (
                        <tr>
                            <td colSpan={4} style={{textAlign: 'center', padding: '20px'}}>
                                Nenhuma turma cadastrada.
                            </td>
                        </tr>
                    ) : (
                        turmaFiltrada.map((turma) => (
                            <tr key={turma.id}>
                                <td>{turma.id}</td>
                                <td>{turma.descricao}</td>
                                <td>{alunosPorTurma[turma.id] || 0}</td>
                                <td>
                                    <button 
                                        className="btn btn-primary btn-sm mr-10"
                                        onClick={() => handleUpdateTurma(turma)}
                                    >
                                        Editar
                                    </button>
                                    <button 
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleDeleteTurma(turma.id)}
                                    >
                                        Excluir
                                    </button>
                                </td>
                            </tr>
                        ))
                    )}
                </tbody>
            </table>

            < ModalNovaTurma
                isOpen={isModalOpen} 
                onClose={() => setIsModalOpen(false)} 
                onReload={refreshTurma}
                updateTurma={updateTurma}
            />

        </div>
    );
};