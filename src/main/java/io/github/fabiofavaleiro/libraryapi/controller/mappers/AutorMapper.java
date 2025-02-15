package io.github.fabiofavaleiro.libraryapi.controller.mappers;


import io.github.fabiofavaleiro.libraryapi.controller.dto.AutorDTO;
import io.github.fabiofavaleiro.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(source = "nome",target = "nome")//para mapear atributos que estão com nomes diferentes
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);

}
