/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Estado;
import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */
public class EstadoDAO implements GenericDAO {

   private Connection conexao;

   public EstadoDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com Sucesso");
      } catch (Exception ex) {
         System.out.println("Problemas ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Boolean alterar(Object pObjeto) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Boolean excluir(Object pObjeto) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Object carregar(int pnNumero) {

      PreparedStatement stmt = null;
      ResultSet rs = null;
      Estado oEstado = null;
      String sql = "Select * From MSW_Estado Where idEstado=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oEstado = new Estado();
            oEstado.setIdEstado(rs.getInt("idEstado"));
            oEstado.setNomeEstado(rs.getString("nomeEstado"));
            oEstado.setUfEstado(rs.getString("uf"));
         }
         return oEstado;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Estado (DAO)! Erro: " + ex.getMessage());
         return null;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
         }
      }

   }

   @Override
   public List<Object> listar() {
      PreparedStatement stmt = null;
      ResultSet rs = null;
      List<Object> lResultado = new ArrayList<>();

      String sql = "Select * From MSW_Estado Order By nomeEstado";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Estado oEstado = new Estado();
            oEstado.setIdEstado(rs.getInt("idEstado"));
            oEstado.setNomeEstado(rs.getString("nomeEstado"));
            oEstado.setUfEstado(rs.getString("uf"));

            lResultado.add(oEstado);
         }

      } catch (SQLException ex) {
         System.out.println("Problemas ao carregar Estado(DAO)! Erro: " + ex.getMessage());
         return null;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
         }
      }
      return lResultado;
   }

}
