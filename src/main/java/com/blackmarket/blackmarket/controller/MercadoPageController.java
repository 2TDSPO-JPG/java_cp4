package com.blackmarket.blackmarket.controller;

import com.blackmarket.blackmarket.entity.Mercado;
import com.blackmarket.blackmarket.repository.MercadoRepository;
import com.blackmarket.blackmarket.service.MercadoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MercadoPageController {

    private final MercadoService mercadoService;
    private final MercadoRepository mercadoRepository;

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        List<Mercado> produtos = mercadoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "produto";
    }

    @GetMapping("/produtos/novo")
    public String novoProdutoForm() {
        return "produto-novo";
    }

    @PostMapping("/produtos/novo")
    public String criarProduto(@ModelAttribute Mercado produto) {
        mercadoService.salvarBrinquedo(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/{id}")
    public String editarProdutoForm(@PathVariable Long id, Model model) {
        Mercado produto = mercadoService.buscarBrinquedoPorId(id);
        model.addAttribute("produto", produto);
        return "produto-editar";
    }

    @PostMapping("/produtos/{id}/atualizar")
    public String atualizarProduto(@PathVariable Long id, @ModelAttribute Mercado produto) {
        produto.setId(id);
        mercadoService.atualizarBrinquedo(produto);
        return "redirect:/produtos";
    }

    @PostMapping("/produtos/{id}/deletar")
    public String deletarProduto(@PathVariable Long id) {
        mercadoService.deletarBrinquedo(id);
        return "redirect:/produtos";
    }
}