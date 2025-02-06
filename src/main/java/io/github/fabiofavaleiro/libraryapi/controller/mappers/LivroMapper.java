package io.github.fabiofavaleiro.libraryapi.controller.mappers;

import io.github.fabiofavaleiro.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.fabiofavaleiro.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.fabiofavaleiro.libraryapi.model.Livro;
import io.github.fabiofavaleiro.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.Expression;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;


    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTo(Livro livro);
}
