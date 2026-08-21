package com.gestor_frotas.gestor_frotas.Service;

import com.gestor_frotas.gestor_frotas.Model.viagemEntity;
import com.gestor_frotas.gestor_frotas.Repository.viagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    private viagemRepository viagemRepository;
    private TomTomService tomTomService;

    // puxando o repository
    public ViagemService(viagemRepository viagemRepository, TomTomService tomTomService ) {
        this.viagemRepository = viagemRepository;
        this.tomTomService = tomTomService;
    }


    // salva as valicações
    @Transactional
    public viagemEntity salvarViagemCompleta (viagemEntity viagemValidacao){
        validarDatas(viagemValidacao);
        validarStatus(viagemValidacao);
        validarRota(viagemValidacao);
        preenchendoCoordenada(viagemValidacao);
        preencherRota(viagemValidacao);

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

    public void preenchendoCoordenada(viagemEntity viagem){

        // requisições do TomTomService das classes Coordenada e geodificar
        TomTomService.Coordenada origem = tomTomService.geocodificar(viagem.getOrigem());
        TomTomService.Coordenada destino = tomTomService.geocodificar(viagem.getDestino());

        // pega a latitude e longitude da api e transforma para o meu código com set
        viagem.setLatitude_origem(origem.getLatitude());
        viagem.setLongitude_origem(origem.getLongitude());

        viagem.setLatitude_destino(destino.getLatitude());
        viagem.setLongitude_destino(destino.getLongitude());

    }

    public void preencherRota(viagemEntity viagem){
        TomTomService.RotaCalculo rota = tomTomService.calculateRoute(viagem.getLatitude_origem().toString(),
                viagem.getLongitude_origem().toString(),
                viagem.getLatitude_destino().toString(),
                viagem.getLongitude_destino().toString());

        viagem.setDistancia_metros(rota.getDistanciaMetros());
        viagem.setDuracao_segundos(rota.getDuracaoSegundos());

            viagem.setData_chegada(
                viagem.getData_saida().plusSeconds(rota.getDuracaoSegundos().longValue()));

    }

}
