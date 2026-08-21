package com.gestor_frotas.gestor_frotas.Service;

import com.gestor_frotas.gestor_frotas.Model.motoristaEntity;
import com.gestor_frotas.gestor_frotas.Model.veiculoEntity;
import com.gestor_frotas.gestor_frotas.Model.viagemEntity;
import com.gestor_frotas.gestor_frotas.Repository.motoristaRepository;
import com.gestor_frotas.gestor_frotas.Repository.veiculoRepository;
import com.gestor_frotas.gestor_frotas.Repository.viagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    private final viagemRepository viagemRepository;
    private final TomTomService tomTomService;
    private final motoristaRepository motoristaRepository;
    private final veiculoRepository veiculoRepository;

    // puxando o repository
    public ViagemService(
            viagemRepository viagemRepository,
            TomTomService tomTomService,
            motoristaRepository motoristaRepository,
            veiculoRepository veiculoRepository
    ) {
        this.viagemRepository = viagemRepository;
        this.tomTomService = tomTomService;
        this.motoristaRepository = motoristaRepository;
        this.veiculoRepository = veiculoRepository;
    }


    // salva as valicações
    @Transactional
    public viagemEntity salvarViagemCompleta (viagemEntity viagemValidacao){
        validarDatas(viagemValidacao);
        validarStatus(viagemValidacao);
        validarRota(viagemValidacao);
        validarMotorista(viagemValidacao);
        validarVeiculo(viagemValidacao);
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

    private void validarMotorista(viagemEntity viagem) {
        if (viagem.getMotorista() == null || viagem.getMotorista().getMotorista_id() == null) {
            throw new RuntimeException("Motorista e obrigatorio");
        }

        motoristaEntity motorista = motoristaRepository.findById(viagem.getMotorista().getMotorista_id())
                .orElseThrow(() -> new RuntimeException("Motorista nao encontrado"));

        viagem.setMotorista(motorista);
    }

    private void validarVeiculo(viagemEntity viagem) {
        if (viagem.getVeiculo() == null || viagem.getVeiculo().getVeiculo_id() == null) {
            throw new RuntimeException("Veiculo e obrigatorio");
        }

        veiculoEntity veiculo = veiculoRepository.findById(viagem.getVeiculo().getVeiculo_id())
                .orElseThrow(() -> new RuntimeException("Veiculo nao encontrado"));

        viagem.setVeiculo(veiculo);
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
