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
public class ItensPedidoCompra {

   private int idItensPedidoCompra;
   private String nomeProduto;
   private int qtdProduto;
   private double vlrUnitario;
   private String situacao;
   private PedidoCompra pedidoCompra;

   public ItensPedidoCompra() {
   }

   public ItensPedidoCompra(int idItensPedidoCompra, String nomeProduto, int qtdProduto, double vlrUnitario, String situacao, PedidoCompra pedidoCompra) {
      this.idItensPedidoCompra = idItensPedidoCompra;
      this.nomeProduto = nomeProduto;
      this.qtdProduto = qtdProduto;
      this.vlrUnitario = vlrUnitario;
      this.situacao = situacao;
      this.pedidoCompra = pedidoCompra;
   }

   public int getIdItensPedidoCompra() {
      return idItensPedidoCompra;
   }

   public void setIdItensPedidoCompra(int idItensPedidoCompra) {
      this.idItensPedidoCompra = idItensPedidoCompra;
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

   public double getVlrUnitario() {
      return vlrUnitario;
   }

   public void setVlrUnitario(double vlrUnitario) {
      this.vlrUnitario = vlrUnitario;
   }

   public String getSituacao() {
      return situacao;
   }

   public void setSituacao(String situacao) {
      this.situacao = situacao;
   }

   public PedidoCompra getPedidoCompra() {
      return pedidoCompra;
   }

   public void setPedidoCompra(PedidoCompra pedidoCompra) {
      this.pedidoCompra = pedidoCompra;
   }

}
