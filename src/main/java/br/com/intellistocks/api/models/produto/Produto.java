package br.com.intellistocks.api.models.produto;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Produto {
    
    private Long id;

    private String nome;

    private BigDecimal preco;

    private TipoProduto TipoProduto;

    private String descricao;

    private String modelo;

    private String marca;
}
