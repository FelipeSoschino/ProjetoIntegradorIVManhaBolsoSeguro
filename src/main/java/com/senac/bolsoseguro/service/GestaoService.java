package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.repository.GestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestaoService {

    @Autowired
    private GestaoRepository gestaoRepository;

    public GestaoService(GestaoRepository gestaoRepository) {
        this.gestaoRepository = gestaoRepository;
    }
}
