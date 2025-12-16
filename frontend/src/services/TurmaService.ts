import api from "./api";

export interface TurmaDTO {
    id: number;
    descricao: string;
};

export const getTurmas = async () => {
    return api.get<TurmaDTO[]>( '/turmas' );
};

export const postTurma = async (turma: TurmaDTO) => {
    return api.post('/turmas', turma);
}