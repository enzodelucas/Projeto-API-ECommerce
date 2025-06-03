package org.serratec.projetoFinal.dto;

import org.serratec.projetoFinal.enuns.Status;

import jakarta.validation.constraints.NotNull;

public class PedidoAtualizarStatusDTO {
	
	@NotNull(message = "O status não pode ser nulo")
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
}
