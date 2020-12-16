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
public class ProdutoRequisicaoCompra {
    private int idProduto;
    private int idRequisicaoCompra;

    public ProdutoRequisicaoCompra() {
    }

    public ProdutoRequisicaoCompra(int idProduto, int idRequisicaoCompra) {
        this.idProduto = idProduto;
        this.idRequisicaoCompra = idRequisicaoCompra;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdRequisicaoCompra() {
        return idRequisicaoCompra;
    }

    public void setIdRequisicaoCompra(int idRequisicaoCompra) {
        this.idRequisicaoCompra = idRequisicaoCompra;
    }
    
    
}
