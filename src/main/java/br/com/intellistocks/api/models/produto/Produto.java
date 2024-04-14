package br.com.intellistocks.api.models.produto;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Produto {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Positive
    private BigDecimal preco;

    //private TipoProduto TipoProduto;

    private String tipo;

    private String descricao;

    private String modelo;

    private String marca;
}
