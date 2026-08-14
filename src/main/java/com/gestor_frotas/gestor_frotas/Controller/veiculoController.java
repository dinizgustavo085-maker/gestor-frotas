package com.gestor_frotas.gestor_frotas.Controller;

import com.gestor_frotas.gestor_frotas.Model.motoristaEntity;
import com.gestor_frotas.gestor_frotas.Model.veiculoEntity;
import com.gestor_frotas.gestor_frotas.Repository.veiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin(origins = "*")
public class veiculoController {
    @Autowired
    private veiculoRepository comandos;

    @PostMapping
    public veiculoEntity salvarVeiculo(@RequestBody veiculoEntity veiculo){
        return comandos.save(veiculo);
    }

    @GetMapping
    public List<veiculoEntity> listaVeiculo(){
        return comandos.findAll();
    }

    @PutMapping("/{id}")
    public veiculoEntity atualizarVeiculo(@PathVariable Integer id, @RequestBody veiculoEntity dadosAtualizadosVeiculos ){
        veiculoEntity veiculoAtual = comandos.findById(id).orElseThrow();

        veiculoAtual.setAno(dadosAtualizadosVeiculos.getAno());
        veiculoAtual.setCor(dadosAtualizadosVeiculos.getCor());
        veiculoAtual.setCombustivel(dadosAtualizadosVeiculos.getCombustivel());
        veiculoAtual.setModelo(dadosAtualizadosVeiculos.getModelo());
        veiculoAtual.setMarca(dadosAtualizadosVeiculos.getMarca());
        veiculoAtual.setPlaca(dadosAtualizadosVeiculos.getPlaca());
        veiculoAtual.setTipo(dadosAtualizadosVeiculos.getTipo());
        veiculoAtual.setQuilometragem(dadosAtualizadosVeiculos.getQuilometragem());
        veiculoAtual.setStatus(dadosAtualizadosVeiculos.getStatus());

        return comandos.save(veiculoAtual);
    }


    @DeleteMapping("/{id}")
    public String deletarVeiculo(@PathVariable Integer id){
        veiculoEntity veiculoDeletar = comandos.findById(id).orElseThrow();

        comandos.deleteById(id);

        return "Veiculo"+veiculoDeletar.getModelo()+" deletado com sucesso";
    }

}
