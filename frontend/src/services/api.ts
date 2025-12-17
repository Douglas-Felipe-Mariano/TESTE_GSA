import axios from "axios";
import { AuthService } from "./AuthService";
import { error } from "console";

const api = axios.create({
    baseURL: 'http://localhost:8080/api'
});

api.interceptors.request.use((config) => {
    //Pega o token salvo
    const token = AuthService.getToken();

    if (token){
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
}, (error) => {
    return Promise.reject(error);
});

api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response && (error.response.status === 401 || error.response.status === 403 )){
            //Token invalido ou expirado
            AuthService.logout();
            window.location.href = '/login'
        }
        return Promise.reject(error);
    }
)

export default api;