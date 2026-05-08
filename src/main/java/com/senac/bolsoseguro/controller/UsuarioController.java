package com.senac.bolsoseguro.controller;

import com.senac.bolsoseguro.dto.Request.UsuarioDTORequest;
import com.senac.bolsoseguro.dto.Response.UsuarioDTOResponse;
import com.senac.bolsoseguro.entity.Usuario;
import com.senac.bolsoseguro.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Tag(name="Usuario", description = "API para gerenciamento de usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

//    @PostMapping
//    public ResponseEntity<Usuario> criar(@RequestBody UsuarioDTORequest dto) {
//        Usuario novoUsuario = usuarioService.criarUsuario(dto);
//        return ResponseEntity.ok(novoUsuario);
//    }


    @GetMapping("/listar")
    @Operation(summary = "Listar Usuarios",description = "end poit para listar todos os usuários")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(this.usuarioService.listarUsuarios());

    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioDTOResponse> criar(@Valid @RequestBody UsuarioDTORequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.criarUsuario(dto));
    }
}
