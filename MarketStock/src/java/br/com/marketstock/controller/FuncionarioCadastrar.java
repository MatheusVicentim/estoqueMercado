/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

//DAO
import br.com.marketstock.dao.FuncionarioDAO;

//Model
import br.com.marketstock.model.Cidade;
import br.com.marketstock.model.Funcionario;
import br.com.marketstock.model.Pessoa;
import br.com.marketstock.model.Setor;
import br.com.marketstock.model.Cargo;
import br.com.marketstock.util.ValidacaoCpf;

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
@WebServlet(name = "FuncionarioCadastrar", urlPatterns = {"/FuncionarioCadastrar"})
public class FuncionarioCadastrar extends HttpServlet {

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
            String gsMensagem = null;
            try {
                Funcionario oFuncionario = new Funcionario();
                Cidade oCidade = new Cidade();
                Setor oSetor = new Setor();
                Cargo oCargo = new Cargo();
                //Atribuos da Pessoa
                if (ValidacaoCpf.isCPF(request.getParameter("cpfCnpjPessoa"))) {
                    oFuncionario.setCpfCnpjPessoa(request.getParameter("cpfCnpjPessoa"));
                }
                oFuncionario.setNomePessoa(request.getParameter("nomePessoa"));
                oFuncionario.setRgLePessoa(request.getParameter("rgLePessoa"));
                oFuncionario.setDataNascPessoa(request.getParameter("dataNascPessoa"));
                oFuncionario.setEmailPessoa(request.getParameter("emailPessoa"));
                oFuncionario.setLogin(request.getParameter("login"));
                oFuncionario.setSenha(request.getParameter("senha"));
                oFuncionario.setNumTelefone(request.getParameter("numTelefone"));
                //Endereço Pessoa
                oFuncionario.setLogradouro(request.getParameter("logradouro"));
                oFuncionario.setNumero(Integer.parseInt(request.getParameter("numero")));
                oFuncionario.setComplemento(request.getParameter("complemento"));
                oFuncionario.setBairro(request.getParameter("bairro"));
                oFuncionario.setStatusPessoa(request.getParameter("statusPessoa"));
                oFuncionario.setTipoPessoa(request.getParameter("tipoPessoa"));
                //Cidade e Estado
                oCidade.setIdCidade(Integer.parseInt(request.getParameter("idCidade")));
                oFuncionario.setCidade(oCidade);
                //Informações do Funcionario
                oFuncionario.setMatriculaFuncionario(request.getParameter("matriculaFuncionario"));
                //Setor
                oSetor.setIdSetor(Integer.parseInt(request.getParameter("idSetor")));
                oFuncionario.setSetor(oSetor);
                //Cargo
                oCargo.setIdCargo(Integer.parseInt(request.getParameter("idCargo")));
                oFuncionario.setCargo(oCargo);
                //Confirmar a senha
                String sConfirmaSenha = request.getParameter("confirmaSenha");
                //oFuncionario.setCidade(Integer.parseInt(request.getParameter("idCidade")));
//         GenericDAO oCidadeDAO = new CidadeDAO();
//         Cidade oCidade = (Cidade) oCidadeDAO.carregar(Integer.parseInt(request.getParameter("idCidade")));
//         oFuncionario.setCidade(oCidade);
//         //Informações do Funcionario
//         oFuncionario.setMatriculaFuncionario(request.getParameter("matriculaFuncionario"));
//         //Buscando Setor
//         GenericDAO oSetorDAO = new SetorDAO();
//         Setor oSetor = (Setor) oSetorDAO.carregar(Integer.parseInt(request.getParameter("idSetor")));
//         oFuncionario.setSetor(oSetor);
//         //Buscando Cargo
//         GenericDAO oCargoDAO = new CargoDAO();
//         Cargo oCargo = (Cargo) oSetorDAO.carregar(Integer.parseInt(request.getParameter("idSetor")));
//         oFuncionario.setSetor(oSetor);
                if (oFuncionario.getSenha().length() > 4 && sConfirmaSenha.length() > 6 && oFuncionario.getSenha().equals("sConfirmaSenha")) {
                    FuncionarioDAO oFuncionarioDAO = new FuncionarioDAO();
                    if (oFuncionarioDAO.cadastrar(oFuncionario)) {
                        gsMensagem = "Informações do Funcionario foram cadastradas com Sucesso! (Servlet)";
                    } else {
                        gsMensagem = "Problemas ao cadastrar informações do Funcionario. Verifique os dados e tente novamente! (Servlet)";
                    }
                } else {
                    request.setAttribute("msg", "<div class='msg alert alert-warning'>Erro ao cadastrar Funcionario, senha diferente da confirmação. Verifique os dados e tente novamente!</div>");
                    request.getRequestDispatcher("cadastrarFuncionario.jsp").forward(request, response);
                }

            } catch (Exception ex) {
                System.out.println("Problemas no Servelet ao Cadastrar Funcionario! Erro: " + ex.getMessage());
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
