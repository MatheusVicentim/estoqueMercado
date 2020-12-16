/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Estoque;
import br.com.marketstock.model.Produto;
import br.com.marketstock.model.Enum;
import br.com.marketstock.model.GenericClass;
import br.com.marketstock.model.Lote;
import br.com.marketstock.dao.ProdutoDAO;
import br.com.marketstock.dao.LoteDAO;
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
public class EstoqueDAO {

   //Variaveis globais
   private Connection conexao;

   public EstoqueDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   public Boolean CadastraProdutoEstoque(Object pObjetoEstoque) {

      String sql = "Insert Into MSW_Estoque(quantProdutoEstoque, tipoEstoque, idProduto, idLote)"
              + " Values(?, ?, ?, ?)";

      Estoque oEstoque = (Estoque) pObjetoEstoque;
      Boolean bRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, oEstoque.getQuantProdutoEstoque());
         stmt.setString(2, oEstoque.getTipoEstoque());
         stmt.setInt(3, oEstoque.getProduto().getIdProduto());
         stmt.setInt(4, oEstoque.getLote().getIdLote());

         stmt.execute();
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao dar Entrada no Estoque (DAO). " + ex.getMessage());
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

   private int QtdEstoque(int pnIdProduto, int pnIdLote) {
      PreparedStatement stmt = null;
      ResultSet rs = null;

      int nQtdEstoque = 0;

      String sql = "Select quantProdutoEstoque From MSW_Estoque Where IdProduto=? and idLote=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdProduto);
         stmt.setInt(2, pnIdLote);
         rs = stmt.executeQuery();

         while (rs.next()) {
            nQtdEstoque = rs.getInt("quantProdutoEstoque");
         }
      } catch (Exception ex) {
         System.out.println("Problemas no Estoque VerificaEstoque (DAO)! Erro: " + ex.getMessage());
         nQtdEstoque = 0;
      }
      return nQtdEstoque;
   }

   public Boolean ControlaEstoque(int pnIdProduto, int pnIdLote, String psAcao, int pnQtdMovimentado) {
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Boolean bRetorno;
      int nQtdtEstoque = 0;
      int nQtdEstoqueAlterado = 0;

      nQtdtEstoque = QtdEstoque(pnIdProduto, pnIdLote);

      String sql = "Update MSW_Estoque"
              + " Set quantProdutoEstoque=?"
              + " Where idProduto = ?"
              + " And idLote = ?";

      try {
         stmt = conexao.prepareStatement(sql);

         if (psAcao.equals(cEnum.saida)) {
            nQtdEstoqueAlterado = nQtdtEstoque - pnQtdMovimentado;
         } else if (psAcao.equals(cEnum.entrada)) {
            nQtdEstoqueAlterado = nQtdtEstoque + pnQtdMovimentado;
         } else {
            nQtdEstoqueAlterado = nQtdtEstoque;
         }

         stmt.setInt(1, nQtdEstoqueAlterado);
         stmt.setInt(2, pnIdProduto);
         stmt.setInt(3, pnIdLote);
         stmt.execute();

         //Chamar método Notificacao
         Notificacao(pnIdProduto, pnIdLote);

         bRetorno = true;
         System.out.println("Estoque alterado com sucesso");
      } catch (Exception ex) {
         System.out.println("Problemas no Estoque Saida Estoque (DAO)! Erro: " + ex.getMessage());
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

   public List<Object> ListarEstoque() {
      List<Object> lResultado = new ArrayList<>();
      Object oEstoque;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select E.IdEstoque, E.QuantProdutoEstoque, E.TipoEstoque"
              + "       , P.NomeProduto, P.CodBarraProd"
              + "       , L.DataFabricacao, L.DataVencimento, (L.DataVencimento - CURRENT_DATE) As QuantDiaVencimento"
              + "       , M.DescricaoMarca, U.TipoUnidadeMed"
              + "  From MSW_Estoque E, MSW_Produto P, MSW_Lote L, MSW_Marca M, MSW_UnidadeMedida U"
              + " Where E.IdProduto = P.IdProduto"
              + "   And E.IdLote = L.IdLote"
              + "   And P.IdMarca = M.IdMarca"
              + "   And P.IdUnidadeMedida = U.IdUnidadeMedida"
              + "Order By QuantDiaVencimento Desc, P.NomeProduto";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
//            Estoque oEstoque = new Estoque();
//            oPessoa.setCpfCnpjPessoa(rs.getString("cpfCnpj"));
//            oPessoa.setNomePessoa(rs.getString("nome"));
//            oFuncionario.setIdFuncionario(rs.getInt("idFuncionario"));
//            oFuncionario.setMatriculaFuncionario(rs.getString("matriculaFuncionario"));
//            oEstoque.setIdEstoque(rs.getInt("idEstoque"));
//            oEstoque.setQuantProdutoEstoque(rs.getInt("quantProdutoEstoque"));
//            oEstoque.setTipoEstoque(rs.getString("tipoEstoque"));
//            oEstoque.setTipoEstoque(rs.getString("tipoEstoque"));
//            oEstoque.setIdLote(rs.getInt("idLote"));
//
//            lResultado.add(oEstoque);

            lResultado.add(rs.getInt("idEstoque"));
            lResultado.add(rs.getInt("quantProdutoEstoque"));
            lResultado.add(rs.getString("tipoEstoque"));
            lResultado.add(rs.getString("NomeProduto"));
            lResultado.add(rs.getString("CodBarraProd"));
            lResultado.add(rs.getString("DataFabricacao"));
            lResultado.add(rs.getString("DataVencimento"));
            lResultado.add(rs.getString("QuantDiaVencimento"));
            lResultado.add(rs.getString("DescricaoMarca"));
            lResultado.add(rs.getString("TipoUnidadeMed"));

         }
         System.out.println("Sucesso ao listar Estoque (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Estoque (DAO) - " + ex);
         lResultado.add(null);
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

   public List<Object> ListarEstoqueProdutoLote(String psTipo) {
      List<Object> lResultado = new ArrayList<>();

      Enum cEnum = new Enum();
      Object oObjetoEstoque;
      String sql;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      if (psTipo.equals(cEnum.estoqueLote)) {
         sql = "Select Sum(E.QuantProdutoEstoque) As QtdProduto, P.NomeProduto, P.IdProduto, L.IdLote, L.NumLote, L.DataVencimento, (L.DataVencimento - CURRENT_DATE) As DiasVencimento";
         sql += " From MSW_Estoque E, MSW_Produto P, MSW_Lote L";
         sql += " Where P.IdProduto = E.IdProduto";
         sql += " And L.IdLote = E.IdLote";
         sql += " And E.QuantProdutoEstoque > 0";
         sql += " And P.Situacao = '" + cEnum.ativo + "'";
         sql += " And L.Situacao = '" + cEnum.ativo + "'";
         sql += " Group By P.NomeProduto, L.NumLote, L.DataVencimento, P.IdProduto, L.IdLote";
         sql += " Order By DiasVencimento, P.IdProduto";

         try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
               GenericClass oGenericClass = new GenericClass();
               oGenericClass.setIdProduto(rs.getInt("idProduto"));
               oGenericClass.setQtdProdutoEstoque(rs.getInt("QtdProduto"));
               oGenericClass.setNomeProduto(rs.getString("NomeProduto"));
               oGenericClass.setIdLote(rs.getInt("idLote"));
               oGenericClass.setNumLote(rs.getString("NumLote"));
               oGenericClass.setDtaVencimento(rs.getString("DataVencimento"));
               oGenericClass.setDiasVencimento(rs.getInt("DiasVencimento"));
               lResultado.add(oGenericClass);
            }
            System.out.println("Sucesso ao listar Estoque Lote(DAO)");
         } catch (Exception ex) {
            System.out.println("Problemas ao Listar Estoque Lote (DAO)" + ex.getMessage());
         } finally {
            try {
               ConnectionFactory.closeConnection(conexao, stmt, rs);
               System.out.println("Conexão encerrada");
            } catch (Exception ex) {
               System.out.println("Erro ao fechar conn = " + ex.getMessage());
            }
         }
      } else {
         sql = "Select Sum(E.QuantProdutoEstoque) As QtdProduto, P.NomeProduto, P.IdProduto, Min((L.DataVencimento - CURRENT_DATE)) As DiasVencimento";
         sql += " From MSW_Estoque E, MSW_Produto P, MSW_Lote L";
         sql += " Where P.IdProduto = E.IdProduto";
         sql += " And L.IdLote = E.IdLote";
         sql += " And E.QuantProdutoEstoque > 0";
         sql += " And P.Situacao = '" + cEnum.ativo + "'";
         sql += " And L.Situacao = '" + cEnum.ativo + "'";
         sql += " Group By P.NomeProduto, P.IdProduto";
         sql += " Order By DiasVencimento, P.NomeProduto";

         try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
               GenericClass oGenericClass = new GenericClass();
               oGenericClass.setQtdProdutoEstoque(rs.getInt("QtdProduto"));
               oGenericClass.setNomeProduto(rs.getString("NomeProduto"));
               oGenericClass.setIdProduto(rs.getInt("IdProduto"));
               //oGenericClass.setNumLote(rs.getString("NumLote"));
               //oGenericClass.setDtaVencimento(rs.getString("DataVencimento"));
               oGenericClass.setDiasVencimento(rs.getInt("DiasVencimento"));
               lResultado.add(oGenericClass);
            }
            System.out.println("Sucesso ao listar Estoque Lote(DAO)");
         } catch (Exception ex) {
            System.out.println("Problemas ao Listar Estoque Lote (DAO)" + ex.getMessage());
         } finally {
            try {
               ConnectionFactory.closeConnection(conexao, stmt, rs);
               System.out.println("Conexão encerrada");
            } catch (Exception ex) {
               System.out.println("Erro ao fechar conn = " + ex.getMessage());
            }
         }
      }

      return lResultado;
   }

   public Object Carregar(int pnIdLote, int pnIdProduto) {
      Estoque oEstoque = new Estoque();
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql;
      sql = "Select P.IdProduto, P.NomeProduto, L.IdLote, L.NumLote";
      sql += " From MSW_Estoque E, MSW_Produto P, MSW_Lote L";
      sql += " Where E.IdProduto = P.IdProduto";
      sql += " And E.IdLote = L.IdLote";
      sql += " And L.IdLote = ?";
      sql += " And P.IdProduto = ?";
      sql += " Group By P.IdProduto, P.NomeProduto, L.IdLote, L.NumLote";

      try {
         stmt = conexao.prepareStatement(sql);

         stmt.setInt(1, pnIdLote);
         stmt.setInt(2, pnIdProduto);
         rs = stmt.executeQuery();
         while (rs.next()) {
            //Lote
            LoteDAO oLoteDAO = new LoteDAO();
            Lote oLote = (Lote) oLoteDAO.carregar(rs.getInt("idLote"));
            oEstoque.setLote(oLote);
            //Produto
            ProdutoDAO oProdutoDAO = new ProdutoDAO();
            Produto oProduto = (Produto) oProdutoDAO.carregar(rs.getInt("idProduto"));
            oEstoque.setProduto(oProduto);
         }
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Estoque(DAO)! Erro: " + ex.getMessage());
         return null;
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar os parâmetros de conexao! Erro: " + ex.getMessage());
         }
      }
      return oEstoque;
   }

   public List<Object> ListarNotificacao() {
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      String sql;

      sql = "Select P.NomeProduto, L.NumLote, N.StatusProduto";
      sql += " From MSW_Estoque E, MSW_Produto P, MSW_Lote L, MSW_Notificacao N";
      sql += " Where P.IdProduto = E.IdProduto";
      sql += " And L.IdLote = E.IdLote";
      sql += " And N.IdNotificacao = E.IdNotificacao";
      sql += " And Not E.IdNotificacao = 0";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            GenericClass oGenericClass = new GenericClass();
            oGenericClass.setNomeProduto(rs.getString("NomeProduto"));
            oGenericClass.setNumLote(rs.getString("NumLote"));
            oGenericClass.setNotificacao(rs.getString("StatusProduto"));
            lResultado.add(oGenericClass);
         }
      } catch (Exception ex) {
         System.out.println("Problemas ao listar Notificações (EstoqueDAO)! Erro: " + ex.getMessage());
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

   //Irá inserir as informações na notificação
   private void Notificacao(int pnIdProduto, int pnIdLote) {
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sqlSelect, sqlUpDate;
      int nTipoNotificacao = 0, nQtdProdutoEstoque = 0, nQtdDiasVencimento = 0, nIdEstoque = 0;

      // 1 - Em Falta | 2 - Vencidoo | 3 - 10 dias vencimento | 4 - 30 dias para vencimento
      //Select para utilizar na notificação
      sqlSelect = "Select Sum(E.QuantProdutoEstoque) As QuantProdutoEstoque, P.IdProduto, L.IdLote, L.DataVencimento, (L.DataVencimento - CURRENT_DATE) As DiasVencimento, E.IdEstoque";
      sqlSelect += " From MSW_Estoque E, MSW_Produto P, MSW_Lote L";
      sqlSelect += " Where P.IdProduto = E.IdProduto";
      sqlSelect += " And L.IdLote = E.IdLote";
      sqlSelect += " And P.Situacao = '" + cEnum.ativo + "'";
      sqlSelect += " And L.Situacao = '" + cEnum.ativo + "'";
      sqlSelect += " And L.IdLote = ?";
      sqlSelect += " And P.IdProduto = ?";
      sqlSelect += " Group By P.IdProduto, L.IdLote, E.IdEstoque";

      //Update notificação
      sqlUpDate = "Update MSW_Estoque Set IdNotificacao = ? Where IdEstoque = ?";

      try {
         stmt = conexao.prepareStatement(sqlSelect);
         stmt.setInt(1, pnIdLote);
         stmt.setInt(2, pnIdProduto);
         rs = stmt.executeQuery();

         while (rs.next()) {
            nQtdProdutoEstoque = rs.getInt("QuantProdutoEstoque");
            nQtdDiasVencimento = rs.getInt("DiasVencimento");
            nIdEstoque = rs.getInt("idEstoque");
         }

         if (nQtdProdutoEstoque <= 0) {
            nTipoNotificacao = 1;
         } else if (nQtdDiasVencimento <= 0) {
            nTipoNotificacao = 2;
         } else if (nQtdDiasVencimento <= 10) {
            nTipoNotificacao = 3;
         } else if (nQtdDiasVencimento <= 30) {
            nTipoNotificacao = 4;
         }

         if (nTipoNotificacao != 0) {

            stmt = conexao.prepareStatement(sqlUpDate);
            stmt.setInt(1, nTipoNotificacao);
            stmt.setInt(2, nIdEstoque);
            stmt.execute();
         }
         System.out.println("Notificação adicionado com sucesso!");
      } catch (Exception ex) {
         System.out.println("Problemas ao adicionar a notificação! (EstoqueDAO) Erro: " + ex.getMessage());
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao encerrar conexão - " + ex.getMessage());
         }
      }

   }

}
