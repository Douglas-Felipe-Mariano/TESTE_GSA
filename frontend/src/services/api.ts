import axios from "axios";
import { AuthService } from "./AuthService";

//configura a URL base da api backend
const api = axios.create({
    baseURL: 'http://localhost:8080'
});

//Interceptador de requisição, é executado antes de qualquer requisição para validar a autenticação de usuario
api.interceptors.request.use((config) => {
    //Pega o token salvo
    const token = AuthService.getToken();

    // Caso o token exista, adiciona no header o authorization
    if (token){
        config.headers.Authorization = `Bearer ${token}`;
    }

    //retorna a configuração para que o frontend prossiga com a requisição
    return config;
}, (error) => {
    return Promise.reject(error);
});


//Interceptador de resposta, executa sempre após a resposta do backend
api.interceptors.response.use(
    //se a resposta for bem sucedida apenas retorna ela
    (response) => response,
    (error) => {
        //verifica se há erro de autenticação
        if (error.response && (error.response.status === 401 || error.response.status === 403 )){
            //caso haja realiza logout do usuario
            AuthService.logout();
        }

        // Propaga o erro para ser tratado onde a requisição foi feita
        return Promise.reject(error);
    }
)

//exporta a instancia axios para ser usada em toda aplicação 
export default api;