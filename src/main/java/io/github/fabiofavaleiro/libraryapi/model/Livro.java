package io.github.fabiofavaleiro.libraryapi.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = " livro")
@Data
public class Livro {

    @Id
    @Column(name = "name")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isben;

    @Column(name = "titulo", length = 150,nullable = false)
    private String titulo;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicaçao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name ="id-autor")
    private Autor autor;
}
