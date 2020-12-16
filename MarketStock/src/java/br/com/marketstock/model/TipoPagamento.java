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
public class TipoPagamento {

   private int idTipoPagamento;
   private String metodoPagamento;

   public TipoPagamento() {
   }

   public TipoPagamento(int idTipoPagamento, String metodoPagamento) {
      this.idTipoPagamento = idTipoPagamento;
      this.metodoPagamento = metodoPagamento;
   }

   public int getIdTipoPagamento() {
      return idTipoPagamento;
   }

   public void setIdTipoPagamento(int idTipoPagamento) {
      this.idTipoPagamento = idTipoPagamento;
   }

   public String getMetodoPagamento() {
      return metodoPagamento;
   }

   public void setMetodoPagamento(String metodoPagamento) {
      this.metodoPagamento = metodoPagamento;
   }

}
