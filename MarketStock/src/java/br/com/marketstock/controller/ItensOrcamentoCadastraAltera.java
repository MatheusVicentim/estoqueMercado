/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.ItensOrcamentoDAO;
import br.com.marketstock.dao.OrcamentoDAO;
import br.com.marketstock.model.ItensOrcamento;
import br.com.marketstock.model.Orcamento;
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
@WebServlet(name = "ItensOrcamentoCadastraAltera", urlPatterns = {"/ItensOrcamentoCadastraAltera"})
public class ItensOrcamentoCadastraAltera extends HttpServlet {

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
         ItensOrcamento oItens = new ItensOrcamento();

         try {
            oItens.setIdItensOrcamento(Integer.parseInt(request.getParameter("idItensOrcamento")));
            oItens.setValorItensOrcamento(Double.parseDouble(request.getParameter("valorItensOrcamento")));
            oItens.setNomeProduto(request.getParameter("nomeProduto"));
            oItens.setQtdProduto(Integer.parseInt(request.getParameter("qtdProduto")));

            OrcamentoDAO oOrcamentoDAO = new OrcamentoDAO();
            //Verifica como esta na jsp essa informação
            Orcamento oOrcamento = (Orcamento) oOrcamentoDAO.carregar(Integer.parseInt(request.getParameter("idOrcamento")));
            oItens.setOrcamento(oOrcamento);

            ItensOrcamentoDAO oItensDAO = new ItensOrcamentoDAO();
            switch (oItens.getIdItensOrcamento()) {
               case 0:
                  if (oItensDAO.cadastrar(oItens)) {
                     gsMensagem = "Informações do Itens Orcamento foram cadastradas com Sucesso!";
                  } else {
                     gsMensagem = "Problemas ao cadastrar informações do Iten Orcamento. Verifique os dados e tente novamente!";
                  }
                  break;
               default:
                  if (oItensDAO.alterar(oItens)) {
                     gsMensagem = "Informações do Itens Orcamento foram alteradas com Sucesso!";
                  } else {
                     gsMensagem = "Erro ao alterar o Itens Orcamento!";
                  }
                  break;
            }
            //Retorna para a jsp do Lote
            request.setAttribute("gsMensagem", gsMensagem);
            response.sendRedirect("OrcamentoListar?listaAtivos=A");
         } catch (Exception ex) {
            System.out.println("Problemas na Servlet Itens Orcamento Cadastrar/Altera! Erro: " + ex.getMessage());
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
