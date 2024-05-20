package br.com.intellistocks.api.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.intellistocks.api.models.TipoProduto.TipoProduto;
import br.com.intellistocks.api.models.produto.Produto;
import br.com.intellistocks.api.repository.ProdutoRepository;
import br.com.intellistocks.api.repository.TipoProdutoRepository;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    ProdutoRepository pRepository;

    @Autowired
    TipoProdutoRepository tRepository;

    @Override
    public void run(String... args) throws Exception {

        TipoProduto tipoProduto = TipoProduto.builder()
                .id(1L)
                .nome("biscoito/bolacha")
                .build();

        tRepository.save(tipoProduto);

        TipoProduto tipoProduto2 = TipoProduto.builder()
                .id(2L)
                .nome("bebidas")
                .build();

        tRepository.save(tipoProduto2);

        Produto produto1 = Produto.builder()
                .id(1L)
                .nome("Bolacha")
                .preco(new BigDecimal("10.88"))
                .tipoProduto(tipoProduto)
                .descricao("Biscoito Recheado Chocolate e Baunilha")
                .modelo("Original Oreo 270g")
                .marca("Oreo")
                .quantidade(30)
                .build();

        Produto produto2 = Produto.builder()
                .id(2L)
                .nome("Bolacha Chocolate")
                .preco(new BigDecimal("8.50"))
                .tipoProduto(tipoProduto)
                .descricao("Biscoito Recheado com Chocolate")
                .modelo("BOLACHA BAUDUCCO 108G RECHEADA CHOCOLATE")
                .marca("Bauducco")
                .quantidade(50)
                .build();

        Produto produto3 = Produto.builder()
                .id(3L)
                .nome("Bolacha Baunilha")
                .preco(new BigDecimal("11.20"))
                .tipoProduto(tipoProduto)
                .descricao("Biscoito Recheado com Baunilha")
                .modelo("Trakinas Baunilha 280g")
                .marca("Trakinas")
                .quantidade(23)
                .build();

                
            Produto produto4 = Produto.builder()
                .id(4L)
                .nome("Whisky Buchanan ́s")
                .preco(new BigDecimal("159.99"))
                .tipoProduto(tipoProduto2)
                .descricao("Whisky Escocês Deluxe Buchanan's 750ml")
                .modelo("12 anos 750ml")
                .marca("Buchanan ́s")
                .quantidade(12)
                .build();
        pRepository.saveAll(List.of(produto1, produto2, produto3, produto4));

        tipoProduto.setProdutos(List.of(produto1, produto2, produto3, produto4));
        tRepository.save(tipoProduto);
    }
}
