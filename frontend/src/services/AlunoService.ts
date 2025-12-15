import api from "./api";
import { IAluno } from "../types/IAluno";
import { create } from "domain";

export const AlunoService = {

    getAll: async () => {
        const response = await api.get<IAluno[]>("/alunos");
        return response.data;
    },

    getAtivos: async () => {
        const response = await api.get<IAluno[]>("/alunos/ativos");
        return response.data;
    },

    create: async (aluno: IAluno) => {
        const response = await api.post<IAluno>("/alunos", aluno);
        return response.data;
    },

    update: async (aluno: IAluno) => {
        const response = await api.put<IAluno>(`/alunos/${aluno.id}`, aluno);
        return response.data;
    },

    delete: async (id: number) => {
        await api.delete(`/alunos/${id}`);
    }
};