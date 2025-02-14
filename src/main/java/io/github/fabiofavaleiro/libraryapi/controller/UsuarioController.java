package io.github.fabiofavaleiro.libraryapi.controller;


import io.github.fabiofavaleiro.libraryapi.controller.dto.UsuarioDTO;
import io.github.fabiofavaleiro.libraryapi.controller.mappers.UsuarioMapper;
import io.github.fabiofavaleiro.libraryapi.model.Usuario;
import io.github.fabiofavaleiro.libraryapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid UsuarioDTO dto){
        var usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }
}
