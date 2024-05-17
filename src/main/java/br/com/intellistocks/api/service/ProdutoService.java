package br.com.intellistocks.api.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.intellistocks.api.models.TipoProduto.TipoProduto;
import br.com.intellistocks.api.models.produto.DadosListagemProduto;
import br.com.intellistocks.api.models.produto.Produto;
import br.com.intellistocks.api.repository.ProdutoRepository;
import br.com.intellistocks.api.repository.TipoProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    TipoProdutoRepository tipoRepository;

    @Autowired
    PagedResourcesAssembler<DadosListagemProduto> pagedResourcesAssembler;

    public Produto createProduto(Produto produto) {
        TipoProduto tipoProduto = produto.getTipoProduto();
        
        if (tipoProduto != null && tipoProduto.getId() == null) {
            tipoProduto = tipoRepository.save(tipoProduto);
        }
        produto.setTipoProduto(tipoProduto);
        
        return produtoRepository.save(produto);
    }
    

    

    public PagedModel<EntityModel<DadosListagemProduto>> listProdutos(String nome, Pageable pageable) {
        Page<Produto> page;
        if (nome != null && !nome.isEmpty()) {
            page = produtoRepository.findByNome(nome, pageable);
        } else {
            page = produtoRepository.findAll(pageable);
        }
        Page<DadosListagemProduto> pageDto = page.map(DadosListagemProduto::new);
        return pagedResourcesAssembler.toModel(pageDto);
    }

    public EntityModel<DadosListagemProduto> readProdutoById(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado!"));
        DadosListagemProduto dadosProduto = new DadosListagemProduto(produto);
        return dadosProduto.toEntityModel();
    }
    

    public void disableProduto(Long id) {
        
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Produto não encontrado!");
        }
        produtoRepository.deleteById(id);

    }

    public Produto editProduto(Long id, Produto produto) {
        
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Produto não encontrado!");
        }    
        produto.setId(id);
        return produtoRepository.save(produto);
    }
}
