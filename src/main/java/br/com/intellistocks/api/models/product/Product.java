package br.com.intellistocks.api.models.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.intellistocks.api.controller.ProductController;
import br.com.intellistocks.api.models.stock.StockMovement;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Product extends EntityModel<Product> {

    // TODO: CRIAR DTO PARA MOSTRAR APENAS O NOME DO TypeProduct

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Positive
    private BigDecimal price;

    @ManyToOne
    @JsonBackReference
    private TypeProduct typeProduct;

    @Column(length = 255)
    private String desc;

    @NotBlank
    private String model;

    @NotBlank
    private String brand;

    @Positive
    private Integer quantity;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private List<StockMovement> stockMovement;

    public EntityModel<Product> toEntityModel() {
        Link selfLink = linkTo(methodOn(ProductController.class).readProductById(id)).withSelfRel();
        Link deleteLink = linkTo(ProductController.class).slash(id).withRel("DELETE");
        Link getAllLink = linkTo(methodOn(ProductController.class).listProduct(Pageable.unpaged(), null)).withRel("GET");
        Link postLink = linkTo(methodOn(ProductController.class).createProduct(null)).withRel("POST");
        Link putLink = linkTo(methodOn(ProductController.class).editProduct(id, null)).withRel("PUT");

        return EntityModel.of(this, selfLink, deleteLink, getAllLink, postLink, putLink);
    }
}
