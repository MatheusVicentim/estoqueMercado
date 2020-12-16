/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.CargoDAO;
import br.com.marketstock.dao.FornecedorDAO;
import br.com.marketstock.dao.FuncionarioDAO;
import br.com.marketstock.dao.GenericDAO;
import br.com.marketstock.dao.PessoaDAO;
import br.com.marketstock.dao.SetorDAO;
import br.com.marketstock.model.Cargo;
import br.com.marketstock.model.Pessoa;
import br.com.marketstock.model.Enum;
import br.com.marketstock.model.Fornecedor;
import br.com.marketstock.model.Funcionario;
import br.com.marketstock.model.Setor;
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
@WebServlet(name = "PessoaTipoCadastra", urlPatterns = {"/PessoaTipoCadastra"})
public class PessoaTipoCadastra extends HttpServlet {

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
                Pessoa oPessoa = new Pessoa();
                Funcionario oFuncionario = new Funcionario();
                Fornecedor oFornecedor = new Fornecedor();
                Enum cEnum = new Enum();
                String sMensagem;
                String tipoPessoa = request.getParameter("tipoPessoa");
                if (tipoPessoa.equals(cEnum.fornecedor)) {
                    oFornecedor.setIdPessoa(Integer.parseInt(request.getParameter("idPessoa")));
                    oFornecedor.setRazaoSocial(request.getParameter("razaoSocial"));
                    oFornecedor.setResponsavelFornecedor(request.getParameter("responsavelFornecedor"));
                    oFornecedor.setTelefoneEmpresa(request.getParameter("telefoneEmpresa"));
                    FornecedorDAO oFornecedorDAO = new FornecedorDAO();
                    try {
                        if (oFornecedorDAO.cadastrar(oFornecedor)) {
                            sMensagem = "Tipo pessoa cadastrada com Sucesso!";
                        } else {
                            sMensagem = "Problemas ao cadastrar tipo pessoa!";
                        }
                        //Retorna para a jsp do Estoque
                        request.setAttribute("gsMensagem", sMensagem);
                        response.sendRedirect("Notificacao");
                    } catch (Exception ex) {
                        System.out.println("Problema na Servlect ao Cadastrar/Alterar Pessoa Fornecedor!" + ex.getMessage());
                    }
                } else {
                    oFuncionario.setIdPessoa(Integer.parseInt(request.getParameter("idPessoa")));
                    oFuncionario.setMatriculaFuncionario(request.getParameter("matriculaFuncionario"));
                    GenericDAO oCargoDAO = new CargoDAO();
                    Cargo oCargo = (Cargo) oCargoDAO.carregar(Integer.parseInt(request.getParameter("idCargo")));
                    oFuncionario.setCargo(oCargo);
                    SetorDAO oSetorDAO = new SetorDAO();
                    Setor oSetor = (Setor) oSetorDAO.carregar(Integer.parseInt(request.getParameter("idSetor")));
                    oFuncionario.setSetor(oSetor);
                    FuncionarioDAO oFuncionarioDAO = new FuncionarioDAO();
                    if (oFuncionarioDAO.cadastrar(oFuncionario)) {
                        sMensagem = "Tipo pessoa cadastrada com Sucesso!";
                    } else {
                        sMensagem = "Problemas ao cadastrar tipo pessoa!";
                    }
                    //Retorna para a jsp do Estoque
                    request.setAttribute("gsMensagem", sMensagem);
                    response.sendRedirect("Notificacao");
                    //int nIdCargo = Integer.parseInt(request.getParameter("idCargo"));
                    //int nIdSetor = Integer.parseInt(request.getParameter("idSetor"));
                }
            } catch (Exception ex) {
                System.out.println("Problema na Servlect ao Cadastrar tipo Pessoa!" + ex.getMessage());
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
