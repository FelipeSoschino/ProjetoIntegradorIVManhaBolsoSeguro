package com.senac.bolsoseguro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gasto_id",nullable = false)
    private int id;
    @Column(name = "gasto_nome",nullable = false)
    private String nome;
    @Column(name = "gasto_valor",nullable = false)
    private double valor;
    @Column(name = "gasto_descricao")
    private String descricao;
    @Column(name = "gasto_data_criacao",nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "gasto_status",nullable = false)
    private int status;


    @Transient
    private String gestaoNome;

    @Transient
    private String categoriaNome;


    @ManyToOne
    @JoinColumn(name = "categoria_id",nullable = false)
    @JsonIgnore
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "gestao_id",nullable = false)
    @JsonIgnore
    private Gestao gestao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
