package com.gestor_frotas.gestor_frotas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class viagemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer data_saida;
    private Integer data_entrada;
    private String origem;
    private String destino;
    private String status;
    private String distancia;
    private String tempo_estimado;
    private String observacao;


    public Integer getData_saida() {
        return data_saida;
    }

    public void setData_saida(Integer data_saida) {
        this.data_saida = data_saida;
    }

    public Integer getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Integer data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getTempo_estimado() {
        return tempo_estimado;
    }

    public void setTempo_estimado(String tempo_estimado) {
        this.tempo_estimado = tempo_estimado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
