package br.com.intellistocks.api.repository;

import br.com.intellistocks.api.models.stock.StockMoviment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovimentRepository extends JpaRepository<StockMoviment, Long> {
}
