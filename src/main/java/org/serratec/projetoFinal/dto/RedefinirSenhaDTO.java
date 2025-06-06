package org.serratec.projetoFinal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RedefinirSenhaDTO {
	
	@NotBlank(message = "O código não pode estar vazio.")
	private String codigo;
	
	@NotBlank(message = "A senha não pode ser vazia.")
	@Size(min = 8, message = "A senha deve ter no mínimo {min} caracteres")
	private String novaSenha;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	
}
