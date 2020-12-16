/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.model;

/**
 *
 * @author mathe
 */
public class GenericClass {

   private int qtdProdutoEstoque;
   private int diasVencimento;
   private int idProduto;
   private int idLote;
   private int idNotificacao;
   private int idPedidoCompra;

   private String nomeProduto;
   private String dtaVencimento;
   private String numLote;
   private String notificacao;
   private String descNotificacao;
   private String desc;
   private String tipoPagamento;
   private String nomePessoa;
   private String descricaoPedido;

   public GenericClass() {
   }

   public GenericClass(int qtdProdutoEstoque, int diasVencimento, int idProduto, int idLote, int idNotificacao, int idPedidoCompra, String nomeProduto, String dtaVencimento, String numLote, String notificacao, String descNotificacao, String desc, String tipoPagamento, String nomePessoa, String descricaoPedido) {
      this.qtdProdutoEstoque = qtdProdutoEstoque;
      this.diasVencimento = diasVencimento;
      this.idProduto = idProduto;
      this.idLote = idLote;
      this.idNotificacao = idNotificacao;
      this.idPedidoCompra = idPedidoCompra;
      this.nomeProduto = nomeProduto;
      this.dtaVencimento = dtaVencimento;
      this.numLote = numLote;
      this.notificacao = notificacao;
      this.descNotificacao = descNotificacao;
      this.desc = desc;
      this.tipoPagamento = tipoPagamento;
      this.nomePessoa = nomePessoa;
      this.descricaoPedido = descricaoPedido;
   }

   public int getQtdProdutoEstoque() {
      return qtdProdutoEstoque;
   }

   public void setQtdProdutoEstoque(int qtdProdutoEstoque) {
      this.qtdProdutoEstoque = qtdProdutoEstoque;
   }

   public int getDiasVencimento() {
      return diasVencimento;
   }

   public void setDiasVencimento(int diasVencimento) {
      this.diasVencimento = diasVencimento;
   }

   public int getIdProduto() {
      return idProduto;
   }

   public void setIdProduto(int idProduto) {
      this.idProduto = idProduto;
   }

   public int getIdLote() {
      return idLote;
   }

   public void setIdLote(int idLote) {
      this.idLote = idLote;
   }

   public int getIdNotificacao() {
      return idNotificacao;
   }

   public void setIdNotificacao(int idNotificacao) {
      this.idNotificacao = idNotificacao;
   }

   public int getIdPedidoCompra() {
      return idPedidoCompra;
   }

   public void setIdPedidoCompra(int idPedidoCompra) {
      this.idPedidoCompra = idPedidoCompra;
   }

   public String getNomeProduto() {
      return nomeProduto;
   }

   public void setNomeProduto(String nomeProduto) {
      this.nomeProduto = nomeProduto;
   }

   public String getDtaVencimento() {
      return dtaVencimento;
   }

   public void setDtaVencimento(String dtaVencimento) {
      this.dtaVencimento = dtaVencimento;
   }

   public String getNumLote() {
      return numLote;
   }

   public void setNumLote(String numLote) {
      this.numLote = numLote;
   }

   public String getNotificacao() {
      return notificacao;
   }

   public void setNotificacao(String notificacao) {
      this.notificacao = notificacao;
   }

   public String getDescNotificacao() {
      return descNotificacao;
   }

   public void setDescNotificacao(String descNotificacao) {
      this.descNotificacao = descNotificacao;
   }

   public String getDesc() {
      return desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public String getTipoPagamento() {
      return tipoPagamento;
   }

   public void setTipoPagamento(String tipoPagamento) {
      this.tipoPagamento = tipoPagamento;
   }

   public String getNomePessoa() {
      return nomePessoa;
   }

   public void setNomePessoa(String nomePessoa) {
      this.nomePessoa = nomePessoa;
   }

   public String getDescricaoPedido() {
      return descricaoPedido;
   }

   public void setDescricaoPedido(String descricaoPedido) {
      this.descricaoPedido = descricaoPedido;
   }

}
