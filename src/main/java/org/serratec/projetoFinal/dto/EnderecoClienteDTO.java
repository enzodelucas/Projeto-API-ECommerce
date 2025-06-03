package org.serratec.projetoFinal.dto;

import org.serratec.projetoFinal.domain.ClienteEndereco;

public class EnderecoClienteDTO {
	private Long id;
	private EnderecoDTO enderecoDTO;
	private String complemento;
	private Integer numero;
	
	public EnderecoClienteDTO(ClienteEndereco clienteEndereco) {
		this.id = clienteEndereco.getId();
		this.enderecoDTO = new EnderecoDTO(clienteEndereco.getEndereco());
		this.complemento = clienteEndereco.getComplemento();
		this.numero = clienteEndereco.getNumero();
		
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	
	

}
