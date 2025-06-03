package org.serratec.projetoFinal.dto;

import org.serratec.projetoFinal.domain.Endereco;

public class EnderecoAtualizarDTO {
    
    private String complemento;
    
    private Integer numero;
    
    

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

    
    
     /*
    public EnderecoAtualizarDTO(Endereco endereco) {
		this.bairro = endereco.getBairro();
		this.localidade = endereco.getLocalidade();
		this.logradouro = ;
	}*/




}