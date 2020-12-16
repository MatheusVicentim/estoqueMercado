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
public class Cargo {
    private int idCargo;
    private String descricaoCargo;

    public Cargo() {
    }

   public Cargo(int idCargo, String descricaoCargo) {
      this.idCargo = idCargo;
      this.descricaoCargo = descricaoCargo;
   }

   public int getIdCargo() {
      return idCargo;
   }

   public void setIdCargo(int idCargo) {
      this.idCargo = idCargo;
   }

   public String getDescricaoCargo() {
      return descricaoCargo;
   }

   public void setDescricaoCargo(String descricaoCargo) {
      this.descricaoCargo = descricaoCargo;
   }

}
