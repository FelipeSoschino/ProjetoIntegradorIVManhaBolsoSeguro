package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.entity.Categoria;
import com.senac.bolsoseguro.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }
}
