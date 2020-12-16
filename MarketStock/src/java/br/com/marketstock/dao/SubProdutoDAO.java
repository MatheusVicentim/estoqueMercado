package br.com.marketstock.dao;

import br.com.marketstock.model.SubProduto;
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
public class SubProdutoDAO implements GenericDAO {

   private Connection conexao;

   public SubProdutoDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {

      SubProduto oSubProduto = (SubProduto) pObjeto;
      String sql = "Insert Into MSW_SubProduto (descricaoSubProduto, situacao)"
              + " Values (?, ?)";
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Boolean bRetorno = true;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oSubProduto.getDescricaoSubProduto());
         stmt.setString(2, "A");
         stmt.execute();

         System.out.println("SubProduto cadastrado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar SubProduto (DAO). " + ex.getMessage());
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
      SubProduto oSubProduto = (SubProduto) pObjeto;
      String sql = "Update MSW_SubProduto "
              + " Set descricaoSubProduto=?"
              + " WHERE idSubProduto=?";
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oSubProduto.getDescricaoSubProduto());
         stmt.setInt(2, oSubProduto.getIdSubProduto());

         stmt.execute();
         System.out.println("SubProduto alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar SubProduto (DAO). " + ex.getMessage());
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
      SubProduto oSubProduto = (SubProduto) pObjeto;
      PreparedStatement stmt = null;
      Boolean bRetorno = true;

      //verifica se esta ativo ou inativo
      String sSituacao = "A";
      if (oSubProduto.getSituacao().equals(sSituacao)) {
         sSituacao = "I";
      } else {
         sSituacao = "A";
      }
      String sql = "Update MSW_SubProduto Set situacao=? Where idSubProduto=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, sSituacao);
         stmt.setInt(2, oSubProduto.getIdSubProduto());
         stmt.execute();
         System.out.println("Status alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println(" Problemas ao ativar/inativar o SubProduto! (DAO)Erro: " + ex.getMessage());
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
      String sql = " Select * "
              + " From MSW_SubProduto"
              + " Where idSubProduto=?";

      PreparedStatement stmt = null;
      ResultSet rs = null;
      SubProduto oSubProduto = new SubProduto();
      Object oRetorno = null;

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oSubProduto.setIdSubProduto(rs.getInt("idSubProduto"));
            oSubProduto.setDescricaoSubProduto(rs.getString("descricaoSubProduto"));
            oSubProduto.setSituacao(rs.getString("situacao"));
         }
         oRetorno = oSubProduto;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar SubProduto! Erro: " + ex.getMessage());
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
              + " From MSW_SubProduto"
              + " Order By descricaoSubProduto";
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            SubProduto oSubProduto = new SubProduto();
            oSubProduto.setIdSubProduto(rs.getInt("idSubProduto"));
            oSubProduto.setDescricaoSubProduto(rs.getString("descricaoSubProduto"));
            oSubProduto.setSituacao(rs.getString("situacao"));
            lResultado.add(oSubProduto);
         }
         System.out.println("Sucesso ao listar SubProduto (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar SubProduto (DAO) - " + ex);
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
   
   public List<Object> listaAtivos() {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      
      String sql = "Select *"
              + " From MSW_SubProduto"
              + " Where situacao = '" + cEnum.ativo + "'"
              + " Order By descricaoSubProduto";
      
      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            SubProduto oSubProduto = new SubProduto();
            oSubProduto.setIdSubProduto(rs.getInt("idSubProduto"));
            oSubProduto.setDescricaoSubProduto(rs.getString("descricaoSubProduto"));
            oSubProduto.setSituacao(rs.getString("situacao"));
            lResultado.add(oSubProduto);
         }
         System.out.println("Sucesso ao listar SubProduto (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar SubProduto (DAO) - " + ex);
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
   
   public Object carregaInativaAtiva(int pnSubProduto) {
      SubProduto oSubProduto = null;
      Object oRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select idSubProduto, situacao"
              + " From MSW_SubProduto"
              + " Where idSubProduto=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnSubProduto);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oSubProduto = new SubProduto();
            oSubProduto.setIdSubProduto(rs.getInt("idSubProduto"));
            oSubProduto.setSituacao(rs.getString("situacao"));
         }
         oRetorno = oSubProduto;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregaInativaAtiva SubProdutoDAO! Erro: " + ex.getMessage());
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
