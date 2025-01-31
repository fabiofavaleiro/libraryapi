package io.github.fabiofavaleiro.libraryapi.repository;

import io.github.fabiofavaleiro.libraryapi.model.Autor;
import io.github.fabiofavaleiro.libraryapi.model.GeneroLivro;
import io.github.fabiofavaleiro.libraryapi.model.Livro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;



@SpringBootTest
class LivroRepositoryTest {


    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("98745-98521");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        //Autor autor = autorRepository.findById(UUID.fromString("0dc63ba1-d7e3-4022-a9d9-cf1739ec59f5")).orElse(null);

        //livro.setAutor(autor);

        repository.save(livro);
    }


    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("98745-98521");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951,1,31));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorLivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("98745-98521");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951,1,31));

        autorRepository.save(autor);


        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutrDoLivro(){
        UUID id = UUID.fromString("fbbf7b73-4628-41aa-9c52-8e9c468e385e");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("7260b25a-c5ca-447d-89c3-30b05980d5aa");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("8e96227d-eb6c-418e-bfa9-8264be8c6b3b");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade(){
        UUID id = UUID.fromString("d99efe6e-7d1c-4f3a-ad22-9898343035a8");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("8e96227d-eb6c-418e-bfa9-8264be8c6b3b");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquesaPorTituloTest(){
        List<Livro> livros = repository.findByTitulo("UFO");
        livros.forEach(System.out::println);
    }

    @Test
    void pesquesaPorIsbn(){
        List<Livro> livros = repository.findByIsbn("98745-98521");
        livros.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloEPreco(){

        var preco = BigDecimal.valueOf(100.00);
        String tituloPesquisa= "UFO";

        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);

    }

    @Test
    void pesquisaProTituloOuIsbn(){
        String tituloPesquisa = "O roubo da casa assombrada";
        String isbn = "20745-98521";

        List<Livro> livroList = repository.findByTituloOrIsbn(tituloPesquisa, isbn);
        livroList.forEach(System.out::println);

    }

    @Test
    void pesquisaLivrosComQuery(){

        var resultado = repository.listarTodosOrdenadoProTituloEPreco();
        resultado.forEach(System.out::println);

    }

    @Test
    void pesqusarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void pesquisaNomesDiferentesLivros(){
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros(){
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarProGeneroQueryParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.FICCAO, "dataPublicacao");
                resultado.forEach(System.out::println);
    }

    @Test
    void listarProGeneroPositionalParamTest(){
        var resultado = repository.findByGeneroPositionalParameters(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }


    @Test
    void deleteByGeneroTest(){
        repository.deleteByGenero(GeneroLivro.FICCAO);
    }

    @Test
    void updateDataPublicacaoTest(){
        repository.updateDataPublicacao(LocalDate.of(2000, 1, 1));
    }
}

