package com.gestor_frotas.gestor_frotas.Controller;

import com.gestor_frotas.gestor_frotas.Model.motoristaEntity;
import com.gestor_frotas.gestor_frotas.Repository.motoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorista")
@CrossOrigin(origins = "*")
public class motoristaController {

    @Autowired
    private motoristaRepository comandos;

    @PostMapping
    public motoristaEntity salvarMotorista(@RequestBody motoristaEntity motorista){
        return comandos.save(motorista);
    }

    // requisição para mostrar a minha lista de dados do motorista
    @GetMapping
    public List<motoristaEntity> listaMotorista(){
        return comandos.findAll();
    }

    // requisição para atualizar os dados
    @PutMapping("/{id}")
    public motoristaEntity atualizarMotorista(@PathVariable Integer id, @RequestBody motoristaEntity dadosAtualizadosMotorista){
        motoristaEntity motoristaAtual = comandos.findById(id).orElseThrow();

        motoristaAtual.setCpf(dadosAtualizadosMotorista.getCpf());
        motoristaAtual.setCategoria_cnh(dadosAtualizadosMotorista.getCategoria_cnh());
        motoristaAtual.setEmail(dadosAtualizadosMotorista.getEmail());
        motoristaAtual.setNome(dadosAtualizadosMotorista.getNome());
        motoristaAtual.setId(dadosAtualizadosMotorista.getId());
        motoristaAtual.setData_nascimento(dadosAtualizadosMotorista.getData_nascimento());
        motoristaAtual.setTelefone(dadosAtualizadosMotorista.getTelefone());
        motoristaAtual.setValidade_cnh(dadosAtualizadosMotorista.getValidade_cnh());

        return comandos.save(motoristaAtual);
    }

    // requisição de deletar o motorista
    @DeleteMapping("/{id}")
    public String deletarMotorista(@PathVariable Integer id){
            motoristaEntity motorista = comandos.findById(id).orElseThrow();
            comandos.deleteById(id);
            return "Motorista"+motorista.getNome()+" deletado com sucesso";
    }


}
