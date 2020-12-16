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
public class Pessoa {
   private int IdPessoa;
   private String cpfCnpjPessoa;
   private String nomePessoa;
   private String rgLePessoa;
   private String dataNascPessoa;
   private String emailPessoa;
   private String login;
   private String senha;
   private String numTelefone;
   private String logradouro;
   private int numero;
   private String complemento;
   private String bairro;
   private String statusPessoa;
   private String tipoPessoa;
   private Cidade cidade;

   public Pessoa() {
   }

   public Pessoa(int IdPessoa, String cpfCnpjPessoa, String nomePessoa, String rgLePessoa, String dataNascPessoa, String emailPessoa, String login, String senha, String numTelefone, String logradouro, int numero, String complemento, String bairro, String statusPessoa, String tipoPessoa, Cidade cidade) {
      this.IdPessoa = IdPessoa;
      this.cpfCnpjPessoa = cpfCnpjPessoa;
      this.nomePessoa = nomePessoa;
      this.rgLePessoa = rgLePessoa;
      this.dataNascPessoa = dataNascPessoa;
      this.emailPessoa = emailPessoa;
      this.login = login;
      this.senha = senha;
      this.numTelefone = numTelefone;
      this.logradouro = logradouro;
      this.numero = numero;
      this.complemento = complemento;
      this.bairro = bairro;
      this.statusPessoa = statusPessoa;
      this.tipoPessoa = tipoPessoa;
      this.cidade = cidade;
   }

   public int getIdPessoa() {
      return IdPessoa;
   }

   public void setIdPessoa(int IdPessoa) {
      this.IdPessoa = IdPessoa;
   }

   public String getCpfCnpjPessoa() {
      return cpfCnpjPessoa;
   }

   public void setCpfCnpjPessoa(String cpfCnpjPessoa) {
      this.cpfCnpjPessoa = cpfCnpjPessoa;
   }

   public String getNomePessoa() {
      return nomePessoa;
   }

   public void setNomePessoa(String nomePessoa) {
      this.nomePessoa = nomePessoa;
   }

   public String getRgLePessoa() {
      return rgLePessoa;
   }

   public void setRgLePessoa(String rgLePessoa) {
      this.rgLePessoa = rgLePessoa;
   }

   public String getDataNascPessoa() {
      return dataNascPessoa;
   }

   public void setDataNascPessoa(String dataNascPessoa) {
      this.dataNascPessoa = dataNascPessoa;
   }

   public String getEmailPessoa() {
      return emailPessoa;
   }

   public void setEmailPessoa(String emailPessoa) {
      this.emailPessoa = emailPessoa;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getSenha() {
      return senha;
   }

   public void setSenha(String senha) {
      this.senha = senha;
   }

   public String getNumTelefone() {
      return numTelefone;
   }

   public void setNumTelefone(String numTelefone) {
      this.numTelefone = numTelefone;
   }

   public String getLogradouro() {
      return logradouro;
   }

   public void setLogradouro(String logradouro) {
      this.logradouro = logradouro;
   }

   public int getNumero() {
      return numero;
   }

   public void setNumero(int numero) {
      this.numero = numero;
   }

   public String getComplemento() {
      return complemento;
   }

   public void setComplemento(String complemento) {
      this.complemento = complemento;
   }

   public String getBairro() {
      return bairro;
   }

   public void setBairro(String bairro) {
      this.bairro = bairro;
   }

   public String getStatusPessoa() {
      return statusPessoa;
   }

   public void setStatusPessoa(String statusPessoa) {
      this.statusPessoa = statusPessoa;
   }

   public String getTipoPessoa() {
      return tipoPessoa;
   }

   public void setTipoPessoa(String tipoPessoa) {
      this.tipoPessoa = tipoPessoa;
   }

   public Cidade getCidade() {
      return cidade;
   }

   public void setCidade(Cidade cidade) {
      this.cidade = cidade;
   }
   
}
