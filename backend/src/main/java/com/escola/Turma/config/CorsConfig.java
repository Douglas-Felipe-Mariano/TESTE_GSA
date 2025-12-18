package com.escola.Turma.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    //Cria o bean de configuração do CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        //configuração do CORS
        CorsConfiguration config = new CorsConfiguration();
        //define qual origem pode acessar a api
        config.setAllowedOrigins(List.of("http://localhost:3000"));

        //define os métodos permitidos
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));

        //permite todos os cabeçalhos
        config.setAllowedHeaders(List.of("*"));

        //Permite envio de cookies e credenciais na requisição
        config.setAllowCredentials(true);

        //registra a configuração para todas as rotas da api
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        //retorna a configuração
        return source;
    }
}