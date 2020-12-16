/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Funcionario;
import br.com.marketstock.dao.TipoPagamentoDAO;
import br.com.marketstock.model.Enum;
import br.com.marketstock.model.PedidoCompra;
import br.com.marketstock.model.SubProduto;
import br.com.marketstock.model.TipoPagamento;
import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class PedidoCompraDAO implements GenericDAO {

   private Connection conexao;

   public PedidoCompraDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      PedidoCompra oPedidoCompra = (PedidoCompra) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Boolean bRetorno = true;

      String sql = "Insert Into MSW_PedidoCompra (descricaoPedido, dtaPedido, situacao, idFuncionario, idTipoPagamento)"
              + " Values (?, ?, ?, ?, ?)";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oPedidoCompra.getDescricaoPedido());
         stmt.setDate(2, Date.valueOf(oPedidoCompra.getDtaPedido()));
         stmt.setString(3, cEnum.ativo);
         stmt.setInt(4, oPedidoCompra.getFuncionario().getIdFuncionario());
         stmt.setInt(5, oPedidoCompra.getTipoPagamento().getIdTipoPagamento());
         stmt.execute();

         System.out.println("PedidoCompra cadastrada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar PedidoCompra (DAO). " + ex.getMessage());
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
      PedidoCompra oPedidoCompra = (PedidoCompra) pObjeto;
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;

      String sql = "Update MSW_PedidoCompra "
              + " Set descricaoPedido=?, idTipoPagamento=?"
              + " WHERE idPedidoCompra=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oPedidoCompra.getDescricaoPedido());
         stmt.setInt(2, oPedidoCompra.getTipoPagamento().getIdTipoPagamento());
         stmt.setInt(3, oPedidoCompra.getIdPedidoCompra());

         stmt.execute();
         System.out.println("PedidoCompra alterada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar PedidoCompra (DAO). " + ex.getMessage());
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
      PedidoCompra oPedidoCompra = (PedidoCompra) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      Boolean bRetorno = true;

      String sql = "Update MSW_PedidoCompra Set situacao=? Where idPedidoCompra=?";

      try {
         stmt = conexao.prepareStatement(sql);
         if (oPedidoCompra.getSituacao().equals(cEnum.ativo)) {
            stmt.setString(1, cEnum.inativo);
         } else {
            stmt.setString(1, cEnum.ativo);
         }
         stmt.setInt(2, oPedidoCompra.getIdPedidoCompra());
         stmt.execute();
         System.out.println("Status alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println(" Problemas ao ativar/inativar o Pedido Compra! (DAO)Erro: " + ex.getMessage());
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
      PreparedStatement stmt = null;
      ResultSet rs = null;
      PedidoCompra oPedidoCompra = new PedidoCompra();
      Object oRetorno = null;

      String sql = "Select *"
              + " From MSW_PedidoCompra"
              + " Where idPedidoCompra=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oPedidoCompra.setIdPedidoCompra(rs.getInt("idPedidoCompra"));
            oPedidoCompra.setDescricaoPedido(rs.getString("descricaoPedido"));
            oPedidoCompra.setDtaPedido(rs.getString("dtaPedido"));
            oPedidoCompra.setSituacao(rs.getString("situacao"));
            //Traz o Tipo de Pagamento
            TipoPagamentoDAO oTipoPagamentoDAO = new TipoPagamentoDAO();
            TipoPagamento oTipoPagamento = (TipoPagamento) oTipoPagamentoDAO.carregar(rs.getInt("idTipoPagamento"));
            oPedidoCompra.setTipoPagamento(oTipoPagamento);
            //Funcionario
            FuncionarioDAO oFuncionarioDAO = new FuncionarioDAO();
            Funcionario oFuncionario = (Funcionario) oFuncionarioDAO.carregar(rs.getInt("idFuncionario"));
            oPedidoCompra.setFuncionario(oFuncionario);
         }
         oRetorno = oPedidoCompra;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar PedidoCompra! Erro: " + ex.getMessage());
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
              + " From MSW_PedidoCompra"
              + " Order By descricaoPedido";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            PedidoCompra oPedidoCompra = new PedidoCompra();
            oPedidoCompra.setIdPedidoCompra(rs.getInt("idPedidoCompra"));
            oPedidoCompra.setDescricaoPedido(rs.getString("descricaoPedido"));
            oPedidoCompra.setDtaPedido(rs.getString("dtaPedido"));
            oPedidoCompra.setSituacao(rs.getString("situacao"));
            TipoPagamentoDAO oTipoPagamentoDAO;
            try {
               oTipoPagamentoDAO = new TipoPagamentoDAO();
               TipoPagamento oTipoPagamento = (TipoPagamento) oTipoPagamentoDAO.carregar(rs.getInt("idTipoPagamento"));
               oPedidoCompra.setTipoPagamento(oTipoPagamento);
               System.out.println("Buscar TipoPagamento!(PedidoCompraDAO), efetuado");
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar TipoPagamento!(PedidoCompraDAO) Erro: " + ex.getMessage());
            }
            FuncionarioDAO oFuncionarioDAO;
            try {
               oFuncionarioDAO = new FuncionarioDAO();
               Funcionario oFuncionario = (Funcionario) oFuncionarioDAO.carregar(rs.getInt("idFuncionario"));
               oPedidoCompra.setFuncionario(oFuncionario);
               System.out.println("Buscar Funcionario!(PedidoCompraDAO), efetuado");
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar Funcionario!(PedidoCompraDAO) Erro: " + ex.getMessage());
            }

            lResultado.add(oPedidoCompra);
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

   public List<Object> ListarAtivos() {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql = "Select *"
              + " From MSW_PedidoCompra"
              + " Where situacao = '" + cEnum.ativo + "'";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            PedidoCompra oPedidoCompra = new PedidoCompra();
            oPedidoCompra.setIdPedidoCompra(rs.getInt("idPedidoCompra"));
            oPedidoCompra.setDescricaoPedido(rs.getString("descricaoPedido"));
            oPedidoCompra.setDtaPedido(rs.getString("dtaPedido"));
            oPedidoCompra.setSituacao(rs.getString("situacao"));
            
            TipoPagamentoDAO oTipoPagamentoDAO;
            try {
               oTipoPagamentoDAO = new TipoPagamentoDAO();
               TipoPagamento oTipoPagamento = (TipoPagamento) oTipoPagamentoDAO.carregar(rs.getInt("idTipoPagamento"));
               oPedidoCompra.setTipoPagamento(oTipoPagamento);
               System.out.println("Buscar TipoPagamento!(PedidoCompraDAO), efetuado");
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar TipoPagamento!(PedidoCompraDAO) Erro: " + ex.getMessage());
            }
            FuncionarioDAO oFuncionarioDAO;
            try {
               oFuncionarioDAO = new FuncionarioDAO();
               Funcionario oFuncionario = (Funcionario) oFuncionarioDAO.carregar(rs.getInt("idFuncionario"));
               oPedidoCompra.setFuncionario(oFuncionario);
               System.out.println("Buscar Funcionario!(PedidoCompraDAO), efetuado");
            } catch (Exception ex) {
               System.out.println("Problemas ao buscar Funcionario!(PedidoCompraDAO) Erro: " + ex.getMessage());
            }
            lResultado.add(oPedidoCompra);
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
}
