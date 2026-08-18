package com.gestor_frotas.gestor_frotas.Service;

import com.gestor_frotas.gestor_frotas.Model.viagemEntity;
import com.gestor_frotas.gestor_frotas.Repository.viagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    private final viagemRepository viagemRepository;

    // puxando o repository
    public ViagemService(viagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    // salva as valicações
    @Transactional
    public viagemEntity salvarViagemCompleta (viagemEntity viagemValidacao){
        validarDatas(viagemValidacao);
        validarStatus(viagemValidacao);
        validarRota(viagemValidacao);

        return viagemRepository.save(viagemValidacao);
    }

    // faz a validação da data_saida e data_entrada
    private void validarDatas(viagemEntity viagemData) {
        if (viagemData.getData_saida() == null || viagemData.getData_entrada() == null) {
            throw new RuntimeException("Data de saída e data de entrada são obrigatórias");
        }

        if (viagemData.getData_entrada().isBefore(viagemData.getData_saida())) {
            throw new RuntimeException("Data de entrada não pode ser antes da data de saída");
        }
    }

    // faz a validação do destino e origem
    private void validarRota(viagemEntity viagemRotas) {
        if (viagemRotas.getOrigem() == null || viagemRotas.getOrigem().isBlank()) {
            throw new RuntimeException("Origem é obrigatória");
        }

        if (viagemRotas.getDestino() == null || viagemRotas.getDestino().isBlank()) {
            throw new RuntimeException("Destino é obrigatório");
        }
    }

    // faz a validação do status
    private void validarStatus(viagemEntity viagemStatus){
        if (viagemStatus.getStatus() == null || viagemStatus.getStatus().isBlank() ){
            viagemStatus.setStatus("AGENDADA");
        }
    }
}
