/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.FuncionarioDAO;
import br.com.marketstock.dao.OrcamentoDAO;
import br.com.marketstock.model.Orcamento;
import br.com.marketstock.model.PedidoCompra;
import br.com.marketstock.dao.PedidoCompraDAO;
import br.com.marketstock.dao.TipoPagamentoDAO;
import br.com.marketstock.model.Funcionario;
import br.com.marketstock.model.TipoPagamento;
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
@WebServlet(name = "PedidoCompraCadastraAltera", urlPatterns = {"/PedidoCompraCadastraAltera"})
public class PedidoCompraCadastrarAltera extends HttpServlet {

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
         PedidoCompra oPedidoCompra = new PedidoCompra();
         String gsMensagem;
         try {
            PedidoCompraDAO oPedidoCompraDAO = new PedidoCompraDAO();
            TipoPagamentoDAO oTipoPagamentoDAO = new TipoPagamentoDAO();
            TipoPagamento oTipoPagamento;
            FuncionarioDAO oFuncionarioDAO = new FuncionarioDAO();
            Funcionario oFuncionario;

            oPedidoCompra.setIdPedidoCompra(Integer.parseInt(request.getParameter("idPedidoCompra")));
            oPedidoCompra.setDescricaoPedido(request.getParameter("descricaoPedido"));
            oPedidoCompra.setDtaPedido(request.getParameter("dtaPedido"));
            oPedidoCompra.setSituacao(request.getParameter("situacao"));
            //oPedidoCompra.setVlrPedido(Double.parseDouble(request.getParameter("vlrPedido")));
            oTipoPagamento = (TipoPagamento) oTipoPagamentoDAO.carregar(Integer.parseInt(request.getParameter("idTipoPagamento")));
            oPedidoCompra.setTipoPagamento(oTipoPagamento);

            if (oPedidoCompra.getIdPedidoCompra() > 0) {
               if (oPedidoCompraDAO.alterar(oPedidoCompra)) {
                  gsMensagem = "Informações do Pedido Compra foram alteradas com Sucesso!";
               } else {
                  gsMensagem = "Erro ao alterar informações do Pedido Compra!";
               }

            } else {
               oFuncionario = (Funcionario) oFuncionarioDAO.carregar(Integer.parseInt(request.getParameter("idFuncionario")));
               oPedidoCompra.setFuncionario(oFuncionario);

               if (oPedidoCompraDAO.cadastrar(oPedidoCompra)) {
                  gsMensagem = "Informações do Pedido Compra foram cadastradas com Sucesso!";
               } else {
                  gsMensagem = "Erro ao gravar informações da Pedido Compra!";
               }

            }

            //Retorna para a JSP da Embalagem
            request.setAttribute("gsMensagem", gsMensagem);
            response.sendRedirect("PedidoCompraListar?tipoLista=G");

         } catch (Exception ex) {
            System.out.println("Problemas na Servlet Cadastra Altera Pedido Compra! Erro: " + ex.getMessage());
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
