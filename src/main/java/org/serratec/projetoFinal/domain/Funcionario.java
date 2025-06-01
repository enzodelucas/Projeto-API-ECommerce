package org.serratec.projetoFinal.domain;

import org.serratec.projetoFinal.dto.FuncionarioInserirDTO;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcionario extends Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String nome;

	@Column (nullable=false, length = 11)
	private String telefone;
	
	@Column (nullable=false, unique = true, length = 14)
	private String cpf;

	@Column (nullable = false, length = 50)
	private String cargo;
	
	public Funcionario() {}

	public Funcionario(FuncionarioInserirDTO funcionarioIns) {
		super(funcionarioIns.getEmail(), funcionarioIns.getSenha());
		super.setTipo("FUNCIONARIO");
		this.nome = funcionarioIns.getNome();
		this.telefone = funcionarioIns.getTelefone();
		this.cpf = funcionarioIns.getCpf();
		this.cargo = funcionarioIns.getCargo();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	@Override //mudar depois
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", cpf=" + cpf + ", cargo="
				+ cargo + "]";
	}
	
	
}
