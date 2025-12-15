import React, {useState,useEffect} from "react";
import { IAluno } from "../../types/IAluno";
import { MOCK_ALUNOS } from "../../services/mockData";

export const ListaALunos = () => {
    const [alunos, setAlunos] = useState<IAluno[]>([]);

    useEffect(() => {
        // Simulando a obtenção dos dados (substitua pelo serviço real se necessário)
        setAlunos(MOCK_ALUNOS);
    }, []);

    return (
        <div c

    )
}