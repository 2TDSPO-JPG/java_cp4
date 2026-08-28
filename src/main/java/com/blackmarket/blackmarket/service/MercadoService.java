package com.blackmarket.blackmarket.service;

import com.blackmarket.blackmarket.entity.Mercado;
import com.blackmarket.blackmarket.exception.exceptions.EntidadeNaoEncontradaException;
import com.blackmarket.blackmarket.exception.exceptions.ErrorDeParametroException;
import com.blackmarket.blackmarket.repository.MercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MercadoService {

    private final MercadoRepository mercadoRepository;


    public Mercado buscarBrinquedoPorId(Long id) {
        Optional<Mercado> brinquedo = mercadoRepository.findById(id);
        Mercado mercadoEncontrado = brinquedo.orElseThrow(() -> new EntidadeNaoEncontradaException("Brinquedo não encontrado"));
        return mercadoEncontrado;
    }

    public Mercado buscarBrinquedoPorIdComHateoas(Long id) {
        Optional<Mercado> brinquedo = mercadoRepository.findById(id);
        Mercado mercadoEncontrado = brinquedo.orElseThrow(() -> new EntidadeNaoEncontradaException("Brinquedo não encontrado"));
        return mercadoEncontrado;
    }

    public Mercado salvarBrinquedo(Mercado mercado){

        System.out.println("Cheguei no service para salvar o brinquedo: " + mercado);

        Mercado mercado1 = Mercado.builder()
                .nome(mercado.getNome())
                .descricao(mercado.getDescricao())
                .preco(mercado.getPreco())
                .tamanho(mercado.getTamanho())
                .tipo(mercado.getTipo())
                .setor(mercado.getSetor())
                .build();
        return mercadoRepository.save(mercado1);
    }

    public Mercado atualizarParcialmente(Long id, Mercado parcialMerc) {

        Mercado mercado = mercadoRepository.findById(id)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Produto não encontrado para o ID: " + id));

        if (parcialMerc.getNome() != null && !parcialMerc.getNome().isBlank()) {
            mercado.setNome(parcialMerc.getNome());
        }

        if (parcialMerc.getTipo() != null && !parcialMerc.getTipo().isBlank()) {
            mercado.setTipo(parcialMerc.getTipo());
        }

        if (parcialMerc.getSetor() != null && !parcialMerc.getSetor().isBlank()) {
            mercado.setSetor(parcialMerc.getSetor());
        }

        if (parcialMerc.getTamanho() != null && !parcialMerc.getTamanho().isNaN()) {
            mercado.setTamanho(parcialMerc.getTamanho());
        }

        if (parcialMerc.getPreco() != null) {
            mercado.setPreco(parcialMerc.getPreco());
        }

        return mercadoRepository.save(mercado);
    }

    public String deletarBrinquedo(Long id){
        Mercado mercado = buscarBrinquedoPorId(id);
        mercadoRepository.delete(mercado);
        return "Brinquedo deletado com sucesso!";
    }

    public Mercado atualizarBrinquedo(Mercado mercadoAtualizado) {
        if (mercadoAtualizado.getCode() != null){
            throw new ErrorDeParametroException("O código do brinquedo não pode ser alterado.");
        }
        Mercado mercadoExistente = buscarBrinquedoPorId(mercadoAtualizado.getId());
        mercadoExistente.setNome(mercadoAtualizado.getNome());
        mercadoExistente.setDescricao(mercadoAtualizado.getDescricao());
        mercadoExistente.setPreco(mercadoAtualizado.getPreco());
        mercadoExistente.setTipo(mercadoAtualizado.getTipo());
        mercadoExistente.setSetor(mercadoAtualizado.getSetor());
        mercadoExistente.setTamanho(mercadoAtualizado.getTamanho());
        return mercadoRepository.save(mercadoExistente);
    }

    public List<Mercado> listaDeBrinquedos(){
        return mercadoRepository.findAll();
    }
}
