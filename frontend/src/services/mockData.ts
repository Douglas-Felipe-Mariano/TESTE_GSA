import { IAluno } from "../types/IAluno";
import { ITurma } from "../types/ITurma";

// Lista fictícia de Turmas
export const MOCK_TURMAS: ITurma[] = [
    { id: 1, descricao: "1º Ano A - Matutino" },
    { id: 2, descricao: "2º Ano B - Vespertino" },
    { id: 3, descricao: "3º Ano C - Noturno" }
];

// Lista fictícia de Alunos (já tínhamos esta)
export const MOCK_ALUNOS: IAluno[] = [
    {
        id: 1,
        nome: "João Silva",
        dataNascimento: "2010-05-15",
        endereco: "Rua das Flores, 10",
        ativo: true,
        turmaId: 1,
        nomeTurma: "1º Ano A - Matutino"
    },
    {
        id: 2,
        nome: "Maria Oliveira",
        dataNascimento: "2011-02-20",
        endereco: "Av. Principal, 500",
        ativo: true,
        turmaId: 1,
        nomeTurma: "1º Ano A - Matutino"
    },
    {
        id: 3,
        nome: "Pedro Santos",
        dataNascimento: "2009-11-10",
        endereco: "Travessa Torta, 8",
        ativo: false, 
        turmaId: 2,
        nomeTurma: "2º Ano B - Vespertino"
    }
];