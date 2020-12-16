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
public class PedidoCompra {
    private int idPedidoCompra;
    private String descricaoPedido;
    private String situacao;
    private String dtaPedido;
    //private double vlrPedido;
    private Funcionario funcionario;
    //private Orcamento orcamento;
    private TipoPagamento tipoPagamento;

    public PedidoCompra() {
    }

    public PedidoCompra(int idPedidoCompra, String descricaoPedido, String situacao, String dtaPedido, Funcionario funcionario, TipoPagamento tipoPagamento) {
        this.idPedidoCompra = idPedidoCompra;
        this.descricaoPedido = descricaoPedido;
        this.situacao = situacao;
        this.dtaPedido = dtaPedido;
        this.funcionario = funcionario;
        this.tipoPagamento = tipoPagamento;
    }

    public int getIdPedidoCompra() {
        return idPedidoCompra;
    }

    public void setIdPedidoCompra(int idPedidoCompra) {
        this.idPedidoCompra = idPedidoCompra;
    }

    public String getDescricaoPedido() {
        return descricaoPedido;
    }

    public void setDescricaoPedido(String descricaoPedido) {
        this.descricaoPedido = descricaoPedido;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDtaPedido() {
        return dtaPedido;
    }

    public void setDtaPedido(String dtaPedido) {
        this.dtaPedido = dtaPedido;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

   
       
}
