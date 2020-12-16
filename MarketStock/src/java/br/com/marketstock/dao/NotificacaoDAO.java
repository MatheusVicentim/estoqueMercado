/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Notificacao;
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
public class NotificacaoDAO implements GenericDAO {

    private Connection conexao;

    public NotificacaoDAO() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object pObjeto) {
        Notificacao oNotificacao = (Notificacao) pObjeto;
        String sql = "Insert Into MSW_Notificacao (idNotificacao, quantProduto, statusProduto, situacao) Values (?, ?, ?, ?)";
        PreparedStatement stmt = null;
        Boolean bRetorno = true;
        try {
            stmt = conexao.prepareStatement(sql);
            //stmt.setInt(1, oNotificacao.getQuantidadeProdutos());
            stmt.setString(2, oNotificacao.getStatusProduto());
            stmt.setString(3, "A");

            stmt.execute();
            System.out.println("Notificação cadastrada com sucesso! (DAO)");
            bRetorno = true;
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar Notificação (DAO). " + ex.getMessage());
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
        Notificacao oNotificacao = (Notificacao) pObjeto;
        String sql = " Update MSW_Notificacao Set quantProduto=?, statusProduto=? WHERE idNotificacao=?";
        PreparedStatement stmt = null;
        Boolean bRetorno = true;
        try {
            stmt = conexao.prepareStatement(sql);
            //stmt.setInt(1, oNotificacao.getQuantidadeProdutos());
            stmt.setString(2, oNotificacao.getStatusProduto());
            stmt.setInt(3, oNotificacao.getIdNotificacao());

            stmt.execute();
            System.out.println("Notificação alterada com sucesso! (DAO)");
            bRetorno = true;
        } catch (Exception ex) {
            System.out.println("Erro ao alterar Notificação (DAO). " + ex.getMessage());
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
        Notificacao oNotificacao = (Notificacao) pObjeto;
        PreparedStatement stmt = null;
        String situacao = "A";
        if (oNotificacao.getSituacao().equals(situacao)) {
            situacao = "I";
        } else {
            situacao = "A";
        }
        String sql = "update MSW_Notificacao set situacao=? where idNotificacao=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oNotificacao.getIdNotificacao());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao ativar/desativar Notificação! Erro: " + ex.getMessage());
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
        String sql = " Select * From MSW_Notificacao Where idNotificacao=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Notificacao oNotificacao = new Notificacao();
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, pnNumero);
            rs = stmt.executeQuery();
            while (rs.next()) {
                oNotificacao.setIdNotificacao(rs.getInt("idNotificacao"));
                //oNotificacao.setQuantidadeProdutos(rs.getInt("quantProduto"));
                oNotificacao.setStatusProduto(rs.getString("statusProduto"));
            }
            return oNotificacao;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Notificação! Erro: " + ex.getMessage());
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
        String sql = "Select * From MSW_Notificacao Order By idNotificacao";
        List<Object> lResultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Notificacao oNotificacao = new Notificacao();
                oNotificacao.setIdNotificacao(rs.getInt("idNotificacao"));
                //oNotificacao.setQuantidadeProdutos(rs.getInt("quantProduto"));
                oNotificacao.setStatusProduto(rs.getString("statusProduto"));
                oNotificacao.setSituacao(rs.getString("situacao"));
                lResultado.add(oNotificacao);
            }
            System.out.println("Sucesso ao listar Notificação (DAO)");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao listar Notificação (DAO) - " + ex);
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
