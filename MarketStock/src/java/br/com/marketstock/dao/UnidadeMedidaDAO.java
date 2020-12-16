/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.UnidadeMedida;
import br.com.marketstock.model.Enum;
import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Vinícius
 */
public class UnidadeMedidaDAO implements GenericDAO {

   private Connection conexao;

   public UnidadeMedidaDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com sucesso");
      } catch (Exception ex) {
         throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      UnidadeMedida oUnidadeMedida = (UnidadeMedida) pObjeto;
      Enum cEnum = new  Enum();
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      
      String sql = "Insert Into MSW_UnidadeMedida (abreviacaoUnidade, descricaoUnidade, situacao) Values (?, ?, ?)";
      
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oUnidadeMedida.getAbreviacaoUnidade());
         stmt.setString(2, oUnidadeMedida.getDescricaoUnidadeMedida());
         stmt.setString(3, cEnum.ativo);

         stmt.execute();
         System.out.println("Unidade de Medida cadastrada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar Unidade de Medida (DAO). " + ex.getMessage());
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
      UnidadeMedida oUnidadeMedida = (UnidadeMedida) pObjeto;
      String sql = " Update MSW_UnidadeMedida Set abreviacaoUnidade=?, descricaoUnidade=? Where idUnidadeMedida=?";
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oUnidadeMedida.getAbreviacaoUnidade());
         stmt.setString(2, oUnidadeMedida.getDescricaoUnidadeMedida());
         stmt.setInt(3, oUnidadeMedida.getIdUnidadeMedida());

         stmt.execute();
         System.out.println("Unidade de Medida alterada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar Unidade de Medida (DAO). " + ex.getMessage());
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
      UnidadeMedida oMedida = (UnidadeMedida) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;

      String sql = "Update MSW_UnidadeMedida set situacao=? Where idUnidadeMedida=?";

      try {
         stmt = conexao.prepareStatement(sql);
         if (oMedida.getSituacao().equals(cEnum.ativo)) {
            stmt.setString(1, cEnum.inativo);
         } else {
            stmt.setString(1, cEnum.ativo);
         }
         stmt.setInt(2, oMedida.getIdUnidadeMedida());
         stmt.execute();
         return true;
      } catch (Exception ex) {
         System.out.println("Problemas ao ativar/desativar Unidade de Medida(DAO)! Erro: " + ex.getMessage());
         return false;
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt);
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parâmetros de conexão! Erro: " + ex.getMessage());
         }
      }
   }

   @Override
   public Object carregar(int pnNumero) {
      UnidadeMedida oUnidadeMedida = new UnidadeMedida();
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = " Select * From MSW_UnidadeMedida Where idUnidadeMedida=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oUnidadeMedida.setIdUnidadeMedida(rs.getInt("idUnidadeMedida"));
            oUnidadeMedida.setAbreviacaoUnidade(rs.getString("abreviacaoUnidade"));
            oUnidadeMedida.setDescricaoUnidadeMedida(rs.getString("descricaoUnidade"));
         }
         return oUnidadeMedida;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Unidade de Medida(DAO)! Erro: " + ex.getMessage());
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
      String sql = "Select * From MSW_UnidadeMedida Order By abreviacaoUnidade";
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            UnidadeMedida oUnidadeMedida = new UnidadeMedida();
            oUnidadeMedida.setIdUnidadeMedida(rs.getInt("idUnidadeMedida"));
            oUnidadeMedida.setAbreviacaoUnidade(rs.getString("abreviacaoUnidade"));
            oUnidadeMedida.setDescricaoUnidadeMedida(rs.getString("descricaoUnidade"));
            oUnidadeMedida.setSituacao(rs.getString("situacao"));
            lResultado.add(oUnidadeMedida);
         }
         System.out.println("Sucesso ao listar Unidade de Medida (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Unidade de Medida (DAO) - " + ex);
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
              + " From MSW_UnidadeMedida"
              + " Where situacao = '" + cEnum.ativo + "'"
              + " Order By abreviacaoUnidade";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            UnidadeMedida oUnidadeMedida = new UnidadeMedida();
            oUnidadeMedida.setIdUnidadeMedida(rs.getInt("idUnidadeMedida"));
            oUnidadeMedida.setAbreviacaoUnidade(rs.getString("abreviacaoUnidade"));
            oUnidadeMedida.setDescricaoUnidadeMedida(rs.getString("descricaoUnidade"));
            oUnidadeMedida.setSituacao(rs.getString("situacao"));
            lResultado.add(oUnidadeMedida);
         }
         System.out.println("Sucesso ao listar Unidade de Medida (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Unidade de Medida (DAO) - " + ex);
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
   
   public Object carregaInativaAtiva(int pnIdMedida) {
      UnidadeMedida oMedida = null;
      Object oRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select idUnidadeMedida, situacao"
              + " From MSW_UnidadeMedida"
              + " Where idUnidadeMedida=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdMedida);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oMedida = new UnidadeMedida();
            oMedida.setIdUnidadeMedida(rs.getInt("idUnidadeMedida"));
            oMedida.setSituacao(rs.getString("situacao"));
         }
         oRetorno = oMedida;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregaInativaAtiva UnidadeMedidaDAO! Erro: " + ex.getMessage());
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
