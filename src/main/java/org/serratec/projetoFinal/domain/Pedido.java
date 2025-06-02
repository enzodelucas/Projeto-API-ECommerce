package org.serratec.projetoFinal.domain;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.projetoFinal.enuns.FormaPgto;
import org.serratec.projetoFinal.enuns.Status;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference("cliente-pedido")
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	
	@ManyToOne
	@JoinColumn(name = "id_enderecoEntrega")
	private ClienteEndereco enderecoEntrega;
	
	@OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProdutoPedido> produtos = new ArrayList<>();
	
	private LocalDate dataPedido;
	
	private Double valorFinal;
	
	@Enumerated(EnumType.STRING)
	private FormaPgto formaPgto;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Double desconto;
	
	@PrePersist
	public void setarPadrao () {
		this.status = Status.PEDIDO_RECEBIDO;
		this.dataPedido = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteEndereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(ClienteEndereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public List<ProdutoPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoPedido> produtos) {
		this.produtos = produtos;
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
	
	@Override
	public String toString() {
		return "Produtos: " + produtos + 
				"\nData do Pedido: " + dataPedido + 
				 "\nDesconto: " + desconto +
				 "Valor final: " + valorFinal +
				"\nForma de pagamento: " + formaPgto +
				"Status do pedido " + status;
	}
}