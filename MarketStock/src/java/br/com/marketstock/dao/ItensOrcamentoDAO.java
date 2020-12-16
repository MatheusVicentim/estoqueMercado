/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.ItensOrcamento;
import br.com.marketstock.model.Orcamento;
import br.com.marketstock.dao.OrcamentoDAO;

import br.com.marketstock.model.Enum;
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
public class ItensOrcamentoDAO implements GenericDAO {

   private Connection conexao;

   public ItensOrcamentoDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com sucesso");
      } catch (Exception ex) {
         throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      ItensOrcamento oItensOrcamento = (ItensOrcamento) pObjeto;
      OrcamentoDAO oOrcamentoDAO;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;

      String sql = "Insert Into MSW_ItensOrcamento (valorOrcamento, nomeProduto, quantProduto, situacao, idOrcamento)"
              + " Values (?, ?, ?, ?, ?)";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setDouble(1, oItensOrcamento.getValorItensOrcamento());
         stmt.setString(2, oItensOrcamento.getNomeProduto());
         stmt.setInt(3, oItensOrcamento.getQtdProduto());
         stmt.setString(4, cEnum.ativo);
         stmt.setInt(5, oItensOrcamento.getOrcamento().getIdOrcamento());
         stmt.execute();
         System.out.println("ItensOrcamento cadastrada com sucesso! (DAO)");

         oOrcamentoDAO = new OrcamentoDAO();
         oOrcamentoDAO.CalculaValorOrcamento(oItensOrcamento);

         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar ItensOrcamento (DAO). " + ex.getMessage());
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
      ItensOrcamento oItens = (ItensOrcamento) pObjeto;
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;

      String sql = " Update MSW_ItensOrcamento"
              + " Set valorOrcamento=?, quantProduto=?"
              + " Where idItensOrcamento=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setDouble(1, oItens.getValorItensOrcamento());
         stmt.setInt(2, oItens.getQtdProduto());
         stmt.setInt(3, oItens.getIdItensOrcamento());
         stmt.execute();

         OrcamentoDAO oOrcamentoDAO = new OrcamentoDAO();
         oOrcamentoDAO.CalculaValorOrcamento(oItens);

         System.out.println("Orçamento alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar Orçamento (DAO). " + ex.getMessage());
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
      ItensOrcamento oItens = (ItensOrcamento) pObjeto;
      OrcamentoDAO oOrcamentoDAO;
      Enum cEnum = new Enum();
      Boolean bRetorno;
      PreparedStatement stmt = null;

      String sql = "Update MSW_ItensOrcamento Set situacao=? Where idItensOrcamento=?";

      try {
         stmt = conexao.prepareStatement(sql);
         if (oItens.getSituacao().equals(cEnum.ativo)) {
            stmt.setString(1, cEnum.inativo);
         } else {
            stmt.setString(1, cEnum.ativo);
         }
         stmt.setInt(2, oItens.getIdItensOrcamento());
         stmt.execute();

         oOrcamentoDAO = new OrcamentoDAO();
         oOrcamentoDAO.CalculaValorOrcamento(oItens);

         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Problemas mudar o Status do ItensOrcamento (DAO)! Erro: " + ex.getMessage());
         bRetorno = false;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão com BD fechada!");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
         }
      }
      return bRetorno;
   }

   @Override
   public Object carregar(int pnNumero) {
      PreparedStatement stmt = null;
      ResultSet rs = null;
      ItensOrcamento oItens = new ItensOrcamento();

      String sql = " Select * "
              + " From MSW_ItensOrcamento"
              + " Where idItensOrcamento=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oItens.setIdItensOrcamento(rs.getInt("idItensOrcamento"));
            oItens.setValorItensOrcamento(rs.getDouble("valorOrcamento"));
            oItens.setQtdProduto(rs.getInt("quantProduto"));
            oItens.setNomeProduto(rs.getString("nomeProduto"));

            OrcamentoDAO oOrcamentoDAO = new OrcamentoDAO();
            Orcamento oOrcamento = (Orcamento) oOrcamentoDAO.carregar(rs.getInt("idOrcamento"));
            oItens.setOrcamento(oOrcamento);
         }
         System.out.println("Orçamento carregado com sucesso! (DAO))");
         return oItens;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Orçamento (DAO)! Erro: " + ex.getMessage());
         return null;
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar os parâmetros de conexao! Erro: " + ex.getMessage());
         }
      }
   }

   @Override
   public List<Object> listar() {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql;
      sql = "Select Io.*, O.IdOrcamento, O.descricaoOrcamento";
      sql += " From MSW_ItensOrcamento Io, MSW_Orcamento O";
      sql += " Where Io.IdOrcamento = O.IdOrcamento";
      sql += " And Io.Situacao = '" + cEnum.ativo + "'";
      sql += " And O.Situacao = '" + cEnum.ativo + "'";
      sql += " And O.IdOrcamento = ?";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            ItensOrcamento oItensOrcamento = new ItensOrcamento();
            oItensOrcamento.setIdItensOrcamento(rs.getInt("idItensOrcamento"));
            oItensOrcamento.setValorItensOrcamento(rs.getDouble("valorOrcamento"));
            oItensOrcamento.setNomeProduto(rs.getString("nomeProduto"));
            oItensOrcamento.setQtdProduto(rs.getInt("quantProduto"));

            OrcamentoDAO oOrcamentoDAO = new OrcamentoDAO();
            try {
               Orcamento oOrcamento = (Orcamento) oOrcamentoDAO.carregar(rs.getInt("idOrcamento"));
               oItensOrcamento.setOrcamento(oOrcamento);
               System.out.println("Buscar Orcamento efetuado com sucesso! (ItensOrcamentoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Orcamento!Erro (ItensOrcamentoDAO): " + ex.getMessage());
            }
            lResultado.add(oItensOrcamento);
         }
         System.out.println("Sucesso ao listar Orçamento (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar ItensOrçamento (DAO) - " + ex);
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

   public List<Object> ListarPorOrcamento(int pnIdOrcamento) {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql;

      sql = "Select Io.IdItensOrcamento, Io.ValorOrcamento, Io.QuantProduto, Io.NomeProduto, Io.Situacao";
      sql += " From MSW_Orcamento O, MSW_ItensOrcamento Io";
      sql += " Where Io.IdOrcamento = O.IdOrcamento";
      //sql += " And IO.Situacao = '" + cEnum.ativo + "'";
      sql += " And O.IdOrcamento = ?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdOrcamento);
         rs = stmt.executeQuery();
         while (rs.next()) {
            ItensOrcamento oItensOrcamento = new ItensOrcamento();
            oItensOrcamento.setIdItensOrcamento(rs.getInt("IdItensOrcamento"));
            oItensOrcamento.setValorItensOrcamento(rs.getDouble("ValorOrcamento"));
            oItensOrcamento.setQtdProduto(rs.getInt("QuantProduto"));
            oItensOrcamento.setNomeProduto(rs.getString("NomeProduto"));
            oItensOrcamento.setSituacao(rs.getString("Situacao"));
            lResultado.add(oItensOrcamento);
         }
         System.out.println("Lista de Itens Orcamento efeutado (DAO)!");
      } catch (Exception ex) {
         System.out.println("Problemas ao listar Itens Orcamento (DAO)! Erro: " + ex.getMessage());
         lResultado = null;
      }
      return lResultado;
   }

   public Object carregaInativaAtiva(int pnIdItens) {
      ItensOrcamento oItens = null;
      Object oRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select idItensOrcamento, situacao, IdOrcamento"
              + " From MSW_ItensOrcamento"
              + " Where idItensOrcamento=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdItens);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oItens = new ItensOrcamento();
            oItens.setIdItensOrcamento(rs.getInt("idItensOrcamento"));
            oItens.setSituacao(rs.getString("situacao"));
            OrcamentoDAO oOrcamentoDAO = new OrcamentoDAO();
            Orcamento oOrcamento = (Orcamento) oOrcamentoDAO.carregar(rs.getInt("idOrcamento"));
            oItens.setOrcamento(oOrcamento);
         }
         oRetorno = oItens;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregaInativaAtiva ItensOrcamento(DAO)! Erro: " + ex.getMessage());
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
}
