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
public class Estoque {
   private Integer idEstoque;
   private Integer quantProdutoEstoque;
   private String tipoEstoque;
   private Produto produto;
   private Lote lote;
   private Notificacao idNotificacao;

   public Estoque() {
   }

   public Estoque(Integer idEstoque, Integer quantProdutoEstoque, String tipoEstoque, Produto produto, Lote lote, Notificacao idNotificacao) {
      this.idEstoque = idEstoque;
      this.quantProdutoEstoque = quantProdutoEstoque;
      this.tipoEstoque = tipoEstoque;
      this.produto = produto;
      this.lote = lote;
      this.idNotificacao = idNotificacao;
   }

   public Integer getIdEstoque() {
      return idEstoque;
   }

   public void setIdEstoque(Integer idEstoque) {
      this.idEstoque = idEstoque;
   }

   public Integer getQuantProdutoEstoque() {
      return quantProdutoEstoque;
   }

   public void setQuantProdutoEstoque(Integer quantProdutoEstoque) {
      this.quantProdutoEstoque = quantProdutoEstoque;
   }

   public String getTipoEstoque() {
      return tipoEstoque;
   }

   public void setTipoEstoque(String tipoEstoque) {
      this.tipoEstoque = tipoEstoque;
   }

   public Produto getProduto() {
      return produto;
   }

   public void setProduto(Produto produto) {
      this.produto = produto;
   }

   public Lote getLote() {
      return lote;
   }

   public void setLote(Lote lote) {
      this.lote = lote;
   }

   public Notificacao getIdNotificacao() {
      return idNotificacao;
   }

   public void setIdNotificacao(Notificacao idNotificacao) {
      this.idNotificacao = idNotificacao;
   }

}
