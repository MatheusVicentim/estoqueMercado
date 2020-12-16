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
public class Lote {

   private int idLote;
   private String numLote;
   private String dataFabricacao;
   private String dataVencimento;
   private int quantDiaVencimento;
   private String descLote;
   private String situacao;
   

   public Lote() {
   }

   public Lote(int idLote, String numLote, String dataFabricacao, String dataVencimento, int quantDiaVencimento, String descLote, String situacao) {
      this.idLote = idLote;
      this.numLote = numLote;
      this.dataFabricacao = dataFabricacao;
      this.dataVencimento = dataVencimento;
      this.quantDiaVencimento = quantDiaVencimento;
      this.descLote = descLote;
      this.situacao = situacao;
   }

   public int getIdLote() {
      return idLote;
   }

   public void setIdLote(int idLote) {
      this.idLote = idLote;
   }

   public String getNumLote() {
      return numLote;
   }

   public void setNumLote(String numLote) {
      this.numLote = numLote;
   }

   public String getDataFabricacao() {
      return dataFabricacao;
   }

   public void setDataFabricacao(String dataFabricacao) {
      this.dataFabricacao = dataFabricacao;
   }

   public String getDataVencimento() {
      return dataVencimento;
   }

   public void setDataVencimento(String dataVencimento) {
      this.dataVencimento = dataVencimento;
   }

   public int getQuantDiaVencimento() {
      return quantDiaVencimento;
   }

   public void setQuantDiaVencimento(int quantDiaVencimento) {
      this.quantDiaVencimento = quantDiaVencimento;
   }

   public String getDescLote() {
      return descLote;
   }

   public void setDescLote(String descLote) {
      this.descLote = descLote;
   }

   public String getSituacao() {
      return situacao;
   }

   public void setSituacao(String situacao) {
      this.situacao = situacao;
   }

}
