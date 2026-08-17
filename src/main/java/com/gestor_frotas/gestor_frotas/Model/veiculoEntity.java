package com.gestor_frotas.gestor_frotas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class veiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String tipo;
    private String cor;
    private Integer quilometragem;
    private String combustivel;
    private String status;
    private String tipo_caminhao;
    private Integer capacidade_carga_kg;
    private Integer quantidade_eixos;
    private String tipo_carroceria;


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo_caminhao() {
        return tipo_caminhao;
    }

    public void setTipo_caminhao(String tipo_caminhao) {
        this.tipo_caminhao = tipo_caminhao;
    }

    public Integer getCapacidade_carga_kg() {
        return capacidade_carga_kg;
    }

    public void setCapacidade_carga_kg(Integer capacidade_carga_kg) {
        this.capacidade_carga_kg = capacidade_carga_kg;
    }

    public Integer getQuantidade_eixos() {
        return quantidade_eixos;
    }

    public void setQuantidade_eixos(Integer quantidade_eixos) {
        this.quantidade_eixos = quantidade_eixos;
    }

    public String getTipo_carroceria() {
        return tipo_carroceria;
    }

    public void setTipo_carroceria(String tipo_carroceria) {
        this.tipo_carroceria = tipo_carroceria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
