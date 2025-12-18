package com.escola.Turma.dto;

//Utilizado record para tornar implicita a presença de getters e setters
//Dto de resposta para login realizado com sucesso, retornando apenas o token jwt
public record LoginResponseDTO(
    String token
) {}
