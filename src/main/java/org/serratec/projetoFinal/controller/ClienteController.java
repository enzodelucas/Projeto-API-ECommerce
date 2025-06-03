package org.serratec.projetoFinal.controller;

import java.net.URI;
import java.util.List;

import org.serratec.projetoFinal.dto.ClienteAtualizarDTO;
import org.serratec.projetoFinal.dto.ClienteDTO;
import org.serratec.projetoFinal.dto.ClienteInserirDTO;
import org.serratec.projetoFinal.dto.EnderecoAtualizarDTO;
import org.serratec.projetoFinal.dto.EnderecoClienteDTO;
import org.serratec.projetoFinal.dto.EnderecoInserirDTO;
import org.serratec.projetoFinal.dto.PedidoDTO;
import org.serratec.projetoFinal.dto.PedidoInserirDTO;
import org.serratec.projetoFinal.service.ClienteService;
import org.serratec.projetoFinal.service.EnderecoService;
import org.serratec.projetoFinal.service.PedidoService;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	PedidoService pedidoService;
	
	@PostMapping 
	@Operation(summary = "Insere um novo cliente", 
	description = "A resposta lista os dados do cliente inserido")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ClienteDTO.class), 
			mediaType = "application/json")}, description = "Retorna o cliente inserido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
	
	public ResponseEntity<ClienteDTO> inserirCliente(@Valid @RequestBody ClienteInserirDTO clienteIns) {
		ClienteDTO clienteDTO = clienteService.inserir(clienteIns);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(clienteDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(clienteDTO);
	}
	
	@GetMapping("/verMeusDados/me") 
	@Operation(summary = "Lista os dados do cliente autenticado", 
	description = "A resposta lista os dados do cliente")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ClienteDTO.class), 
			mediaType = "application/json")}, description = "Retorna todos os cliente"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<ClienteDTO> verMeusDados() {
		return ResponseEntity.ok(clienteService.buscarDados());
	}
	
	@DeleteMapping("/deletarConta/me") 
	@Operation(summary = "Deleta o cliente autenticado", 
	description = "A resposta deleta os dados do cliente")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna ok"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<Void> deletarConta() {
		clienteService.deletar();
		return ResponseEntity.noContent().build();
	}

	@PutMapping("atualizarDados/me")
	@Operation(summary = "Atualiza dados do cliente autenticado", 
	description = "A resposta lista os dados do cliente editado")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ClienteDTO.class), 
			mediaType = "application/json")}, description = "Retorna os dados do cliente"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<ClienteDTO> atualizarDados(@RequestBody ClienteAtualizarDTO clienteAtt){
	ClienteDTO cliente = clienteService.atualizar(clienteAtt);
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping("/inserirEndereco/me") 
	@Operation(summary = "Insere endereço no cliente autenticado", 
	description = "A resposta motra o endereço inserido")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = EnderecoClienteDTO.class), 
			mediaType = "application/json")}, description = "Retorna todos os cliente"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<EnderecoClienteDTO> inserirEndereco(@Valid @RequestBody EnderecoInserirDTO enderecoIns) {	
		EnderecoClienteDTO endereco = clienteService.inserirEndereco(enderecoIns);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(endereco.getId()) //teste
				.toUri();
		
		
		return ResponseEntity.created(uri).body(endereco);
		
	}
	
	@PutMapping("atualizarEndereco/me")
	@Operation(summary = "Atualiza o endereço do cliente autenticado", 
	description = "A resposta lista os dados do endereco editado")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = EnderecoClienteDTO.class), 
			mediaType = "application/json")}, description = "Retorna os dados do cliente"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<EnderecoClienteDTO> atualizarEndereco(@RequestBody EnderecoAtualizarDTO endAtuDTO){
		EnderecoClienteDTO endAtualizadoDTO = clienteService.atualizarEnd(endAtuDTO);
		return ResponseEntity.ok(endAtualizadoDTO);
	}
	
	@DeleteMapping("/me/deletarEndereco/{id}")
	@Operation(summary = "Deleta o endereco do cliente autenticado", 
	description = "A resposta deleta o endereço do cliente autenticado")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna ok"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
		clienteService.deletarEndereço(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/inserirPedido/me")
	@Operation(summary = "Insere um pedido no cliente autenticado", 
	description = "A resposta lista o pedido inserido")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = PedidoDTO.class), 
			mediaType = "application/json")}, description = "Retorna os dados do pedido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	
	public ResponseEntity<PedidoDTO> inserirPedido(@Valid @RequestBody PedidoInserirDTO pedidoIns){
		PedidoDTO pedidoDTO = pedidoService.inserirPedido(pedidoIns);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(pedidoDTO.getId())
				.toUri();
		return	ResponseEntity.created(uri).body(pedidoDTO);
	}
	
	@GetMapping("/me/listarPedidoId/{id}")
	@Operation(summary = "Lista pedido por id do cliente autenticado", 
	description = "A resposta lista o pedido escolhido")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = PedidoDTO.class), 
			mediaType = "application/json")}, description = "Retorna os dados do pedido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<PedidoDTO> listarPedidoId(@PathVariable Long id){
		PedidoDTO pedidoDTO = pedidoService.listarPedidosId(id);
		return ResponseEntity.ok(pedidoDTO);
		
	}
	
	@PutMapping("/me/cancelarPedido/{id}")
	@Operation(summary = "Edita o status do pedido por id do cliente autenticado para cancelado", 
	description = "A resposta lista o status do pedido como cancelado")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = PedidoDTO.class), 
			mediaType = "application/json")}, description = "Retorna os dados do pedido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<PedidoDTO> cancelarPedido(@PathVariable Long id){
		PedidoDTO pedidoDTO = pedidoService.pedidoCancelado(id);
		return ResponseEntity.ok(pedidoDTO);
	}
	
	@PutMapping("/me/marcarPedidoEntregue/{id}")
	@Operation(summary = "Edita o status do pedido por id do cliente autenticado para entregue", 
	description = "A resposta lista o status do pedido como entregue")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = PedidoDTO.class), 
			mediaType = "application/json")}, description = "Retorna os dados do pedido"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
	public ResponseEntity<PedidoDTO> marcarEntregue(@PathVariable Long id){
		PedidoDTO pedidoDTO = pedidoService.pedidoEntregue(id);
		return ResponseEntity.ok(pedidoDTO);
	}
	
	@GetMapping("/me/listarEndereco")
	@Operation(summary = "Lista o endereço do cliente", 
	description = " A resposta lista o endereço do cliente autenticado")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = EnderecoClienteDTO.class), 
			mediaType = "application/json")}, description = "Retorna o endereço"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
		})
    public ResponseEntity<List<EnderecoClienteDTO>> listarEnderecos() {
        List<EnderecoClienteDTO> enderecoClienteDTO = enderecoService.listarEnderecoCliente();
        return ResponseEntity.ok(enderecoClienteDTO);

    }
	
	
	
}
