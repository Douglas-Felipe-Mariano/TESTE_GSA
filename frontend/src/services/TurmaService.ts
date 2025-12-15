import api from "./api";
import { ITurma } from "../types/ITurma";

export const TurmaService = {

    getAll: async () => {
        const response = await api.get<ITurma[]>("/turmas");
        return response.data;
    },

    create: async (turma: ITurma) => {
        const response = await api.post<ITurma>("/turmas", turma);
        return response.data;
    },

    update: async (turma: ITurma) => {
        const response = await api.put<ITurma>(`/turmas/${turma.id}`, turma);
        return response.data;
    },

    delete: async (id: number) => {
        await api.delete(`/turmas/${id}`);
    }  
};