package br.com.intellistocks.api.service;

import br.com.intellistocks.api.models.product.Product;
import br.com.intellistocks.api.models.stock.StockMoviment;
import br.com.intellistocks.api.repository.ProductRepository;
import br.com.intellistocks.api.repository.StockMovimentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StockMovimentService {

    @Autowired
    private StockMovimentRepository stockMovimentRepository;

    @Autowired
    private ProductRepository productRepository;

    // TODO: VERIFICAR SE EXISTE QUANTIDADE SUFICIENTE PRA FAZER A SOMA
    @Transactional
    public StockMoviment createMoviment(StockMoviment stockMoviment) {
        Product product = productRepository.findById(stockMoviment.getProduct().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Integer quantity = product.getQuantity() != null ? product.getQuantity() : 0;

        if (stockMoviment.getTypeMoviment().toString() == "INPUT") {
            product.setQuantity(quantity + stockMoviment.getQuantity());
        } else if (stockMoviment.getTypeMoviment().toString() == "OUTPUT") {
            if (quantity < stockMoviment.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product quantity should be bigger then" +
                        " the movement.");
            }
            product.setQuantity(quantity - stockMoviment.getQuantity());
        }

        productRepository.save(product);
        stockMovimentRepository.save(stockMoviment);
        return stockMoviment;
    }


    public Page<StockMoviment> listMoviment(Pageable pageable) {
        return stockMovimentRepository.findAll(pageable);
    }

    public Optional<StockMoviment> getMoviment(Long id) {
        if (!stockMovimentRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Moviment with id: " + id + " not found!");
        return stockMovimentRepository.findById(id);
    }

    public void deleteMoviment(Long id) {
        if (!stockMovimentRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Moviment with id: " + id + " not found!");
        stockMovimentRepository.deleteById(id);
    }

}
