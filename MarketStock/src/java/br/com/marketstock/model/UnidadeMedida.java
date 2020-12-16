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
public class UnidadeMedida {
    private int idUnidadeMedida;
    private String abreviacaoUnidade;
    private String descricaoUnidadeMedida;
    private String situacao;

    public UnidadeMedida() {
    }

   public UnidadeMedida(int idUnidadeMedida, String abreviacaoUnidade, String descricaoUnidadeMedida, String situacao) {
      this.idUnidadeMedida = idUnidadeMedida;
      this.abreviacaoUnidade = abreviacaoUnidade;
      this.descricaoUnidadeMedida = descricaoUnidadeMedida;
      this.situacao = situacao;
   }

   public int getIdUnidadeMedida() {
      return idUnidadeMedida;
   }

   public void setIdUnidadeMedida(int idUnidadeMedida) {
      this.idUnidadeMedida = idUnidadeMedida;
   }

   public String getAbreviacaoUnidade() {
      return abreviacaoUnidade;
   }

   public void setAbreviacaoUnidade(String abreviacaoUnidade) {
      this.abreviacaoUnidade = abreviacaoUnidade;
   }

   public String getDescricaoUnidadeMedida() {
      return descricaoUnidadeMedida;
   }

   public void setDescricaoUnidadeMedida(String descricaoUnidadeMedida) {
      this.descricaoUnidadeMedida = descricaoUnidadeMedida;
   }

   public String getSituacao() {
      return situacao;
   }

   public void setSituacao(String situacao) {
      this.situacao = situacao;
   }
    
}
