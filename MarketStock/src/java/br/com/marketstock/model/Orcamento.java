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
//Feito pelo fornecedor
public class Orcamento {

   private int idOrcamento;
   private double valorTotal;
   private String descricaoOrcamento;
   private String situacao;
   private Fornecedor fornecedor;

   public Orcamento() {
   }

   public Orcamento(int idOrcamento, double valorTotal, String descricaoOrcamento, String situacao, Fornecedor fornecedor) {
      this.idOrcamento = idOrcamento;
      this.valorTotal = valorTotal;
      this.descricaoOrcamento = descricaoOrcamento;
      this.situacao = situacao;
      this.fornecedor = fornecedor;
   }

   public int getIdOrcamento() {
      return idOrcamento;
   }

   public void setIdOrcamento(int idOrcamento) {
      this.idOrcamento = idOrcamento;
   }

   public double getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(double valorTotal) {
      this.valorTotal = valorTotal;
   }

   public String getDescricaoOrcamento() {
      return descricaoOrcamento;
   }

   public void setDescricaoOrcamento(String descricaoOrcamento) {
      this.descricaoOrcamento = descricaoOrcamento;
   }

   public String getSituacao() {
      return situacao;
   }

   public void setSituacao(String situacao) {
      this.situacao = situacao;
   }

   public Fornecedor getFornecedor() {
      return fornecedor;
   }

   public void setFornecedor(Fornecedor fornecedor) {
      this.fornecedor = fornecedor;
   }

}
