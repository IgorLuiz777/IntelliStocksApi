package br.com.intellistocks.api.controller;

import br.com.intellistocks.api.models.produto.Produto;
import br.com.intellistocks.api.models.produto.ProdutoDTO;
import br.com.intellistocks.api.service.ProdutoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoService.createProduto(produto);
    }

    @GetMapping
    public List<ProdutoDTO> listProdutos() {
        return produtoService.listProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> readProdutoById(@PathVariable Long id) {
        return produtoService.readProdutoById(id);
    }

    @GetMapping("/disable")
    public List<ProdutoDTO> listDisableProdutos() {
        return produtoService.listDisableProdutos();
    }

    @PutMapping("/{id}")
    public Produto editProduto(
            @PathVariable Long id,
            @RequestBody Produto produto) {
        return produtoService.editProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoService.disableProduto(id);
    }
}
