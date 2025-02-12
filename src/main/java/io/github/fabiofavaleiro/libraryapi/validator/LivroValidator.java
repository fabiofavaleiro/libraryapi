package io.github.fabiofavaleiro.libraryapi.validator;


import io.github.fabiofavaleiro.libraryapi.exception.CampoInvalidoException;
import io.github.fabiofavaleiro.libraryapi.exception.RegistroDuplicadoException;
import io.github.fabiofavaleiro.libraryapi.model.Livro;
import io.github.fabiofavaleiro.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private static final int ANO_EXIGENCIA_PRECO = 2020;

    private final LivroRepository repository;

    public void validar(Livro livro){
        if(esxisteLivroComIsbn(livro)){
            throw new RegistroDuplicadoException("Nobre utilizador deste sistema, o livro que quer cadastrar já possui ISBN em nossa base de dados!");
        }

        if(isPrecoObrigatorioNulo(livro)){
            throw new CampoInvalidoException("preco","Caro usuario deste sistema: Quando um livro tem data de publuicação a partir de 2020 o preço é obrigatorio!");
        }
    }



    private boolean isPrecoObrigatorioNulo(Livro livro) {
        return livro.getPreco() == null && (livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO);
    }


    private boolean esxisteLivroComIsbn(Livro livro){
        Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());

        if(livro.getId() == null){
            return livroEncontrado.isPresent();
        }
        return livroEncontrado.map(Livro::getId).stream().anyMatch(id-> !id.equals(livro.getId()));
    }


}
