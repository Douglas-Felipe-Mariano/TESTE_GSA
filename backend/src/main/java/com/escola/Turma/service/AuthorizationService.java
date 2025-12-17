package com.escola.Turma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.escola.Turma.model.Usuario;
import com.escola.Turma.repository.UsuarioRepository;

@Service
public class AuthorizationService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return usuarioRepository.findByUserName(userName)
                                .orElseThrow(() -> new RuntimeException("Usuario Não Encontrado!"));
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        
        String senhaHash = passwordEncoder.encode(usuario.getPassword());
        usuario.setSenha(senhaHash);

        return usuarioRepository.save(usuario);
    }

}
