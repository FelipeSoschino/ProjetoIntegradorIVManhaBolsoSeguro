package com.senac.bolsoseguro.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private int id;
    @Column(name = "usuario_nome",nullable = false)
    private String nome;
    @Column(name = "usuario_cpf",nullable = false)
    private String cpf;
    @Column(name = "usuario_email",nullable = false)
    private String email;
    @Column(name = "usuario_senha_hash",nullable = false)
    private String senha;
    @Column(name = "usuario_data_criacao",nullable = false)
    private LocalDateTime data;
    @Column(name = "usuario_status",nullable = false)
    private int status;

    @OneToMany(mappedBy = "usuario")
    private List<Gestao> gestoes;

    public List<Gestao> getGestoes() {
        return gestoes;
    }

    public void setGestoes(List<Gestao> gestoes) {
        this.gestoes = gestoes;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
