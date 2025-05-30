package org.serratec.projetoFinal.domain;

import org.serratec.projetoFinal.dto.FuncionarioInserirDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcionario extends Usuario{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String telefone;
	
	private String cpf;

	private String cargo;
	

	public Funcionario(FuncionarioInserirDTO funcionarioIns) {
		super(funcionarioIns.getEmail(), funcionarioIns.getSenha());
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
	
	
}
