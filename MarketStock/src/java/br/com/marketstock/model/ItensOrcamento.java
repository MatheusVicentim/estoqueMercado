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
// Feito pelo Fornecedor
public class ItensOrcamento {
    private int idItensOrcamento;
    private double valorItensOrcamento;
    private String nomeProduto;
    private int qtdProduto;
    private String situacao;
    private Orcamento orcamento;

    public ItensOrcamento() {
    }

    public ItensOrcamento(int idItensOrcamento, double valorItensOrcamento, String nomeProduto, int qtdProduto, String situacao, Orcamento orcamento) {
        this.idItensOrcamento = idItensOrcamento;
        this.valorItensOrcamento = valorItensOrcamento;
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.situacao = situacao;
        this.orcamento = orcamento;
    }

    public int getIdItensOrcamento() {
        return idItensOrcamento;
    }

    public void setIdItensOrcamento(int idItensOrcamento) {
        this.idItensOrcamento = idItensOrcamento;
    }

    public double getValorItensOrcamento() {
        return valorItensOrcamento;
    }

    public void setValorItensOrcamento(double valorItensOrcamento) {
        this.valorItensOrcamento = valorItensOrcamento;
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

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
    
}
