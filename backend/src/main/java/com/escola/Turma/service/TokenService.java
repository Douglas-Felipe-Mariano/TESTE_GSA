package com.escola.Turma.service;

import java.util.Date;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.escola.Turma.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

    //chave da api em forma de string, sendo buscada no application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    //Transforma a chave string para o tipo compativel com JWT
    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // Método para gerar o token JWT
    public String gerarToken(Usuario usuario) {
        try {
            return Jwts.builder()
                       .setIssuer("API Escola")
                       .setSubject(usuario.getUserName())
                       .setExpiration(dataexpiracao())
                       .signWith(getKey(), SignatureAlgorithm.HS256)
                       .compact();
        } catch (RuntimeException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    // Pega o "sujeito" do token (Descobre quem é o usuario)
    public String getSubject(String tokenJWT){
        try {
            String tokenLimpo = tokenJWT.replace("Bearer ", "");

            Claims claims = Jwts.parserBuilder()
                                 .setSigningKey(getKey())
                                 .build()
                                 .parseClaimsJws(tokenLimpo)
                                 .getBody();

            return claims.getSubject();                                 
        } catch (RuntimeException exception){
            throw new RuntimeException("Token JWT invalido ou expirado!");
        }
    }

    //seta o tempo de expiração do token para 24 horas
    private Date dataexpiracao(){
        Date data = new Date(System.currentTimeMillis() + 86400000);
        return data;
    }

}
