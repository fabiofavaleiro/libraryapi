package io.github.fabiofavaleiro.libraryapi.repository;

import io.github.fabiofavaleiro.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;


    //@Transactional // esta anotação fará a abertura da transação e no final do metodo fará p commit ou rollback fechando a transação
    @Test
    void transacaoSimples(){
        transacaoService.executar();
    }

    @Test
    void transacaoEstadomanaged(){
        transacaoService.atualizacaoSemAtualizar();
    }
}
