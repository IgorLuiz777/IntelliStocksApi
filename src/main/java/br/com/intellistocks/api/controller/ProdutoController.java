package br.com.intellistocks.api.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.intellistocks.api.models.produto.Produto;
import br.com.intellistocks.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping()
    @ResponseStatus(CREATED)
    public Produto cadastrarProduto(@RequestBody Produto produto) {

        return produtoRepository.save(produto);

    }

    
}
