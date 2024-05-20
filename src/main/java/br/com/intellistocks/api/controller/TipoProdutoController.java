package br.com.intellistocks.api.controller;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.intellistocks.api.models.TipoProduto.TipoProduto;
import br.com.intellistocks.api.service.TipoProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tipoProduto")
@Tag(name = "TipoProduto", description = "Endpoints para operações relacionadas a tipos de produtos")
public class TipoProdutoController {

    @Autowired
    TipoProdutoService tProdutoService;
    
    @PostMapping
    @Operation(
        summary = "Criar um novo tipo de produto",
        description = "Cria um novo tipo de produto com os dados fornecidos no corpo da requisição"
    )
    public TipoProduto createProduto(@RequestBody TipoProduto tipoProduto) {
        return tProdutoService.create(tipoProduto);
    }

    @GetMapping
    @Operation(
        summary = "Listar todos os tipos de produtos",
        description = "Retorna uma lista paginada de todos os tipos de produtos, ordenados pelo nome em ordem alfabética"
    )
    public Page<TipoProduto> listTipoProdutos(
            @RequestParam(required = false) String nome,
            @PageableDefault(sort = "nome", direction = Direction.ASC) Pageable pageable) {
        return tProdutoService.listTipoProdutos(nome, pageable);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar um tipo de produto por ID",
        description = "Retorna um tipo de produto com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de produto encontrado"),
        @ApiResponse(responseCode = "404", description = "Tipo de produto não encontrado")
    })
    public ResponseEntity<TipoProduto> readTipoProdutoById(@PathVariable Long id){
        return tProdutoService.readTipoProdutoById(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar um tipo de produto",
        description = "Atualiza um tipo de produto existente com os dados fornecidos no corpo da requisição"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de produto atualizado"),
        @ApiResponse(responseCode = "404", description = "Tipo de produto não encontrado")
    })
    public TipoProduto updateTipoProduto(@PathVariable Long id, @RequestBody TipoProduto tipoProduto) {
        return tProdutoService.editTipoProduto(id, tipoProduto);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Excluir um tipo de produto",
        description = "Exclui um tipo de produto com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tipo de produto excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tipo de produto não encontrado")
    })
    public void deleteTipoProduto(@PathVariable Long id) {
        tProdutoService.deleteTipoProduto(id);
    }
}
