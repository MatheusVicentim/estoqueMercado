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
public class Setor {
    private int idSetor;
    private String descricaoSetor;

    public Setor() {
    }

   public Setor(int idSetor, String descricaoSetor) {
      this.idSetor = idSetor;
      this.descricaoSetor = descricaoSetor;
   }

   public int getIdSetor() {
      return idSetor;
   }

   public void setIdSetor(int idSetor) {
      this.idSetor = idSetor;
   }

   public String getDescricaoSetor() {
      return descricaoSetor;
   }

   public void setDescricaoSetor(String descricaoSetor) {
      this.descricaoSetor = descricaoSetor;
   }
    
}