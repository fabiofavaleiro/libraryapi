package io.github.fabiofavaleiro.libraryapi.controller;

import io.github.fabiofavaleiro.libraryapi.controller.dto.AutorDTO;
import io.github.fabiofavaleiro.libraryapi.controller.dto.ErroResposta;
import io.github.fabiofavaleiro.libraryapi.controller.mappers.AutorMapper;
import io.github.fabiofavaleiro.libraryapi.exception.OperacaoNaoPermitidaException;
import io.github.fabiofavaleiro.libraryapi.exception.RegistroDuplicadoException;
import io.github.fabiofavaleiro.libraryapi.model.Autor;
import io.github.fabiofavaleiro.libraryapi.model.Usuario;
import io.github.fabiofavaleiro.libraryapi.security.SecurityService;
import io.github.fabiofavaleiro.libraryapi.service.AutorService;
import io.github.fabiofavaleiro.libraryapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController  implements GenericController{

    private final AutorService service;
    //private final SecurityService securityService;
    private final AutorMapper mapper;


    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> salvar(@RequestBody @Valid AutorDTO dto){

        //System.out.println(authentication);
        //UserDetails usuarioLogado = (UserDetails) authentication.getPrincipal();
        //Usuario usuario = usuarioService.obterPorLogin(usuarioLogado.getUsername());


        Autor autor = mapper.toEntity(dto);
        //autor.setIdUsuario(usuario.getId());

        service.salvar(autor);
        URI location = gerarHeaderLocation(autor.getId()); //ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
    public  ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        return service.obterPorId(idAutor).map(autor -> {
            AutorDTO dto = mapper.toDTO(autor); return ResponseEntity.ok(dto);
        }).orElseGet( ()-> ResponseEntity.notFound().build() );


       /* if(autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDTO dto = mapper.toDTO(autor);//new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build(); */
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
    public ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(value = "nome", required = false) String nome,@RequestParam(value = "nacionalidade", required = false) String nacionalidade){
        List<Autor> resultado = service.pesquisaByExample(nome, nacionalidade);
        List<AutorDTO> lista = resultado.stream().map(mapper :: toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public  ResponseEntity<Void> atualizar(@PathVariable("id") String id, @RequestBody @Valid Autor dto){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var autor = autorOptional.get();
        autor.setNome(dto.getNome());
        autor.setNacionalidade(dto.getNacionalidade());
        autor.setDataNascimento(dto.getDataNascimento());

        service.atualizar(autor);

        return ResponseEntity.noContent().build();
    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErroResposta handleErrosNaoTratados(RuntimeException e){
//        var message = "Ocorreu um erro inesperado, favor abra um chamamdo em nosso portal //link//, e aguarde!";
//        return new ErroResposta((HttpStatus.INTERNAL_SERVER_ERROR.value()),message,List.of());
//    }

}
