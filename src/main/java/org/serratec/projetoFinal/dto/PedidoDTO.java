package org.serratec.projetoFinal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.projetoFinal.domain.Pedido;
import org.serratec.projetoFinal.domain.ProdutoPedido;
import org.serratec.projetoFinal.enuns.FormaPgto;
import org.serratec.projetoFinal.enuns.Status;

public class PedidoDTO {

	private Long id;

	private String emailCliente;
	
	private String cepCliente;
	
	private LocalDate dataPedido;
	
	private Double valorFinal;
	
	private FormaPgto formaPgto;
	
	private Status status;
	
	private Double desconto;
	
	private List<ProdutoPedido> produtos = new ArrayList<>();

	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.emailCliente = pedido.getCliente().getEmail();
		this.cepCliente = pedido.getEnderecoEntrega().getEndereco().getCep();
		this.dataPedido = pedido.getDataPedido();
		this.valorFinal = pedido.getValorFinal();
		this.formaPgto = pedido.getFormaPgto();
		this.status = pedido.getStatus();
		this.produtos = pedido.getProdutos();
		this.desconto = pedido.getDesconto();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getCepCliente() {
		return cepCliente;
	}

	public void setCepCliente(String cepCliente) {
		this.cepCliente = cepCliente;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public FormaPgto getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ProdutoPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoPedido> produtos) {
		this.produtos = produtos;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	
	
	
	
}
