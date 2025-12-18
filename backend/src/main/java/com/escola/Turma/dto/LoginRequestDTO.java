package com.escola.Turma.dto;

//Utilizado record para tornar implicita a presença de getters e setters
//dto de requisição feita para validar login
public record LoginRequestDTO(
    String usuario,
    String senha
) {}
