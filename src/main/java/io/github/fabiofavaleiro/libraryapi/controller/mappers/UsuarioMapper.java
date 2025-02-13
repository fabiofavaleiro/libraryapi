package io.github.fabiofavaleiro.libraryapi.controller.mappers;


import io.github.fabiofavaleiro.libraryapi.controller.dto.UsuarioDTO;
import io.github.fabiofavaleiro.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDTO dto);
}
