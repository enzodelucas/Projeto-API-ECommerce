package org.serratec.projetoFinal.enuns;

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
	
	// fazer o @JsonCreator
}
