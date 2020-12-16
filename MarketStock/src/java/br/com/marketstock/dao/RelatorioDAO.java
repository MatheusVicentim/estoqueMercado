/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.GenericClass;
import br.com.marketstock.model.Notificacao;
import br.com.marketstock.model.Enum;
import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GuiO.o
 */
public class RelatorioDAO {

   private Connection conexao;

   public RelatorioDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   public List<Object> ListarProdutoLoteRelatorio(int pnIdLote, int pnIdProduto) {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      Object oObjetoEstoque;
      String sql;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      sql = "Select Sum(E.QuantProdutoEstoque) As QtdProduto, P.NomeProduto, P.IdProduto, L.IdLote, L.NumLote, L.DataVencimento, (L.DataVencimento - CURRENT_DATE) As DiasVencimento";
      sql += " From MSW_Estoque E, MSW_Produto P, MSW_Lote L";
      sql += " Where P.IdProduto = E.IdProduto";
      sql += " And L.IdLote = E.IdLote";
      sql += " And E.QuantProdutoEstoque > 0";
      sql += " And P.Situacao = '" + cEnum.ativo + "'";
      sql += " And L.Situacao = '" + cEnum.ativo + "'";

      if (pnIdLote != 0) {
         sql += " And E.IdLote = " + pnIdLote;
      }
      if (pnIdProduto != 0) {
         sql += " And E.IdProduto = " + pnIdProduto;
      }

      sql += " Group By P.NomeProduto, L.NumLote, L.DataVencimento, P.IdProduto, L.IdLote";
      sql += " Order By DiasVencimento, P.IdProduto";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            GenericClass oGenericClass = new GenericClass();
            oGenericClass.setIdProduto(rs.getInt("idProduto"));
            oGenericClass.setNomeProduto(rs.getString("NomeProduto"));
            oGenericClass.setQtdProdutoEstoque(rs.getInt("QtdProduto"));
            oGenericClass.setIdLote(rs.getInt("idLote"));
            oGenericClass.setNumLote(rs.getString("NumLote"));
            oGenericClass.setDtaVencimento(rs.getString("DataVencimento"));
            oGenericClass.setDiasVencimento(rs.getInt("DiasVencimento"));
            lResultado.add(oGenericClass);
         }
         System.out.println("Sucesso ao listar Relatório de EstoqueLote(DAO)");
      } catch (Exception ex) {
         System.out.println("Problemas ao Listar Relatório de EstoqueLote (DAO)" + ex.getMessage());
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

   public List<Object> ListarNotificacoesRelatorio() {
      List<Object> lResultado = new ArrayList<>();
      br.com.marketstock.model.Enum cEnum = new br.com.marketstock.model.Enum();
      Object oObjetoEstoque;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      String sql;

      sql = "Select N.IdNotificacao, N.StatusProduto, P.NomeProduto, L.NumLote";
      sql += " From MSW_Notificacao N, MSW_Estoque E, MSW_Produto P, MSW_Lote L";
      sql += " Where E.IdProduto = P.IdProduto";
      sql += " And E.IdLote = L.IdLote";
      sql += " And E.IdNotificacao = N.IdNotificacao";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            GenericClass oGenericClass = new GenericClass();
            oGenericClass.setIdNotificacao(rs.getInt("IdNotificacao"));
            oGenericClass.setDescNotificacao(rs.getString("StatusProduto"));
            oGenericClass.setNomeProduto(rs.getString("NomeProduto"));
            oGenericClass.setNumLote(rs.getString("NumLote"));
            lResultado.add(oGenericClass);
         }
         System.out.println("Sucesso ao listar  Relatório de Notificação(DAO)");
      } catch (Exception ex) {
         System.out.println("Problemas ao Listar Relatório de Notificação (DAO)" + ex.getMessage());
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

   public List<Object> ListarProdutoLoteRelatorio() {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      Object oObjetoEstoque;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      String sql;

      sql = "Select Sum(E.QuantProdutoEstoque) As QuantProdutoEstoque, P.NomeProduto, L.NumLote, L.DataVencimento";
      sql += " From MSW_Estoque E, MSW_Produto P, MSW_Lote L";
      sql += " Where P.IdProduto = E.IdProduto";
      sql += " And L.IdLote = E.IdLote";
      sql += " And P.Situacao = '" + cEnum.ativo + "'";
      sql += " And L.Situacao = '" + cEnum.ativo + "'";
      sql += " Group By P.NomeProduto, L.NumLote, L.DataVencimento, P.IdProduto, L.IdLote";
      sql += " Order By P.IdProduto";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            GenericClass oGenericClass = new GenericClass();
            oGenericClass.setQtdProdutoEstoque(rs.getInt("QuantProdutoEstoque"));
            oGenericClass.setNomeProduto(rs.getString("NomeProduto"));
            oGenericClass.setNumLote(rs.getString("NumLote"));
            oGenericClass.setDtaVencimento(rs.getString("DataVencimento"));
            lResultado.add(oGenericClass);
         }
         System.out.println("Sucesso ao listar  Relatório de Conferencia(DAO)");
      } catch (Exception ex) {
         System.out.println("Problemas ao Listar Relatório de Conferencia (DAO)" + ex.getMessage());
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

   public List<Object> ListarPedidoCompra(int pnIdPedido) {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      Object oObjetoEstoque;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      String sql;

      sql = "Select Ip.NomeProduto, Ip.QuantProduto, Pc.DescricaoPedido, Tp.MetodoPagamento, P.Nome";
      sql += " From MSW_PedidoCompra Pc, MSW_ItensPedido Ip, MSW_Pessoa P, MSW_TipoPagamento Tp";
      sql += " Where Ip.IdPedidoCompra = Pc.IdPedidoCompra";
      sql += " And Tp.IdTipoPagamento = Pc.IdTipoPagamento";
      sql += " And P.IdPessoa = Pc.IdFuncionario";
      sql += " And Pc.IdPedidoCompra = " + pnIdPedido;

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            GenericClass oGenericClass = new GenericClass();
            oGenericClass.setNomeProduto(rs.getString("NomeProduto"));
            oGenericClass.setQtdProdutoEstoque(rs.getInt("QuantProduto"));
            oGenericClass.setDesc(rs.getString("DescricaoPedido"));
            oGenericClass.setTipoPagamento(rs.getString("MetodoPagamento"));
            oGenericClass.setNomePessoa(rs.getString("Nome"));
            lResultado.add(oGenericClass);
         }
         System.out.println("Sucesso ao listar  Relatório de Conferencia(DAO)");
      } catch (Exception ex) {
         System.out.println("Problemas ao Listar Relatório de Conferencia (DAO)" + ex.getMessage());
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
