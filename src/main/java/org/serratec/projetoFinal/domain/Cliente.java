package org.serratec.projetoFinal.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.projetoFinal.dto.ClienteInserirDTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome não pode ficar vazio.")
	@Size(min = 3, max = 150, message = "O nome deve ter no mínimo {min} letras e no máximo {max} letras.")
	private String nome;

	@NotBlank(message = "O telefone não pode ficar vazio.")
	private String telefone;

	@NotBlank(message = "O email não pode ficar vazio.")
	@Email(message = "O email deve ser preenchido corretamente. ex: email@exemplo.com")
	private String email;

	@NotBlank(message = "O CPF não pode ficar vazio.")
	@CPF(message = "O CPF deve ser preenchido corretamente.")
	private String cpf;

	@NotBlank(message = "A senha não pode ser vazia.")
	@Size(min = 8, max = 16, message = "A senha deve ter no mínimo {min} caracteres e no máximo {max} caracteres.")
	private String senha;

	@JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) //teste
	private List<ClienteEndereco> enderecos = new ArrayList<>();
	
	public Cliente () {}

	public Cliente (ClienteInserirDTO clienteIns) {
		this.nome = clienteIns.getNome();
		this.email = clienteIns.getEmail();
		this.senha = clienteIns.getSenha();
		this.cpf = clienteIns.getCpf();
		this.telefone = clienteIns.getTelefone();
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<ClienteEndereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<ClienteEndereco> enderecos) {
		this.enderecos = enderecos;
	}




}
