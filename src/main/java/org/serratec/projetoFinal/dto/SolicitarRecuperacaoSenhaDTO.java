package org.serratec.projetoFinal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SolicitarRecuperacaoSenhaDTO {

	@NotBlank(message = "O email não pode ficar vazio.")
	@Email(message = "O email deve ser preenchido corretamente. ex: email@exemplo.com")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
