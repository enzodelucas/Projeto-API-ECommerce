package org.serratec.projetoFinal.controller;

import java.security.Principal;
import java.util.List;

import org.serratec.projetoFinal.dto.FavoritoDTO;
import org.serratec.projetoFinal.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;
    
    
    @GetMapping
    @Operation(summary = "Lista os favoritos do cliente", 
    description = "A resposta lista os favoritos pelo id do cliente")
@ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            content = {@Content(schema = @Schema(implementation = FavoritoDTO.class), 
            mediaType = "application/json")}, description = "Retorna os produtos favoritos"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
            } )
    public ResponseEntity<List<FavoritoDTO>> listar(Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok(favoritoService.listarFavoritos(email));
    }

    @PostMapping("/{idProduto}")
    @Operation(summary = "Insere um produto na lista de favoritos", 
    description = "A resposta exibe uma mensagem")
@ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Retorna uma mensagem"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
            } )
    public ResponseEntity<String> adicionar(@PathVariable Long idProduto, Principal principal) {
        String email = principal.getName(); // pgar o email do usuario
        return ResponseEntity.ok(favoritoService.adicionarFavorito(email, idProduto));
    }


    @DeleteMapping("/{idProduto}")
    @Operation(summary = "Deleta um favorito do cliente", 
    description = "A resposta deleta um produto favorito pelo id do cliente e do produto")
@ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Retorna ok"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
            } )
    public ResponseEntity<String> remover(@PathVariable Long idProduto, Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok(favoritoService.removerFavorito(email, idProduto));
    }
}
