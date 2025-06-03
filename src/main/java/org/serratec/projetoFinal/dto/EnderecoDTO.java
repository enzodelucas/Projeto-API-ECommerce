package org.serratec.projetoFinal.dto;

import org.serratec.projetoFinal.domain.Endereco;

public class EnderecoDTO {
	private String cep;
	
	private String bairro;
	
	private String uf;
	
	private String localidade;
	
	private String logradouro;
	
	
	public EnderecoDTO(Endereco endereco) {
		this.cep = endereco.getCep();
		this.bairro = endereco.getBairro();
		this.uf = endereco.getUf();
		this.localidade = endereco.getLocalidade();
		this.logradouro = endereco.getLogradouro();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	

}
