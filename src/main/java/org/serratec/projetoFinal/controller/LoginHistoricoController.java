package org.serratec.projetoFinal.controller;

import java.security.Principal;
import java.util.List;

import org.serratec.projetoFinal.domain.LoginHistorico;
import org.serratec.projetoFinal.dto.ProdutoDTO;
import org.serratec.projetoFinal.service.LoginHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/login/historico")
public class LoginHistoricoController {

	@Autowired
    private LoginHistoricoService loginHistoricoService;

    public LoginHistoricoController(LoginHistoricoService loginHistoricoService) {
        this.loginHistoricoService = loginHistoricoService;
    }

    @GetMapping
    @Operation(summary = "Lista o historico de login", 
	description = "A resposta lista o historco de login")
@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
			content = {@Content(schema = @Schema(implementation = ProdutoDTO.class), 
			mediaType = "application/json")}, description = "Retorna os logins"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
			} ) 
    public List<LoginHistorico> historicoPegar(Principal principal) {
        return loginHistoricoService.historicoPuxa(principal.getName()); // Aqui também é o email
    }
}