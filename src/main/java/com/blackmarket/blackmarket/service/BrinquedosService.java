package com.blackmarket.blackmarket.service;

import com.blackmarket.blackmarket.entity.Brinquedo;
import com.blackmarket.blackmarket.exception.exceptions.EntidadeNaoEncontradaException;
import com.blackmarket.blackmarket.exception.exceptions.ErrorDeParametroException;
import com.blackmarket.blackmarket.repository.BrinquedosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrinquedosService {

    private final BrinquedosRepository brinquedosRepository;

    public Brinquedo buscarBrinquedoPorId(Long id) {
        Optional<Brinquedo> brinquedo = brinquedosRepository.findById(id);
        Brinquedo brinquedoEncontrado = brinquedo.orElseThrow(() -> new EntidadeNaoEncontradaException("Brinquedo não encontrado"));
        return brinquedoEncontrado;
    }

    public Brinquedo salvarBrinquedo(Brinquedo brinquedo){
        Brinquedo brinquedo1 = Brinquedo.builder()
                .nome(brinquedo.getNome())
                .descricao(brinquedo.getDescricao())
                .preco(brinquedo.getPreco())
                .build();
        return brinquedosRepository.save(brinquedo1);
    }

    public String deletarBrinquedo(Long id){
        Brinquedo brinquedo = buscarBrinquedoPorId(id);
        brinquedosRepository.delete(brinquedo);
        return "Brinquedo deletado com sucesso!";
    }

    public Brinquedo atualizarBrinquedo(Brinquedo brinquedoAtualizado) {
        if (brinquedoAtualizado.getCode() != null){
            throw new ErrorDeParametroException("O código do brinquedo não pode ser alterado.");
        }
        Brinquedo brinquedoExistente = buscarBrinquedoPorId(brinquedoAtualizado.getId());
        brinquedoExistente.setNome(brinquedoAtualizado.getNome());
        brinquedoExistente.setDescricao(brinquedoAtualizado.getDescricao());
        brinquedoExistente.setPreco(brinquedoAtualizado.getPreco());
        return brinquedosRepository.save(brinquedoExistente);
    }
}
