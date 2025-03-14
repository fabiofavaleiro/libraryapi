package io.github.fabiofavaleiro.libraryapi.service;

import io.github.fabiofavaleiro.libraryapi.exception.OperacaoNaoPermitidaException;
import io.github.fabiofavaleiro.libraryapi.model.Autor;
import io.github.fabiofavaleiro.libraryapi.model.Usuario;
import io.github.fabiofavaleiro.libraryapi.repository.AutorRepository;
import io.github.fabiofavaleiro.libraryapi.repository.LivroRepository;
import io.github.fabiofavaleiro.libraryapi.security.SecurityService;
import io.github.fabiofavaleiro.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;
    private final SecurityService securityService;


    public Autor salvar(Autor autor){
        validator.validar(autor);
        Usuario usuario = securityService.obterUsuarioLogado();
        autor.setUsuario(usuario);
        return repository.save(autor);
    }

    public void atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar é necessario que o autor ja esteja cadastrado");

        }
        validator.validar(autor);
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Autor autor){
        if (possuiLivro(autor)){
            System.out.println("O autor possui livro(s) Cadastrado(s), por isso não é permitido apaga-lo");
            throw new OperacaoNaoPermitidaException("O autor possui livro(s) Cadastrado(s), por isso não é permitido apaga-lo");
        }
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

    public List<Autor> pesquisaByExample(String nome, String nacionalidade){
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Autor> autorExample = Example.of(autor,matcher);
        return repository.findAll(autorExample);

    }

    public boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
