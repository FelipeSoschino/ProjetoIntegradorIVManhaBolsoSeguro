package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.repository.GestaoRepository;
import org.springframework.stereotype.Service;

@Service
public class GestaoService {

    private GestaoRepository gestaoRepository;

    public GestaoService(GestaoRepository gestaoRepository) {
        this.gestaoRepository = gestaoRepository;
    }
}
