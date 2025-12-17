import React from 'react';
import './App.css';
import { ListaAlunos } from './pages/Alunos/ListaAlunos';
import { BrowserRouter, Link, Route, Routes, useLocation, useNavigate } from 'react-router-dom';
import { ListaTurmas } from './pages/Turmas/ListaTurmas';
import { Login } from './pages/Login/Login';
import { AuthService } from './services/AuthService';

const LayoutMenu = () => {
  const navigate = useNavigate();
  const location = useLocation();

  if (location.pathname === '/login' || location.pathname === "/"){
    return null;
  }

  const handleLogout = () => {
    AuthService.logout();
    navigate('/login');
  }

  return (
        <nav className="navbar">
            <div className="nav-brand">Sistema Escolar</div>
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


function App() {
  return (
    <BrowserRouter>
    
      <LayoutMenu/>
      
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
