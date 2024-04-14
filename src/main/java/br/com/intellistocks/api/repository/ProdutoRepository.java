package br.com.intellistocks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.intellistocks.api.models.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
