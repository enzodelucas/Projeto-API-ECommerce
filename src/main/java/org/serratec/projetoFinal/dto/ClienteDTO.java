package org.serratec.projetoFinal.dto;

import java.util.List;

import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.Endereco;
import org.serratec.projetoFinal.domain.Pedido;

public class ClienteDTO {

	private Long id;

	private String nome;

	private String telefone;

	private String email;

	private String cpf;
	
	private Endereco endereco;
	
	private List<Pedido> pedidos;


	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.endereco = cliente.getEndereco(); // teste 
		this.pedidos = cliente.getPedidos(); // teste	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
}
