package br.com.intellistocks.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.intellistocks.api.models.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    List<Produto> findByAtivoTrue();

    List<Produto> findByAtivoFalse();

    Produto findByIdAndAtivoTrue(Long id);

    @Query("SELECT p FROM Produto p WHERE p.nome = :nome")
    Page<Produto> findByNome(@Param("nome") String nome, Pageable pageable);

}
