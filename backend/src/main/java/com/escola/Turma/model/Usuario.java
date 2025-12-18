package com.escola.Turma.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails {

    @Id //Informa que o campo é um ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Define que o ID será gerado automaticamente pelo banco de dados
    @Column(name = "UsuarioId")                         // Informa que o campo refere-se a uma coluna da tabela
    private Integer id;                                 // Atributo convencional da classe java.

    @Column(name = "UsusarioNome", length = 255, unique = true, nullable = false)
    private String userName;

    @Column(name = "Senha", length = 255 ,nullable = false)
    private String senha;


    public Usuario() {}

    public Usuario(String userName, String senha) {
        this.userName = userName;
        this.senha = senha;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha; 
    }

    @Override
    public String getUsername() {
        return userName; 
    }

    @Override
    public boolean isAccountNonExpired() {
      return true; 
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired() { 
        return true; 
    }

    @Override
    public boolean isEnabled() { 
        return true; 
    }
}