package org.serratec.projetoFinal.controller;

import org.serratec.projetoFinal.dto.RedefinirSenhaDTO;
import org.serratec.projetoFinal.dto.SolicitarRecuperacaoSenhaDTO;
import org.serratec.projetoFinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/solicitarRecuperacao")
	@Operation(summary = "Solicita recuperação de senha", 
	description = "A resposta envia um codigo de recuperação por e-mail")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "Retorna uma mensagem"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
	public ResponseEntity<String> solicitarRecuperacao(@Valid @RequestBody SolicitarRecuperacaoSenhaDTO solicitacao){
		usuarioService.solicitarRecuperacao(solicitacao);
		return ResponseEntity.ok("Código de recuperação enviado por e-mail");
	}
	
	@PostMapping("/redefinirSenha")
	@Operation(summary = "Redefine a senha", 
	description = "A resposta exibe uma mensagem")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			description = "Retorna uma mensagem"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
	public ResponseEntity<String> redefinirSenha(@Valid @RequestBody RedefinirSenhaDTO redefinirSenha){
		usuarioService.resetarSenha(redefinirSenha);
		return ResponseEntity.ok("Senha redefinida com sucesso.");
	}
}
