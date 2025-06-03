package org.serratec.projetoFinal.controller;

import java.net.URI;
import java.util.List;

import org.serratec.projetoFinal.domain.Categoria;
import org.serratec.projetoFinal.domain.Produto;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@PostMapping("/inserirProdutos")
	public ResponseEntity<ProdutoDTO> inserirProdutos(@Valid @RequestBody ProdutoInserirDTO produtoIns) {
		ProdutoDTO produtoDTO = produtoService.inserir(produtoIns);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(produtoDTO);
	}

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> listar() {
		return ResponseEntity.ok(produtoService.listarTodos());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id,
			@Valid @RequestBody ProdutoInserirDTO produtoIns) {
		
		ProdutoDTO produtoAtt = produtoService.editar(id, produtoIns);
		return ResponseEntity.ok(produtoAtt);
	}
	
	@GetMapping("/listarPaginado")
	public ResponseEntity<Page<ProdutoDTO>> listarPaginado(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) 
	Pageable pageable) {
		return ResponseEntity.ok(produtoService.listarProdutoPaginado(pageable));
	}
	
	@GetMapping("/buscarPorValor") 
		public ResponseEntity<List<ProdutoDTO>> buscarPorValor(@RequestParam Double valorMinimo, @RequestParam Double valorMaximo) {
		List<ProdutoDTO> produtos = produtoService.listarPorValor(valorMinimo, valorMaximo);
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/buscarPorNome")
	public ResponseEntity<List<ProdutoDTO>> buscarPorNome(@RequestParam String nome){
		List<ProdutoDTO> produtos = produtoService.listarPorNome(nome);
		return ResponseEntity.ok(produtos);
	}
	
}
