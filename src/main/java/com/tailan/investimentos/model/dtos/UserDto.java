package com.tailan.investimentos.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Schema
public record UserDto(

        @NotBlank(message = "Username é obrigatorio.")
        String username,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail deve ser válido.")
        String email,

        @NotBlank(message = "A senha é obrigatorio.")
        String password) {
}
