/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.dao.EstadoDAO;
import br.com.marketstock.model.Cidade;
import br.com.marketstock.model.Estado;

import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */
public class CidadeDAO implements GenericDAO {

   private Connection conexao;

   public CidadeDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com Sucesso");
      } catch (Exception ex) {
         System.out.println("Problemas ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Boolean alterar(Object pObjeto) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Boolean excluir(Object pObjeto) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Object carregar(int pnNumero) {
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Cidade oCidade = null;
      String sql = "Select * From MSW_Cidade Where idCidade=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oCidade = new Cidade();
            oCidade.setIdCidade(rs.getInt("idCidade"));
            oCidade.setNomeCidade(rs.getString("nomeCidade"));
            //busca a estado
            EstadoDAO oEstadoDAO = new EstadoDAO();
            Estado oEstado = (Estado) oEstadoDAO.carregar(rs.getInt("idEstado"));
            oCidade.setEstado(oEstado);
         }
         return oCidade;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Cidade (DAO)! Erro: " + ex.getMessage());
         return false;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
         }
      }
   }

   @Override
   public List<Object> listar() {
      String sql = "Select * From MSW_Cidade Order By nomeCidade";

      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Cidade oCidade = new Cidade();
            oCidade.setIdCidade(rs.getInt("idCidade"));
            oCidade.setNomeCidade(rs.getString("nomeCidade"));

            //Busca Estado da cidade
//            EstadoDAO oEstadoDAO;
//            try {
//               oEstadoDAO = new EstadoDAO();
//               Estado oEstado = (Estado) oEstadoDAO.carregar(rs.getInt("idEstado"));
//               oCidade.setEstado(oEstado);
//            } catch (Exception ex) {
//               System.out.println("Problemas ao buscar Estado! Erro: " + ex.getMessage());
//            }
            lResultado.add(oCidade);
         }
         System.out.println("Sucesso ao listar Cidade (DAO)");
      } catch (Exception ex) {
         lResultado.add(null);
         ex.printStackTrace();
         System.out.println("Erro ao listar Cidade (DAO) - " + ex);
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

   public List<Object> listarEstadoCidade(Integer pnNumero) {
      String sql = "Select C.IdCidade, C.NomeCidade, E.IdEstado"
              + " From MSW_Estado E, MSW_Cidade C"
              + " Where C.IdEstado = E.IdEstado"
              + " and E.IdEstado = ?"
              + " Order By NomeCidade";

      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Cidade oCidade = new Cidade();
//            oPessoa.setCpfCnpjPessoa(rs.getString("cpfCnpj"));
//            oPessoa.setNomePessoa(rs.getString("nome"));
            oCidade.setIdCidade(rs.getInt("idCidade"));
            oCidade.setNomeCidade(rs.getString("NomeCidade"));

            lResultado.add(oCidade);
         }
         System.out.println("Sucesso ao listar Cidade (DAO)");
      } catch (Exception ex) {
         lResultado.add(null);
         ex.printStackTrace();
         System.out.println("Erro ao listar Cidade (DAO) - " + ex);
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

   public List<Cidade> listar(int idEstado) {
      List<Cidade> resultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      String sql = "Select * from MSW_Cidade where idEstado = ? order by nomeCidade";
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, idEstado);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Cidade oCidade = new Cidade();
            oCidade.setIdCidade(rs.getInt("idCidade"));
            oCidade.setNomeCidade(rs.getString("nomeCidade"));

//            try {
//               EstadoDAO oEstadoDAO = new EstadoDAO();
//               oCidade.setEstado((Estado) oEstadoDAO.carregar(rs.getInt("idEstado")));
//            } catch (Exception ex) {
//               System.out.println("Problemas ao listar Cidade (DAO)! Erro: " + ex.getMessage());
//            }

            resultado.add(oCidade);
         }

      } catch (Exception ex) {
         System.out.println("Problemas ao listar Cidade (DAO)! Erro: " + ex.getMessage());
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt);
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
         }
      }
      return resultado;
   }
}
