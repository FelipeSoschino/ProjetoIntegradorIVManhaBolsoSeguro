package com.senac.bolsoseguro.controller;

import com.senac.bolsoseguro.dto.Request.GestaoDTORequest;
import com.senac.bolsoseguro.dto.Response.GestaoDTOResponse;
import com.senac.bolsoseguro.entity.Gestao;
import com.senac.bolsoseguro.service.GestaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gestao")
public class GestaoController {

    private GestaoService gestaoService;

    public GestaoController(GestaoService gestaoService) {
        this.gestaoService = gestaoService;
    }

    @PostMapping("criar/{usuarioId}")
    public ResponseEntity<GestaoDTOResponse> criarGestao(@PathVariable int usuarioId, @Valid @RequestBody GestaoDTORequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.gestaoService.criarGestao(usuarioId,dto));
    }

    @GetMapping("listar/")
    public ResponseEntity<List<Gestao>> listarGestao(){
        return ResponseEntity.ok(this.gestaoService.listarGestao());
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<GestaoDTOResponse> atualizar(@PathVariable int id, @Valid @RequestBody GestaoDTORequest dto) {

        GestaoDTOResponse response = this.gestaoService.atualizarGestao(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deletar/{gestaoId}")
    @Operation(summary = "Remover gestao", description = "Endpoint para remover uma gestao dado seu ID")
    public void apagarGestao(@PathVariable("usuarioId") Integer gestaoId){
        this.gestaoService.apagarGestao(gestaoId);
    }




}
