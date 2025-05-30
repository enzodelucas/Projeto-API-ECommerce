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

import jakarta.validation.Valid;

@RequestMapping("/categorias")
@RestController
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@PostMapping
	public ResponseEntity<Categoria> inserirCategoria(@Valid @RequestBody Categoria categoria) {
		categoriaService.inserir(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).body(categoria);
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias() {
		return ResponseEntity.ok(categoriaService.listar());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id,
			@Valid @RequestBody Categoria categoria) {
		Categoria categoriaAtt = categoriaService.editar(id, categoria);
		if (categoriaAtt == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoriaAtt);
	}

}
