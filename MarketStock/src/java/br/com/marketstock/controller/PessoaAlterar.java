/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

//DAO
import br.com.marketstock.dao.GenericDAO;
import br.com.marketstock.dao.PessoaDAO;
import br.com.marketstock.dao.CidadeDAO;
//Model
import br.com.marketstock.model.Cidade;
import br.com.marketstock.model.Pessoa;
//Util
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
@WebServlet(name = "PessoaAlterar", urlPatterns = {"/PessoaAlterar"})
public class PessoaAlterar extends HttpServlet {

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
            String sCpfCnpj = (String) sessao.getAttribute("cpfCnpjPessoa");
            String sNomePessoa = request.getParameter("nomePessoa");
            String sRgLePessoa = request.getParameter("rgLePessoa");
            String sDataNascPessoa = request.getParameter("dataNascPessoa");
            String sEmailPessoa = request.getParameter("emailPessoa");
            String sLogin = request.getParameter("login");
            String sSenha = request.getParameter("senha");
            String sNumTelefone = request.getParameter("numTelefone");
            //Endereço
            String sLogradouro = request.getParameter("logradouro");
            int nNumero = Integer.parseInt(request.getParameter("numero"));
            String sComplemento = request.getParameter("complemento");
            String sBairro = request.getParameter("bairro");
            String sNumero = request.getParameter("numero");
            String sStatusPessoa = request.getParameter("statusPessoa");
            String sTipoPessoa = request.getParameter("tipoPessoa");
            int nIdCidade = Integer.parseInt(request.getParameter("idCidade"));
            String sConfirmarSenhaPessoa = request.getParameter("confirmarSenhaPessoa");
            try {
                if (sSenha == null ? sConfirmarSenhaPessoa == null : sSenha.equals(sConfirmarSenhaPessoa)) {
                    Pessoa oPessoa = new Pessoa();
                    if (sCpfCnpj.length() == 14 && (sNumTelefone.length() == 14 || sNumTelefone.length() == 15 || sNumTelefone.length() == 0) && sNomePessoa.length() > 6) {
                        if (ValidacaoCpf.isCPF(sCpfCnpj)) {
                            oPessoa.setCpfCnpjPessoa(sCpfCnpj);
                        }
                        oPessoa.setNomePessoa(sNomePessoa);
                        oPessoa.setRgLePessoa(sRgLePessoa);
                        oPessoa.setDataNascPessoa(sDataNascPessoa);
                        oPessoa.setEmailPessoa(sEmailPessoa);
                        oPessoa.setLogin(sLogin);
                        oPessoa.setSenha(sSenha);
                        oPessoa.setNumTelefone(sNumTelefone);
                        //Endereço
                        oPessoa.setLogradouro(sLogradouro);
                        oPessoa.setNumero(nNumero);
                        oPessoa.setComplemento(sComplemento);
                        oPessoa.setBairro(sBairro);
                        oPessoa.setStatusPessoa(sStatusPessoa);
                        oPessoa.setTipoPessoa(sTipoPessoa);
                        //Passa a cidade
                        //Busca a marca no banco
                        GenericDAO oCidadeDAO = new CidadeDAO();
                        Cidade oCidade = (Cidade) oCidadeDAO.carregar(nIdCidade);
                        oPessoa.setCidade(oCidade);
                    }
                    PessoaDAO oPessoaDAO = new PessoaDAO();
                    if (oPessoaDAO.alterar(oPessoa)) {
                        System.out.println("Pessoa alterada com Sucesso! (Servlet)");
                        request.setAttribute("msg", "<div class='msg alert alert-success'>Pessoa alterada com Sucesso!</div>");
                    } else {
                        request.setAttribute("msg", "<div class='msg alert alert-warning'>Erro ao alterar pessoa. Verifique os dados e tente novamente</div>");
                    }
                } else {
                    request.setAttribute("msg", "<div class='msg alert alert-warning'>Erro ao alterar pessoa, senha diferente da confirmação. Verifique os dados e tente novamente!</div>");
                    request.getRequestDispatcher("cadastros/pessoa/alterarPessoa.jsp").forward(request, response);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Erro ao Alterar pessoa! Erro: " + ex.getMessage());
                request.setAttribute("msg", "<div class='msg alert alert-warning'>Erro ao alterar pessoa</div>");
            }
            response.sendRedirect("PessoaListar");
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
