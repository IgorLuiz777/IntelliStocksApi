package br.com.intellistocks.api.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

import br.com.intellistocks.api.models.TipoProduto.TipoProduto;
import br.com.intellistocks.api.service.TipoProdutoService;

@RestController
@RequestMapping("/tipoProduto")
public class TipoProdutoController {

    @Autowired
    TipoProdutoService tProdutoService;
    
    @PostMapping
    @ResponseStatus(CREATED)
    public TipoProduto createProduto(@RequestBody TipoProduto tipoProduto) {
        return tProdutoService.create(tipoProduto);
    }

    @GetMapping
    public Page<TipoProduto> listTipoProdutos(
            @RequestParam(required = false) String nome,
            @PageableDefault(sort = "nome", direction = Direction.ASC) Pageable pageable) {
        return tProdutoService.listTipoProdutos(nome, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProduto> readTipoProdutoById(@PathVariable Long id){
        return tProdutoService.readTipoProdutoById(id);
    }

    @PutMapping("/{id}")
    public TipoProduto updateTipoProduto(@PathVariable Long id, @RequestBody TipoProduto tipoProduto) {
        return tProdutoService.editTipoProduto(id, tipoProduto);
    }

    @DeleteMapping("/{id}")
    public void deleteTipoProduto(@PathVariable Long id) {
        tProdutoService.deleteTipoProduto(id);
    }
}
