import api from "./api";

export interface LoginDTO {
    usuario: string;
    senha: string;
}

export interface TokenResponse {
    token: string;
}

export const AuthService = {
    login: async (credenciais: LoginDTO) => {
        return api.post<TokenResponse>("/api/auth/login", credenciais)
    },

    setToken: (token: string) => {
        localStorage.setItem('token', token);
    },

    getToken: () => {
        return localStorage.getItem('token');
    },

    logout: () => {
        localStorage.removeItem('token');
    },

    isAuthenticated: () =>  {
        return !!localStorage.getItem('token')
    }
};