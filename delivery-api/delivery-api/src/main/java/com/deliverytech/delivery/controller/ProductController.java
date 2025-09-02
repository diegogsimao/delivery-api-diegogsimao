
package com.deliverytech.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.Requests.ProductDTO;
import com.deliverytech.delivery.DTOs.Response.ProductResponseDTO;
import com.deliverytech.delivery.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/produto")
@Tag(name = "Produtos", description = "Operações relacionadas a Produtos")
public class ProductController {

        private ProductService produtoService;

        @Autowired
        public ProductController(
                        ProductService produtoService) {
                this.produtoService = produtoService;
        }

        @PostMapping
        @PreAuthorize("hasRole('RESTAURANTE')")
        @Operation(summary = "Cria um novo Produto")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
        })
        public ProductResponseDTO createProduto(@RequestBody ProductDTO produto) {
                if (produto == null) {
                        throw new IllegalArgumentException("Produto não pode ser nulo");
                }

                return produtoService.create(produto);
        }

        @PutMapping("{id}")
        @Operation(summary = "Atualiza um Produto existente")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado para restaurante pesquisado")
        })
        @PreAuthorize("hasRole('RESTAURANTE')")
        public ResponseEntity<ProductResponseDTO> updateProduto(
                        @PathVariable Long id,
                        @RequestBody ProductDTO produtos) {

                return ResponseEntity.ok(produtoService.update(produtos));
        }

        @PatchMapping("/{id}/disponibilidade")
        @PreAuthorize("hasRole('RESTAURANTE')")
        @Operation(summary = "Atualiza a disponibilidade de um Produto existente")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado para restaurante pesquisado")
        })
        public ResponseEntity<ProductResponseDTO> updateProdutoDisponibilidade(
                        @PathVariable Long id,
                        @RequestBody boolean disponibilidade) {

                return ResponseEntity.ok(
                                produtoService.updateAvailable(id, disponibilidade));
        }

        @DeleteMapping("{id}")
        @PreAuthorize("hasRole('RESTAURANTE')")
        @Operation(summary = "Remove um Produto existente")
        @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "Removido com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado para restaurante pesquisado")
        })
        public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
                produtoService.delete(id);
                return ResponseEntity.noContent().build();
        }

        @GetMapping("/restaurantes/{restauranteId}/produtos")
        @PreAuthorize("hasRole('CLIENTE') or hasRole('RESTAURANTE')")
        @Operation(summary = "Lista todos os Produtos de um Restaurante")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
        })
        public ResponseEntity<ProductResponseDTO> getProdutosByRestauranteId(
                        @PathVariable Long restauranteId) {

                ProductResponseDTO produtos = produtoService.findByRestauranteId(restauranteId);
                return ResponseEntity.ok(produtos);
        }

}
