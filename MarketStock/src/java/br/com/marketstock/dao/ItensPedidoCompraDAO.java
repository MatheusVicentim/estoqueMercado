/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.dao.PedidoCompraDAO;
import br.com.marketstock.model.ItensPedidoCompra;
import br.com.marketstock.model.PedidoCompra;
import br.com.marketstock.model.Enum;
import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */
public class ItensPedidoCompraDAO implements GenericDAO {

   private Connection conexao;

   public ItensPedidoCompraDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      ItensPedidoCompra oItens = (ItensPedidoCompra) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Boolean bRetorno = true;

      String sql = "Insert Into MSW_ItensPedido (nomeProduto, quantProduto, situacao, idPedidoCompra)"
              + " Values (?, ?, ?, ?)";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oItens.getNomeProduto());
         stmt.setInt(2, oItens.getQtdProduto());
         stmt.setString(3, cEnum.ativo);
         stmt.setInt(4, oItens.getPedidoCompra().getIdPedidoCompra());
         stmt.execute();

         System.out.println("ItensPedidoCompra cadastrada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar ItensPedidoCompra (DAO). " + ex.getMessage());
         bRetorno = false;
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao encerrar conexão - " + ex.getMessage());
         }
      }

      return bRetorno;
   }

   @Override
   public Boolean alterar(Object pObjeto) {
      ItensPedidoCompra oItens = (ItensPedidoCompra) pObjeto;
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;

      String sql = "Update MSW_ItensPedido"
              + " Set nomeProduto=?, quantProduto=?"
              + " WHERE idItensPedido=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oItens.getNomeProduto());
         stmt.setInt(2, oItens.getQtdProduto());
         stmt.setInt(3, oItens.getIdItensPedidoCompra());
         stmt.execute();

         System.out.println("ItensPedidoCompra alterada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar ItensPedidoCompra (DAO). " + ex.getMessage());
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
      ItensPedidoCompra oItens = (ItensPedidoCompra) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      Boolean bRetorno = true;

      String sql = "Update MSW_ItensPedido Set situacao=? Where idItensPedido=?";

      try {
         stmt = conexao.prepareStatement(sql);
         if (oItens.getSituacao().equals(cEnum.ativo)) {
            stmt.setString(1, cEnum.inativo);
         } else {
            stmt.setString(1, cEnum.ativo);
         }
         stmt.setInt(2, oItens.getIdItensPedidoCompra());
         stmt.execute();
         System.out.println("Status alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println(" Problemas ao ativar/inativar o Itens Pedido Compra! (DAO)Erro: " + ex.getMessage());
         bRetorno = false;
         ex.printStackTrace();
         return false;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar os parametros de conexao! Erro: " + ex.getMessage());
         }
      }
      return bRetorno;
   }

   @Override
   public Object carregar(int pnNumero) {
      ItensPedidoCompra oItens = new ItensPedidoCompra();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Object oRetorno = null;

      String sql = "Select *"
              + " From MSW_ItensPedido"
              + " Where idItensPedido=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oItens.setIdItensPedidoCompra(rs.getInt("idItensPedido"));
            oItens.setNomeProduto(rs.getString("nomeProduto"));
            oItens.setQtdProduto(rs.getInt("quantProduto"));
            oItens.setSituacao(rs.getString("situacao"));
            //Funcionario
            PedidoCompraDAO oPedidoCompraDAO = new PedidoCompraDAO();
            PedidoCompra oPedidoCompra = (PedidoCompra) oPedidoCompraDAO.carregar(rs.getInt("idPedidoCompra"));
            oItens.setPedidoCompra(oPedidoCompra);
         }
         oRetorno = oItens;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar ItensPedidoCompra! Erro: " + ex.getMessage());
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
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select *"
              + " From MSW_ItensPedido"
              + " Order By nomeProduto";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            ItensPedidoCompra oItens = new ItensPedidoCompra();
            oItens.setIdItensPedidoCompra(rs.getInt("idItensPedido"));
            oItens.setNomeProduto(rs.getString("nomeProduto"));
            oItens.setQtdProduto(rs.getInt("quantProduto"));
            oItens.setSituacao(rs.getString("situacao"));
            PedidoCompraDAO oPedidoCompraDAO = new PedidoCompraDAO();
            try {
               PedidoCompra oPedidoCompra = (PedidoCompra) oPedidoCompraDAO.carregar(rs.getInt("idPedidoCompra"));
               oItens.setPedidoCompra(oPedidoCompra);
               System.out.println("Buscar PedidoCompra!(ItensPedidoCompraDAO), efetuado");
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar PedidoCompra!(ItensPedidoCompraDAO) Erro: " + ex.getMessage());
            }
            lResultado.add(oItens);
         }
         System.out.println("Sucesso ao listar PedidoCompra (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar PedidoCompra (DAO) - " + ex);
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

   public List<Object> ListarPorPedidoCompra(int pnIdOrcamento) {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql;

      sql = "Select Ip.IdItensPedido, Ip.NomeProduto, Ip.QuantProduto, Ip.Situacao";
      sql += " From MSW_PedidoCompra P, MSW_ItensPedido Ip";
      sql += " Where Ip.idPedidoCompra = P.idPedidoCompra";
      //sql += " And IO.Situacao = '" + cEnum.ativo + "'";
      sql += " And P.idPedidoCompra = ?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdOrcamento);
         rs = stmt.executeQuery();
         while (rs.next()) {
            ItensPedidoCompra oItens = new ItensPedidoCompra();
            oItens.setIdItensPedidoCompra(rs.getInt("idItensPedido"));
            oItens.setNomeProduto(rs.getString("nomeProduto"));
            oItens.setQtdProduto(rs.getInt("quantProduto"));
            oItens.setSituacao(rs.getString("Situacao"));
            lResultado.add(oItens);
         }
         System.out.println("Lista de Itens Pedido Compra efeutado(DAO)!");
      } catch (Exception ex) {
         System.out.println("Problemas ao listar Itens Pedido Compra(DAO)! Erro: " + ex.getMessage());
         lResultado = null;
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

   public Object CarregaInativaAtiva(int pnIdItens) {
      ItensPedidoCompra oItens = null;
      Object oRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select idItensPedido, situacao, idPedidoCompra"
              + " From MSW_ItensPedido"
              + " Where idItensPedido=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdItens);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oItens = new ItensPedidoCompra();
            oItens.setIdItensPedidoCompra(rs.getInt("idItensPedido"));
            oItens.setSituacao(rs.getString("situacao"));
            PedidoCompraDAO oPedidoCompraDAO = new PedidoCompraDAO();
            PedidoCompra oCompra = (PedidoCompra) oPedidoCompraDAO.carregar(rs.getInt("idPedidoCompra"));
            oItens.setPedidoCompra(oCompra);
         }
         oRetorno = oItens;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregaInativaAtiva ItensPedidoCompra(DAO)! Erro: " + ex.getMessage());
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
