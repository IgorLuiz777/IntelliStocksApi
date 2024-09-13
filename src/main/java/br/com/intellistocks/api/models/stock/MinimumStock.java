package br.com.intellistocks.api.models.stock;

import br.com.intellistocks.api.models.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinimumStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private Integer minQuantity;

    // TODO: MENSAGEM QUANDO ATINGIR O MÍNIMO
}
