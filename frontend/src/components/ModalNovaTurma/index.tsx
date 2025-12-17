import React, { useEffect, useState } from "react";
import './styles.css';
import { TurmaDTO, TurmaService } from "../../services/TurmaService";

interface ModalNovaTurmaProps {
    isOpen: boolean;
    onClose: () => void;
    onReload: () => void;
    updateTurma?: TurmaDTO | null; 
}

export const ModalNovaTurma: React.FC<ModalNovaTurmaProps> = ({ isOpen, onClose, onReload, updateTurma }) => {

    const [descricao, setDescricao] = useState('');
    
    useEffect(() => {
        if(isOpen) {
            if(updateTurma) {
                setDescricao(updateTurma.descricao);
            } else {
                setDescricao("");
            }
        }
    }, [isOpen, updateTurma])

    const handleSalvarTurma = async (e: React.FormEvent) => {
        e.preventDefault();

        if(!descricao){
            alert("Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        try {
            if (updateTurma) {
                await TurmaService.update(updateTurma.id, {descricao});
                alert ("Turma Atualizada com sucesso!")
            } else {
                await TurmaService.create({descricao});
                alert ("Turma Criada com Sucesso!")
            }
            onReload();
        } catch (error) {
            console.error('Erro ao salvar turma: ', error);
            alert("Erro ao salvar turma. Tente novamente.");
        }
    };

    const handleFecharModal = () => {
        limparFormulario();
        onClose();
        onReload();
    }

    const limparFormulario = () => {
        setDescricao('');
    }

    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-container">
                <div className="modal-header">
                    <h2>{updateTurma ? "Editar Turma" : "Nova Turma"}</h2>
                    <button className="btn-close" onClick={handleFecharModal}>X</button>
                </div>

                <div className="modal-body">
                    <form onSubmit={handleSalvarTurma}> 
                        <div className="form-group">
                            <label>Turma:</label>
                            <input 
                                type="text" 
                                placeholder="EX: 1° B" 
                                value={descricao}
                                onChange={(e) => setDescricao(e.target.value)}
                            />
                        </div>  

                        <div className="modal-footer">
                            <button className="btn-cancel" onClick={onClose}>
                                Cancelar
                            </button>
                            <button type="submit" className="btn-save">
                                Salvar
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}