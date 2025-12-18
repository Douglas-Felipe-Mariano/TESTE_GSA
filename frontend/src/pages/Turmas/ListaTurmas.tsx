import React, { useEffect, useState } from 'react';
import { ModalNovaTurma } from '../../components/ModalNovaTurma';

import './ListaTurmas.css';
import { TurmaDTO,  TurmaService } from '../../services/TurmaService';
import { AlunoResponseDTO, AlunoService } from '../../services/AlunoService';

export const ListaTurmas: React.FC = () => {

    // Estado do modal e verificação se é create ou update
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [updateTurma, setUpdateTurma] = useState<TurmaDTO | null>(null);

    // Listas populadas pela api    
    const [alunos, setAlunos] = useState<AlunoResponseDTO[]>([]);
    const [turmas, setTurmas] = useState<TurmaDTO[]>([]);

    // Armazena a quantidade de alunos por turma, usando o id da turma como chave
    const [alunosPorTurma, setAlunosPorTurma] = useState<{[key:number] : number}>({});

    // Estados de filtros
    const [filtroTurma, setFiltroTurma] = useState("");
    

    // carrega os dados
    const carregarDados = async () => {
        try{
            const [responseAlunos, responseTurmas] = await Promise.all([
                AlunoService.getAll(),
                TurmaService.getAll()
            ]);

            setAlunos(responseAlunos.data);
            setTurmas(responseTurmas.data);

            //Atualiza a contagem de alunos por turma
            contarAlunosPorTurma(responseAlunos.data, responseTurmas.data);
        } catch (error) {
            console.error("Erro ao carregar dados:", error);
        }
    };

    //Conta quantos alunos existem em cada turma
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
        // Aplica o filtro caso haja seleção
        const turmaMatch = filtroTurma === "" || String(turmas.id) === filtroTurma;

        return turmaMatch;
    })

    //limpa os filtros
    const limparFiltros = () =>{
        setFiltroTurma(""); 
    }


    // Função apra abrir o modal para criação de nova turma
    const handleNovaTurma = () => { 
        setUpdateTurma(null);
        setIsModalOpen(true);
    }

    // Abre o modal para edição de uma turma, preenchendo os dados existentes
    const handleUpdateTurma = (turma: TurmaDTO) => { 
        setUpdateTurma(turma);
        setIsModalOpen(true);
    }

    // Exclui uma turma, validando se há alunos matriculados
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

    // Fecha para fechar o modal
    const handleCloseModal = () => { 
        setIsModalOpen(false);
    }

    // Atualiza a listagem de alunos
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