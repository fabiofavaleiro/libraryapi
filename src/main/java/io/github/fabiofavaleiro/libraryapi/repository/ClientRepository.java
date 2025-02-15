package io.github.fabiofavaleiro.libraryapi.repository;

import io.github.fabiofavaleiro.libraryapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByClientId(String clientId);
}
