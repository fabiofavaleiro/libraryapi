package io.github.fabiofavaleiro.libraryapi.service;

import io.github.fabiofavaleiro.libraryapi.model.Autor;
import io.github.fabiofavaleiro.libraryapi.model.GeneroLivro;
import io.github.fabiofavaleiro.libraryapi.model.Livro;
import io.github.fabiofavaleiro.libraryapi.repository.AutorRepository;
import io.github.fabiofavaleiro.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar(){


        Autor autor = new Autor();
        autor.setNome("Fulano");
        autor.setNacionalidade("Basileiro");
        autor.setDataNascimento(LocalDate.of(1992, 1,3));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("98746-98521");
        livro.setPreco(BigDecimal.valueOf(110));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFULOGIA");
        livro.setDataPublicacao(LocalDate.of(1991,2,3));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("José")){
            throw new RuntimeException("Rollback");
        }

    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("68c22eb4-ca4e-4d93-9c15-d91585f39c53")).orElse(null);
        livro.setDataPublicacao(LocalDate.of(2024, 6, 1));
    }
}
