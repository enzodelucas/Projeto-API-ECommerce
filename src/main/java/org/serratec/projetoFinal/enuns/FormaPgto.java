package org.serratec.projetoFinal.enuns;

import org.serratec.projetoFinal.exception.EnumException;

import com.fasterxml.jackson.annotation.JsonCreator;
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
	
	@JsonCreator
	 public static FormaPgto validar(Object valor) throws EnumException{
		 if (valor instanceof String) {
			 for (FormaPgto f : values()) {
				 if(valor.equals(f.getTipo())) {
					 return f;
				 }
			 } 
			 throw new EnumException("Forma de pagamento inválido");
		 } throw new EnumException("Dado deve ser uma string");
	 }
	
}
