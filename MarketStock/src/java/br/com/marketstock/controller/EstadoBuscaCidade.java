/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.CidadeDAO;
import br.com.marketstock.model.Cidade;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "EstadoBuscaCidade", urlPatterns = {"/EstadoBuscaCidade"})
public class EstadoBuscaCidade extends HttpServlet {

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
            try {
                Integer nIdEstado = Integer.parseInt(request.getParameter("idEstado"));

                CidadeDAO oCidadeDAO = new CidadeDAO();
                List<Object> lstCidades = oCidadeDAO.listarEstadoCidade(nIdEstado);

                Gson gson = new Gson();
                String jsonCidades = gson.toJson(lstCidades);
                response.setCharacterEncoding("iso-8859-1");
                response.getWriter().write(jsonCidades);
            } catch (Exception ex) {
                System.out.println("Problemas ao validar cpf/cnpj"
                        + " Erro: " + ex.getMessage());
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
