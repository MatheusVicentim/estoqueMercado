/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

//Model
import br.com.marketstock.model.Cidade;
import br.com.marketstock.model.Pessoa;
import br.com.marketstock.model.Enum;
import br.com.marketstock.util.Criptografar;
//Util
import br.com.marketstock.util.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabriel Vinicius
 */
public class PessoaDAO implements GenericPessoa {

   private Connection conexao;

   public PessoaDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      PreparedStatement stmt = null;
      Enum cEnum = new Enum();
      Criptografar criptografar = new Criptografar();
      ResultSet rs = null;
      Boolean bCadastrou;
      Pessoa oPessoa = (Pessoa) pObjeto;

      String sql = "Insert Into MSW_Pessoa (cpfCnpj, nome, rgLe, dataNasci, email, numtelefone, login, senha, logradouro, numero, complemento, bairro, statusPessoa, tipoPessoa, idCidade)"
              + " Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
              + " Returning idPessoa";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oPessoa.getCpfCnpjPessoa());
         stmt.setString(2, oPessoa.getNomePessoa());
         stmt.setString(3, oPessoa.getRgLePessoa());
         stmt.setDate(4, Date.valueOf(oPessoa.getDataNascPessoa()));
         stmt.setString(5, oPessoa.getEmailPessoa());
         stmt.setString(6, oPessoa.getNumTelefone());
         stmt.setString(7, oPessoa.getLogin());
         stmt.setString(8, Criptografar.md5(oPessoa.getSenha()));
         stmt.setString(9, oPessoa.getLogradouro());

         stmt.setInt(10, oPessoa.getNumero());

         stmt.setString(11, oPessoa.getComplemento());
         stmt.setString(12, oPessoa.getBairro());
         stmt.setString(13, cEnum.prospect);
         stmt.setString(14, oPessoa.getTipoPessoa());
         stmt.setInt(15, oPessoa.getCidade().getIdCidade());

         rs = stmt.executeQuery();
         bCadastrou = true;

         System.out.println("Pessoa cadastrada com sucesso! (DAO)");
      } catch (Exception ex) {
         bCadastrou = false;
         System.out.println("Erro ao cadastrar Pessoa (DAO). " + ex.getMessage());
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao encerrar conexão - " + ex.getMessage());
         }
      }
      return bCadastrou;
   }

   @Override
   public Boolean alterar(Object pObjeto) {

      Pessoa oPessoa = (Pessoa) pObjeto;
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;

      String sql = "Update MSW_Pessoa "
              + "Set nome=?, rgLe ?, dataNasci=?, email=?, numtelefone=?, logradouro=?, numero=?, complemento=?, bairro=?, statusPessoa=?, tipoPessoa=?, idCidade=? "
              + "WHERE cpf=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oPessoa.getNomePessoa());
         stmt.setString(2, oPessoa.getRgLePessoa());
         stmt.setDate(3, Date.valueOf(oPessoa.getDataNascPessoa()));
         stmt.setString(4, oPessoa.getEmailPessoa());
         stmt.setString(5, oPessoa.getNumTelefone());
         stmt.setString(6, oPessoa.getLogradouro());

         stmt.setInt(7, oPessoa.getNumero());

         stmt.setString(8, oPessoa.getComplemento());
         stmt.setString(9, oPessoa.getBairro());
         stmt.setString(10, oPessoa.getStatusPessoa());
         stmt.setString(11, oPessoa.getTipoPessoa());

         stmt.setInt(12, oPessoa.getCidade().getIdCidade());

         stmt.setString(13, oPessoa.getCpfCnpjPessoa());

         stmt.execute();
         System.out.println("Pessoa alterada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar Pessoa (DAO). " + ex.getMessage());
         bRetorno = false;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao encerrar conexão - " + ex.getMessage());
         }
      }
      return bRetorno;
   }

   @Override
   public Boolean excluir(String psCpfCnpj) {
      Pessoa oPessoa = new Pessoa();
      String sql = "Delete From Only MSW_Pessoa WHERE cpfCnpj = ?;";
      PreparedStatement stmt = null;
      Connection conn = null;
      Boolean bRetorno = true;
      try {
         stmt = conexao.prepareStatement(sql);
         //stmt.setString(1, oPessoa.getStatusPessoa());
         stmt.setString(1, psCpfCnpj);
         stmt.execute();
         System.out.println("Pessoa Excluida! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao Inativar Pessoa (DAO) " + ex.getMessage());
         bRetorno = false;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão Encerrada!");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conexão = " + ex.getMessage());
         }
      }
      return bRetorno;
   }

   @Override
   public List<Object> listar() {
      String sql = "Select * From MSW_Pessoa;";
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;
      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Pessoa oPessoa = new Pessoa();
            oPessoa.setCpfCnpjPessoa(rs.getString("cpfCnpj"));
            oPessoa.setNomePessoa(rs.getString("nome"));
            oPessoa.setRgLePessoa(rs.getString("rgLe"));
            oPessoa.setDataNascPessoa(rs.getString("dataNasci"));
            oPessoa.setEmailPessoa(rs.getString("email"));
            oPessoa.setNumTelefone(rs.getString("numTelefone"));
            oPessoa.setLogin(rs.getString("login"));
            oPessoa.setSenha(rs.getString("senha"));
            oPessoa.setLogradouro(rs.getString("logradouro"));
            oPessoa.setNumero(rs.getInt("numero"));
            oPessoa.setComplemento(rs.getString("complemento"));
            oPessoa.setBairro(rs.getString("bairro"));
            oPessoa.setStatusPessoa(rs.getString("statusPessoa"));
            oPessoa.setTipoPessoa(rs.getString("tipoPessoa"));

            CidadeDAO oCidadeDAO;
            try {
               oCidadeDAO = new CidadeDAO();
               Cidade oCidade = (Cidade) oCidadeDAO.carregar(rs.getInt("idCidade"));
               oPessoa.setCidade(oCidade);
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar Cidade! Erro: " + ex.getMessage());
            }

            lResultado.add(oPessoa);
         }
         System.out.println("Sucesso ao listar Pessoas (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar pessoas (DAO) - " + ex);
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão Encerrada!");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return lResultado;
   }

   @Override
   public Object carregar(int pnIdPessoa) {
      String sql = "Select * From MSW_Pessoa Where idPessoa = ?";
      Pessoa oPessoa = new Pessoa();
      Object oRetorno = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdPessoa);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oPessoa.setIdPessoa(rs.getInt("idPessoa"));
            oPessoa.setCpfCnpjPessoa(rs.getString("cpfCnpj"));
            oPessoa.setNomePessoa(rs.getString("nome"));
            oPessoa.setRgLePessoa(rs.getString("rgLe"));
            /*Verificar se no projeto a data vai ser do tipo data ou string*/
            oPessoa.setDataNascPessoa(rs.getString("dataNasci"));
            oPessoa.setEmailPessoa(rs.getString("email"));
            oPessoa.setNumTelefone(rs.getString("numTelefone"));
            oPessoa.setLogin(rs.getString("login"));
            oPessoa.setSenha(rs.getString("senha"));
            oPessoa.setLogradouro(rs.getString("logradouro"));
            oPessoa.setNumero(rs.getInt("numero"));
            oPessoa.setComplemento(rs.getString("complemento"));
            oPessoa.setBairro(rs.getString("bairro"));
            oPessoa.setStatusPessoa(rs.getString("statusPessoa"));
            oPessoa.setTipoPessoa(rs.getString("tipoPessoa"));

            /*Trasendo a cidade*/
            CidadeDAO oCidadeDAO = new CidadeDAO();
            Cidade oCidade = (Cidade) oCidadeDAO.carregar(rs.getInt("idCidade"));
            oPessoa.setCidade(oCidade);
         }

         oRetorno = oPessoa;
         System.out.println("Sucesso ao carregar Pessoa (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao carregar Pessoa(DAO) - " + ex.getMessage());
         oRetorno = null;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão Encerrada!");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return oRetorno;
   }

   public List<Object> listar(String psCpfCnpj) {
      String sql = "Select * From MSW_Pessoa Where cpfCnpj = ?";
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, psCpfCnpj);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Pessoa oPessoa = new Pessoa();
            oPessoa.setCpfCnpjPessoa(rs.getString("cpfCnpj"));
            oPessoa.setNomePessoa(rs.getString("nome"));
            oPessoa.setRgLePessoa(rs.getString("rgLe"));
            /*Verificar se no projeto a data vai ser do tipo data ou string*/
            oPessoa.setDataNascPessoa(rs.getString("dataNasci"));
            oPessoa.setEmailPessoa(rs.getString("email"));
            oPessoa.setNumTelefone(rs.getString("numTelefone"));
            oPessoa.setLogin(rs.getString("login"));
            oPessoa.setSenha(rs.getString("senha"));
            oPessoa.setLogradouro(rs.getString("logradouro"));
            oPessoa.setNumero(rs.getInt("numero"));
            oPessoa.setComplemento(rs.getString("complemento"));
            oPessoa.setBairro(rs.getString("bairro"));
            oPessoa.setStatusPessoa(rs.getString("statusPessoa"));
            oPessoa.setTipoPessoa(rs.getString("tipoPessoa"));

            /*Trasendo a cidade*/
            CidadeDAO oCidadeDAO = new CidadeDAO();
            Cidade oCidade = (Cidade) oCidadeDAO.carregar(rs.getInt("idCidade"));
            oPessoa.setCidade(oCidade);
         }
         System.out.println("Sucesso ao listar Pessoas (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Pessoas (DAO) - " + ex);
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão Encerrada!");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return lResultado;
   }

   public List<Object> listaPessoaPro() {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql = "Select *"
              + " From MSW_Pessoa "
              + " Where statusPessoa = ?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, cEnum.prospect);

         rs = stmt.executeQuery();
         while (rs.next()) {
            Pessoa oPessoa = new Pessoa();
            oPessoa.setIdPessoa(rs.getInt("idPessoa"));
            oPessoa.setCpfCnpjPessoa(rs.getString("cpfCnpj"));
            oPessoa.setNomePessoa(rs.getString("nome"));
            oPessoa.setTipoPessoa(rs.getString("tipoPessoa"));
            oPessoa.setStatusPessoa(rs.getString("statusPessoa"));

            lResultado.add(oPessoa);
         }
         System.out.println("Sucesso ao listar Pessoas Tipos (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Pessoas Tipos (DAO) - " + ex);
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão Encerrada!");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return lResultado;
   }

   public Object carregaProspect(int pnIdPessoa) {
      Pessoa oPessoa = new Pessoa();
      Object oRetorno = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql = "Select * From MSW_Pessoa Where idPessoa = ?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdPessoa);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oPessoa.setIdPessoa(rs.getInt("idPessoa"));
            oPessoa.setCpfCnpjPessoa(rs.getString("cpfCnpj"));
            oPessoa.setNomePessoa(rs.getString("nome"));
            oPessoa.setTipoPessoa(rs.getString("tipoPessoa"));
         }

         oRetorno = oPessoa;
         System.out.println("Sucesso ao carregar Pessoa (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao carregar Pessoa(DAO) - " + ex.getMessage());
         oRetorno = null;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão Encerrada!");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return oRetorno;
   }

   public void alteraProspect(int pnIdPessoa) {
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      Connection conn = null;
      
      String sql;

      sql = "Update MSW_Pessoa";
      sql += " Set statusPessoa = '" + cEnum.ativo + "'";
      sql += " Where IdPessoa = " + pnIdPessoa;
      
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.execute();
         System.out.println("Status do Tipo Pessoa alterada");
      } catch (Exception ex) {
         System.out.println("Problemas ao mudar Status do tipo de pessoa (PessoaDAO)" + ex.getMessage());
      }finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao encerrar conexão - " + ex.getMessage());
         }
      }
   }
}
