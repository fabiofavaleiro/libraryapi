package io.github.fabiofavaleiro.libraryapi.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String login;

    @Column
    private String senha;

    @Column(name = "roles")
    private List<String> roles;


}
