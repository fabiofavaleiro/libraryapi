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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;


    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951,1,31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("62395860-30f9-4060-adc6-ee18e289499f");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960,1,30));
            repository.save(autorEncontrado);
        }


    }

    @Test
    public void listarTest(){
        List<Autor> list = repository.findAll();
        list.forEach(System.out::println);

    }

    @Test
    public void  countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("e9af0271-ea3a-408d-a7e2-65132cca5ced");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("e7f78376-ac6a-48bb-8cd1-bf4df12cc9a0");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americado");
        autor.setDataNascimento(LocalDate.of(1970,8,5));


        Livro livro = new Livro();
        livro.setIsbn("20745-98521");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O roubo da casa assombrada");
        livro.setDataPublicacao(LocalDate.of(1999,1,2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("20745-98521");
        livro2.setPreco(BigDecimal.valueOf(999));
        livro2.setGenero(GeneroLivro.BIOGRAFIA);
        livro2.setTitulo("O roubo da casa assombrada");
        livro2.setDataPublicacao(LocalDate.of(2000,2,3));
        livro2.setAutor(autor);


        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        //livroRepository.saveAll(autor.getLivros());
    }


    //@Transactional
    @Test
    void listarLirosAutor(){
        var id = UUID.fromString("3eb6d721-10c2-419e-8409-dd34cd406fb6");
        var autor = repository.findById(id).get();

        List<Livro> listaLivros = livroRepository.findByAutor(autor);
        autor.setLivros(listaLivros);

        autor.getLivros().forEach(System.out::println);
    }


}
