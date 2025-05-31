package org.serratec.projetoFinal.exception;

public class UsuarioNaoPermitidoException extends RuntimeException {

	public UsuarioNaoPermitidoException(String mensagem) {
		super(mensagem);
	}
}
