package br.com.intellistocks.api.models.produto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.intellistocks.api.controller.ProdutoController;
import br.com.intellistocks.api.models.TipoProduto.TipoProduto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Produto extends EntityModel<Produto> {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Positive
    private BigDecimal preco;

    @ManyToOne
    @JsonBackReference
    private TipoProduto tipoProduto;

    @Column(length = 255)
    private String descricao;

    @NotBlank
    private String modelo;

    @NotBlank
    private String marca;

    @Builder.Default
    private Boolean ativo = true;

    public EntityModel<Produto> toEntityModel() {
        Link selfLink = linkTo(methodOn(ProdutoController.class).readProdutoById(id)).withSelfRel();
        Link deleteLink = linkTo(ProdutoController.class).slash(id).withRel("DELETE");
        Link getAllLink = linkTo(methodOn(ProdutoController.class).listProdutos(Pageable.unpaged(), null)).withRel("GET");
        Link postLink = linkTo(methodOn(ProdutoController.class).createProduto(null)).withRel("POST");
        Link putLink = linkTo(methodOn(ProdutoController.class).editProduto(id, null)).withRel("PUT");
    
        return EntityModel.of(this, selfLink, deleteLink, getAllLink, postLink, putLink);
    }

}
