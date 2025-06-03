package org.serratec.projetoFinal.dto;

import org.serratec.projetoFinal.domain.Produto;

public class ProdutoDTO {

	private Long id;

	private String nome;

	private String categoria;

	private Double valor;

	private String descricao;
	
	private Integer estoque;

	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.categoria = produto.getCategoria().getNome();
		this.descricao = produto.getDescricao();
		this.estoque = produto.getEstoque();
	}

	
	
	public Integer getEstoque() {
		return estoque;
	}



	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
