/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gabriel Vinicius
 */
public class ConnectionFactory {

   public static Connection getConnection() throws SQLException, Exception {
      try {
         Class.forName("org.postgresql.Driver");
         return DriverManager.getConnection("jdbc:postgresql://localhost:5432/SCAESM", "postgres", "postdba");
      } catch (Exception ex) {
         throw new Exception(ex.getMessage());
      }
   }

   private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
      try {
         if (rs != null) {
            rs.close();
         }
         if (stmt != null) {
            stmt.close();
         }
         if (conn != null) {
            conn.close();
         }
      } catch (Exception e) {
         throw new Exception(e.getMessage());
      }
   }

   public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
      close(conn, stmt, rs);
   }

   public static void closeConnection(Connection conn, Statement stmt) throws Exception {
      close(conn, stmt, null);
   }

   public static void closeConnection(Connection conn) throws Exception {
      close(conn, null, null);
   }

   public static void closeConnect(Connection conexao, PreparedStatement stmt) throws Exception {
      close(conexao, stmt, null);
   }
}
