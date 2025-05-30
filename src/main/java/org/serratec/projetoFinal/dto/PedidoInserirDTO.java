package org.serratec.projetoFinal.dto;

import java.util.List;

import org.serratec.projetoFinal.enuns.FormaPgto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PedidoInserirDTO {

	private List<ProdutoInserir> produto;
	
	@NotNull(message = "É necessário um Id de endereço para realizar seu pedido.")
	private Long idEndereco;
	
	private FormaPgto formaPgto;

	
	
	public List<ProdutoInserir> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoInserir> produto) {
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
