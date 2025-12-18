import React, { useEffect, useState } from "react";
import './styles.css';
import { TurmaService, TurmaDTO } from "../../services/TurmaService";
import { AlunoResponseDTO, AlunoService } from "../../services/AlunoService";

/* Props do modal para abrir/fechar, recarregar lista e editar aluno */
interface ModalNovoAlunoProps {
    isOpen: boolean;
    onClose: () => void;
    onReload: () => void;
    updateAluno?: AlunoResponseDTO | null;
}

/* Componente Modal para criar/editar aluno */
export const ModalNovoAluno: React.FC<ModalNovoAlunoProps> = ({ isOpen, onClose, onReload, updateAluno }) => {

    // Estados do formulario
    const [nome, setNome] = useState('');
    const [dataNascimento, setDataNascimento] = useState('');
    const [endereco, setEndereco] = useState('');
    const [turmaId, setTurmaId] = useState<number | string>('');

    // Lista de turmas para select 
    const [listaTurmas, setListaTurmas] = useState<TurmaDTO[]>([]);

     // Carrega turmas e dados do aluno ao abrir o modal
    useEffect(() => {
        if (isOpen){
            carregarTurmas();
        }    

        if (updateAluno){
            setNome(updateAluno.nome);
            setDataNascimento(updateAluno.dataNascimento);
            setEndereco(updateAluno.endereco || "");
            setTurmaId(updateAluno.turmaId);
        } else{
            limparFormulario();
        }

    }, [isOpen, updateAluno]);

    // Busca todas as turmas da API 
    const carregarTurmas = async () => {
        try {
            const response = await TurmaService.getAll();
            setListaTurmas(response.data);
        } catch (error) {
            console.error('Erro ao carregar turmas:', error);
            alert("Erro ao conectar com o servidor.");
        }
    };

    // Salva ou atualiza aluno via API 
    const handleSalvarAluno = async (e: React.FormEvent) => {
        e.preventDefault();

        const dadosPayload = {
            nome,
            dataNascimento,
            endereco,
            turmaId: Number(turmaId)
        };

        
        try {
            if(updateAluno){
                await AlunoService.update(updateAluno.id, dadosPayload);
                alert("Aluno Atualizado Com Sucesso!");
            }else{
                await AlunoService.create(dadosPayload);
                alert("Aluno Criado Com Sucesso!");
            }
            limparFormulario();
            onReload();
        
        } catch (error) {
            console.error('Erro ao salvar aluno: ', error);
            alert("Erro ao salvar aluno. Tente novamente.");
        }
    };

    // Fecha modal e limpa formulário 
    const handleFecharModal = () => {
        limparFormulario();
        onClose();
        onReload();
    }

    // Limpa todos os campos do formulario
    const limparFormulario = () => {
        setNome('');
        setDataNascimento('');
        setEndereco('');
        setTurmaId('');
    }

    //Não renderiza o modal se estiver fechado
    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-container">
                <div className="modal-header">
                    <h2>{updateAluno ? "Editar Aluno" : "Novo Aluno"}</h2>
                    <button className="btn-close" onClick={handleFecharModal}>X</button>
                </div>

                <div className="modal-body">
                    <form onSubmit={handleSalvarAluno}> 
                        <div className="form-group">
                            <label>Nome Completo:</label>
                            <input 
                                type="text" 
                                placeholder="Nome do Aluno" 
                                value={nome}
                                onChange={(e) => setNome(e.target.value)}
                            />
                        </div>  
                        <div className="form-row">
                            <div className="form-group half-width">
                                <label>Data de Nascimento:</label>
                                <input 
                                    type="date" 
                                    value={dataNascimento}
                                    onChange={(e) => setDataNascimento(e.target.value)}
                                />
                            </div>
                            <div className="form-group half-width">
                                <label>Turma:</label>
                                <select 
                                    value={turmaId}
                                    onChange={(e) => setTurmaId(e.target.value)}
                                >
                                    <option value="" disabled>Selecione a Turma</option>
                                    {listaTurmas.map(turma => (
                                        <option key={turma.id} value={turma.id}>
                                            {turma.descricao}
                                        </option>
                                    ))}
                                </select>
                            </div>
                        </div>
                        <div className="form-group">
                            <label>Endereco:</label>
                            <input 
                                type="text" 
                                placeholder="Rua, número, bairro..." 
                                value={endereco}
                                onChange={(e) => setEndereco(e.target.value)}
                            />
                        </div>

                        <div className="modal-footer">
                            <button className="btn-cancel" onClick={onClose}>
                                Cancelar
                            </button>
                            <button className="btn-save">
                                Salvar
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}