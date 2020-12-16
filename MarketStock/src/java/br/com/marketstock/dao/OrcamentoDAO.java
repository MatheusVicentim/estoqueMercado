/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Orcamento;
import br.com.marketstock.model.Enum;
import br.com.marketstock.model.Fornecedor;
import br.com.marketstock.dao.FornecedorDAO;
import br.com.marketstock.model.ItensOrcamento;
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
public class OrcamentoDAO implements GenericDAO {

   private Connection conexao;

   public OrcamentoDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com sucesso");
      } catch (Exception ex) {
         throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      Orcamento oOrcamento = (Orcamento) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;

      String sql = "Insert Into MSW_Orcamento (descricaoOrcamento, situacao, idFornecedor)"
              + " Values (?, ?, ?)";
      //+ " Returning idOrcamento";

      try {
         stmt = conexao.prepareStatement(sql);
         //stmt.setDouble(1, oOrcamento.getValorTotal());
         stmt.setString(1, oOrcamento.getDescricaoOrcamento());
         stmt.setString(2, cEnum.ativo);
         stmt.setInt(3, oOrcamento.getFornecedor().getIdFornecedor());
         stmt.execute();

         System.out.println("Orçamento cadastrado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar Orçamento (DAO). " + ex.getMessage());
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
      Orcamento oOrcamento = (Orcamento) pObjeto;
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;

      String sql = " Update MSW_Orcamento"
              + " Set descricaoOrcamento=?"
              + " Where idOrcamento=?";

      try {

         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oOrcamento.getDescricaoOrcamento());
         //stmt.setInt(2, oOrcamento.getFornecedor().getIdFornecedor());
         stmt.setInt(2, oOrcamento.getIdOrcamento());
         stmt.execute();

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
      Orcamento oOrcamento = (Orcamento) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      Boolean bRetorno = true;

      String sql = "Update MSW_Orcamento Set Situacao=? Where idOrcamento=?";

      try {
         stmt = conexao.prepareStatement(sql);
         if (oOrcamento.getSituacao().equals(cEnum.ativo)) {
            stmt.setString(1, cEnum.inativo);
         } else {
            stmt.setString(1, cEnum.ativo);
         }
         stmt.setInt(2, oOrcamento.getIdOrcamento());
         stmt.execute();
         System.out.println("Status do Orçamento Alterado! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar Status do Orçamento (DAO) " + ex.getMessage());
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
   public Object carregar(int pnNumero) {
      String sql = " Select * "
              + " From MSW_Orcamento"
              + " Where idOrcamento=?";

      PreparedStatement stmt = null;
      ResultSet rs = null;
      Orcamento oOrcamento = new Orcamento();

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oOrcamento.setIdOrcamento(rs.getInt("idOrcamento"));
            oOrcamento.setValorTotal(rs.getDouble("valorTotalOrcamento"));
            oOrcamento.setDescricaoOrcamento(rs.getString("descricaoOrcamento"));
            oOrcamento.setSituacao(rs.getString("situacao"));
            //Trasemdo o Fornecedor
            FornecedorDAO oFornecedorDAO = new FornecedorDAO();
            Fornecedor oFornecedor = (Fornecedor) oFornecedorDAO.carregar(rs.getInt("idFornecedor"));
            oOrcamento.setFornecedor(oFornecedor);
         }
         System.out.println("Orçamento carregado com sucesso! (DAO))");
         return oOrcamento;
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
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql = "Select *"
              + " From MSW_Orcamento";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Orcamento oOrcamento = new Orcamento();
//            oPessoa.setCpfCnpjPessoa(rs.getString("cpfCnpj"));
//            oPessoa.setNomePessoa(rs.getString("nome"));
            oOrcamento.setIdOrcamento(rs.getInt("idOrcamento"));
            oOrcamento.setValorTotal(rs.getDouble("valorTotalOrcamento"));
            oOrcamento.setDescricaoOrcamento(rs.getString("descricaoOrcamento"));
            oOrcamento.setSituacao(rs.getString("situacao"));
            // trasendo a lista de Fornecedor
            FornecedorDAO oFornecedorDAO;
            try {
               oFornecedorDAO = new FornecedorDAO();
               Fornecedor oFornecedor = (Fornecedor) oFornecedorDAO.carregar(rs.getInt("idFornecedor"));
               oOrcamento.setFornecedor(oFornecedor);
               System.out.println("Buscar Fornecedor!(OrcamentoDAO), efetuado");
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar Fornecedor!(OrcamentoDAO) Erro: " + ex.getMessage());
            }

            lResultado.add(oOrcamento);
         }
         System.out.println("Sucesso ao listar Orçamento (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Orçamento (DAO) - " + ex);
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

   //Será utilizado para listar orçamentos ativos que iram ser cadastro itens da requisição
   public List<Object> ListarAtivos() {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql = "Select *"
              + " From MSW_Orcamento"
              + " Where situacao = '" + cEnum.ativo + "'";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Orcamento oOrcamento = new Orcamento();
            oOrcamento.setIdOrcamento(rs.getInt("idOrcamento"));
            oOrcamento.setValorTotal(rs.getDouble("valorTotalOrcamento"));
            oOrcamento.setDescricaoOrcamento(rs.getString("descricaoOrcamento"));
            oOrcamento.setSituacao(rs.getString("situacao"));
            // trasendo a lista de Fornecedor
            FornecedorDAO oFornecedorDAO;
            try {
               oFornecedorDAO = new FornecedorDAO();
               Fornecedor oFornecedor = (Fornecedor) oFornecedorDAO.carregar(rs.getInt("idFornecedor"));
               oOrcamento.setFornecedor(oFornecedor);
               System.out.println("Buscar Fornecedor!(OrcamentoDAO), efetuado");
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar fornecedor!(OrcamentoDAO) Erro: " + ex.getMessage());
            }

            lResultado.add(oOrcamento);
         }
         System.out.println("Sucesso ao listar Orçamento (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Orçamento (DAO) - " + ex);
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

   //Fazer o método para fazer o calculo do valor total, sera chamado da ItensOrcamentoDAO
   public Boolean CalculaValorOrcamento(Object pObjeto) {
      ItensOrcamento oItensOrcamento = (ItensOrcamento) pObjeto;
      //Orcamento oOrcamento = new Orcamento();
      PreparedStatement stmt = null;
      Connection conn = null;

      Boolean bRetorno = false;
      double nValorTotalOrcamento;
      String sql;

      nValorTotalOrcamento = VlrItensOrcamento(oItensOrcamento.getOrcamento().getIdOrcamento());

      sql = " Update MSW_Orcamento"
              + " Set valorTotalOrcamento=?"
              + "WHERE idOrcamento=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setDouble(1, nValorTotalOrcamento);
         stmt.setInt(2, oItensOrcamento.getOrcamento().getIdOrcamento());
         stmt.execute();
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Problemas ao CalcularValorTotalOrcamento! Erro: " + ex.getMessage());
         bRetorno = false;
      }

      return bRetorno;
   }

   private double VlrItensOrcamento(int pnIdOrcamento) {
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql;
      double nVlrOrcamento = 0;

      sql = "Select Sum(Io.ValorOrcamento * Io.QuantProduto) As VlrItens";
      sql += " From MSW_ItensOrcamento Io, MSW_Orcamento O";
      sql += " Where Io.IdOrcamento = O.IdOrcamento";
      sql += " And Io.situacao = '" + cEnum.ativo + "'";
      sql += " And O.situacao = '" + cEnum.ativo + "'";
      sql += " And O.IdOrcamento = ?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdOrcamento);
         rs = stmt.executeQuery();
         while (rs.next()) {
            nVlrOrcamento = rs.getDouble("VlrItens");
         }

      } catch (Exception ex) {
         System.out.println("Erro ao retornar Valor Orçamento! Erro: " + ex.getMessage());
      }
      return nVlrOrcamento;
   }
   
   
   
}
