/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.UnidadeMedidaDAO;
import br.com.marketstock.model.UnidadeMedida;
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
@WebServlet(name = "UnidadeMedidaCadastraAltera", urlPatterns = {"/UnidadeMedidaCadastraAltera"})
public class UnidadeMedidaCadastraAltera extends HttpServlet {

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
            String gsMensagem;
            UnidadeMedida oMedida = new UnidadeMedida();
            try {
                oMedida.setIdUnidadeMedida(Integer.parseInt(request.getParameter("idUnidadeMedida")));
                oMedida.setDescricaoUnidadeMedida(request.getParameter("descricaoUnidadeMedida"));
                oMedida.setAbreviacaoUnidade(request.getParameter("abreviacaoUnidade"));
                UnidadeMedidaDAO oMedidaDAO = new UnidadeMedidaDAO();
                switch (oMedida.getIdUnidadeMedida()) {
                    case 0:
                        if (oMedidaDAO.cadastrar(oMedida)) {
                            gsMensagem = "Unidade Medida Cadastrada com Sucesso!";
                        } else {
                            gsMensagem = "Problemas ao cadastrar informações do Unidade Medida. Verifique os dados e tente novamente!";
                        }
                        break;
                    default:
                        if (oMedidaDAO.alterar(oMedida)) {
                            gsMensagem = "Unidade Medida Alteradas com Sucesso!";
                        } else {
                            gsMensagem = "Erro ao Alterar a Unidade Medida!";
                        }
                        break;
                }
                request.setAttribute("gsMensagem", gsMensagem);
                response.sendRedirect("UnidadeMedidaListar");
            } catch (Exception ex) {
                System.out.println("Problemas na Servlet UnidadeMedidaCadastroAltera! Erro: " + ex.getMessage());
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
