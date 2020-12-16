/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.FornecedorDAO;
import br.com.marketstock.model.Cidade;
import br.com.marketstock.model.Fornecedor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mathe
 */
@WebServlet(name = "FornecedorAlterar", urlPatterns = {"/FornecedorAlterar"})
public class FornecedorAlterar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        //Pega sessão vigente no contexto do servidor
        HttpSession sessao = request.getSession(false);
        //Verifica se existe sessão e se existe usuario logado
        if (sessao != null) {
            String gsMensagem = null;
            try {
                Fornecedor oFornecedor = new Fornecedor();
                Cidade oCidade = new Cidade();
                //Atribuos da Pessoa
                oFornecedor.setCpfCnpjPessoa(request.getParameter("cpfCnpjPessoa"));
                oFornecedor.setNomePessoa(request.getParameter("nomePessoa"));
                oFornecedor.setRgLePessoa(request.getParameter("rgLePessoa"));
                oFornecedor.setDataNascPessoa(request.getParameter("dataNascPessoa"));
                oFornecedor.setEmailPessoa(request.getParameter("emailPessoa"));
                oFornecedor.setLogin(request.getParameter("login"));
                oFornecedor.setSenha(request.getParameter("senha"));
                oFornecedor.setNumTelefone(request.getParameter("numTelefone"));
                //Endereço Pessoa
                oFornecedor.setLogradouro(request.getParameter("logradouro"));
                oFornecedor.setNumero(Integer.parseInt(request.getParameter("numero")));
                oFornecedor.setComplemento(request.getParameter("complemento"));
                oFornecedor.setBairro(request.getParameter("bairro"));
                oFornecedor.setStatusPessoa(request.getParameter("statusPessoa"));
                oFornecedor.setTipoPessoa(request.getParameter("tipoPessoa"));
                //Cidade e Estado
                oCidade.setIdCidade(Integer.parseInt(request.getParameter("idCidade")));
                oFornecedor.setCidade(oCidade);
                //Informações do Fornecedor
                oFornecedor.setRazaoSocial(request.getParameter("razaoSocial"));
                oFornecedor.setResponsavelFornecedor(request.getParameter("responsavelFornecedor"));
                oFornecedor.setTelefoneEmpresa(request.getParameter("telefoneEmpresa"));
                //Confirmar a senha
                String sConfirmaSenha = request.getParameter("confirmaSenha");

                if (oFornecedor.getSenha().length() >= 4 && sConfirmaSenha.length() >= 4 && oFornecedor.getSenha() != sConfirmaSenha) {
                    oFornecedor.setSenha(request.getParameter(sConfirmaSenha));
                    FornecedorDAO oFornecedorDAO = new FornecedorDAO();
                    if (oFornecedorDAO.alterar(oFornecedor)) {
                        gsMensagem = "Informações do Fornecedor foram alterado com Sucesso! (Servlet)";
                    } else {
                        gsMensagem = "Problemas ao alterado informações do Fornecedor. Verifique os dados e tente novamente! (Servlet)";
                    }
                } else {
                    request.setAttribute("msg", "<div class='msg alert alert-warning'>Erro ao alterado Fornecedor, senha diferente da confirmação. Verifique os dados e tente novamente!</div>");
                    request.getRequestDispatcher("cadastrarFornecedor.jsp").forward(request, response);
                }

            } catch (Exception ex) {
                System.out.println("Problemas no Servelet ao alterado Funcionario! Erro: " + ex.getMessage());
            }    
        } else {
            request.setAttribute("mensagem", "Pessoa não Logado ao sistema");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
