/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.dao.PessoaDAO;
import br.com.marketstock.dao.SetorDAO;
import br.com.marketstock.dao.CargoDAO;

import br.com.marketstock.model.Cidade;
import br.com.marketstock.model.Funcionario;
import br.com.marketstock.model.Pessoa;
import br.com.marketstock.model.Setor;
import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */
public class FuncionarioDAO implements GenericDAO {

   private Connection conexao;

   public FuncionarioDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com sucesso");
      } catch (Exception ex) {
         throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {

      Funcionario oFuncionario = (Funcionario) pObjeto;
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;
      
      String sql = "Insert Into MSW_Funcionario (idFuncionario, matriculaFuncionario, idSetor, idCargo) Values (?, ?, ?, ?)";

      try {
         
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, oFuncionario.getIdPessoa());
         stmt.setString(2, oFuncionario.getMatriculaFuncionario());
         stmt.setInt(3, oFuncionario.getSetor().getIdSetor());
         stmt.setInt(4, oFuncionario.getCargo().getIdCargo());
         stmt.execute();
         
         //Necessario para mudar status da pessoa para ativo
         PessoaDAO oPessoaDAO = new PessoaDAO();
         oPessoaDAO.alteraProspect(oFuncionario.getIdPessoa());
         
         System.out.println("Funcionario cadastrado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar Funcionario (DAO). " + ex.getMessage());
         bRetorno = false;
      } finally {
         try {
            stmt = conexao.prepareStatement(sql);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao encerrar conexão - " + ex.getMessage());
         }
      }
      return bRetorno;
   }

   @Override
   public Boolean alterar(Object pObjeto) {

      Funcionario oFuncionario = (Funcionario) pObjeto;
      String sql = " Update MSW_Funcionario"
              + " Set matriculaFuncionario=?, idSetor=?, idCargo=?"
              + " WHERE idFuncionario=?";
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;
      try {
         PessoaDAO oPessoaDAO = new PessoaDAO();
         oPessoaDAO.alterar(oFuncionario);

         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oFuncionario.getMatriculaFuncionario());
         stmt.setInt(2, oFuncionario.getSetor().getIdSetor());
         stmt.setInt(3, oFuncionario.getCargo().getIdCargo());
         stmt.setInt(4, oFuncionario.getIdFuncionario());

         stmt.execute();
         System.out.println("Funcionario alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar Funcionario (DAO). " + ex.getMessage());
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
   public Boolean excluir(Object pObjeto) {

      Funcionario oFuncionario = (Funcionario) pObjeto;
      Boolean bRetorno = true;

      try {
         //Passando CPF/CNPJ para desativar a Pessoa
         PessoaDAO oPessoaDAO = new PessoaDAO();
         oPessoaDAO.excluir(oFuncionario.getCpfCnpjPessoa());

         System.out.println("Funcionario excluido com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao excluir Funcionario(DAO). " + ex.getMessage());
         bRetorno = false;
//      } finally {
//         try {
//            ConnectionFactory.closeConnect(conexao, stmt);
//            System.out.println("Conexão encerrada");
//         } catch (Exception ex) {
//            System.out.println("Erro ao encerrar conexão - " + ex.getMessage());
//         }
      }
      return bRetorno;
   }

   @Override
   public Object carregar(int pnNumero) {

      String sql = " Select * "
              + " From MSW_Funcionario F, MSW_Pessoa P"
              + " Where F.idFuncionario = P.idPessoa"
              + " And F.idFuncionario=?";
      
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Funcionario oFuncionario = new Funcionario();
      Object oRetorno = null;

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oFuncionario.setIdFuncionario(rs.getInt("idFuncionario"));
            oFuncionario.setNomePessoa(rs.getString("nome"));
         }
         oRetorno = oFuncionario;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Funcionario(DAO)! Erro: " + ex.getMessage());
         oRetorno = null;
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar os parâmetros de conexao! Erro: " + ex.getMessage());
         }
      }
      return oRetorno;
   }

   @Override
   public List<Object> listar() {

      String sql = "Select *"
              + " From MSW_Funcionario F, MSW_Pessoa P"
              + " Where F.idFuncionario = P.idPessoa"
              + " Order By nome";
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;
      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Funcionario oFuncionario = new Funcionario();
//            oPessoa.setCpfCnpjPessoa(rs.getString("cpfCnpj"));
//            oPessoa.setNomePessoa(rs.getString("nome"));
            oFuncionario.setIdFuncionario(rs.getInt("idFuncionario"));
            oFuncionario.setMatriculaFuncionario(rs.getString("matriculaFuncionario"));
            oFuncionario.setNomePessoa(rs.getString("nome"));
            
            SetorDAO oSetorDAO;
            try {
               oSetorDAO = new SetorDAO();
               Setor oSetor = (Setor) oSetorDAO.carregar(rs.getInt("idSetor"));
               oFuncionario.setSetor(oSetor);
               System.out.println("Buscar Setor!(FuncionarioDAO), efetuado");
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar Setor!(FuncionarioDAO) Erro: " + ex.getMessage());
            }
            CargoDAO oCargoDAO;
            try {
               oSetorDAO = new SetorDAO();
               Setor oSetor = (Setor) oSetorDAO.carregar(rs.getInt("idSetor"));
               oFuncionario.setSetor(oSetor);
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar Setor!(FuncionarioDAO) Erro: " + ex.getMessage());
            }

            lResultado.add(oFuncionario);
         }
         System.out.println("Sucesso ao listar Funcionario (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Funcionario (DAO) - " + ex);
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return lResultado;
   }

}
