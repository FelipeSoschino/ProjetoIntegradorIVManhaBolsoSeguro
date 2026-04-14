package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;


    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }
}
