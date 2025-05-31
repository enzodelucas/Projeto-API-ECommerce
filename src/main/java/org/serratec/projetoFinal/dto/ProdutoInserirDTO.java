package org.serratec.projetoFinal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProdutoInserirDTO {
	
	@NotBlank(message = "O nome não pode ser vazio.")
	@Size(max = 200, message = "O nome não pode passar de {max} caracteres.")
	private String nome;
	
	@NotNull(message = "O valor do produto não pode ser vazio.")
	private Double valor;
	
	@NotBlank(message = "A descrição não pode ser vazia.")
	@Size(min = 10, max = 2000, message = "A descrição deve ter no mínimo {min} caracteres e no máximo {max} caracteres.")
	private String descricao;
	
	@NotBlank(message = "O nome da categoria não pode ser vazio.")
	@Size(max = 50, message = "O nome da categoria não pode passar de {max} caracteres.")
	private String categoria;
	

	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
