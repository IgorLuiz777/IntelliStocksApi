package br.com.intellistocks.api.service;

import br.com.intellistocks.api.models.stock.StockMovement;
import br.com.intellistocks.api.repository.StockMovementRepository;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.intellistocks.api.models.product.TypeProduct;
import br.com.intellistocks.api.models.product.ProductListResponse;
import br.com.intellistocks.api.models.product.ProductMovementResponse;
import br.com.intellistocks.api.models.product.Product;
import br.com.intellistocks.api.repository.ProductRepository;
import br.com.intellistocks.api.repository.TypeProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TypeProductRepository typeProductRepository;

    @Autowired
    StockMovementRepository stockMovementRepository;

    @Autowired
    PagedResourcesAssembler<ProductListResponse> pagedResourcesAssembler;

    public Product createProduct(Product product) {
        TypeProduct typeProduct = product.getTypeProduct();
        if (typeProduct != null && typeProduct.getId() == null) {
            typeProduct = typeProductRepository.save(typeProduct);
        }
        product.setTypeProduct(typeProduct);
        return productRepository.save(product);
    }

    public PagedModel<EntityModel<ProductListResponse>> listProduct(String name, Pageable pageable) {
        Page<Product> page = (name != null && !name.isEmpty())
                ? productRepository.findByNameContainingIgnoreCase(name, pageable)
                : productRepository.findAll(pageable);
        Page<ProductListResponse> pageDto = page.map(ProductListResponse::new);
        return pagedResourcesAssembler.toModel(pageDto);
    }

    public EntityModel<ProductListResponse> readProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
        return new ProductListResponse(product).toEntityModel();
    }

    public ProductMovementResponse getMovementsById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        return new ProductMovementResponse(product);
    }
    

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        }
        stockMovementRepository.deleteById(id);
        productRepository.deleteById(id);
    }

    public Product editProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        }
        product.setId(id);
        return productRepository.save(product);
    }
}


