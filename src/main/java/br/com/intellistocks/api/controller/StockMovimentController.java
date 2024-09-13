package br.com.intellistocks.api.controller;

import br.com.intellistocks.api.models.stock.StockMoviment;
import br.com.intellistocks.api.service.StockMovimentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name = "StockMoviment")
@RequestMapping("/stockMoviment")
public class StockMovimentController {

    @Autowired
    private StockMovimentService stockMovimentService;

    @PostMapping
    public StockMoviment postStockMoviment(@RequestBody StockMoviment stockMoviment) {
        return stockMovimentService.createMoviment(stockMoviment);
    }

    @GetMapping
    public Page<StockMoviment> getAllStockMoviment(Pageable pageable) {
        return stockMovimentService.listMoviment(pageable);
    }

    @GetMapping("/{id}")
    public Optional<StockMoviment> getStockMovimentById(@PathVariable long id) {
        return stockMovimentService.getMoviment(id);
    }

    @DeleteMapping("/{id}")
     public void deleteStockMovimentById(@PathVariable long id) {
        stockMovimentService.deleteMoviment(id);
    }
}
