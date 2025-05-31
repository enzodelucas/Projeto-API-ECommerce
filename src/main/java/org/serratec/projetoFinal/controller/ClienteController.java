package org.serratec.projetoFinal.controller;

import java.net.URI;
import java.util.List;

import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.ClienteEndereco;
import org.serratec.projetoFinal.domain.Endereco;
import org.serratec.projetoFinal.dto.ClienteDTO;
import org.serratec.projetoFinal.dto.ClienteInserirDTO;
import org.serratec.projetoFinal.dto.EnderecoClienteDTO;
import org.serratec.projetoFinal.dto.EnderecoDTO;
import org.serratec.projetoFinal.dto.EnderecoInserirDTO;
import org.serratec.projetoFinal.repository.ClienteEnderecoRepository;
import org.serratec.projetoFinal.service.ClienteService;
import org.serratec.projetoFinal.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//colocar autenticação 
	/*@PostMapping("/InserirEndereco/{id}")
	public ResponseEntity<EnderecoDTO> inserirEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoInserirDTO enderecoIns) {	
		Cliente cliente = clienteService.listarId(id);
		Endereco endereco = enderecoService.buscarInserirTeste(enderecoIns.getCep());	
		ClienteEndereco clienteteste = new ClienteEndereco(cliente, endereco);
		cliente.getEnderecos().add(clienteteste);
		EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
		clienteEnderecoRepository.save(clienteteste);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(endereco.getCep())
				.toUri();
		return ResponseEntity.created(uri).body(enderecoDTO);
		
	}*/ //CORRIGIR 
	
}
