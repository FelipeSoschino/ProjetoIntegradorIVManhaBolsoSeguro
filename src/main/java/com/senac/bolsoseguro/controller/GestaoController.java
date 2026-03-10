package com.senac.bolsoseguro.controller;

import com.senac.bolsoseguro.service.GestaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gestao")
public class GestaoController {

    private GestaoService gestaoService;

    public GestaoController(GestaoService gestaoService) {
        this.gestaoService = gestaoService;
    }
}
