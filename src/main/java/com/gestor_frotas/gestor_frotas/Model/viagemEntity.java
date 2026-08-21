package com.gestor_frotas.gestor_frotas.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class viagemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer viagem_id;

    // transforma em json e faz a pré-defido o valor que devo colocar de data/hora
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ("yyyy/MM/dd HH:mm"))
    @Column(name = "data_saida")
    private LocalDateTime data_saida;

    // transforma em json e faz a pré-defido o valor que devo colocar de data/hora
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ("yyyy/MM/dd HH:mm"))
    @Column(name = "data_estimada")
    private LocalDateTime data_entrada;

    private Long distancia_metros;
    private Long duracao_segundos;
    private LocalDateTime data_chegada;

    private String origem;
    private String destino;
    private String status;
    private String distancia;

    private Double latitude_origem;
    private Double longitude_origem;
    private Double latitude_destino;
    private Double longitude_destino;

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
//        Duration duracao = Duration.between(data_saida, data_entrada);
//
//         long horas = duracao.toHours();
//         long minutos = duracao.toMinutesPart();
//
//         return horas + "h " + minutos + "min";
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
        return null;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getTempo_estimado() {
        return tempo_estimado;
    }

    public void setTempo_estimado(LocalDate tempo_estimado) {
        this.tempo_estimado = tempo_estimado;
    }

    public LocalDateTime getData_saida() {
        return data_saida;
    }

    public void setData_saida(LocalDateTime data_saida) {
        this.data_saida = data_saida;
    }

    public LocalDateTime getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDateTime data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Integer getViagem_id() {
        return null;
    }

    public void setViagem_id(Integer viagem_id) {
        this.viagem_id = viagem_id;
    }

    public void setLatitde(Double latitude) {
    }

    public Double getLatitude_origem() {
        return latitude_origem;
    }

    public void setLatitude_origem(Double latitude_origem) {
        this.latitude_origem = latitude_origem;
    }

    public Double getLongitude_origem() {
        return longitude_origem;
    }

    public void setLongitude_origem(Double longitude_origem) {
        this.longitude_origem = longitude_origem;
    }

    public Double getLatitude_destino() {
        return latitude_destino;
    }

    public void setLatitude_destino(Double latitude_destino) {
        this.latitude_destino = latitude_destino;
    }

    public Double getLongitude_destino() {
        return longitude_destino;
    }

    public void setLongitude_destino(Double longitude_destino) {
        this.longitude_destino = longitude_destino;
    }

    public Long getDistancia_metros() {
        return distancia_metros;
    }

    public void setDistancia_metros(Long distancia_metros) {
        this.distancia_metros = distancia_metros;
    }

    public Long getDuracao_segundos() {
        return duracao_segundos;
    }

    public void setDuracao_segundos(Long duracao_segundos) {
        this.duracao_segundos = duracao_segundos;
    }

    public LocalDateTime getData_chegada() {
        return data_chegada;
    }

    public void setData_chegada(LocalDateTime data_chegada) {
        this.data_chegada = data_chegada;
    }
}
