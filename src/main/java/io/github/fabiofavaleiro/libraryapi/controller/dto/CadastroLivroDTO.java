package io.github.fabiofavaleiro.libraryapi.controller.dto;




import io.github.fabiofavaleiro.libraryapi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(
        @ISBN
        @NotBlank(message = "O Campo isbn é obrigatorio")
        String isbn,
        @NotBlank(message = "O Campo titulo é obrigatorio")
        String titulo,
        @NotNull(message = "O Campo data de publicação é obrigatorio")
        @Past(message = "Caro usuraio, a data informada é posterior a hora configurada neste computador")
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        @NotNull(message = "O Campo identificação do autor é obrigatorio")
        UUID idAutor
        ) {
}
