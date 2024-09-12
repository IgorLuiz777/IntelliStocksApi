package br.com.intellistocks.api.service;

import br.com.intellistocks.api.models.typeProduct.TypeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.intellistocks.api.repository.TypeProductRepository;

@Service
public class TypeProductService {
    
    @Autowired
    TypeProductRepository typeProductRepository;

    public TypeProduct create(TypeProduct typeProduct) {
        return typeProductRepository.save(typeProduct);
    }
    
    public Page<TypeProduct> listTypeProduct(String name, Pageable pageable) {
        if (name != null) {
            return typeProductRepository.findByName(name, pageable);
        }
        return typeProductRepository.findAll(pageable);
    }

    public ResponseEntity<TypeProduct> readTipoProdutoById(Long id) {

        return typeProductRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public TypeProduct editTypeProduct(Long id, TypeProduct typeProduct) {
        typeProduct.setId(id);
        return typeProductRepository.save(typeProduct);
    }

    public void deleteTypeProduct(Long id) {
        typeProductRepository.deleteById(id);
    }
}
