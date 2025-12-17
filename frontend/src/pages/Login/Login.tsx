import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthService } from "../../services/AuthService";
import './Login.css';

export const Login: React.FC = () => {
    const navigate = useNavigate();

    //Parametros de entrada
    const [usuario, setUsuario] = useState("");
    const [senha, setSenha] = useState("");

    //Estados de erro
    const [erro, setErro] = useState("");
    const [carregando, setCarregando] = useState(false);

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        setErro("");
        setCarregando(true);

        try{
            const response = await AuthService.login({usuario, senha});

            //salva o token JWT vindo do backend
            const {token} = response.data;
            AuthService.setToken(token);

            //leva o usuario para a lista de alunos
            navigate('/alunos');

        } catch (error: any) {
            console.error(error);
            if (error.response?.status === 403 || error.reponse?.status === 401){
                setErro("Usuario ou Senha Incorretos.")
            } else {
                setErro("Erroa ao conectar com o servidor. Tente mais tarde")
            }
        } finally {
            setCarregando(false);
        }
    };

    return(
        <div className="login-container">
            <div className="login-card">
                <h2>Login</h2>

                {erro && <div className="error-massage">{erro}</div>}

                <form onSubmit={handleLogin}>
                    <div className="form-group">
                        <label htmlFor="Usuario">Usuario</label>
                        <input
                            id="usuario"
                            className="form-control"
                            placeholder="user"
                            value={usuario}
                            onChange={(e) => setUsuario(e.target.value)}    
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="senha">Senha</label>
                        <input 
                            id="senha"
                            type="password" 
                            className="form-control"
                            placeholder="******"
                            value={senha}
                            onChange={(e) => setSenha(e.target.value)}
                            required
                        />
                    </div>

                    <button type="submit" className="btn-login" disabled={carregando}>
                        {carregando ? "Entrando..." : "Entrar"}
                    </button>
                </form>
            </div>
        </div>
    )

}