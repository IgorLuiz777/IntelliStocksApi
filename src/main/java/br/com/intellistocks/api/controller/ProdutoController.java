package br.com.intellistocks.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
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

import br.com.intellistocks.api.models.produto.DadosListagemProduto;
import br.com.intellistocks.api.models.produto.Produto;
import br.com.intellistocks.api.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Endpoints para operações relacionadas a produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Criar Produto",
        description = "Cria um novo produto com os dados fornecidos no corpo da requisição"
    )
    public Produto createProduto(
            @Parameter(description = "Dados do produto a ser criado") @RequestBody Produto produto) {
        return produtoService.createProduto(produto);
    }

    @GetMapping
    @Operation(
        summary = "Listar Produtos",
        description = "Retorna uma lista paginada de produtos, ordenados pelo nome em ordem alfabética"
    )
    public PagedModel<EntityModel<DadosListagemProduto>> listProdutos(
            @PageableDefault(sort = "nome", direction = Direction.ASC) Pageable pageable,
            @Parameter(description = "Nome do produto para filtrar (opcional)") @RequestParam(required = false) String nome) {
        return produtoService.listProdutos(nome, pageable);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obter Produto por ID",
        description = "Retorna um produto com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public EntityModel<DadosListagemProduto> readProdutoById(
            @Parameter(description = "ID do produto a ser buscado") @PathVariable Long id) {
        return produtoService.readProdutoById(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Editar Produto",
        description = "Atualiza um produto existente com os dados fornecidos no corpo da requisição"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public Produto editProduto(
            @Parameter(description = "ID do produto a ser atualizado") @PathVariable Long id,
            @Parameter(description = "Novos dados do produto") @RequestBody Produto produto) {
        return produtoService.editProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "Excluir Produto",
        description = "Exclui um produto com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public void deleteProduto(
            @Parameter(description = "ID do produto a ser excluído") @PathVariable Long id) {
        produtoService.disableProduto(id);
    }

}

