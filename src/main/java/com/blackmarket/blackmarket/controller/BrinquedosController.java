package com.blackmarket.blackmarket.controller;

import com.blackmarket.blackmarket.entity.Brinquedo;
import com.blackmarket.blackmarket.service.BrinquedosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brinquedos")
public class BrinquedosController {

    private final BrinquedosService brinquedosService;

    @GetMapping("/buscar-id-{id}")
    public Brinquedo buscarBrinquedo(@PathVariable Long id){
        return brinquedosService.buscarBrinquedoPorId(id);
    }

    @PostMapping
    public Brinquedo criarBrinquedo(@RequestBody Brinquedo brinquedo){
        return brinquedosService.salvarBrinquedo(brinquedo);
    }

    @DeleteMapping("/deletar-id-{id}")
    public String deletarBrinquedo(@PathVariable Long id) {
        return brinquedosService.deletarBrinquedo(id);
    }

    @PutMapping("/atualizar")
    public Brinquedo atualizarBrinquedo( @RequestBody Brinquedo brinquedoAtualizado) {
        return brinquedosService.atualizarBrinquedo(brinquedoAtualizado);
    }
}
