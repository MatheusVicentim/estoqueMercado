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
public class Notificacao {
    private int idNotificacao;
    private int qtdProduto;
    private String statusProduto;
    private String situacao;

    public Notificacao() {
    }

    public Notificacao(int idNotificacao, int qtdProduto, String statusProduto, String situacao) {
        this.idNotificacao = idNotificacao;
        this.qtdProduto = qtdProduto;
        this.statusProduto = statusProduto;
        this.situacao = situacao;
    }

    public int getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(int idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public int getQuantidadeProduto() {
        return qtdProduto;
    }

    public void setQuantidadeProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public String getStatusProduto() {
        return statusProduto;
    }

    public void setStatusProduto(String statusProduto) {
        this.statusProduto = statusProduto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
}
