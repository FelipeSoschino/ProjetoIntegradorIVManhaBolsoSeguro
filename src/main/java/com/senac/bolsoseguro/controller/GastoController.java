package com.senac.bolsoseguro.controller;

import com.senac.bolsoseguro.dto.Request.GastoDTORequest;
import com.senac.bolsoseguro.dto.Response.GastoDTOResponse;
import com.senac.bolsoseguro.service.GastoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gasto")
public class GastoController {

    private GastoService gastoService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }
    @PostMapping("/criar/gestao/{gestaoId}/categoria/{categoriaId}")
    public ResponseEntity<GastoDTOResponse> criarGasto(
            @PathVariable int gestaoId,
            @PathVariable int categoriaId,
            @Valid @RequestBody GastoDTORequest dto) {

        // Chama o service passando os dois IDs e o DTO
        GastoDTOResponse response = this.gastoService.criarGasto(gestaoId, categoriaId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/gestao/{gestaoId}")
    public ResponseEntity<List<GastoDTOResponse>> buscarGastosPorGestao(@PathVariable int gestaoId) {
        // Chama o método do service que usa a nossa @Query customizada
        List<GastoDTOResponse> resposta = this.gastoService.listarGastosPorGestaoId(gestaoId);

        // Retorna a lista com o status HTTP 200 OK
        return ResponseEntity.ok(resposta);
    }

}
