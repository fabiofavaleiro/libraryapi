package io.github.fabiofavaleiro.libraryapi.service;

import io.github.fabiofavaleiro.libraryapi.model.Client;
import io.github.fabiofavaleiro.libraryapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    private final PasswordEncoder encoder;

    public Client salvar(Client client){
        var senhaCriptografada = encoder.encode(client.getClientSecret());
        client.setClientSecret(senhaCriptografada);

        return repository.save(client);
    }

    public Client obterPorClienteID(String clientId){
        return repository.findByClientId(clientId);
    }
}
