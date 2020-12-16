/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.model;

/**
 *
 * @author Gabriel Vinícius
 */
public class RequisicaoCompra {
    private int idRequisicaoCompra;
    private String descricaoRequisicaoCompra;
    private String situacao;

    public RequisicaoCompra() {
    }

    public RequisicaoCompra(int idRequisicaoCompra, String descricaoRequisicaoCompra, String situacao) {
        this.idRequisicaoCompra = idRequisicaoCompra;
        this.descricaoRequisicaoCompra = descricaoRequisicaoCompra;
        this.situacao = situacao;
    }

    public int getIdRequisicaoCompra() {
        return idRequisicaoCompra;
    }

    public void setIdRequisicaoCompra(int idRequisicaoCompra) {
        this.idRequisicaoCompra = idRequisicaoCompra;
    }

    public String getDescricaoRequisicaoCompra() {
        return descricaoRequisicaoCompra;
    }

    public void setDescricaoRequisicaoCompra(String descricaoRequisicaoCompra) {
        this.descricaoRequisicaoCompra = descricaoRequisicaoCompra;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
}
