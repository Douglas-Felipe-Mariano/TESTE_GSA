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

    //Injeta o usuario repository
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Injeta o password encoder para criptografar a senha (gerar um HASH)
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Metodo sobrescrito, assinatura da interface implementada (obrigatorio)
    //Busca o usuario no banco para validação 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return usuarioRepository.findByUserName(userName)
                                .orElseThrow(() -> new RuntimeException("Usuario Não Encontrado!"));
    }

    //Método para cadastrar usuario
    public Usuario cadastrarUsuario(Usuario usuario) {
        
        //Recebe a senha pura e transforma a senha em hash
        String senhaHash = passwordEncoder.encode(usuario.getPassword());
        usuario.setSenha(senhaHash);

        return usuarioRepository.save(usuario);
    }

}
