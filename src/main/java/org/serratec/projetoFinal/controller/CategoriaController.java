package org.serratec.projetoFinal.controller;

import java.net.URI;
import java.util.List;


import org.serratec.projetoFinal.domain.Categoria;
import org.serratec.projetoFinal.exception.CategoriaException;
import org.serratec.projetoFinal.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RequestMapping("/categorias")
@RestController
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	
	@PostMapping("/inserirCategoria")
	@Operation(summary = "Insere uma nova categorias", 
	description = "A resposta lista os dados da categoria inserida")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = Categoria.class), 
			mediaType = "application/json")}, description = "Retorna a Categoria inserida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<Categoria> inserirCategoria(@Valid @RequestBody Categoria categoria) {
		categoriaService.inserir(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).body(categoria);
	}

	@GetMapping
	@Operation(summary = "Lista todos as categorias", 
	description = "A resposta lista os dados das categorias")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = Categoria.class), 
			mediaType = "application/json")}, description = "Retorna todas as categorias"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<List<Categoria>> listarCategorias() {
		return ResponseEntity.ok(categoriaService.listar());
	}

	@PutMapping("/{id}")
	@Operation(summary = "Edita uma categorias", 
	description = "A resposta lista os dados da categoria editada")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = Categoria.class), 
			mediaType = "application/json")}, description = "Retorna a categoria editada"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id,
			@Valid @RequestBody Categoria categoria) {
		Categoria categoriaAtt = categoriaService.editar(id, categoria);
		return ResponseEntity.ok(categoriaAtt);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Lista uma categoria por id", 
	description = "A resposta lista os dados da categoria escolhida")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = Categoria.class), 
			mediaType = "application/json")}, description = "Retorna a categoria escolhida"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long id){
		Categoria categoria = categoriaService.buscarCategoria(id);
		
		return ResponseEntity.ok(categoria);
	}

}
