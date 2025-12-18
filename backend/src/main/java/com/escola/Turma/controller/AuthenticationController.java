package com.escola.Turma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.Turma.dto.LoginRequestDTO;
import com.escola.Turma.dto.LoginResponseDTO;
import com.escola.Turma.model.Usuario;
import com.escola.Turma.service.AuthorizationService;
import com.escola.Turma.service.TokenService;

import jakarta.validation.Valid;

@RestController //Define que é um controlador REST
@RequestMapping("/api/auth") //Define a rota que receberá as requisições
@CrossOrigin(origins = "*") //Configura e informa o CORS que pode receber requisições de qualquer url
public class AuthenticationController {

     //Injeta o gerenciador de autenticação , para validar o usuario e autenticalo em caso de sucesso no login
    @Autowired
    private AuthenticationManager authenticationManager;

    //Injeta o serviço de token para poder gerar um token 
    @Autowired
    private TokenService tokenService;

    // Injeta o serviço de autorização para utilizar os métodos com as regras de negócio
    @Autowired
    private AuthorizationService authorizationService;

    //Método para tentativa de login
    //Em caso de sucesso retorna o token
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data){
        try
        {
            //Encapsula os parametros de login em um objeto
            var userNameSenha = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
    
            //Valida a senha, se estiver errada retorna um erro 403
            Authentication auth = this.authenticationManager.authenticate(userNameSenha);
    
            //gera o token
            var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
    
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    //Método para cadastro de usuario
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Usuario data){
        authorizationService.cadastrarUsuario(data);

        return ResponseEntity.ok().build();
    }
}
