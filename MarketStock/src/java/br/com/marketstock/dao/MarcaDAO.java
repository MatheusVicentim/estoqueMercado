/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Marca;
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
public class MarcaDAO implements GenericDAO {

   private Connection conexao;

   public MarcaDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      Marca oMarca = (Marca) pObjeto;
      String sql = "Insert Into MSW_Marca (descricaoMarca, situacao)"
              + " Values (?, ?)";
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Boolean bRetorno = true;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oMarca.getDescricaoMarca());
         stmt.setString(2, "A");
         stmt.execute();

         System.out.println("Marca cadastrada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar Marca (DAO). " + ex.getMessage());
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
      Marca oMarca = (Marca) pObjeto;
      String sql = "Update MSW_Marca "
              + " Set descricaoMarca=?"
              + " WHERE idMarca=?";
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      Connection conn = null;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oMarca.getDescricaoMarca());
         stmt.setInt(2, oMarca.getIdMarca());

         stmt.execute();
         System.out.println("Marca alterada com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar Marca (DAO). " + ex.getMessage());
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
      Marca oMarca = (Marca) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      Boolean bRetorno = true;

      String sql = "Update MSW_Marca Set situacao=? Where idMarca=?";

      try {
         stmt = conexao.prepareStatement(sql);
         if (oMarca.getSituacao().equals(cEnum.ativo)) {
            stmt.setString(1, cEnum.inativo);
         } else {
            stmt.setString(1, cEnum.ativo);
         }
         stmt.setInt(2, oMarca.getIdMarca());
         stmt.execute();
         System.out.println("Status alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println(" Problemas ao ativar/inativar o Marca! (DAO)Erro: " + ex.getMessage());
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
      Marca oMarca = new Marca();
      Object oRetorno = null;

      String sql = " Select * "
              + " From MSW_Marca"
              + " Where idMarca=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oMarca.setIdMarca(rs.getInt("idMarca"));
            oMarca.setDescricaoMarca(rs.getString("descricaoMarca"));
         }
         oRetorno = oMarca;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Marca(DAO)! Erro: " + ex.getMessage());
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
              + " From MSW_Marca"
              + " Order By descricaoMarca";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Marca oMarca = new Marca();
            oMarca.setIdMarca(rs.getInt("idMarca"));
            oMarca.setDescricaoMarca(rs.getString("descricaoMarca"));
            oMarca.setSituacao(rs.getString("situacao"));
            lResultado.add(oMarca);
         }
         System.out.println("Sucesso ao listar Marca (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Marca (DAO) - " + ex);
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
              + " From MSW_Marca"
              + " Where situacao = '" + cEnum.ativo + "'"
              + " Order By descricaoMarca";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Marca oMarca = new Marca();
            oMarca.setIdMarca(rs.getInt("idMarca"));
            oMarca.setDescricaoMarca(rs.getString("descricaoMarca"));
            oMarca.setSituacao(rs.getString("situacao"));
            lResultado.add(oMarca);
         }
         System.out.println("Sucesso ao listar Marca (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Marca (DAO) - " + ex);
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
   
   public Object carregaInativaAtiva(int pnMarca) {
      Marca oMarca = null;
      Object oRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select idMarca, situacao"
              + " From MSW_Marca"
              + " Where idMarca=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnMarca);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oMarca = new Marca();
            oMarca.setIdMarca(rs.getInt("idMarca"));
            oMarca.setSituacao(rs.getString("situacao"));
         }
         oRetorno = oMarca;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregaInativaAtiva MarcaDAO! Erro: " + ex.getMessage());
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
