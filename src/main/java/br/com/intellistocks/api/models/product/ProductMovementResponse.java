package br.com.intellistocks.api.models.product;

import java.util.List;

import br.com.intellistocks.api.models.stock.StockMovement;

public record ProductMovementResponse(Long id, List<StockMovement> movements) {
    
    public ProductMovementResponse(Product product) {
        this(product.getId(), product.getStockMovement());
    }

}
