/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.model;

import java.sql.Date;

/**
 *
 * @author Gabriel Vinicius
 */
public class Movimentacao {
    private int idMovimentacao;
    private String tipoMovimentacao;
    private int qtdMovimentacao;
    private int qtdEstoque;
    private int qtdMinimaEstoque;
    private Date dataMovimentacao;
    private String situacao;
    private Notificacao notificacao;
    private Lote lote;

    public Movimentacao() {
    }

   public Movimentacao(int idMovimentacao, String tipoMovimentacao, int qtdMovimentacao, int qtdEstoque, int qtdMinimaEstoque, Date dataMovimentacao, String situacao, Notificacao notificacao, Lote lote) {
      this.idMovimentacao = idMovimentacao;
      this.tipoMovimentacao = tipoMovimentacao;
      this.qtdMovimentacao = qtdMovimentacao;
      this.qtdEstoque = qtdEstoque;
      this.qtdMinimaEstoque = qtdMinimaEstoque;
      this.dataMovimentacao = dataMovimentacao;
      this.situacao = situacao;
      this.notificacao = notificacao;
      this.lote = lote;
   }

   public int getIdMovimentacao() {
      return idMovimentacao;
   }

   public void setIdMovimentacao(int idMovimentacao) {
      this.idMovimentacao = idMovimentacao;
   }

   public String getTipoMovimentacao() {
      return tipoMovimentacao;
   }

   public void setTipoMovimentacao(String tipoMovimentacao) {
      this.tipoMovimentacao = tipoMovimentacao;
   }

   public int getQtdMovimentacao() {
      return qtdMovimentacao;
   }

   public void setQtdMovimentacao(int qtdMovimentacao) {
      this.qtdMovimentacao = qtdMovimentacao;
   }

   public int getQtdEstoque() {
      return qtdEstoque;
   }

   public void setQtdEstoque(int qtdEstoque) {
      this.qtdEstoque = qtdEstoque;
   }

   public int getQtdMinimaEstoque() {
      return qtdMinimaEstoque;
   }

   public void setQtdMinimaEstoque(int qtdMinimaEstoque) {
      this.qtdMinimaEstoque = qtdMinimaEstoque;
   }

   public Date getDataMovimentacao() {
      return dataMovimentacao;
   }

   public void setDataMovimentacao(Date dataMovimentacao) {
      this.dataMovimentacao = dataMovimentacao;
   }

   public String getSituacao() {
      return situacao;
   }

   public void setSituacao(String situacao) {
      this.situacao = situacao;
   }

   public Notificacao getNotificacao() {
      return notificacao;
   }

   public void setNotificacao(Notificacao notificacao) {
      this.notificacao = notificacao;
   }

   public Lote getLote() {
      return lote;
   }

   public void setLote(Lote lote) {
      this.lote = lote;
   }

}
