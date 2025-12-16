import React, { useEffect, useState } from "react";
import './styles.css';
import { postTurma } from "../../services/TurmaService";

interface ModalNovaTurmaProps {
    isOpen: boolean;
    onClose: () => void;
    onReload: () => void;
}

export const ModalNovaTurma: React.FC<ModalNovaTurmaProps> = ({ isOpen, onClose, onReload }) => {

    const [descricao, setDescricao] = useState('');
    


    const handleSalvarTurma = async (e: React.FormEvent) => {
        e.preventDefault();

        if(!descricao){
            alert("Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        try {
            await postTurma({
                id: Number(),
                descricao,
            });

            alert("Turma cadastrada com sucesso!");
            limparFormulario();
            onClose();
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
                    <h2>Nova Turma</h2>
                    <button className="btn-close" onClick={handleFecharModal}>X</button>
                </div>

                <div className="modal-body">
                    <form onSubmit={handleSalvarTurma}> 
                        <div className="form-group">
                            <label>Turma:</label>
                            <input 
                                type="text" 
                                placeholder="Nome do Aluno" 
                                value={descricao}
                                onChange={(e) => setDescricao(e.target.value)}
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