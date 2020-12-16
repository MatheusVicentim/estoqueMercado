/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.model;

import java.sql.Date;

/**
 *
 * @author Gabriel Vinícius
 */
public class ItensRequisicao {
    private int idItensRequisicao;
    private Date dataRequisicao;
    private String nomeProduto;
    private int qtdProduto;
    private String situacao;
    private RequisicaoCompra requisicaoCompra;

    public ItensRequisicao() {
    }

    public ItensRequisicao(int idItensRequisicao, Date dataRequisicao, String nomeProduto, int qtdProduto, String situacao, RequisicaoCompra requisicaoCompra) {
        this.idItensRequisicao = idItensRequisicao;
        this.dataRequisicao = dataRequisicao;
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.situacao = situacao;
        this.requisicaoCompra = requisicaoCompra;
    }

    public int getIdItensRequisicao() {
        return idItensRequisicao;
    }

    public void setIdItensRequisicao(int idItensRequisicao) {
        this.idItensRequisicao = idItensRequisicao;
    }

    public Date getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(Date dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public RequisicaoCompra getRequisicaoCompra() {
        return requisicaoCompra;
    }

    public void setRequisicaoCompra(RequisicaoCompra requisicaoCompra) {
        this.requisicaoCompra = requisicaoCompra;
    }
    
    
    
}
