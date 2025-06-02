package org.serratec.projetoFinal.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;


@Entity
public class ProdutoPedido {
	
	@EmbeddedId
	private ProdutoPedidoPK id = new ProdutoPedidoPK();

	@NotNull(message = "A quantidade não pode ser vazia.")
	private Integer quantidade;
	
	private Double valor = id.getProduto().getValor() * quantidade;
	
	
	public ProdutoPedido () {}


	public ProdutoPedido(Pedido pedido, Produto produto, Integer quantidade, Double valor) {
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.quantidade = quantidade;
		this.valor = valor;
	}


	public ProdutoPedidoPK getId() {
		return id;
	}


	public void setId(ProdutoPedidoPK id) {
		this.id = id;
	}


	public void setPedido(Pedido pedido) {
		this.id.setPedido(pedido);
	}



	public void setProduto(Produto produto) {
		this.id.setProduto(produto);
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}

	
	
	
}
