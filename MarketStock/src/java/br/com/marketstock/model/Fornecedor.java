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
public class Fornecedor extends Pessoa {

   private int idFornecedor;
   private String razaoSocial;
   private String responsavelFornecedor;
   private String telefoneEmpresa;

   public Fornecedor() {
   }

   public Fornecedor(int idFornecedor, String razaoSocial, String responsavelFornecedor, String telefoneEmpresa) {
      this.idFornecedor = idFornecedor;
      this.razaoSocial = razaoSocial;
      this.responsavelFornecedor = responsavelFornecedor;
      this.telefoneEmpresa = telefoneEmpresa;
   }

   public Fornecedor(int idFornecedor, String razaoSocial, String responsavelFornecedor, String telefoneEmpresa, int IdPessoa, String cpfCnpjPessoa, String nomePessoa, String rgLePessoa, String dataNascPessoa, String emailPessoa, String login, String senha, String numTelefone, String logradouro, int numero, String complemento, String bairro, String statusPessoa, String tipoPessoa, Cidade cidade) {
      super(IdPessoa, cpfCnpjPessoa, nomePessoa, rgLePessoa, dataNascPessoa, emailPessoa, login, senha, numTelefone, logradouro, numero, complemento, bairro, statusPessoa, tipoPessoa, cidade);
      this.idFornecedor = idFornecedor;
      this.razaoSocial = razaoSocial;
      this.responsavelFornecedor = responsavelFornecedor;
      this.telefoneEmpresa = telefoneEmpresa;
   }

   public int getIdFornecedor() {
      return idFornecedor;
   }

   public void setIdFornecedor(int idFornecedor) {
      this.idFornecedor = idFornecedor;
   }

   public String getRazaoSocial() {
      return razaoSocial;
   }

   public void setRazaoSocial(String razaoSocial) {
      this.razaoSocial = razaoSocial;
   }

   public String getResponsavelFornecedor() {
      return responsavelFornecedor;
   }

   public void setResponsavelFornecedor(String responsavelFornecedor) {
      this.responsavelFornecedor = responsavelFornecedor;
   }

   public String getTelefoneEmpresa() {
      return telefoneEmpresa;
   }

   public void setTelefoneEmpresa(String telefoneEmpresa) {
      this.telefoneEmpresa = telefoneEmpresa;
   }

}
