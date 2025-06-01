package org.serratec.projetoFinal.domain;

import java.util.ArrayList;
import java.util.List;

import org.serratec.projetoFinal.dto.ClienteInserirDTO;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {
	

	@Column(nullable = false, length = 150)
	private String nome;

	@Column (nullable=false, length = 11)
	private String telefone;

	@Column (nullable=false, unique = true, length = 14)
	private String cpf;


	@JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) //teste
	private List<ClienteEndereco> enderecos = new ArrayList<>();

	public Cliente () {}

	public Cliente (ClienteInserirDTO clienteIns) {
		super(clienteIns.getEmail(), clienteIns.getSenha());
		super.setTipo("ROLE_CLIENTE");
		this.nome = clienteIns.getNome();
		this.cpf = clienteIns.getCpf();
		this.telefone = clienteIns.getTelefone();
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

	public List<ClienteEndereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<ClienteEndereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public String toString() {
		return "mudar depois";
	}

	





}
