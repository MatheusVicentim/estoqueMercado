/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Cidade;
import br.com.marketstock.model.Fornecedor;
import br.com.marketstock.model.Funcionario;
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
 * @author mathe
 */
public class FornecedorDAO implements GenericDAO {

    private Connection conexao;

    public FornecedorDAO() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object pObjeto) {
        Fornecedor oFornecedor = (Fornecedor) pObjeto;
        PreparedStatement stmt = null;
        Boolean bRetorno = true;
        Connection conn = null;

        String sql = "Insert Into MSW_Fornecedor (idFornecedor, razaoSocial, responsavelFornecedor, telefoneEmpresa)"
                + " Values (?, ?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oFornecedor.getIdPessoa());
            stmt.setString(2, oFornecedor.getRazaoSocial());
            stmt.setString(3, oFornecedor.getResponsavelFornecedor());
            stmt.setString(4, oFornecedor.getTelefoneEmpresa());
            stmt.execute();

            //Necessario para mudar status da pessoa para ativo
            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.alteraProspect(oFornecedor.getIdPessoa());

            System.out.println("Fornecedor cadastrada com sucesso! (DAO)");
            bRetorno = true;
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar Fornecedor (DAO). " + ex.getMessage());
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

        Fornecedor oFornecedor = (Fornecedor) pObjeto;
        String sql = " Update MSW_Fornecedor"
                + " Set razaoSocial=?, responsavelFornecedor=?, telefoneEmpresa=?"
                + "WHERE idFornecedor=?";
        PreparedStatement stmt = null;
        Boolean bRetorno = true;
        Connection conn = null;
        try {
            stmt = conexao.prepareStatement(sql);

            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.alterar(oFornecedor);

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oFornecedor.getRazaoSocial());
            stmt.setString(2, oFornecedor.getResponsavelFornecedor());
            stmt.setString(3, oFornecedor.getTelefoneEmpresa());
            stmt.setInt(4, oFornecedor.getIdFornecedor());

            stmt.execute();
            System.out.println("Fornecedor alterada com sucesso! (DAO)");
            bRetorno = true;
        } catch (Exception ex) {
            System.out.println("Erro ao alterar Fornecedor (DAO). " + ex.getMessage());
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

        Fornecedor oFornecedor = (Fornecedor) pObjeto;
        Boolean bRetorno = true;

        try {
            //Passando CPF/CNPJ para desativar a Pessoa
            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.excluir(oFornecedor.getCpfCnpjPessoa());

            System.out.println("Fornecedor excluido com sucesso! (DAO)");
            bRetorno = true;
        } catch (Exception ex) {
            System.out.println("Erro ao excluir Fornecedor(DAO). " + ex.getMessage());
            bRetorno = false;
//      } finally {
//         try {
//            ConnectionFactory.closeConnect(conexao, stmt);
//            System.out.println("Conexão encerrada");
//         } catch (Exception ex) {
//            System.out.println("Erro ao encerrar conexão - " + ex.getMessage());
//         }
        }
        return bRetorno;

    }

    @Override
    public Object carregar(int pnNumero) {

        String sql = " Select * "
                + " From MSW_Fornecedor F, MSW_Pessoa P"
                + " Where F.idFornecedor = P.idPessoa"
                + " And F.idFornecedor=?";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Fornecedor oFornecedor = new Fornecedor();

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, pnNumero);
            rs = stmt.executeQuery();
            while (rs.next()) {
                oFornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                oFornecedor.setRazaoSocial(rs.getString("razaoSocial"));
                oFornecedor.setResponsavelFornecedor(rs.getString("responsavelFornecedor"));
                oFornecedor.setNomePessoa(rs.getString("nome"));
            }
            return oFornecedor;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Fornecedor(DAO)! Erro: " + ex.getMessage());
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

        String sql = "Select *"
                + " From MSW_Fornecedor F, MSW_Pessoa P"
                + " Where F.idFornecedor = P.idPessoa"
                + " Order By nome";
        List<Object> lResultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor oFornecedor = new Fornecedor();
                oFornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                oFornecedor.setRazaoSocial(rs.getString("razaoSocial"));
                oFornecedor.setResponsavelFornecedor((rs.getString("responsavelFornecedor")));
                oFornecedor.setTelefoneEmpresa((rs.getString("telefoneEmpresa")));
                //oFornecedor.setNomePessoa(rs.getString("nomePessoa"));
                lResultado.add(oFornecedor);
            }
            System.out.println("Sucesso ao listar Fornecedor (DAO)");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao listar Fornecedor (DAO) - " + ex);
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
