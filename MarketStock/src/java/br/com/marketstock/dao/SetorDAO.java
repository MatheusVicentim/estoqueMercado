/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Cidade;
import br.com.marketstock.model.Pessoa;
import br.com.marketstock.model.Setor;
import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class SetorDAO implements GenericDAO {

   private Connection conexao;

   public SetorDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      Setor oSetor = (Setor) pObjeto;
      boolean bRetorno = true;
      String sql = "Insert Into MSW_Setor (descricao)"
              + " VALUES (?)";
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oSetor.getDescricaoSetor());

         rs = stmt.executeQuery();

         System.out.println("Setor cadastrado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar Setor (DAO). " + ex.getMessage());
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
      Setor oSetor = (Setor) pObjeto;
      String sql = "Update MSW_Setor"
              + " Set descricao=?"
              + " Where idSetor=?";
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oSetor.getDescricaoSetor());
         stmt.setInt(1, oSetor.getIdSetor());

         stmt.execute();
         System.out.println("Setor alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar Setor (DAO). " + ex.getMessage());
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
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Object carregar(int pnNumero) {
      String sql = "Select * From MSW_Setor Where idSetor = ?";
      Setor oSetor = new Setor();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oSetor.setIdSetor(rs.getInt("idSetor"));
            oSetor.setDescricaoSetor(rs.getString("descricao"));
         }
         System.out.println("Sucesso ao carregar Setor (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao carregar Setor (DAO) - " + ex.getMessage());
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão Encerrada!");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return oSetor;
   }

   @Override
   public List<Object> listar() {
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select * From MSW_Setor";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Setor oSetor = new Setor();
            oSetor.setIdSetor(rs.getInt("idSetor"));
            oSetor.setDescricaoSetor(rs.getString("descricao"));
            
            lResultado.add(oSetor);
         }
         System.out.println("Sucesso ao listar Setor (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Setor (DAO) - " + ex);
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão Encerrada!");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return lResultado;
   }
}
