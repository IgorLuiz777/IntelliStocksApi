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

import br.com.intellistocks.api.models.product.TypeProduct;
import br.com.intellistocks.api.service.TypeProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;

@RestController
@RequestMapping("/typeProduct")
@Tag(name = "TypeProduct", description = "Endpoints para operações relacionadas a tipos de produtos")
public class TypeProductController {

    @Autowired
    TypeProductService typeProductService;

    @PostMapping
    @Operation(
            summary = "Criar um novo tipo de produto",
            description = "Cria um novo tipo de produto com os dados fornecidos no corpo da requisição"
    )
    public TypeProduct createProduct(@RequestBody TypeProduct typeProduct) {
        return typeProductService.create(typeProduct);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os tipos de produtos",
            description = "Retorna uma lista paginada de todos os tipos de produtos, ordenados pelo nome em ordem alfabética"
    )
    public Page<TypeProduct> listTypeProduct(
            @RequestParam(required = false) String name,
            @ParameterObject @PageableDefault(sort = "name", direction = Direction.ASC) Pageable pageable) {
        return typeProductService.listTypeProduct(name, pageable);
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
    public ResponseEntity<TypeProduct> readTypeProductById(@PathVariable Long id){
        return typeProductService.readTipoProdutoById(id);
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
    public TypeProduct updateTypeProduct(@PathVariable Long id, @RequestBody TypeProduct typeProduct) {
        return typeProductService.editTypeProduct(id, typeProduct);
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
    public void deleteTypeProduct(@PathVariable Long id) {
        typeProductService.deleteTypeProduct(id);
    }
}
