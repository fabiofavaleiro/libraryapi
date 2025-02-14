package io.github.fabiofavaleiro.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "O campo \"login\" é obrigadorio!")
        String login,
        @Email (message = "E-mail invalido")
        @NotBlank(message = "O campo \"E-mail\" é obrigadorio!")
        String email,
        @NotBlank(message = "O campo \"senha\" é obrigadorio!")
        String senha,
        List<String> roles
    ) {
}
