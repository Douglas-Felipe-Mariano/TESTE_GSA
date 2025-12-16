import api from "./api";

export interface AlunoResponseDTO {
    id: number;
    nome: string;
    dataNascimento: string;
    endereco?: string;
    ativo: boolean;
    turmaId: number;
};

export interface AlunoRequestDTO {
    nome: string;
    dataNascimento: string;
    endereco?: string;
    turmaId: number;
}

export const AlunoService = {
    getAll: async () => {
        return api.get<AlunoResponseDTO[]>("/alunos");
    },

    create: async (aluno: AlunoRequestDTO) => {
        return api.post("/alunos", aluno);
    },

    update: async (id: number, aluno: AlunoRequestDTO) => {
        return api.put(`/alunos/${id}`, aluno);
    },

    delete: async (id: Number) => {
        return api.delete(`/alunos/${id}`);
    }
};

