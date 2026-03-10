package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.repository.GastoRepository;
import org.springframework.stereotype.Service;

@Service
public class GastoService {

    private GastoRepository gastoRepository;

    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }
}
