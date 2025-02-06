package io.github.fabiofavaleiro.libraryapi.controller;

import io.github.fabiofavaleiro.libraryapi.controller.dto.CadastroLivroDTO;

import io.github.fabiofavaleiro.libraryapi.controller.dto.ErroResposta;
import io.github.fabiofavaleiro.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.fabiofavaleiro.libraryapi.controller.mappers.LivroMapper;
import io.github.fabiofavaleiro.libraryapi.exception.RegistroDuplicadoException;
import io.github.fabiofavaleiro.libraryapi.model.Livro;
import io.github.fabiofavaleiro.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = mapper.toEntity(dto);
        service.salvar(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id)).map(livro -> {
            var dto = mapper.toDTo(livro);
            return ResponseEntity.ok(dto);
        }).orElseGet(() ->ResponseEntity.notFound().build());


    }
}
