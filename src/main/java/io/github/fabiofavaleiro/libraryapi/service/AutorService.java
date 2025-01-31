package io.github.fabiofavaleiro.libraryapi.service;

import io.github.fabiofavaleiro.libraryapi.model.Autor;
import io.github.fabiofavaleiro.libraryapi.repository.AutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);
    }

    public void atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar é necessario que o autor ja esteja cadastrado");

        }
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Autor autor){
        repository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
        if((nome != null) && (nacionalidade != null)){
            System.out.println("foi inteiro");
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }
        if (nome != null){
            System.out.println("foi nome");
            return repository.findByNome(nome);
        }
        if (nacionalidade != null){
            System.out.println("foi nacionalidade");
            return repository.findByNacionalidade(nacionalidade);
        }
        return repository.findAll();

    }
}
