/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.model;

/**
 *
 * @author Gabriel Vinicius
 */
public class SubProduto {
    private int idSubProduto;
    private String descricaoSubProduto;
    private String situacao;

    public SubProduto() {
    }

    public SubProduto(int idSubProduto, String descricaoSubProduto, String situacao) {
        this.idSubProduto = idSubProduto;
        this.descricaoSubProduto = descricaoSubProduto;
        this.situacao = situacao;
    }

    public int getIdSubProduto() {
        return idSubProduto;
    }

    public void setIdSubProduto(int idSubProduto) {
        this.idSubProduto = idSubProduto;
    }

    public String getDescricaoSubProduto() {
        return descricaoSubProduto;
    }

    public void setDescricaoSubProduto(String descricaoSubProduto) {
        this.descricaoSubProduto = descricaoSubProduto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
}
