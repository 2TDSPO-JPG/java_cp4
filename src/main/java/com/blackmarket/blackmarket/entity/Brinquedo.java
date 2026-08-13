package com.blackmarket.blackmarket.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "T_BRINQUEDO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Brinquedo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "O nome do brinquedo não pode ser vazio")
    private String nome;
    @Column
    private String descricao;
    @Column
    private Double preco;
    @Column
    private String code;

    @PrePersist
    @PreUpdate
    public void prePersist(){
        if (this.code == null || this.code.isBlank()){
            generateCode();
        }
    }

    private String generateCode(){
        String codeFunc = UUID.randomUUID().toString().replace("[0-9]", "").substring(0, 8).toUpperCase();
        while (codeFunc.length() < 8){
            codeFunc += UUID.randomUUID().toString().replace("[0-9]", "");
        }
        this.code = "TOY-" + codeFunc.substring(0, 8);
        return this.code;
    }

}
