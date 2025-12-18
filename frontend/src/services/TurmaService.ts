import api from "./api";

//Dto do response recebido para requisições de turmas 
export interface TurmaDTO {
    id: number;
    descricao: string;
};

//Dto do request enviado para cadastro ou edição de turmas
export interface TurmaCadastroDTO {
    descricao: string;
}

//Todos os metodos http relacionados a turma
export const TurmaService = {
    getAll: async () => {
        return api.get<TurmaDTO[]>("/api/turmas");
    },

    create: async (turma: TurmaCadastroDTO) => {
        return api.post("/api/turmas", turma);
    },

    update: async (id: number, turma: TurmaCadastroDTO) => {
        return api.put(`/api/turmas/${id}`, turma);
    },

    delete: async (id: number) => {
        return api.delete(`/api/turmas/${id}`);
    }
};