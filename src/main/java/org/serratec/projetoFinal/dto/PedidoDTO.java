package org.serratec.projetoFinal.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.projetoFinal.domain.Pedido;
import org.serratec.projetoFinal.domain.ProdutoPedido;
import org.serratec.projetoFinal.enuns.FormaPgto;
import org.serratec.projetoFinal.enuns.Status;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "id", "dataPedido", "emailCliente", "cepCliente",
    "produtos", "valorFinal", "desconto", "formaPgto", "status"
}) // anotação para sempre que conventer para json, aparecer assim
public class PedidoDTO {

	private Long id;

	private String emailCliente;
	
	private String cepCliente;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataPedido;
	
	private Double valorFinal;
	
	private FormaPgto formaPgto;
	
	private Status status;
	
	private Double desconto;
	
	private List<ProdutoPedidoDTO> produtos = new ArrayList<>();

	public PedidoDTO(Pedido pedido) {
	    this.id = pedido.getId();
	    this.status = pedido.getStatus();
	    this.emailCliente = pedido.getCliente().getEmail();
	    this.cepCliente = pedido.getEnderecoEntrega().getEndereco().getCep();
	    this.dataPedido = pedido.getDataPedido();
	    this.formaPgto = pedido.getFormaPgto();
	    this.desconto = pedido.getDesconto();
	    this.valorFinal = pedido.getValorFinal(); 
	    for (ProdutoPedido p : pedido.getProdutos()) {
	        this.produtos.add(new ProdutoPedidoDTO(p));
	    }
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

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public List<ProdutoPedidoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoPedidoDTO> produtos) {
		this.produtos = produtos;
	}
	
	
	
	
}
