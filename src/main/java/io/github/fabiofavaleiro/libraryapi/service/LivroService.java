package io.github.fabiofavaleiro.libraryapi.service;

import io.github.fabiofavaleiro.libraryapi.model.Livro;
import io.github.fabiofavaleiro.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {


    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return  repository.findById(id);
    }

}
