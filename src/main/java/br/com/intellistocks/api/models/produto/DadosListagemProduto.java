package br.com.intellistocks.api.models.produto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import br.com.intellistocks.api.controller.ProdutoController;

public record DadosListagemProduto(Long id, String nome, String TipoProdutoNome , BigDecimal preco, String descricao, String modelo, String marca) {
    
    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getTipoProduto().getNome(), produto.getPreco(), produto.getDescricao(), produto.getModelo(), produto.getMarca());
    }

    public EntityModel<DadosListagemProduto> toEntityModel() {
        Link selfLink = linkTo(methodOn(ProdutoController.class).readProdutoById(id)).withSelfRel();
        Link deleteLink = linkTo(ProdutoController.class).slash(id).withRel("DELETE");
        Link getAllLink = linkTo(methodOn(ProdutoController.class).listProdutos(Pageable.unpaged(), null)).withRel("GET");
        Link postLink = linkTo(methodOn(ProdutoController.class).createProduto(null)).withRel("POST");
        Link putLink = linkTo(methodOn(ProdutoController.class).editProduto(id, null)).withRel("PUT");
    
        return EntityModel.of(this, selfLink, deleteLink, getAllLink, postLink, putLink);
    }
    
    
}
