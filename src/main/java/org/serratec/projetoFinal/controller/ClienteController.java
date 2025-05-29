package org.serratec.projetoFinal.controller;

import java.net.URI;
import java.util.List;

import org.serratec.projetoFinal.dto.ClienteDTO;
import org.serratec.projetoFinal.dto.ClienteInserirDTO;
import org.serratec.projetoFinal.repository.ClienteRepository;
import org.serratec.projetoFinal.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<ClienteDTO> inserirCliente(@Valid @RequestBody ClienteInserirDTO clienteIns) {
		ClienteDTO clienteDTO = clienteService.inserir(clienteIns);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(clienteDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(clienteDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		return ResponseEntity.ok(clienteService.listar());
	}
	
}
