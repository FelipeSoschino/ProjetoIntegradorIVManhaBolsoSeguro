package com.senac.bolsoseguro.controller;

import com.senac.bolsoseguro.dto.Request.CategoriaDTORequest;
import com.senac.bolsoseguro.dto.Response.CategoriaDTOResponse;
import com.senac.bolsoseguro.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {
    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<CategoriaDTOResponse> criar(@Valid @RequestBody CategoriaDTORequest dto) {
        CategoriaDTOResponse response = this.categoriaService.criarCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
