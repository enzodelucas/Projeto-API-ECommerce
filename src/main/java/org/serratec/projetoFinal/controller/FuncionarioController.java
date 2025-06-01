package org.serratec.projetoFinal.controller;

import java.net.URI;

import org.serratec.projetoFinal.dto.FuncionarioDTO;
import org.serratec.projetoFinal.dto.FuncionarioInserirDTO;
import org.serratec.projetoFinal.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;

	@PostMapping
	public ResponseEntity<FuncionarioDTO> inserirFuncionario(@Valid @RequestBody FuncionarioInserirDTO funcionarioIns) {
		FuncionarioDTO funcionarioDTO = funcionarioService.inserir(funcionarioIns);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(funcionarioDTO);
	}
}
