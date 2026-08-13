package com.gestor_frotas.gestor_frotas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class motoristaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cpf;
    private String nome;
    private String categoria_cnh;
    private String validade_cnh;
    private Integer data_nascimento;
    private Integer telefone;
    private String email;

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria_cnh() {
        return categoria_cnh;
    }

    public void setCategoria_cnh(String categoria_cnh) {
        this.categoria_cnh = categoria_cnh;
    }

    public String getValidade_cnh() {
        return validade_cnh;
    }

    public void setValidade_cnh(String validade_cnh) {
        this.validade_cnh = validade_cnh;
    }

    public Integer getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Integer data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
