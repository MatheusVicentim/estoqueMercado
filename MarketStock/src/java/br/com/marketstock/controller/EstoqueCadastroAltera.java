/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.EstoqueDAO;
import br.com.marketstock.dao.LoteDAO;
import br.com.marketstock.dao.ProdutoDAO;
import br.com.marketstock.model.Estoque;
import br.com.marketstock.model.Lote;
import br.com.marketstock.model.Produto;
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
@WebServlet(name = "EstoqueCadastroAltera", urlPatterns = {"/EstoqueCadastroAltera"})
public class EstoqueCadastroAltera extends HttpServlet {

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
            String gsMensagem = "";
            String gsFlag;

            Estoque oEstoque = new Estoque();
            Lote oLote;
            Produto oProduto;
            try {
                EstoqueDAO oEstoqueDAO = new EstoqueDAO();
                LoteDAO oLoteDAO = new LoteDAO();
                ProdutoDAO oProdutoDAO = new ProdutoDAO();

                //Passando valor para a classe
                oEstoque.setIdEstoque(Integer.parseInt(request.getParameter("idEstoque")));
                oEstoque.setQuantProdutoEstoque(Integer.parseInt(request.getParameter("quantEstoque")));
                oEstoque.setTipoEstoque(request.getParameter("tipoEstoque"));
                //Busca Lote
                oLote = (Lote) oLoteDAO.carregar(Integer.parseInt(request.getParameter("idLote")));
                oEstoque.setLote(oLote);
                //Buca Produto
                oProduto = (Produto) oProdutoDAO.carregar(Integer.parseInt(request.getParameter("idProduto")));
                oEstoque.setProduto(oProduto);
                gsFlag = request.getParameter("operacao");

                if (oEstoque.getIdEstoque() == 0) {
                    if (oEstoqueDAO.CadastraProdutoEstoque(oEstoque)) {
                        gsMensagem = "Informações do Estoque foram cadastradas com Sucesso!";
                    } else {
                        gsMensagem = "Problemas ao cadastrar informações do Estoque. Verifique os dados e tente novamente!";
                    }
                }

                request.setAttribute("gsMensagem", gsMensagem);
                //request.getRequestDispatcher("ModeloListar").forward(request, response);
                response.sendRedirect("EstoqueListar?tipoTela=ESTOQUEPRODUTO");

            } catch (Exception ex) {
                System.out.println("Problemas na Servlet EstoqueCadastroAltera! Erro:" + ex.getMessage());
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
