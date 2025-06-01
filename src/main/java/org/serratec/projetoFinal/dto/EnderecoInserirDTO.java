package org.serratec.projetoFinal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EnderecoInserirDTO {
	
	@NotBlank(message = "O cep deve ser preenchido.")
	private String cep;

	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}


}
