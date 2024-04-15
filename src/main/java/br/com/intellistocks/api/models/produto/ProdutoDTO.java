package br.com.intellistocks.api.models.produto;

import java.math.BigDecimal;

public record ProdutoDTO(Long id, String nome, BigDecimal preco, String tipo, String descricao, String modelo, String marca) {
    
    public ProdutoDTO(Produto produto) {

        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getTipo(), produto.getDescricao(), produto.getModelo(), produto.getMarca());

    }

}
