/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.model;

import java.io.InputStream;

/**
 *
 * @author Gabriel Vinicius
 */
public class Produto {

   private int idProduto;
   private String nomeProduto;
   private String codigoBarra;
   private InputStream imagemProduto;
   //não será necessario quant aqui
   //private int quantidadeProduto;
   private String situacao;
   private UnidadeMedida unidadeMedida;
   private Embalagem embalagem;
   private SubProduto subProduto;
   //Não será necessario o lote
   //private Lote lote;
   private Marca marca;
   private Integer fint;
   //private byte[] imagemProduto;

   public Produto() {
   }

   public Produto(int idProduto, String nomeProduto, String codigoBarra, int quantidadeProduto, String situacao, UnidadeMedida unidadeMedida, Embalagem embalagem, SubProduto subProduto, InputStream imagemProduto, Integer fint, Marca marca) {
      this.idProduto = idProduto;
      this.nomeProduto = nomeProduto;
      this.codigoBarra = codigoBarra;
      this.situacao = situacao;
      this.unidadeMedida = unidadeMedida;
      this.embalagem = embalagem;
      this.subProduto = subProduto;
      this.imagemProduto = imagemProduto;
      this.fint = fint;
      this.marca = marca;
   }

   public int getIdProduto() {
      return idProduto;
   }

   public void setIdProduto(int idProduto) {
      this.idProduto = idProduto;
   }

   public String getNomeProduto() {
      return nomeProduto;
   }

   public void setNomeProduto(String nomeProduto) {
      this.nomeProduto = nomeProduto;
   }

   public String getCodigoBarra() {
      return codigoBarra;
   }

   public void setCodigoBarra(String codigoBarra) {
      this.codigoBarra = codigoBarra;
   }

   public String getSituacao() {
      return situacao;
   }

   public void setSituacao(String situacao) {
      this.situacao = situacao;
   }

   public UnidadeMedida getUnidadeMedida() {
      return unidadeMedida;
   }

   public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
      this.unidadeMedida = unidadeMedida;
   }

   public Embalagem getEmbalagem() {
      return embalagem;
   }

   public void setEmbalagem(Embalagem embalagem) {
      this.embalagem = embalagem;
   }

   public SubProduto getSubProduto() {
      return subProduto;
   }

   public void setSubProduto(SubProduto subProduto) {
      this.subProduto = subProduto;
   }

   public InputStream getImagemProduto() {
      return imagemProduto;
   }

   public void setImagemProduto(InputStream imagemProduto) {
      this.imagemProduto = imagemProduto;
   }

   public Integer getFint() {
      return fint;
   }

   public void setFint(Integer fint) {
      this.fint = fint;
   }

   public Marca getMarca() {
      return marca;
   }

   public void setMarca(Marca marca) {
      this.marca = marca;
   }

}
