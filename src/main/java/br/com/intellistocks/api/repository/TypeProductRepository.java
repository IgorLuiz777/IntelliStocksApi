package br.com.intellistocks.api.repository;

import br.com.intellistocks.api.models.typeProduct.TypeProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TypeProductRepository extends JpaRepository<TypeProduct, Long> {
    
    @Query("SELECT t FROM TypeProduct t WHERE t.name = :name")
    Page<TypeProduct> findByName(@Param("name") String name, Pageable pageable);
}
