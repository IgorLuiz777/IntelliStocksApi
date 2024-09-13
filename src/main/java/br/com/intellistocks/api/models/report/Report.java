package br.com.intellistocks.api.models.report;

import br.com.intellistocks.api.models.product.Product;
import br.com.intellistocks.api.models.stock.StockMovement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @ManyToOne
    private Product product;

    @OneToMany
    private List<StockMovement> movements;

    private String delimiter;

    private String header;

    private LocalDate initialDate;

    private LocalDate finalDate;

    private Double total;
}
