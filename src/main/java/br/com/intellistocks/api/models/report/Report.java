package br.com.intellistocks.api.models.report;

import br.com.intellistocks.api.models.product.Product;
import br.com.intellistocks.api.models.stock.StockMoviment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private List<StockMoviment> movimentacoes;

    private String delimiter;

    private String header;

    private LocalDate initialDate;

    private LocalDate finalDate;

    private Double total;
}
