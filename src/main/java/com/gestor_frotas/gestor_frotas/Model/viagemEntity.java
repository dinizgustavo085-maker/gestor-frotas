package com.gestor_frotas.gestor_frotas.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class viagemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // transforma em json e faz a pré-defido o valor que devo colocar de data/hora
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ("yyyy/MM/dd  HH:mm"))
    @Column(name = "data_saida")
    private LocalDate data_saida;

    // transforma em json e faz a pré-defido o valor que devo colocar de data/hora
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ("yyyy/MM/dd  HH:mm"))
    @Column(name = "data_estimada")
    private LocalDate data_entrada;

    private String origem;
    private String destino;
    private String status;
    private String distancia;

    // varivel para calcular o tempo estimado
    @Column(name = "tempo_estimado")
    private LocalDate tempo_estimado;



    private String observacao;

    public viagemEntity() {
    }

    // para o calculo o tempo estimado com data e hora para o futuro
//    public String calcularTempoEstimado(){
//        if (data_entrada == null && data_saida == null ){
//            return "A data entrada e data saida não podem ser a anterior";
//        }
//        return
//
//    }

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

    public LocalDate getData_saida() {
        return data_saida;
    }

    public void setData_saida(LocalDate data_saida) {
        this.data_saida = data_saida;
    }

    public LocalDate getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDate data_entrada) {
        this.data_entrada = data_entrada;
    }

    public LocalDate getTempo_estimado() {
        return tempo_estimado;
    }

    public void setTempo_estimado(LocalDate tempo_estimado) {
        this.tempo_estimado = tempo_estimado;
    }
}
