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
        return api.get<AlunoResponseDTO[]>("/api/alunos");
    },

    create: async (aluno: AlunoRequestDTO) => {
        return api.post("/api/alunos", aluno);
    },

    update: async (id: number, aluno: AlunoRequestDTO) => {
        return api.put(`/api/alunos/${id}`, aluno);
    },

    delete: async (id: Number) => {
        return api.delete(`/api/alunos/${id}`);
    }
};

