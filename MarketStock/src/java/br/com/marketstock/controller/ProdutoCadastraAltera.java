/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.controller;

import br.com.marketstock.dao.EmbalagemDAO;
import br.com.marketstock.dao.MarcaDAO;
import br.com.marketstock.dao.ProdutoDAO;
import br.com.marketstock.dao.SubProdutoDAO;
import br.com.marketstock.dao.UnidadeMedidaDAO;
import br.com.marketstock.model.Embalagem;
import br.com.marketstock.model.Marca;
import br.com.marketstock.model.Produto;
import br.com.marketstock.model.SubProduto;
import br.com.marketstock.model.UnidadeMedida;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author mathe
 */
@WebServlet(name = "ProdutoCadastraAltera", urlPatterns = {"/ProdutoCadastraAltera"})
public class ProdutoCadastraAltera extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws org.apache.commons.fileupload.FileUploadException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException {
        response.setContentType("text/html;charset=iso-8859-1");
        //Pega sessão vigente no contexto do servidor
        HttpSession sessao = request.getSession(false);
        //Verifica se existe sessão e se existe usuario logado
        if (sessao != null) {
            String gsMensagem;
            Produto oProduto = new Produto();
            Marca oMarca;
            Embalagem oEmbalagem;
            SubProduto oSubProduto;
            UnidadeMedida oUnidadeMedida;
            boolean bMultipart = ServletFileUpload.isMultipartContent(request);
            try {
                UnidadeMedidaDAO oUnidadeMedidaDAO = new UnidadeMedidaDAO();
                EmbalagemDAO oEmbalagemDAO = new EmbalagemDAO();
                SubProdutoDAO oSubProdutoDAO = new SubProdutoDAO();
                MarcaDAO oMarcaDAO = new MarcaDAO();
                ProdutoDAO oProdutoDAO = new ProdutoDAO();
                if (bMultipart) {
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    upload.setSizeMax(100 * 1024 * 1024); //50Mb
                    List items = upload.parseRequest(request);
                    InputStream is = null;
                    Iterator it = items.iterator();
                    while (it.hasNext()) {
                        FileItem oFileItem = (FileItem) it.next();
                        if (!oFileItem.isFormField()) {
                            is = oFileItem.getInputStream();
                            oProduto.setImagemProduto(is);
                            oProduto.setFint((int) oFileItem.getSize());
                        } else {
                            String dados = oFileItem.getFieldName();
                            switch (dados) {
                                case "nomeProduto":
                                    oProduto.setNomeProduto(oFileItem.getString());
                                    break;
                                case "codigoBarra":
                                    oProduto.setCodigoBarra(oFileItem.getString());
                                    break;
                                case "situacao":
                                    oProduto.setSituacao(oFileItem.getString());
                                    break;
                                case "idUnidadeMedida":
                                    oUnidadeMedida = (UnidadeMedida) oUnidadeMedidaDAO.carregar(Integer.parseInt(oFileItem.getString()));
                                    oProduto.setUnidadeMedida(oUnidadeMedida);
                                    break;
                                case "idEmbalagem":
                                    oEmbalagem = (Embalagem) oEmbalagemDAO.carregar(Integer.parseInt(oFileItem.getString()));
                                    oProduto.setEmbalagem(oEmbalagem);
                                    break;
                                case "idSubProduto":
                                    oSubProduto = (SubProduto) oSubProdutoDAO.carregar(Integer.parseInt(oFileItem.getString()));
                                    oProduto.setSubProduto(oSubProduto);
                                    break;
                                case "idMarca":
                                    oMarca = (Marca) oMarcaDAO.carregar(Integer.parseInt(oFileItem.getString()));
                                    oProduto.setMarca(oMarca);
                                    break;
                                case "idProduto":
                                    if (!oFileItem.getString().isEmpty()) {
                                        oProduto.setIdProduto(Integer.parseInt(oFileItem.getString()));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
                //Verifica qual ação será feita
                if (oProduto.getIdProduto() == 0) {
                    if (oProdutoDAO.cadastrar(oProduto)) {
                        gsMensagem = "Informações do Produto foram cadastradas com Sucesso!";
                    } else {
                        gsMensagem = "Problemas ao cadastrar!";
                    }
                } else {
                    if (oProdutoDAO.alterar(oProduto)) {
                        gsMensagem = "Produto alterado com Sucesso!";
                    } else {
                        gsMensagem = "Erro ao Alterar Produto!";
                    }
                }
                //Retorna para a JSP dos Produtos
                request.setAttribute("gsMensagem", gsMensagem);
                //request.getRequestDispatcher("ModeloListar").forward(request, response);
                response.sendRedirect("ProdutoListar?tipoTela=L");
            } catch (Exception ex) {
                System.out.println("Problemas na Servlet ProdutoCadastraAltera! Erro:" + ex.getMessage());
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(ProdutoCadastraAltera.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(ProdutoCadastraAltera.class.getName()).log(Level.SEVERE, null, ex);
        }
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
