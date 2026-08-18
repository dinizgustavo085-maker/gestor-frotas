package com.gestor_frotas.gestor_frotas.Service;

import com.gestor_frotas.gestor_frotas.Model.veiculoEntity;
import com.gestor_frotas.gestor_frotas.Repository.veiculoRepository;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    private final veiculoRepository veiculoRepository;

    public VeiculoService (veiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    // salva os dados de veiculo
    public veiculoEntity salvarVeiculo(veiculoEntity veiculo){
        validarVeiculo(veiculo);
        return veiculoRepository.save(veiculo);
    }
    // valida os dados de placa e status
    private void validarVeiculo(veiculoEntity veiculo){
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isBlank()){
            throw new RuntimeException("PLACA DE VEICULO OBRIGATÓRIO");
        }

        if (veiculo.getStatus() == null || veiculo.getStatus().isBlank()){
            veiculo.setStatus("DISPONIVEL");
        }
    }
}
