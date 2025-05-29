package org.serratec.projetoFinal.dto;

import org.serratec.projetoFinal.domain.ClienteEndereco;

public class EnderecoClienteDTO {
	private Long id;
	private EnderecoDTO enderecoDTO;
	
	public EnderecoClienteDTO(ClienteEndereco clienteEndereco) {
		this.id = clienteEndereco.getId();
		this.enderecoDTO = new EnderecoDTO(clienteEndereco.getEndereco());
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}

	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

	

}
