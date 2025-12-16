import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8080/api'
});

export interface TurmaDTO {
    id: number;
    descricao: string;
};

export interface AlunoResponseDTO {
    id: number;
    nome: string;
    dataNascimento: string;
    endereco?: string;
    ativo: boolean;
    turmaId: number;
};

export interface AlunoRequestDTO{
    nome: string;
    dataNascimento: string;
    endereco?: string;
    turmaId: number;
}

export const getTurmas = async () => {
    return api.get<TurmaDTO[]>( '/turmas' );
};

export const postAluno = async (aluno: AlunoRequestDTO) => {
    return api.post( '/alunos', aluno );
};

export const getAlunos = async () => {
    return api.get<AlunoResponseDTO[]>( '/alunos' );
};


export default api;