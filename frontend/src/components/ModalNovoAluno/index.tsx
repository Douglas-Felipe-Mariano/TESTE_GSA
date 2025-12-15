import React from "react";
import './styles.css';
import { MOCK_TURMAS } from "../../services/mockData";

interface ModalNovoAlunoProps {
    isOpen: boolean;
    onClose: () => void;
}

export const ModalNovoAluno: React.FC<ModalNovoAlunoProps> = ({ isOpen, onClose }) => {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-container">
                <div className="modal-header">
                    <h2>Novo Aluno</h2>
                    <button className="btn-close" onClick={onClose}>X</button>
                </div>

                <div className="modal-body">
                    <form>
                        <div className="form-group">
                            <label>Nome Completo:</label>
                            <input type="text" placeholder="Nome do Aluno" />
                        </div>  
                        <div className="form-row">
                            <div className="form-group half-width">
                                <label>Data de Nascimento:</label>
                                <input type="date" />
                            </div>
                            <div className="form-group half-width">
                                <label>Turma:</label>
                                <select defaultValue=''>
                                    <option value="" disabled>Selecione a Turma</option>
                                    {MOCK_TURMAS.map(turma => (
                                        <option key={turma.id} value={turma.id}>
                                            {turma.descricao}
                                        </option>
                                    ))}
                                </select>
                            </div>
                        </div>
                        <div className="form-group">
                            <label>Endereco:</label>
                            <input type="text" placeholder="Rua, número, bairro..." />
                        </div>
                    </form>
                </div>
                <div className="modal-footer">
                    <button className="btn-cancel" onClick={onClose}>
                        Cancelar
                    </button>
                    <button className="btn-save">
                        Salvar
                    </button>
                </div>
            </div>
        </div>
    )
}