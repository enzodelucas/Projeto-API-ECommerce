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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/solicitarRecuperacao")
	public ResponseEntity<String> solicitarRecuperacao(@RequestBody SolicitarRecuperacaoSenhaDTO solicitacao){
		usuarioService.solicitarRecuperacao(solicitacao);
		return ResponseEntity.ok("Código de recuperação enviado por e-mail");
	}
	
	@PostMapping("/redifinirSenha")
	public ResponseEntity<String> redifinirSenha(@RequestBody RedefinirSenhaDTO redefinirSenha){
		usuarioService.resetarSenha(redefinirSenha);
		return ResponseEntity.ok("Senha redifinida com sucesso.");
	}
}
