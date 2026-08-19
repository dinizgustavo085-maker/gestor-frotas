package com.gestor_frotas.gestor_frotas.Service;

import com.gestor_frotas.gestor_frotas.Model.motoristaEntity;
import org.springframework.stereotype.Service;
import com.gestor_frotas.gestor_frotas.Repository.motoristaRepository;

@Service
public class MotoristaService {

    private final motoristaRepository motoristaRepository;

    public MotoristaService (motoristaRepository motoristaRepository){
        this.motoristaRepository = motoristaRepository;
    }

    // salva os dados de motorista
    public motoristaEntity salvarMotorista(motoristaEntity motorista){
        validarMotorista(motorista);
        return motoristaRepository.save(motorista);
    }

    // valida os dados nome e cpf do motorista
    private void validarMotorista(motoristaEntity motorista){
        if (motorista.getNome() == null || motorista.getNome().isBlank()){
            throw new RuntimeException("Nome do motorista é obrigatório");
        }

        if (motorista.getCpf() == null || motorista.getCpf().isBlank()) {
            throw new RuntimeException("CPF do motorista é obrigatório");
        }
    }


}
