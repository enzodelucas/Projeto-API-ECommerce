package org.serratec.projetoFinal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EnderecoInserirDTO {
	
	@NotBlank(message = "O cep deve ser preenchido.")
	@Pattern (regexp = "^[0-9]{8}$", message = "O CEP deve conter 8 dígitos numericos.")
	private String cep;

	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}


}
