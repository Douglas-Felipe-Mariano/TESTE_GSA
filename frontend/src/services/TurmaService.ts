import api from "./api";

export interface TurmaDTO {
    id: number;
    descricao: string;
};

export interface TurmaCadastroDTO {
    descricao: string;
}

export const TurmaService = {
    getAll: async () => {
        return api.get<TurmaDTO[]>("/turmas");
    },

    create: async (turma: TurmaCadastroDTO) => {
        return api.post("/turmas", turma);
    },

    update: async (id: number, turma: TurmaCadastroDTO) => {
        return api.put(`/turmas/${id}`, turma);
    },

    delete: async (id: number) => {
        return api.delete(`/turmas/${id}`);
    }
};