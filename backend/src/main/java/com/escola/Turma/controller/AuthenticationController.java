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

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data){

        //Encapsula os parametros de login em um objeto
        var userNameSenha = new UsernamePasswordAuthenticationToken(data.userName(), data.senha());

        //Valida a senha, se estiver errada retorna um erro 403
        Authentication auth = this.authenticationManager.authenticate(userNameSenha);

        //gera o token
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Usuario data){
        authorizationService.cadastrarUsuario(data);

        return ResponseEntity.ok().build();
    }
}
