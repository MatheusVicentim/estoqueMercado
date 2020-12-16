/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.TipoPagamento;
import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Vinicius
 */
public class TipoPagamentoDAO implements GenericDAO {

    private Connection conexao;

    public TipoPagamentoDAO() throws Exception {
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
        TipoPagamento oTipoPagamento = null;
        String sql = "Select * From MSW_TipoPagamento Where idTipoPagamento=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, pnNumero);
            rs = stmt.executeQuery();
            while (rs.next()) {
                oTipoPagamento = new TipoPagamento();
                oTipoPagamento.setIdTipoPagamento(rs.getInt("idTipoPagamento"));
                oTipoPagamento.setMetodoPagamento(rs.getString("metodoPagamento"));
            }
            return oTipoPagamento;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar TipoPagamento (DAO)! Erro: " + ex.getMessage());
            return false;
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
       List<Object> lResultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "Select * From MSW_TipoPagamento Order By metodoPagamento";

        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TipoPagamento oTipoPagamento = new TipoPagamento();
                oTipoPagamento.setIdTipoPagamento(rs.getInt("idTipoPagamento"));
                oTipoPagamento.setMetodoPagamento(rs.getString("metodoPagamento"));
                lResultado.add(oTipoPagamento);
            }
            System.out.println("Sucesso ao listar TipoPagamento (DAO)");
        } catch (Exception ex) {
            lResultado.add(null);
            ex.printStackTrace();
            System.out.println("Erro ao listar TipoPagamento (DAO) - " + ex);
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
