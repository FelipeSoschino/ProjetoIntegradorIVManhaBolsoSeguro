package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        
        this.categoriaRepository = categoriaRepository;
    }
}
