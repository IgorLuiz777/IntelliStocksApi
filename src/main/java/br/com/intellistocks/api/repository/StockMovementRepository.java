package br.com.intellistocks.api.repository;

import br.com.intellistocks.api.models.stock.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
