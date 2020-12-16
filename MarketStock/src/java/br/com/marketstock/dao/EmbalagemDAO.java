/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Embalagem;
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
public class EmbalagemDAO implements GenericDAO {

   private Connection conexao;

   public EmbalagemDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com sucesso");
      } catch (Exception ex) {
         throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      Embalagem oEmbalagem = (Embalagem) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      Boolean bRetorno = true;

      String sql = "Insert Into MSW_Embalagem (tipoEmbalagem, quantEmbalagem, descricaoEmbalagem, codBarrasEmbalagem, situacao) Values (?, ?, ?, ?, ?)";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oEmbalagem.getTipoEmbalagem());
         stmt.setInt(2, oEmbalagem.getQuantidadeEmbalagem());
         stmt.setString(3, oEmbalagem.getDescricaoEmbalagem());
         stmt.setString(4, oEmbalagem.getCodigoBarraEmbalagem());
         stmt.setString(5, cEnum.ativo);

         stmt.execute();
         System.out.println("Embalagem cadastrada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar Embalagem (DAO). " + ex.getMessage());
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
      Embalagem oEmbalagem = (Embalagem) pObjeto;
      String sql = " Update MSW_Embalagem Set tipoEmbalagem=?, quantEmbalagem=?, descricaoEmbalagem=?, codBarrasEmbalagem=? Where idEmbalagem=?";
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oEmbalagem.getTipoEmbalagem());
         stmt.setInt(2, oEmbalagem.getQuantidadeEmbalagem());
         stmt.setString(3, oEmbalagem.getDescricaoEmbalagem());
         stmt.setString(4, oEmbalagem.getCodigoBarraEmbalagem());
         stmt.setInt(5, oEmbalagem.getIdEmbalagem());

         stmt.execute();
         System.out.println("Embalagem alterada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar Embalagem (DAO). " + ex.getMessage());
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
      Embalagem oEmbalagem = (Embalagem) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      
      String sql = "Update MSW_Embalagem Set situacao=? Where idEmbalagem=?";

      try {
         stmt = conexao.prepareStatement(sql);
         if (oEmbalagem.getSituacao().equals(cEnum.ativo)) {
            stmt.setString(1, cEnum.inativo);
         } else {
            stmt.setString(1, cEnum.ativo);
         }
         stmt.setInt(2, oEmbalagem.getIdEmbalagem());
         stmt.execute();
         return true;
      } catch (Exception ex) {
         System.out.println("Problemas ao ativar/desativar Embalagem (DAO)! Erro: " + ex.getMessage());
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
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Embalagem oEmbalagem = new Embalagem();

      String sql = " Select * From MSW_Embalagem Where idEmbalagem=?";
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oEmbalagem.setIdEmbalagem(rs.getInt("idEmbalagem"));
            oEmbalagem.setTipoEmbalagem(rs.getString("tipoEmbalagem"));
            oEmbalagem.setQuantidadeEmbalagem(rs.getInt("quantEmbalagem"));
            oEmbalagem.setDescricaoEmbalagem(rs.getString("descricaoEmbalagem"));
            oEmbalagem.setCodigoBarraEmbalagem(rs.getString("codBarrasEmbalagem"));
         }
         return oEmbalagem;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Embalagem (DAO)! Erro: " + ex.getMessage());
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
      String sql = "Select * From MSW_Embalagem Order By descricaoEmbalagem";
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Embalagem oEmbalagem = new Embalagem();
            oEmbalagem.setIdEmbalagem(rs.getInt("idEmbalagem"));
            oEmbalagem.setTipoEmbalagem(rs.getString("tipoEmbalagem"));
            oEmbalagem.setQuantidadeEmbalagem(rs.getInt("quantEmbalagem"));
            oEmbalagem.setDescricaoEmbalagem(rs.getString("descricaoEmbalagem"));
            oEmbalagem.setCodigoBarraEmbalagem(rs.getString("codBarrasEmbalagem"));
            oEmbalagem.setSituacao(rs.getString("situacao"));
            lResultado.add(oEmbalagem);
         }
         System.out.println("Sucesso ao listar Embalagem (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Embalagem (DAO) - " + ex);
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

      String sql = "Select * From MSW_Embalagem Where situacao = '" + cEnum.ativo + "' Order By descricaoEmbalagem";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Embalagem oEmbalagem = new Embalagem();
            oEmbalagem.setIdEmbalagem(rs.getInt("idEmbalagem"));
            oEmbalagem.setTipoEmbalagem(rs.getString("tipoEmbalagem"));
            oEmbalagem.setQuantidadeEmbalagem(rs.getInt("quantEmbalagem"));
            oEmbalagem.setDescricaoEmbalagem(rs.getString("descricaoEmbalagem"));
            oEmbalagem.setCodigoBarraEmbalagem(rs.getString("codBarrasEmbalagem"));
            oEmbalagem.setSituacao(rs.getString("situacao"));
            lResultado.add(oEmbalagem);
         }
         System.out.println("Sucesso ao listar Embalagem (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Embalagem (DAO) - " + ex);
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

   public Object carregaInativaAtiva(int pnIdEmbalagem) {
      Embalagem oEmbalagem = null;
      Object oRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select idEmbalagem, situacao"
              + " From MSW_Embalagem"
              + " Where idEmbalagem=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdEmbalagem);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oEmbalagem = new Embalagem();
            oEmbalagem.setIdEmbalagem(rs.getInt("idEmbalagem"));
            oEmbalagem.setSituacao(rs.getString("situacao"));
         }
         oRetorno = oEmbalagem;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregaInativaAtiva EmbalagemDAO! Erro: " + ex.getMessage());
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
