package br.com.intellistocks.api.controller;

import br.com.intellistocks.api.models.stock.StockMovement;
import br.com.intellistocks.api.service.StockMovementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name = "stockMovement")
@RequestMapping("/stockMovement")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @PostMapping
    public StockMovement postStockMovement(@RequestBody StockMovement stockMovement) {
        return stockMovementService.createMovement(stockMovement);
    }

    @GetMapping
    public Page<StockMovement> getAllStockMovement(Pageable pageable) {
        return stockMovementService.listMovement(pageable);
    }

    @GetMapping("/{id}")
    public Optional<StockMovement> getStockMovementById(@PathVariable long id) {
        return stockMovementService.getMovement(id);
    }

    @DeleteMapping("/{id}")
     public void deleteStockMovementById(@PathVariable long id) {
        stockMovementService.deleteMovement(id);
    }
}
