package com.blackmarket.blackmarket.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "TDS_TB_MERCADO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Mercado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome do produto não pode ser vazio")
    private String nome;
    @NotBlank(message = "A descrição do produto não pode ser vazia")
    private String descricao;
    @NotNull(message = "O preço do produto não pode ser nulo")
    private Double preco;
    @NotBlank(message = "O tipo do produto não pode ser vazio")
    private String tipo;
    @NotBlank(message = "O setor do produto não pode ser vazio")
    private String setor;
    @NotNull(message = "O tamanho do produto não pode ser nulo")
    private Double tamanho;
    private String code;

    @PrePersist
    @PreUpdate
    public void prePersist(){
        if (this.code == null || this.code.isBlank()){
            generateCode();
        }
    }

    private String generateCode(){
        String codeFunc = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 8).toUpperCase();
        while (codeFunc.length() < 8){
            codeFunc += UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        }
        this.code = "PROD-" + codeFunc.substring(0, 8);
        return this.code;
    }

}
