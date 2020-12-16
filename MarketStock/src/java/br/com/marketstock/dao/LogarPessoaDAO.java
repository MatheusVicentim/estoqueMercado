/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Pessoa;
import br.com.marketstock.model.Cidade;
import br.com.marketstock.util.ConnectionFactory;
import br.com.marketstock.util.Criptografar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mathe
 */
public class LogarPessoaDAO {
   private Connection conexao;
   

    public LogarPessoaDAO() throws Exception{
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw  new Exception( "Problemas ao conectar no BD! Erro: " +ex.getMessage());
        }
    }

public Pessoa logar(String psLogin, String psSenha, String psTipoPessoa) throws Exception {
        PreparedStatement stmt = null;
        Criptografar oCriptografar = new Criptografar();
        ResultSet rs = null;
        Pessoa oPessoa = null;
        
        String sql = "Select * From MSW_Pessoa Where login=? And senha=? And tipoPessoa=?";
        
        try {
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, psLogin);
            stmt.setString(2, Criptografar.md5(psSenha));
            stmt.setString(3, psTipoPessoa );
            rs = stmt.executeQuery();
            
            Cidade oCidade = null;
            while (rs.next()) {
                oPessoa = new Pessoa(rs.getInt("idPessoa"),
                                     rs.getString("CPFCNPJ"),
                                     rs.getString("nome"),
                                     rs.getString("rgLe"),
                                     rs.getString("dataNasci"),                                    
                                     rs.getString("email"),
                                     rs.getString("numTelefone"),
                                     rs.getString("login"),
                                     rs.getString("senha"),                                     
                                     rs.getString("logradouro"),
                                     rs.getInt("numero"),
                                     rs.getString("complemento"),
                                     rs.getString("bairro"),
                                     rs.getString("statusPessoa"),
                                     rs.getString("tipoPessoa"),
                                     oCidade);
            }
            return oPessoa;
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar Pessoa(DAO)! Erro: " + ex.getMessage());
            return null;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
                System.out.println("Conexão Fechada");
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: " + ex.getMessage());
            }
        }
    }
}
