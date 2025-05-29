package org.serratec.projetoFinal.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FormaPgto {
	CARTAO_CREDITO("Cartão de Crédito"), 
	CARTAO_DEBITO("Cartão de Debito"),
	PIX("Pix");
	
	private String tipo;

	private FormaPgto(String tipo) {
		this.tipo = tipo;
	}
	
	@JsonValue
	public String getTipo() {
		return tipo;
	}
	
	
	//fazer @JsonCreator
	
}
