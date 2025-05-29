package org.serratec.projetoFinal.dto;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.projetoFinal.domain.Endereco;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteInserirDTO {
	
	@NotBlank(message = "O nome não pode ficar vazio.")
	@Size(min = 3, max = 150, message = "O nome deve ter no mínimo {min} letras e no máximo {max} letras.")
	private String nome;

	@NotBlank(message = "O telefone não pode ficar vazio.")
	@Size(min = 11, message = "O telefone deve ter no mínimo {min} dígitos.")
	@Pattern(regexp = "^[0-9]{11}$", message = "O telefone deve conter 11 dígitos númericos")
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

	private String confirmaSenha;
	
	private EnderecoInserirDTO enderecoInsDTO;


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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public EnderecoInserirDTO getEnderecoInsDTO() {
		return enderecoInsDTO;
	}

	public void setEnderecoInsDTO(EnderecoInserirDTO enderecoInsDTO) {
		this.enderecoInsDTO = enderecoInsDTO;
	}

	

}
