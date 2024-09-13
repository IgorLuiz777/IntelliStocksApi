package br.com.intellistocks.api.models.stock;

import br.com.intellistocks.api.models.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockMoviment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    private Integer quantity;

    private LocalDate dateMoviment;

    private TypeMoviment typeMoviment;
}
