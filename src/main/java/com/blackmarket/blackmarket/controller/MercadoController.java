package com.blackmarket.blackmarket.controller;

import com.blackmarket.blackmarket.entity.Mercado;
import com.blackmarket.blackmarket.service.MercadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mercado")
public class MercadoController {

    private final MercadoService mercadoService;

    @GetMapping("/buscar-id-{id}")
    public Mercado buscarProduto(@PathVariable Long id){
        return mercadoService.buscarBrinquedoPorId(id);
    }

    @PostMapping
    public Mercado criarProduto(@RequestBody Mercado mercado){
        return mercadoService.salvarBrinquedo(mercado);
    }

    @DeleteMapping("/deletar-id-{id}")
    public String deletarProduto(@PathVariable Long id) {
        return mercadoService.deletarBrinquedo(id);
    }

    @PutMapping("/atualizar")
    public Mercado atualizarProduto(@RequestBody Mercado mercadoAtualizado) {
        return mercadoService.atualizarBrinquedo(mercadoAtualizado);
    }

    @GetMapping
    public List<Mercado> listarProduto() {
        return mercadoService.listaDeBrinquedos();
    }

    @PatchMapping("/atualizar-parcialmente-id-{id}")
    public Mercado atualizarProdutoParcialmente(@PathVariable Long id, @RequestBody Mercado parcialMerc) {
        return mercadoService.atualizarParcialmente(id, parcialMerc);
    }

}
