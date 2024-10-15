package br.com.intellistocks.api.models.product;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import br.com.intellistocks.api.controller.ProductController;

public record ProductListResponse(Long id, String name, String typeProductName, Long typeProductId,BigDecimal price, String desc,
                                  String model, String brand, Integer quantity) {
    
    public ProductListResponse(Product product) {
        this(product.getId(), product.getName(), product.getTypeProduct().getName(), product.getTypeProduct().getId(),product.getPrice(),
                product.getDesc(), product.getModel(), product.getBrand(), product.getQuantity());
    }

    public EntityModel<ProductListResponse> toEntityModel() {
        Link selfLink = linkTo(methodOn(ProductController.class).readProductById(id)).withSelfRel();
        Link deleteLink = linkTo(ProductController.class).slash(id).withRel("DELETE");
        Link getAllLink = linkTo(methodOn(ProductController.class).listProduct(Pageable.unpaged(), null))
                .withRel("GET");
        Link postLink = linkTo(methodOn(ProductController.class).createProduct(null)).withRel("POST");
        Link putLink = linkTo(methodOn(ProductController.class).editProduct(id, null)).withRel("PUT");
    
        return EntityModel.of(this, selfLink, deleteLink, getAllLink, postLink, putLink);
    }
    
    
}
