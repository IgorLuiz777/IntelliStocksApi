package br.com.intellistocks.api.controller;

import br.com.intellistocks.api.models.product.Product;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springdoc.core.annotations.ParameterObject;
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

import br.com.intellistocks.api.models.product.ProductListResponse;
import br.com.intellistocks.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/product")
@Tag(name = "Produto", description = "Endpoints para operações relacionadas a produtos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Criar Produto",
            description = "Cria um novo produto com os dados fornecidos no corpo da requisição"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados de entrada")
    })
    public Product createProduct(
            @Parameter(description = "Dados do produto a ser criado") @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    @Operation(
            summary = "Listar Produtos",
            description = "Retorna uma lista paginada de produtos. Utilize os parâmetros opcionais 'name' para filtrar produtos pelo nome."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
    })
    public PagedModel<EntityModel<ProductListResponse>> listProduct(
            @ParameterObject @PageableDefault(sort = "name", direction = Direction.ASC) Pageable pageable,
            @Parameter(description = "Nome do produto para filtrar (opcional)") @RequestParam(required = false) String name) {
        return productService.listProduct(name, pageable);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Obter Produto por ID",
            description = "Retorna um produto com base no ID fornecido"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductListResponse.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public EntityModel<ProductListResponse> readProductById(
            @Parameter(description = "ID do produto a ser buscado") @PathVariable Long id) {
        return productService.readProductById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Editar Produto",
            description = "Atualiza um produto existente com os dados fornecidos no corpo da requisição"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados de entrada")
    })
    public Product editProduct(
            @Parameter(description = "ID do produto a ser atualizado") @PathVariable Long id,
            @Parameter(description = "Novos dados do produto") @RequestBody Product product) {
        return productService.editProduct(id, product);
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
    public void deleteProduct(
            @Parameter(description = "ID do produto a ser excluído") @PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
