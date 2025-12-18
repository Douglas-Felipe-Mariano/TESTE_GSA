import React from 'react';
import './App.css';
import { ListaAlunos } from './pages/Alunos/ListaAlunos';
import { BrowserRouter, Link, Route, Routes, useLocation, useNavigate } from 'react-router-dom';
import { ListaTurmas } from './pages/Turmas/ListaTurmas';
import { Login } from './pages/Login/Login';
import { AuthService } from './services/AuthService';

import LogoEscola from './assets/LogoEscola.svg';

//menu no header da pagina para navegação entre as rotas 
const LayoutMenu = () => {
  const navigate = useNavigate();
  const location = useLocation();

  //inicia a aplicação na tela de login
  if (location.pathname === '/login' || location.pathname === "/"){
    return null;
  }

  //Método de Logout
  const handleLogout = () => {
    AuthService.logout();
    navigate('/login');
  }

  return (
        <nav className="navbar">
            <div className="nav-brand">
              <img src={LogoEscola} alt="Logo Sistema Escolar" style={{ height: '35px' }} />
              Sistema Escolar</div>
            <div className="nav-links">
                <Link to="/alunos" className="nav-link">Alunos</Link>
                <Link to="/turmas" className="nav-link">Turmas</Link>
                <button onClick={handleLogout} className="nav-link btn-sair">
                    Sair
                </button>
            </div>
        </nav>
    );
}

//Raiz do app react, apenas chamas as paginas 
function App() {
  return (
    <BrowserRouter>
    
      <LayoutMenu/>
      3+
      <div className='app-content'>
        <Routes>
          <Route path='/' element={<Login/>} />

          <Route path='/login' element={<Login/>} />
          <Route path='/alunos' element={<ListaAlunos/>} />
          <Route path='/turmas' element={<ListaTurmas/>} />
          
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
