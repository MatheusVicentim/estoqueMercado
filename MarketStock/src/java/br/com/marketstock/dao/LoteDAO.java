/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Lote;
import br.com.marketstock.model.Enum;
import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Vinícius
 */
public class LoteDAO implements GenericDAO {

   private Connection conexao;

   public LoteDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com sucesso");
      } catch (Exception ex) {
         throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      Lote oLote = (Lote) pObjeto;
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      Boolean bRetorno = true;

      String sql = "Insert Into MSW_Lote (numLote, dataFabricacao, dataVencimento, quantDiaVencimento, descLote, situacao) Values (?, ?, ?, ?, ?, ?)";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oLote.getNumLote());
         stmt.setDate(2, java.sql.Date.valueOf(oLote.getDataFabricacao()));
         stmt.setDate(3, java.sql.Date.valueOf(oLote.getDataVencimento()));
         stmt.setInt(4, oLote.getQuantDiaVencimento());
         stmt.setString(5, oLote.getDescLote());
         stmt.setString(6, cEnum.ativo);

         stmt.execute();
         System.out.println("Lote cadastrado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao cadastrar Lote (DAO). " + ex.getMessage());
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
      Lote oLote = (Lote) pObjeto;
      String sql = " Update MSW_Lote Set numLote=?, dataFabricacao=?, dataVencimento=?, quantDiaVencimento=?, descLote=? Where idLote=?";
      PreparedStatement stmt = null;
      Boolean bRetorno = true;
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oLote.getNumLote());
         stmt.setDate(2, java.sql.Date.valueOf(oLote.getDataFabricacao()));
         stmt.setDate(3, java.sql.Date.valueOf(oLote.getDataVencimento()));
         stmt.setInt(4, oLote.getQuantDiaVencimento());
         stmt.setString(5, oLote.getDescLote());
         stmt.setInt(6, oLote.getIdLote());

         stmt.execute();
         System.out.println("Lote alterado com sucesso! (DAO)");
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao alterar Lote (DAO). " + ex.getMessage());
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
      Lote oLote = (Lote) pObjeto;
      Enum cEnum = new Enum();
      Boolean bRetorno;
      PreparedStatement stmt = null;

      String sql = "Update MSW_Lote Set situacao=? Where idLote=?";

      try {
         stmt = conexao.prepareStatement(sql);
         if (oLote.getSituacao().equals(cEnum.ativo)) {
            stmt.setString(1, cEnum.inativo);
         } else {
            stmt.setString(1, cEnum.ativo);
         }
         stmt.setInt(2, oLote.getIdLote());

         stmt.execute();
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Problemas mudar o Status do Lote (DAO)! Erro: " + ex.getMessage());
         bRetorno = false;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão com BD fechada!");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
         }
      }
      return bRetorno;
   }

   @Override
   public Object carregar(int pnNumero) {
      String sql = " Select * From MSW_Lote Where idLote=?";
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Lote oLote = new Lote();
      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnNumero);
         rs = stmt.executeQuery();
         while (rs.next()) {
            oLote.setIdLote(rs.getInt("idLote"));
            oLote.setNumLote(rs.getString("numLote"));
            oLote.setDataFabricacao(rs.getDate("dataFabricacao").toString());
            oLote.setDataVencimento(rs.getDate("dataVencimento").toString());
            oLote.setDescLote(rs.getString("descLote"));

            //SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            //String date1 = df.format(rs.getString("dataFabricacao"));
            //Date date2 = df.parse(data2);
         }
         return oLote;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Lote! Erro: " + ex.getMessage());
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
      String sql = "Select * From MSW_Lote Order By dataVencimento";
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Lote oLote = new Lote();
            oLote.setIdLote(rs.getInt("idLote"));
            oLote.setNumLote(rs.getString("numLote"));
            oLote.setDataFabricacao(rs.getString("dataFabricacao"));
            oLote.setDataVencimento(rs.getString("dataVencimento"));
            oLote.setDescLote(rs.getString("descLote"));
            oLote.setSituacao(rs.getString("situacao"));
            //Dia Vencimento
            LocalDate dtaAgora = LocalDate.now();
            LocalDate dtaVencimento = LocalDate.parse(oLote.getDataVencimento());
            Long dtaDiferencadias = ChronoUnit.DAYS.between(dtaAgora, dtaVencimento);
            oLote.setQuantDiaVencimento(Integer.valueOf(dtaDiferencadias.toString()));

            lResultado.add(oLote);
         }
         System.out.println("Sucesso ao listar Lote (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Lote (DAO) - " + ex);
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

   public Object carregaInativaAtiva(int pnIdLote) {
      Lote oLote = null;
      Object oRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select idLote, situacao"
              + " From MSW_Lote"
              + " Where idLote=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdLote);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oLote = new Lote();
            oLote.setIdLote(rs.getInt("idLote"));
            oLote.setSituacao(rs.getString("situacao"));
         }
         oRetorno = oLote;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregaInativaAtiva Lote(DAO)! Erro: " + ex.getMessage());
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

   public List<Object> listarAtivos() {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      
      String sql = "Select *"
              + " From MSW_Lote"
              + " Where situacao = '" + cEnum.ativo + "'"
              + " Order By dataVencimento";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Lote oLote = new Lote();
            oLote.setIdLote(rs.getInt("idLote"));
            oLote.setNumLote(rs.getString("numLote"));
            //Dia Vencimento
            //LocalDate dtaAgora = LocalDate.now();
            //LocalDate dtaVencimento = LocalDate.parse(oLote.getDataVencimento());
            //Long dtaDiferencadias = ChronoUnit.DAYS.between(dtaAgora, dtaVencimento);
            //oLote.setQuantDiaVencimento(Integer.valueOf(dtaDiferencadias.toString()));

            lResultado.add(oLote);
         }
         System.out.println("Sucesso ao listar Lote Ativos (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Lote Ativos (DAO) - " + ex);
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
