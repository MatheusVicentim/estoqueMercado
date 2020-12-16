/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import br.com.marketstock.model.Marca;
import br.com.marketstock.model.Produto;
import br.com.marketstock.model.UnidadeMedida;
import br.com.marketstock.model.Embalagem;
import br.com.marketstock.model.SubProduto;
import br.com.marketstock.model.Enum;

import br.com.marketstock.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */
public class ProdutoDAO implements GenericDAO {

   private Connection conexao;

   public ProdutoDAO() throws Exception {
      try {
         this.conexao = ConnectionFactory.getConnection();
         System.out.println("Conectado com SUCESSO");
      } catch (Exception ex) {
         throw new Exception("Problema ao conectar no BD! Erro: " + ex.getMessage());
      }
   }

   @Override
   public Boolean cadastrar(Object pObjeto) {
      String sql = "Insert Into MSW_Produto(nomeProduto, codBarraProd, situacao, imagemProduto, idMarca, idUnidadeMedida, idEmbalagem, idSubProduto)"
              + " Values(?, ?, ?, ?, ?, ?, ?, ?)";

      Produto oProduto = (Produto) pObjeto;
      Enum cEnum = new Enum();
      Boolean bRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oProduto.getNomeProduto());
         stmt.setString(2, oProduto.getCodigoBarra());
         stmt.setString(3, cEnum.ativo);
         //stmt.setBytes(3, oProduto.getImagemProduto());
         stmt.setBinaryStream(4, oProduto.getImagemProduto());
         stmt.setInt(5, oProduto.getMarca().getIdMarca());
         stmt.setInt(6, oProduto.getUnidadeMedida().getIdUnidadeMedida());
         stmt.setInt(7, oProduto.getEmbalagem().getIdEmbalagem());
         stmt.setInt(8, oProduto.getSubProduto().getIdSubProduto());
         stmt.execute();

         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Erro ao dar Entrada no Produto (DAO). " + ex.getMessage());
         bRetorno = false;
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao encerrar conexão - " + ex.getMessage());
         }
      }

      return bRetorno;
   }

   @Override
   public Boolean alterar(Object pObjeto) {
      String sql = "Update MSW_Produto"
              + " Set nomeProduto=?, codBarraProd=?, imagemProduto=?, idMarca=?, idUnidadeMedida=?, idEmbalagem=?, idSubProduto=?"
              + " Where idProduto=?";

      Produto oProduto = (Produto) pObjeto;
      Boolean bRetorno;
      PreparedStatement stmt = null;

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setString(1, oProduto.getNomeProduto());
         stmt.setString(2, oProduto.getCodigoBarra());
         //stmt.setBytes(3, oProduto.getImagemProduto());
         stmt.setBinaryStream(3, oProduto.getImagemProduto());
         stmt.setInt(4, oProduto.getMarca().getIdMarca());
         stmt.setInt(5, oProduto.getUnidadeMedida().getIdUnidadeMedida());
         stmt.setInt(6, oProduto.getEmbalagem().getIdEmbalagem());
         stmt.setInt(7, oProduto.getSubProduto().getIdSubProduto());
         stmt.setInt(8, oProduto.getIdProduto());

         stmt.execute();
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Problemas ao alterar Produto(DAO)! Erro: " + ex.getMessage());
         bRetorno = false;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão com BD fechada!");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
         }
      }
      return bRetorno;
   }

   @Override
   public Boolean excluir(Object pObjeto) {
      String sql = "Update MSW_Produto Set situacao=? Where idProduto=?";

      Produto oProduto = (Produto) pObjeto;
      Enum cEnum = new Enum();
      Boolean bRetorno;
      PreparedStatement stmt = null;

      try {
         stmt = conexao.prepareStatement(sql);
         System.out.println("Alerta");

         if (oProduto.getSituacao().equals(cEnum.ativo)) {
            stmt.setString(1, cEnum.inativo);
         } else {
            stmt.setString(1, cEnum.ativo);
         }
         stmt.setInt(2, oProduto.getIdProduto());

         stmt.execute();
         bRetorno = true;
      } catch (Exception ex) {
         System.out.println("Problemas mudar o Status do Produto (DAO)! Erro: " + ex.getMessage());
         bRetorno = false;
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão com BD fechada!");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
         }
      }
      return bRetorno;
   }

   @Override
   public Object carregar(int pnIdProduto) {
      String sql = "Select *"
              + " From MSW_Produto"
              + " Where idProduto=?";

      Produto oProduto = null;
      Object oRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdProduto);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oProduto = new Produto();
            oProduto.setIdProduto(rs.getInt("idProduto"));
            oProduto.setNomeProduto(rs.getString("nomeProduto"));
            oProduto.setCodigoBarra(rs.getString("codBarraProd"));
            oProduto.setImagemProduto(rs.getBinaryStream("imagemProduto"));
            //Busca de dados de outros tabelas
            //Unidade de Medida
            UnidadeMedidaDAO oUnidadeMedidaDAO = new UnidadeMedidaDAO();
            UnidadeMedida oUnidadeMedida = (UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idUnidadeMedida"));
            oProduto.setUnidadeMedida(oUnidadeMedida);
            //Embalagem
            EmbalagemDAO oEmbalagemDAO = new EmbalagemDAO();
            Embalagem oEmbalagem = (Embalagem) oEmbalagemDAO.carregar(rs.getInt("idEmbalagem"));
            oProduto.setEmbalagem(oEmbalagem);
            //Sub Produto
            SubProdutoDAO oSubProdutoDAO = new SubProdutoDAO();
            SubProduto oSubProduto = (SubProduto) oSubProdutoDAO.carregar(rs.getInt("idSubProduto"));
            oProduto.setSubProduto(oSubProduto);
            //Marca
            MarcaDAO oMarcaDAO = new MarcaDAO();
            Marca oMarca = (Marca) oMarcaDAO.carregar(rs.getInt("idMarca"));
            oProduto.setMarca(oMarca);
         }
         oRetorno = oProduto;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregar Produto! Erro: " + ex.getMessage());
         oRetorno = null;
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar os parâmetros de conexao! Erro: " + ex.getMessage());
         }
      }

      return oRetorno;
   }

   @Override
   public List<Object> listar() {
      String sql = "Select *"
              + " From MSW_Produto"
              + " Order By nomeProduto";
      List<Object> lResultado = new ArrayList<>();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;
      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Produto oProduto = new Produto();
            oProduto.setIdProduto(rs.getInt("idProduto"));
            oProduto.setNomeProduto(rs.getString("nomeProduto"));
            oProduto.setCodigoBarra((rs.getString("codBarraProd")));
            //oProduto.setImagemProduto(rs.getBytes("imagemProduto"));
            oProduto.setImagemProduto(rs.getBinaryStream("imagemProduto"));
            oProduto.setSituacao(rs.getString("situacao"));
            //Busca Marca
            MarcaDAO oMarcaDAO;
            try {
               oMarcaDAO = new MarcaDAO();
               Marca oMarca = (Marca) oMarcaDAO.carregar(rs.getInt("idMarca"));
               oProduto.setMarca(oMarca);
               System.out.println("Buscar Marca efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Marca!Erro (ProdutoDAO): " + ex.getMessage());
            }
            //Busca Unidade de Medida
            UnidadeMedidaDAO oUnidadeMedidaDAO;
            try {
               oUnidadeMedidaDAO = new UnidadeMedidaDAO();
               UnidadeMedida oUnidadeMedida = (UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idUnidadeMedida"));
               oProduto.setUnidadeMedida(oUnidadeMedida);
               System.out.println("Buscar Unidade de Medida efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Unidade de Medida!Erro (ProdutoDAO): " + ex.getMessage());
            }
            //Busca Embalagem
            EmbalagemDAO oEmbalagemDAO;
            try {
               oEmbalagemDAO = new EmbalagemDAO();
               Embalagem oEmbalagem = (Embalagem) oEmbalagemDAO.carregar(rs.getInt("idEmbalagem"));
               oProduto.setEmbalagem(oEmbalagem);
               System.out.println("Busca de Embalagem efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Embalagem!Erro (ProdutoDAO): " + ex.getMessage());
            }
            //Busca SubProduto
            SubProdutoDAO oSubProdutoDAO;
            try {
               oSubProdutoDAO = new SubProdutoDAO();
               SubProduto oSubProduto = (SubProduto) oSubProdutoDAO.carregar(rs.getInt("idSubProduto"));
               oProduto.setSubProduto(oSubProduto);
               System.out.println("Busca de Subproduto efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Subproduto!Erro (ProdutoDAO): " + ex.getMessage());
            }
            lResultado.add(oProduto);
         }
         System.out.println("Sucesso ao listar Produto (DAO)");
      } catch (SQLException ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Produto (DAO) - " + ex.getMessage());
         lResultado.add(null);
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return lResultado;
   }

   public Produto getImagemProduto(int pnIdProduto) {
      Produto oProduto = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      //String sql = "select nomeJogador, fotoJogador from jogador where idJogador=?";
      String sql = "Select nomeProduto, imagemProduto"
              + " From MSW_Produto"
              + " Where idProduto=?";

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdProduto);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oProduto = new Produto();
            oProduto.setNomeProduto(rs.getString("nomeProduto"));
            oProduto.setImagemProduto(rs.getBinaryStream("imagemProduto"));
         }
      } catch (SQLException ex) {
         System.out.println("Problemas ao obter foto do Produto na ProdutoDAO" + ex.getMessage());
         ex.printStackTrace();
      } finally {
         try {
            ConnectionFactory.closeConnect(conexao, stmt);
            System.out.println("Conexão com BD fechada!");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar parametros de conexão! Erro: " + ex.getMessage());
         }
      }
      return oProduto;
   }

   public Object carregaInativaAtiva(int pnIdProduto) {
      String sql = "Select idProduto, situacao"
              + " From MSW_Produto"
              + " Where idProduto=?";

      Produto oProduto = null;
      Object oRetorno;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      try {
         stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, pnIdProduto);
         rs = stmt.executeQuery();

         while (rs.next()) {
            oProduto = new Produto();
            oProduto.setIdProduto(rs.getInt("idProduto"));
            oProduto.setSituacao(rs.getString("situacao"));
         }
         oRetorno = oProduto;
      } catch (Exception ex) {
         System.out.println("Problemas ao carregaInativaAtiva ProdutoDAO! Erro: " + ex.getMessage());
         oRetorno = null;
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Problemas ao fechar os parâmetros de conexao! Erro: " + ex.getMessage());
         }
      }

      return oRetorno;
   }

   public List<Object> listaAtivos() {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Connection conn = null;

      String sql = "Select *"
              + " From MSW_Produto"
              + " Where situacao = '" + cEnum.ativo + "'"
              + " Order By nomeProduto";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Produto oProduto = new Produto();
            oProduto.setIdProduto(rs.getInt("idProduto"));
            oProduto.setNomeProduto(rs.getString("nomeProduto"));
            oProduto.setCodigoBarra((rs.getString("codBarraProd")));
            //oProduto.setImagemProduto(rs.getBytes("imagemProduto"));
            oProduto.setImagemProduto(rs.getBinaryStream("imagemProduto"));
            oProduto.setSituacao(rs.getString("situacao"));
            //Busca Marca
            MarcaDAO oMarcaDAO;
            try {
               oMarcaDAO = new MarcaDAO();
               Marca oMarca = (Marca) oMarcaDAO.carregar(rs.getInt("idMarca"));
               oProduto.setMarca(oMarca);
               System.out.println("Buscar Marca efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Marca!Erro (ProdutoDAO): " + ex.getMessage());
            }
            //Busca Unidade de Medida
            UnidadeMedidaDAO oUnidadeMedidaDAO;
            try {
               oUnidadeMedidaDAO = new UnidadeMedidaDAO();
               UnidadeMedida oUnidadeMedida = (UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idUnidadeMedida"));
               oProduto.setUnidadeMedida(oUnidadeMedida);
               System.out.println("Buscar Unidade de Medida efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Unidade de Medida!Erro (ProdutoDAO): " + ex.getMessage());
            }
            //Busca Embalagem
            EmbalagemDAO oEmbalagemDAO;
            try {
               oEmbalagemDAO = new EmbalagemDAO();
               Embalagem oEmbalagem = (Embalagem) oEmbalagemDAO.carregar(rs.getInt("idEmbalagem"));
               oProduto.setEmbalagem(oEmbalagem);
               System.out.println("Busca de Embalagem efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Embalagem!Erro (ProdutoDAO): " + ex.getMessage());
            }
            //Busca SubProduto
            SubProdutoDAO oSubProdutoDAO;
            try {
               oSubProdutoDAO = new SubProdutoDAO();
               SubProduto oSubProduto = (SubProduto) oSubProdutoDAO.carregar(rs.getInt("idSubProduto"));
               oProduto.setSubProduto(oSubProduto);
               System.out.println("Busca de Subproduto efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Subproduto!Erro (ProdutoDAO): " + ex.getMessage());
            }
            lResultado.add(oProduto);
         }
         System.out.println("Sucesso ao listar Produto (DAO)");
      } catch (SQLException ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Produto (DAO) - " + ex.getMessage());
         lResultado.add(null);
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return lResultado;
   }

   public List<Object> rel(int pnIdProduto, String psNomeProduto, String psCodBarra, int pnUnidadeMedida, int pnEmbalagem, int pnMarca) {
      List<Object> lResultado = new ArrayList<>();
      Enum cEnum = new Enum();
      PreparedStatement stmt = null;
      ResultSet rs = null;

      String sql = "Select * From MSW_Produto P";
      sql += " Where P.situacao = '" + cEnum.ativo + "'";
      if (pnIdProduto > 0) {
         sql += " And P.idProduto = " + pnIdProduto;
      }
      if (psNomeProduto.length() > 0) {
         sql += " And P.nomeProduto = '" + psNomeProduto + "'"; 
      }
      if (psCodBarra.length() > 0) {
         sql += " And P.codBarraProd = '" + psCodBarra + "'";
      }
      if (pnUnidadeMedida > 0) {
         sql += " And P.idUnidadeMedida = " + pnUnidadeMedida;
      }
      if (pnEmbalagem > 0) {
         sql += " And P.idEmbalagem = " + pnEmbalagem;
      }
      if (pnMarca > 0) {
         sql += " And P.idMarca = " + pnMarca;
      }
      sql += " Order By nomeProduto";

      try {
         stmt = conexao.prepareStatement(sql);
         rs = stmt.executeQuery();
         while (rs.next()) {
            Produto oProduto = new Produto();
            
            oProduto.setIdProduto(rs.getInt("idProduto"));
            oProduto.setNomeProduto(rs.getString("nomeProduto"));
            oProduto.setCodigoBarra((rs.getString("codBarraProd")));
            
            //Busca Marca
            MarcaDAO oMarcaDAO;
            try {
               oMarcaDAO = new MarcaDAO();
               Marca oMarca = (Marca) oMarcaDAO.carregar(rs.getInt("idMarca"));
               oProduto.setMarca(oMarca);
               System.out.println("Buscar Marca efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Marca!Erro (ProdutoDAO): " + ex.getMessage());
            }
            //Busca Unidade de Medida
            UnidadeMedidaDAO oUnidadeMedidaDAO;
            try {
               oUnidadeMedidaDAO = new UnidadeMedidaDAO();
               UnidadeMedida oUnidadeMedida = (UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idUnidadeMedida"));
               oProduto.setUnidadeMedida(oUnidadeMedida);
               System.out.println("Buscar Unidade de Medida efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Unidade de Medida!Erro (ProdutoDAO): " + ex.getMessage());
            }
            //Busca Embalagem
            EmbalagemDAO oEmbalagemDAO;
            try {
               oEmbalagemDAO = new EmbalagemDAO();
               Embalagem oEmbalagem = (Embalagem) oEmbalagemDAO.carregar(rs.getInt("idEmbalagem"));
               oProduto.setEmbalagem(oEmbalagem);
               System.out.println("Busca de Embalagem efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Embalagem!Erro (ProdutoDAO): " + ex.getMessage());
            }
            //Busca SubProduto
            SubProdutoDAO oSubProdutoDAO;
            try {
               oSubProdutoDAO = new SubProdutoDAO();
               SubProduto oSubProduto = (SubProduto) oSubProdutoDAO.carregar(rs.getInt("idSubProduto"));
               oProduto.setSubProduto(oSubProduto);
               System.out.println("Busca de Subproduto efetuado com sucesso! (ProdutoDAO)");
            } catch (Exception ex) {
               System.out.println("Erro ao Buscar Subproduto!Erro (ProdutoDAO): " + ex.getMessage());
            }
            
            lResultado.add(oProduto);
         }
         System.out.println("Sucesso ao listar Fornecedor (DAO)");
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println("Erro ao listar Fornecedor (DAO) - " + ex);
      } finally {
         try {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
            System.out.println("Conexão encerrada");
         } catch (Exception ex) {
            System.out.println("Erro ao fechar conn = " + ex.getMessage());
         }
      }
      return lResultado;
   }

}
