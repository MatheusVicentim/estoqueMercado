/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.model.Pessoa;
import br.com.marketstock.dao.LogarPessoaDAO;
import java.io.IOException;
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
@WebServlet(name = "PessoaLogar", urlPatterns = {"/PessoaLogar"})
public class PessoaLogar extends HttpServlet {

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
      //if (sessao != null) {
      String sLogin = request.getParameter("login");
      String sSenha = request.getParameter("senha");
      String sTipoPessoa = request.getParameter("tipoPessoa");
      Pessoa oPessoa = null;
      String gsUsuarioLogado = "";
      try {
         LogarPessoaDAO oLoginPessoaDAO = new LogarPessoaDAO();
         oPessoa = (Pessoa) oLoginPessoaDAO.logar(sLogin, sSenha, sTipoPessoa);
         if (oPessoa != null) {
            sessao = request.getSession();
            sessao.setAttribute("idPessoa", oPessoa.getIdPessoa());
            sessao.setAttribute("login", oPessoa.getLogin());
            sessao.setAttribute("cpfCnpj", oPessoa.getCpfCnpjPessoa());
            sessao.setAttribute("nomePessoa", oPessoa.getNomePessoa());
            sessao.setAttribute("tipoPessoa", oPessoa.getTipoPessoa());
            request.getRequestDispatcher("indexLogado.jsp").forward(request, response);
            response.setCharacterEncoding("UTF-8");
         } else {
            request.setAttribute("msg", "<div class='my-3 text-center alert alert-danger'>Erro de Login. Verifique se digitou as informações corretamente!<div>");
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            response.sendRedirect("index.jsp");
         }

      } catch (Exception ex) {
         System.out.println("Erro no Servlet Logar - " + ex.getMessage());
      }
      //} else {
      //   request.setAttribute("mensagem", "Pessoa não Logado ao sistema");
      //   request.getRequestDispatcher("index.jsp").forward(request, response);
      //}

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
