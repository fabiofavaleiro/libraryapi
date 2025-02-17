package io.github.fabiofavaleiro.libraryapi.controller.dto;

import io.github.fabiofavaleiro.libraryapi.model.Autor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Autor")
public record AutorDTO(
        UUID id,
        @NotBlank(message = "O 'nome' é um campo obrigatorio!")
        @Size(min = 2,max = 100, message = "Valor informado para 'nome' é maior ou menior que o tamanho permitido!")
        @Schema(name = "nome")
        String nome,
        @NotNull(message = "A 'data de nascimento' é um campo obrigatorio!")
        @Past(message = "'Data de nascimento' precisa estar no passado!")
        @Schema(name = "dataNascimento")
        LocalDate dataNascimento,
        @NotBlank(message = "A 'nacionalidade' é um campo obrigatorio!")
        @Size(min = 2,max = 100, message = "Valor informado para 'nacionalidade' é maior ou menior que o tamanho permitido!")
        @Schema(name = "nacionalidade")
        String nacionalidade

)
{

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);

        return autor;
    }
}
