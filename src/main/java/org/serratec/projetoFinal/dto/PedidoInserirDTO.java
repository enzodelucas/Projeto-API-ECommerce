package org.serratec.projetoFinal.dto;

import java.util.List;

import org.serratec.projetoFinal.domain.ProdutoExemplo;
import org.serratec.projetoFinal.enuns.FormaPgto;

public class PedidoInserirDTO {

	private List<ProdutoExemplo> produto;
	
	private Long idEndereco;
	
	private FormaPgto formaPgto;

	
	
	public List<ProdutoExemplo> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoExemplo> produto) {
		this.produto = produto;
	}

	
	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public FormaPgto getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}
	
	
	
	
	
	
	
	
}
