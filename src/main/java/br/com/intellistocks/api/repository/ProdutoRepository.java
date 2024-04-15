package br.com.intellistocks.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.intellistocks.api.models.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    List<Produto> findByAtivoTrue();

    List<Produto> findByAtivoFalse();

    Produto findByIdAndAtivoTrue(Long id);

}
