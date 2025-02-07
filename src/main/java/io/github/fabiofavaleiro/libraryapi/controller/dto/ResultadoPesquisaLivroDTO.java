package io.github.fabiofavaleiro.libraryapi.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaLivroDTO(
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        BigDecimal preco,
        UUID idAutor
) { }
