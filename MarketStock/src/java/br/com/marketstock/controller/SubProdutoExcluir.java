/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.model.SubProduto;
import br.com.marketstock.dao.SubProdutoDAO;
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
 * @author Gabriel Vinicius
 */
@WebServlet(name = "SubProdutoExcluir", urlPatterns = {"/SubProdutoExcluir"})
public class SubProdutoExcluir extends HttpServlet {

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
            int nIdSubProduto = Integer.parseInt(request.getParameter("idSubProduto"));
            String sMensagem = "";
            try {
                SubProdutoDAO oSubProdutoDAO = new SubProdutoDAO();
                SubProduto oSubProduto = (SubProduto) oSubProdutoDAO.carregaInativaAtiva(nIdSubProduto);
                //Usado para abrir conexão com BD
                oSubProdutoDAO = new SubProdutoDAO();
                if (oSubProdutoDAO.excluir(oSubProduto)) {
                    sMensagem = "Sub Produto " + oSubProduto.getSituacao() + " com sucesso!";
                } else {
                    sMensagem = "Problemas ao ativar ou excluir Sub Produto. Verifique os dados informados e tente novamente!";
                }
                request.setAttribute("mensagem", sMensagem);
                request.getRequestDispatcher("SubProdutoListar").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Problemas no Servelet ao excluir SubProduto! Erro: " + ex.getMessage());
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
