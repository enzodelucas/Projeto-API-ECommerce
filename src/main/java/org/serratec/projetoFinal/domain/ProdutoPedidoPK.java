package org.serratec.projetoFinal.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ProdutoPedidoPK implements Serializable{


    private static final long serialVersionUID = 1L;

    @JsonBackReference("produto")
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @JsonBackReference("pedido")
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProdutoPedidoPK other = (ProdutoPedidoPK) obj;
        return Objects.equals(pedido, other.pedido) && Objects.equals(produto, other.produto);
    }



}

