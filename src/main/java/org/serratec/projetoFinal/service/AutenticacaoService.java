package org.serratec.projetoFinal.service;

import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.Funcionario;
import org.serratec.projetoFinal.domain.Usuario;
import org.serratec.projetoFinal.exception.NaoEncontradoException;
import org.serratec.projetoFinal.exception.UsuarioNaoPermitidoException;
import org.serratec.projetoFinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Cliente clienteAutenticacao() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if (usuario != null) {
			if (usuario instanceof Cliente) {
				return (Cliente) usuario;
			} throw new UsuarioNaoPermitidoException("Este usuário não é um cliente");
		} 
		throw new NaoEncontradoException("Usuário não encontrado");
	}
	
	public Funcionario funcionarioAutenticacao() { //se precisar
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if (usuario != null) {
			if (usuario instanceof Funcionario) {
				return (Funcionario) usuario;
			} throw new UsuarioNaoPermitidoException("Este usuário não é um funcionario");
		} 
		throw new NaoEncontradoException("Usuário não encontrado");
	}
	
}
