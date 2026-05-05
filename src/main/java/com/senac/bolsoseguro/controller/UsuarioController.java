package com.senac.bolsoseguro.controller;

import com.senac.bolsoseguro.dto.Request.UsuarioDTORequest;
import com.senac.bolsoseguro.entity.Usuario;
import com.senac.bolsoseguro.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody UsuarioDTORequest dto) {
        Usuario novoUsuario = usuarioService.criarUsuario(dto);
        return ResponseEntity.ok(novoUsuario);
    }
}
