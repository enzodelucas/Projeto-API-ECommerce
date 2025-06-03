package org.serratec.projetoFinal.dto;

import org.serratec.projetoFinal.domain.ProdutoPedido;

public class ProdutoPedidoDTO {

    private Long id;
    private String nome;
    private Integer quantidade;
    private Double valorUnitario;
    private Double valorTotal;

    public ProdutoPedidoDTO(ProdutoPedido pedidoProduto) {
        this.id = pedidoProduto.getId().getProduto().getId();
        this.nome = pedidoProduto.getId().getProduto().getNome();
        this.quantidade = pedidoProduto.getQuantidade();
        this.valorUnitario = pedidoProduto.getId().getProduto().getValor();
        this.valorTotal = this.valorUnitario * this.quantidade;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}