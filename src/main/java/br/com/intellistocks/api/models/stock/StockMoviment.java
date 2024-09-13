package br.com.intellistocks.api.models.stock;

import br.com.intellistocks.api.models.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockMoviment {

    // TODO: CRIAR DTO PARA MOSTRAR APENAS O NOME E QUANTITY DO Product

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    @NotNull
    @Positive
    private Integer quantity;

    private LocalDateTime dateMoviment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeMoviment typeMoviment;

    // A anotação permite executar ações antes de persistir no BD
    @PrePersist
    public void prePersist() {
        dateMoviment = LocalDateTime.now();
    }

}


