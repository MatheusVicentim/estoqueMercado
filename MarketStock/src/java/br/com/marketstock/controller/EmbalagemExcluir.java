/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.EmbalagemDAO;
import br.com.marketstock.model.Embalagem;
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
@WebServlet(name = "EmbalagemExcluir", urlPatterns = {"/EmbalagemExcluir"})
public class EmbalagemExcluir extends HttpServlet {

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
        try {
            HttpSession sessao = request.getSession(false);
            // Verifica se existe sessão e se existe usuario lgado
            if (sessao != null) {
                int nIdEmbalagem = Integer.parseInt(request.getParameter("idEmbalagem"));
                String sMensagem = "";
                EmbalagemDAO oEmbalagemDAO = new EmbalagemDAO();
                Embalagem oEmbalagem = (Embalagem) oEmbalagemDAO.carregaInativaAtiva(nIdEmbalagem);
                //Usado para abrir conexão com BD
                oEmbalagemDAO = new EmbalagemDAO();
                if (oEmbalagemDAO.excluir(oEmbalagem)) {
                    sMensagem = "Lote " + oEmbalagem.getSituacao() + " com sucesso!";
                } else {
                    sMensagem = "Problemas ao ativar ou excluir Lote. Verifique os dados informados e tente novamente!";
                }
                request.setAttribute("mensagem", sMensagem);
                request.getRequestDispatcher("EmbalagemListar").forward(request, response);
            } else {
                request.setAttribute("mensagem", "Usuario não logado ao sistema");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Problemas no Servelet ao excluir Usuário! Erro: " + ex.getMessage());
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
