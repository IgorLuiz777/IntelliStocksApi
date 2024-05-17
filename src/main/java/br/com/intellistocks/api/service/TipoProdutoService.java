package br.com.intellistocks.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.intellistocks.api.models.TipoProduto.TipoProduto;
import br.com.intellistocks.api.repository.TipoProdutoRepository;

@Service
public class TipoProdutoService {
    
    @Autowired
    TipoProdutoRepository tipoRepository;

    public TipoProduto create(TipoProduto tipoProduto) {
        return tipoRepository.save(tipoProduto);
    }
    
    public Page<TipoProduto> listTipoProdutos(String nome, Pageable pageable) {
        if (nome != null) {
            return tipoRepository.findByNome(nome, pageable);
        }
        return tipoRepository.findAll(pageable);
    }

    public ResponseEntity<TipoProduto> readTipoProdutoById(Long id) {

        return tipoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public TipoProduto editTipoProduto(Long id, TipoProduto tipoProduto) {
        tipoProduto.setId(id);
        return tipoRepository.save(tipoProduto);
    }

    public void deleteTipoProduto(Long id) {
        tipoRepository.deleteById(id);
    }
}
