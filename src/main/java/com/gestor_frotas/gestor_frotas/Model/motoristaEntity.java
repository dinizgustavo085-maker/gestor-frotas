package com.gestor_frotas.gestor_frotas.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
public class motoristaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // faz a validação do cpf para seja igual a 14 caracteres digitados
    @CPF(message = "CPF inválido")
    @Column(length = 14, unique = true)
    private String cpf;

    private String nome;
    private String categoria_cnh;
    private String validade_cnh;

    // transforma em json com isso tenho um formato de atributo pré-defido
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ("yyyy/MM/dd"))
    @Column(name = "data_nascimento")
    private LocalDate data_nascimento;

    private Integer telefone;
    private String email;

    public motoristaEntity(){
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

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
