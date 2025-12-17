package com.escola.Turma.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.escola.Turma.model.Usuario;
import com.escola.Turma.repository.UsuarioRepository;
import com.escola.Turma.service.AuthorizationService;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe usuário, se não, cria o admin
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUserName("admin");
            admin.setSenha("admin123"); 
            
            authorizationService.cadastrarUsuario(admin);
        }
    }
}