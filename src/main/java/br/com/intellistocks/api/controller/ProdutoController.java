package br.com.intellistocks.api.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.intellistocks.api.models.produto.Produto;
import br.com.intellistocks.api.models.produto.DadosListagemProduto;
import br.com.intellistocks.api.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoService.createProduto(produto);
    }

    @GetMapping
    public PagedModel<EntityModel<DadosListagemProduto>> listProdutos(
        @PageableDefault(sort = "nome", direction = Direction.ASC) Pageable pageable,
        @RequestParam(required = false) String nome) {
        return produtoService.listProdutos(nome, pageable);
    }

    @GetMapping("/{id}")
    public EntityModel<DadosListagemProduto> readProdutoById(@PathVariable Long id) {
        return produtoService.readProdutoById(id);
    }

    @PutMapping("/{id}")
    public Produto editProduto(
            @PathVariable Long id,
            @RequestBody Produto produto) {
        return produtoService.editProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduto(@PathVariable Long id) {
        produtoService.disableProduto(id);
    }

}
