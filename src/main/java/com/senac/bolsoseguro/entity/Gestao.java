package com.senac.bolsoseguro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Gestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gestao_id",nullable = false)
    private int id;
    @Column(name = "gestao_nome",nullable = false)
    private String nome;
    @Column(name = "gestao_valor_estimado",nullable = false)
    private double valorEstimado;
    @Column(name = "gestao_periodo",nullable = false)
    private int periodo;
    @Column(name = "gestao_inicio",nullable = false)
    private LocalDate dataInicio;
    @Column(name = "gestao_fim",nullable = false)
    private LocalDate dataFim;
    @Column(name = "gestao_data_criacao",nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "gestao_status",nullable = false)
    private int status;

    @Transient
    private String usuarioNome;

    @OneToMany(mappedBy = "gestao")
    private List<Gasto> gastos;

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    @JsonIgnore
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

    public double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
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
