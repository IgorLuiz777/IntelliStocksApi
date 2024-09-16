package br.com.intellistocks.api.models.stock;

import br.com.intellistocks.api.models.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement {

    // TODO: CRIAR DTO PARA MOSTRAR APENAS O NOME E QUANTITY DO Product

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    @Positive
    private Integer quantity;

    private LocalDateTime dateMovement;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeMovement typeMovement;

    // A anotação permite executar ações antes de persistir no BD
    @PrePersist
    public void prePersist() {
        dateMovement = LocalDateTime.now();
    }

}


