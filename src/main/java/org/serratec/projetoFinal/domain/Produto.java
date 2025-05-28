package org.serratec.projetoFinal.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome não pode ser vazio.")
	@Size(max = 200, message = "O nome não pode passar de {max} caracteres.")
	private String nome;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@NotNull(message = "O valor do produto não pode ser vazio.")
	private BigDecimal valor;
	
	@NotBlank(message = "A descrição não pode ser vazia.")
	@Size(min = 10, max = 2000, message = "A descrição deve ter no mínimo {min} caracteres e no máximo {max} caracteres.")
	private String descricao;
	

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
