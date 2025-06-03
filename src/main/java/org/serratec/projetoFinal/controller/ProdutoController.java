package org.serratec.projetoFinal.controller;

import java.net.URI;
import java.util.List;

import org.serratec.projetoFinal.domain.Categoria;
import org.serratec.projetoFinal.domain.Produto;
import org.serratec.projetoFinal.dto.ClienteDTO;
import org.serratec.projetoFinal.dto.ProdutoDTO;
import org.serratec.projetoFinal.dto.ProdutoInserirDTO;
import org.serratec.projetoFinal.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@PostMapping("/inserirProdutos")
	@Operation(summary = "Insere um novo produto", 
	description = "A resposta lista os dados do produto inserido")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ProdutoDTO.class), 
			mediaType = "application/json")}, description = "Retorna o produto inserido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
	public ResponseEntity<ProdutoDTO> inserirProdutos(@Valid @RequestBody ProdutoInserirDTO produtoIns) {
		ProdutoDTO produtoDTO = produtoService.inserir(produtoIns);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(produtoDTO);
	}

	@GetMapping
	@Operation(summary = "Lista todos os produtos", 
	description = "A resposta lista os produtos")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ProdutoDTO.class), 
			mediaType = "application/json")}, description = "Retorna os produtos"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
	public ResponseEntity<List<ProdutoDTO>> listar() {
		return ResponseEntity.ok(produtoService.listarTodos());
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Edita um produto", 
	description = "A resposta lista os dados do produto editado")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ProdutoDTO.class), 
			mediaType = "application/json")}, description = "Retorna o produto editado"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
	public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id,
			@Valid @RequestBody ProdutoInserirDTO produtoIns) {
		
		ProdutoDTO produtoAtt = produtoService.editar(id, produtoIns);
		return ResponseEntity.ok(produtoAtt);
	}
	
	@GetMapping("/listarPaginado")
	@Operation(summary = "Lista todos os produtos", 
	description = "A resposta lista os produtos por paginas")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ProdutoDTO.class), 
			mediaType = "application/json")}, description = "Retorna os produtos"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
	public ResponseEntity<Page<ProdutoDTO>> listarPaginado(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) 
	Pageable pageable) {
		return ResponseEntity.ok(produtoService.listarProdutoPaginado(pageable));
	}
	
	@GetMapping("/buscarPorValor") 
	@Operation(summary = "Lista os produtos por faixa de valor", 
	description = "A resposta lista os produtos na faixa de valor escolhida")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ProdutoDTO.class), 
			mediaType = "application/json")}, description = "Retorna os produtos"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
		public ResponseEntity<List<ProdutoDTO>> buscarPorValor(@RequestParam Double valorMinimo, @RequestParam Double valorMaximo) {
		List<ProdutoDTO> produtos = produtoService.listarPorValor(valorMinimo, valorMaximo);
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/buscarPorNome")
	@Operation(summary = "Lista todos os produtos", 
	description = "A resposta lista os produtos por nome")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ProdutoDTO.class), 
			mediaType = "application/json")}, description = "Retorna os produtos com o nome escolhido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
	public ResponseEntity<List<ProdutoDTO>> buscarPorNome(@RequestParam String nome){
		List<ProdutoDTO> produtos = produtoService.listarPorNome(nome);
		return ResponseEntity.ok(produtos);
	}
	
}
