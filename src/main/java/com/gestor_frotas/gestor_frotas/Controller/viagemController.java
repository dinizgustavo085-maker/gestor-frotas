package com.gestor_frotas.gestor_frotas.Controller;

import com.gestor_frotas.gestor_frotas.Model.viagemEntity;
import com.gestor_frotas.gestor_frotas.Repository.viagemRepository;
import com.gestor_frotas.gestor_frotas.Service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagem")
@CrossOrigin(origins = "*")
public class viagemController {

    @Autowired
    public viagemRepository comandos;

    @Autowired
    private ViagemService viagemService;

    // Requição de salvar os dados da viagem
    @PostMapping
    public viagemEntity salvarViagem(@RequestBody viagemEntity viagem){
        return viagemService.salvarViagemCompleta(viagem);
    }

    // Requição de andar pela a lista de dados da viagem
    @GetMapping
    public List<viagemEntity> listaViagem(){
        return comandos.findAll();
    }

    @PutMapping("/{id}")
    public viagemEntity atualizarViagem(@PathVariable Integer id, @RequestBody viagemEntity dadosAtualizadosViagem){
        viagemEntity viagemAtual = comandos.findById(id).orElseThrow();

        viagemAtual.setData_entrada(dadosAtualizadosViagem.getData_entrada());
        viagemAtual.setData_saida(dadosAtualizadosViagem.getData_saida());
        viagemAtual.setDestino(dadosAtualizadosViagem.getDestino());
        viagemAtual.setDistancia(dadosAtualizadosViagem.getDistancia());
        viagemAtual.setOrigem(dadosAtualizadosViagem.getOrigem());
        viagemAtual.setId(dadosAtualizadosViagem.getId());
        viagemAtual.setStatus(dadosAtualizadosViagem.getStatus());
        viagemAtual.setTempo_estimado(dadosAtualizadosViagem.getTempo_estimado());
        viagemAtual.setObservacao(dadosAtualizadosViagem.getObservacao());

        return comandos.save(viagemAtual);

    }

    public String deletarViagem(@PathVariable Integer id){
        viagemEntity viagemDeletar = comandos.findById(id).orElseThrow();

        comandos.deleteById(id);

        return "Viagem"+viagemDeletar.getOrigem()+" deletada com sucesso";

    }

}
