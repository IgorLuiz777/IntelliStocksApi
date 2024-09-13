package br.com.intellistocks.api.service;

import br.com.intellistocks.api.models.product.Product;
import br.com.intellistocks.api.models.stock.StockMovement;
import br.com.intellistocks.api.repository.ProductRepository;
import br.com.intellistocks.api.repository.StockMovementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private ProductRepository productRepository;

    // TODO: VERIFICAR SE EXISTE QUANTIDADE SUFICIENTE PRA FAZER A SOMA
    @Transactional
    public StockMovement createMovement(StockMovement stockMovement) {
        Product product = productRepository.findById(stockMovement.getProduct().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Integer quantity = product.getQuantity() != null ? product.getQuantity() : 0;

        if (stockMovement.getTypeMovement().toString() == "INPUT") {
            product.setQuantity(quantity + stockMovement.getQuantity());
        } else if (stockMovement.getTypeMovement().toString() == "OUTPUT") {
            if (quantity < stockMovement.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product quantity should be bigger then" +
                        " the movement.");
            }
            product.setQuantity(quantity - stockMovement.getQuantity());
        }

        productRepository.save(product);
        stockMovementRepository.save(stockMovement);
        return stockMovement;
    }


    public Page<StockMovement> listMovement(Pageable pageable) {
        return stockMovementRepository.findAll(pageable);
    }

    public Optional<StockMovement> getMovement(Long id) {
        if (!stockMovementRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Movement with id: " + id + " not found!");
        return stockMovementRepository.findById(id);
    }

    public void deleteMovement(Long id) {
        if (!stockMovementRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Movement with id: " + id + " not found!");
        stockMovementRepository.deleteById(id);
    }

}
