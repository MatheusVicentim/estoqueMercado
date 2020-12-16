/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.CidadeDAO;
import br.com.marketstock.dao.PessoaDAO;
import br.com.marketstock.dao.GenericDAO;
import br.com.marketstock.model.Cidade;
import br.com.marketstock.model.Pessoa;
import br.com.marketstock.util.ValidacaoCpf;
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
@WebServlet(name = "PessoaCadastraAltera", urlPatterns = {"/PessoaCadastraAltera"})
public class PessoaCadastraAltera extends HttpServlet {

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
            int nIdPessoa = Integer.parseInt(request.getParameter("idPessoa"));
            String sCpfCnpj = request.getParameter("cpfCnpjPessoa");
            String sSenha = request.getParameter("senha");
            String sConfirmaSenha = request.getParameter("confirmarSenhaPessoa");
            String sNumTelefone = request.getParameter("numTelefone");
            String sMensagem;
            Pessoa oPessoa = new Pessoa();
            try {
                if (sSenha == null ? sConfirmaSenha == null : sSenha.equals(sConfirmaSenha)) {
                    //if (sCpfCnpj.length() == 14 && (sNumTelefone.length() == 14 || sNumTelefone.length() == 15 || sNumTelefone.length() == 0)) {
                    if (ValidacaoCpf.isCPF(sCpfCnpj)) {
                        oPessoa.setCpfCnpjPessoa(sCpfCnpj);
                    }
                    if (nIdPessoa > 0) {
                        oPessoa.setIdPessoa(nIdPessoa);
                    }
                    oPessoa.setCpfCnpjPessoa(sCpfCnpj);
                    oPessoa.setNomePessoa(request.getParameter("nomePessoa"));
                    oPessoa.setRgLePessoa(request.getParameter("rgLePessoa"));
                    oPessoa.setDataNascPessoa(request.getParameter("dataNascPessoa"));
                    oPessoa.setEmailPessoa(request.getParameter("emailPessoa"));
                    oPessoa.setLogin(request.getParameter("login"));
                    oPessoa.setSenha(request.getParameter("senha"));
                    oPessoa.setNumTelefone(request.getParameter("numTelefone"));
                    oPessoa.setTipoPessoa(request.getParameter("tipoPessoa"));
                    //Endereço
                    oPessoa.setLogradouro(request.getParameter("logradouro"));
                    oPessoa.setNumero(Integer.parseInt(request.getParameter("numero")));
                    oPessoa.setComplemento(request.getParameter("complemento"));
                    oPessoa.setBairro(request.getParameter("bairro"));

                    GenericDAO oCidadeDAO = new CidadeDAO();
                    Cidade oCidade = (Cidade) oCidadeDAO.carregar(Integer.parseInt(request.getParameter("idCidade")));
                    oPessoa.setCidade(oCidade);
                    //}
                }
                try {
                    PessoaDAO oPessoaDAO = new PessoaDAO();
                    if (nIdPessoa > 0) {
                        if (oPessoaDAO.alterar(oPessoa)) {
                            sMensagem = "Informações do Produto foram alteradas com Sucesso!";
                        } else {
                            sMensagem = "Problemas ao alterar!";
                        }
                    } else {
                        if (oPessoaDAO.cadastrar(oPessoa)) {
                            sMensagem = "Problemas ao cadastrar!";
                        } else {
                            sMensagem = "Informações do Produto foram cadastradas com Sucesso!";
                        }
                    }
                    //Retorna para a jsp do Estoque
                    request.setAttribute("gsMensagem", sMensagem);
                    response.sendRedirect("PessoaTipoListar");
                } catch (Exception ex) {
                    System.out.println("Problema na Servlect ao Cadastrar/Alterar Pessoa!" + ex.getMessage());
                }
            } catch (Exception ex) {
                System.out.println("Erro ao alimentar a Classe Pessoa! Servlet PessoaCadastraAltera. Erro: " + ex.getMessage());
            }
        } else {
            request.setAttribute("mensagem", "Usuário não Logado ao sistema");
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
