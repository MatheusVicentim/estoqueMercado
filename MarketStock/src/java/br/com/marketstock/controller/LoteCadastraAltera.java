/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.LoteDAO;
import br.com.marketstock.model.Lote;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
//import java.sql.Date;
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
@WebServlet(name = "LoteCadastraAltera", urlPatterns = {"/LoteCadastraAltera"})
public class LoteCadastraAltera extends HttpServlet {

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

            Lote oLote = new Lote();

            try {
                oLote.setIdLote(Integer.parseInt(request.getParameter("idLote")));
                oLote.setNumLote(request.getParameter("numLote"));
                oLote.setDescLote(request.getParameter("descLote"));
                oLote.setDataFabricacao(request.getParameter("dataFabricacao"));
                oLote.setDataVencimento(request.getParameter("dataVencimento"));

                //Calculando a diferença de dias
                LocalDate dtaAgora = LocalDate.now();
                LocalDate dtaVencimento = LocalDate.parse(oLote.getDataVencimento());
                Long dtaDiferencadias = ChronoUnit.DAYS.between(dtaAgora, dtaVencimento);
                oLote.setQuantDiaVencimento(Integer.valueOf(dtaDiferencadias.toString()));

                LoteDAO oLoteDAO = new LoteDAO();
                switch (oLote.getIdLote()) {
                    case 0:
                        if (oLoteDAO.cadastrar(oLote)) {
                            gsMensagem = "Informações do Lote foram cadastradas com Sucesso!";
                        } else {
                            gsMensagem = "Problemas ao cadastrar informações do Lote. Verifique os dados e tente novamente!";
                        }
                        break;
                    default:
                        if (oLoteDAO.alterar(oLote)) {
                            gsMensagem = "Informações do Lote foram alteradas com Sucesso!";
                        } else {
                            gsMensagem = "Erro ao alterar o Lote!";
                        }
                        break;
                }
                //Retorna para a jsp do Lote
                request.setAttribute("gsMensagem", gsMensagem);
                response.sendRedirect("LoteListar");
            } catch (Exception ex) {
                System.out.println("Problemas na Servlet LoteCadastroAltera! Erro: " + ex.getMessage());
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
