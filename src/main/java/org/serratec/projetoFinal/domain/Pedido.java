package org.serratec.projetoFinal.domain;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProdutoPedido> produtos = new ArrayList<>();
	
	private LocalDate dataPedido;
	
	@Enumerated(EnumType.STRING)
	private Status status;

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


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	@PrePersist
	public void dataPedido() {
		this.dataPedido = LocalDate.now();
	}

	public List<ProdutoPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoPedido> produtos) {
		this.produtos = produtos;
	}
	
	
	
	
}