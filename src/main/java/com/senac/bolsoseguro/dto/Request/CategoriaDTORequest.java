package com.senac.bolsoseguro.dto.Request;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTORequest {
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    // Getter e Setter
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}

