package com.escola.Turma.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.escola.Turma.model.Usuario;
import com.escola.Turma.repository.UsuarioRepository;
import com.escola.Turma.service.AuthorizationService;

@Configuration
public class DataInitializer implements CommandLineRunner {

     // Injeta o repositório de usuários para verificar e persistir dados
    @Autowired
    private UsuarioRepository usuarioRepository;

     // Injeta o serviço de autorização para cadastrar usuários com regras de negócio
    @Autowired
    private AuthorizationService authorizationService;

    //Executa assim que a aplicação inicia, criando usuario e senha imediato
    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe usuário, se não, cria o admin
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUserName("admin");
            admin.setSenha("admin123"); 
            
            //cadastra o usuario usando o serviço de autorização 
            authorizationService.cadastrarUsuario(admin);
        }
    }
}