package br.com.intellistocks.api.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.intellistocks.api.models.produto.Produto;
import br.com.intellistocks.api.models.produto.ProdutoDTO;
import br.com.intellistocks.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<ProdutoDTO> listProdutos() {
        List<Produto> produtos = produtoRepository.findByAtivoTrue();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        for (Produto produto : produtos) {
            produtoDTOs.add(new ProdutoDTO(produto));
        }
        return produtoDTOs;
    }

    public ResponseEntity<ProdutoDTO> readProdutoById(Long id) {
        Produto produto = verifyProduto(id);
        return ResponseEntity.ok(new ProdutoDTO(produto));
    }

    public List<ProdutoDTO> listDisableProdutos() {
        List<Produto> produtos = produtoRepository.findByAtivoFalse();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        for (Produto produto : produtos) {
            produtoDTOs.add(new ProdutoDTO(produto));
        }
        return produtoDTOs;
    }

    public void disableProduto(Long id) {
        produtoRepository.findById(id)
                .ifPresent(produto -> {
                    if (produto.getAtivo()) {
                        produto.setAtivo(false);
                        produtoRepository.save(produto);
                    }
                });
    }

    public Produto editProduto(Long id, Produto produto) {
        verifyProduto(id);
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    private Produto verifyProduto(Long id) {
        Produto produto = produtoRepository.findByIdAndAtivoTrue(id);
        if (produto == null) {
            throw new ResponseStatusException(NOT_FOUND, "Produto não encontrado ou não está ativo");
        }
        return produto;
    }
}
