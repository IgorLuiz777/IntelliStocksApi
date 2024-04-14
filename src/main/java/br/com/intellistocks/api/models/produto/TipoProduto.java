package br.com.intellistocks.api.models.produto;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TipoProduto {
    
    private Long id;

    private String nome;

}
