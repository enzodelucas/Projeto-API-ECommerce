package org.serratec.projetoFinal.domain;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class ProdutoPedido {
	
	@EmbeddedId
	private ProdutoPedidoPK id = new ProdutoPedidoPK();

	@NotNull(message = "A quantidade não pode ser vazia.")
	private Integer quantidade;
	
	private BigDecimal valor;
	
	private BigDecimal desconto;
	
	
	
	
	public ProdutoPedido () {}


	public ProdutoPedido(Pedido pedido, Produto produto, Integer quantidade, BigDecimal valor,
			BigDecimal desconto) {
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.quantidade = quantidade;
		this.valor = valor;
		this.desconto = desconto;
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


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public BigDecimal getDesconto() {
		return desconto;
	}


	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	
	
	
}
