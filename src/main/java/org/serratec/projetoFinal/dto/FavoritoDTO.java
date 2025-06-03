package org.serratec.projetoFinal.dto;

public class FavoritoDTO {

    private Long idProduto;
    private String nomeProduto;

    public FavoritoDTO(Long idProduto, String nomeProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }
}