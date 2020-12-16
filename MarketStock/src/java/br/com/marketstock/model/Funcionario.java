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
public class Funcionario extends Pessoa{
    private int idFuncionario;
    private String matriculaFuncionario;
    private Setor setor;
    private Cargo cargo;

    public Funcionario() {
    }

   public Funcionario(int idFuncionario, String matriculaFuncionario, Setor setor, Cargo cargo) {
      this.idFuncionario = idFuncionario;
      this.matriculaFuncionario = matriculaFuncionario;
      this.setor = setor;
      this.cargo = cargo;
   }

   public Funcionario(int idFuncionario, String matriculaFuncionario, Setor setor, Cargo cargo, int IdPessoa, String cpfCnpjPessoa, String nomePessoa, String rgLePessoa, String dataNascPessoa, String emailPessoa, String login, String senha, String numTelefone, String logradouro, int numero, String complemento, String bairro, String statusPessoa, String tipoPessoa, Cidade cidade) {
      super(IdPessoa, cpfCnpjPessoa, nomePessoa, rgLePessoa, dataNascPessoa, emailPessoa, login, senha, numTelefone, logradouro, numero, complemento, bairro, statusPessoa, tipoPessoa, cidade);
      this.idFuncionario = idFuncionario;
      this.matriculaFuncionario = matriculaFuncionario;
      this.setor = setor;
      this.cargo = cargo;
   }

   public int getIdFuncionario() {
      return idFuncionario;
   }

   public void setIdFuncionario(int idFuncionario) {
      this.idFuncionario = idFuncionario;
   }

   public String getMatriculaFuncionario() {
      return matriculaFuncionario;
   }

   public void setMatriculaFuncionario(String matriculaFuncionario) {
      this.matriculaFuncionario = matriculaFuncionario;
   }

   public Setor getSetor() {
      return setor;
   }

   public void setSetor(Setor setor) {
      this.setor = setor;
   }

   public Cargo getCargo() {
      return cargo;
   }

   public void setCargo(Cargo cargo) {
      this.cargo = cargo;
   }

}
