package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.dto.Request.CategoriaDTORequest;
import com.senac.bolsoseguro.dto.Response.CategoriaDTOResponse;
import com.senac.bolsoseguro.entity.Categoria;
import com.senac.bolsoseguro.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    // Construtor padrão ouro
    public CategoriaService(CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    public CategoriaDTOResponse criarCategoria(CategoriaDTORequest dto) {
        // 1. Converte DTO para a Entidade Categoria
        Categoria categoria = modelMapper.map(dto, Categoria.class);

        // 2. Salva no banco de dados (o ID nasce aqui)
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        // 3. Retorna o DTO de resposta preenchido
        return modelMapper.map(categoriaSalva, CategoriaDTOResponse.class);
    }

    public List<Categoria> listarCategorias(){return categoriaRepository.findAll();}
}