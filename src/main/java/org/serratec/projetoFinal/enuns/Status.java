package org.serratec.projetoFinal.enuns;

import org.serratec.projetoFinal.exception.EnumException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
	
	PEDIDO_RECEBIDO("Pedido Recebido"),
	AGUARDANDO_PAGAMENTO("Aguardando Pagamento"),
	PROCESSANDO("Processando"),
	ENVIADO("Enviado"),
	ENTREGUE("Entregue"),
	CANCELADO("Cancelado");
	
	private String tipo;

	private Status(String tipo) {
	this.tipo = tipo;
	}

	@JsonValue
	public String getTipo() {
		return tipo;
	}
	
	 @JsonCreator
	 public static Status validar(Object valor) throws EnumException{
		 if (valor instanceof String) {
			 for (Status st : values()) {
				 if(valor.equals(st.getTipo())) {
					 return st;
				 }
			 } 
			 throw new EnumException("Status inválido");
		 } throw new EnumException("Dado deve ser uma string");
	 }
	 
}
