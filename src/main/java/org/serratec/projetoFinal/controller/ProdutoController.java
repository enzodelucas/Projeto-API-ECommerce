package org.serratec.projetoFinal.controller;

import java.net.URI;
import java.util.List;

import org.serratec.projetoFinal.domain.Produto;
import org.serratec.projetoFinal.dto.ProdutoDTO;
import org.serratec.projetoFinal.dto.ProdutoInserirDTO;
import org.serratec.projetoFinal.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@PostMapping
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
}
