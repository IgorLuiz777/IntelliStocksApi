package br.com.intellistocks.api.controller;

import br.com.intellistocks.api.models.stock.StockMovement;
import br.com.intellistocks.api.service.StockMovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name = "stockMovement", description = "Operações relacionadas ao movimento de estoque.")
@RequestMapping("/stockMovement")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @PostMapping
    @Operation(summary = "Cria um novo movimento de estoque.", description = "Cria um novo registro de movimento de estoque com as informações fornecidas.")
    public StockMovement postStockMovement(
            @Parameter(description = "Informações do movimento de estoque a ser criado.") @RequestBody StockMovement stockMovement) {
        return stockMovementService.createMovement(stockMovement);
    }

    @GetMapping
    @Operation(summary = "Obtém todos os movimentos de estoque.", description = "Retorna uma lista paginada de todos os movimentos de estoque.")
    public Page<StockMovement> getAllStockMovement(
            @Parameter(description = "Parâmetros de paginação para a lista de movimentos de estoque.") Pageable pageable) {
        return stockMovementService.listMovement(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um movimento de estoque pelo ID.", description = "Retorna um movimento de estoque específico com base no ID fornecido.")
    public Optional<StockMovement> getStockMovementById(
            @Parameter(description = "ID do movimento de estoque a ser retornado.") @PathVariable long id) {
        return stockMovementService.getMovement(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um movimento de estoque pelo ID.", description = "Remove um movimento de estoque específico com base no ID fornecido.")
    public void deleteStockMovementById(
            @Parameter(description = "ID do movimento de estoque a ser deletado.") @PathVariable long id) {
        stockMovementService.deleteMovement(id);
    }
}
