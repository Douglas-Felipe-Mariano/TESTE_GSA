package com.escola.Turma.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.escola.Turma.repository.UsuarioRepository;
import com.escola.Turma.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException{

            //tenta recuperar o token do cabeçalho  
            String token = this.recoverToken(request);
            
            //se existir token valida
            if (token != null){
                try {
                    //Pega o username atravéz do token
                    var login = tokenService.getSubject(token);
    
                    //Valida se o usuario existe no banco
                    UserDetails user = usuarioRepository.findByUserName(login).orElse(null);
    
                    //se existir usuario faz a autenticação
                    if (user != null){
                        //Cria a autenticação para o spring security
                        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception ex) {
                    System.out.println("Token Invalido Ignorado " + ex.getMessage());
                }
            }

            filterChain.doFilter(request, response);
    }

    //Limpa o cabeçalho e pega só a string do token
    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}
