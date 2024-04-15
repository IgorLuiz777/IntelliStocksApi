package br.com.intellistocks.api.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.intellistocks.api.models.produto.Produto;
import br.com.intellistocks.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping()
    @ResponseStatus(CREATED)
    public Produto createProduto(@RequestBody Produto produto) {

        return produtoRepository.save(produto);

    }

    @GetMapping()
    public List<Produto> listProdutos() {

        return produtoRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> readProdutoById(@PathVariable Long id) {

        return produtoRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public Produto editProduto(@PathVariable Long id, @RequestBody Produto produto) {

        verifyProduto(id);
        produto.setId(id);

        return produtoRepository.save(produto);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduto(@PathVariable Long id) {

        verifyProduto(id);
        produtoRepository.deleteById(id);

    }

    private void verifyProduto(Long id) {

        produtoRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto com esse Id, não existe!"));

    }
    
}
