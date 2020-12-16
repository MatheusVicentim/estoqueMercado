/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Cargo;
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
public class CargoDAO implements GenericDAO {

   private Connection conexao;

   public CargoDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      Cargo oCargo = (Cargo) pObjeto;
      boolean bRetorno = true;
      String sql = "Insert Into MSW_Cargo (descricao)"
              + " VALUES (?)";
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oCargo.getDescricaoCargo());

         rs = stmt.executeQuery();

         System.out.println("Cargo cadastrado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar Cargo (DAO). " + ex.getMessage());
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
      Cargo oCargo = (Cargo) pObjeto;
      String sql = "Update MSW_Cargo"
              + " Set descricao=?"
              + " Where idCargo=?";
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oCargo.getDescricaoCargo());
         stmt.setInt(1, oCargo.getIdCargo());

         stmt.execute();
         System.out.println("Cargo alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar cargo (DAO). " + ex.getMessage());
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
      String sql = "Select * From MSW_Cargo Where idCargo = ?";
      Cargo oCargo = new Cargo();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oCargo.setIdCargo(rs.getInt("idCargo"));
            oCargo.setDescricaoCargo(rs.getString("descricao"));
         }
         System.out.println("Sucesso ao carregar Cargo (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao carregar Cargo (DAO) - " + ex.getMessage());
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão Encerrada!");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return oCargo;
   }

   @Override
   public List<Object> listar() {
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select * From MSW_Cargo";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Cargo oCargo = new Cargo();
            oCargo.setIdCargo(rs.getInt("idCargo"));
            oCargo.setDescricaoCargo(rs.getString("descricao"));
            lResultado.add(oCargo);
         }
         System.out.println("Sucesso ao listar Cargo (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Cargo (DAO) - " + ex);
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
