package org.serratec.projetoFinal.controller;

import java.net.URI;
import java.util.List;

import org.serratec.projetoFinal.dto.ClienteAtualizarDTO;
import org.serratec.projetoFinal.dto.ClienteDTO;
import org.serratec.projetoFinal.dto.ClienteInserirDTO;
import org.serratec.projetoFinal.dto.EnderecoClienteDTO;
import org.serratec.projetoFinal.dto.EnderecoInserirDTO;
import org.serratec.projetoFinal.repository.ClienteEnderecoRepository;
import org.serratec.projetoFinal.service.ClienteService;
import org.serratec.projetoFinal.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	ClienteEnderecoRepository clienteEnderecoRepository;
	
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
	
	@GetMapping("/verMeusDados/me") //deu certo
	public ResponseEntity<ClienteDTO> verMeusDados() {
		return ResponseEntity.ok(clienteService.buscarDados());
	}
	
	@DeleteMapping("/deletarConta/me") //deu certo
	public ResponseEntity<Void> deletarConta() {
		clienteService.deletar();
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/inserirEndereco/me") //deu certo
	public ResponseEntity<EnderecoClienteDTO> inserirEndereco(@Valid @RequestBody EnderecoInserirDTO enderecoIns) {	
		EnderecoClienteDTO endereco = clienteService.inserirEndereco(enderecoIns);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(endereco.getId()) //teste
				.toUri();
		
		
		return ResponseEntity.created(uri).body(endereco);
		
	}
	
	
	@PutMapping("atualizarDados/me") // deu certo
	public ResponseEntity<ClienteDTO> atualizarDados(@RequestBody ClienteAtualizarDTO clienteAtt){
	ClienteDTO cliente = clienteService.atualizar(clienteAtt);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/deletarEndereco/{id}")
	public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
		clienteService.deletarEndereço(id);
		return ResponseEntity.noContent().build();
	}
}
