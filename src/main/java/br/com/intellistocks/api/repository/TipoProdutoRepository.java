package br.com.intellistocks.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.intellistocks.api.models.TipoProduto.TipoProduto;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {
    
    @Query("SELECT t FROM TipoProduto t WHERE t.nome = :nome")
    Page<TipoProduto> findByNome(@Param("nome") String nome, Pageable pageable);
}
