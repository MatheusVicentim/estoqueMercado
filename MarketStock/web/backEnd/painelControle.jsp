<%-- 
    Document   : painelControle
    Created on : 07/03/2020, 17:07:32
    Author     : mathe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
   <c:when test = "${sessionScope.idPessoa != null}">
      <!DOCTYPE html>
      <html lang="en">
         
         <!--Alterar a forma como é alimentada as combos do relatório para ser igual a da Cidade-->
         <head>
            <%-- Importações jquery/ajax --%>
            <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>

            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta name="description" content="">
            <meta name="author" content="">
            <title>MarketStock</title>
            <link rel="icon" type="img/png" href="/images/iconPretoPeq.ico"/>
            <link href="../css/myCss.css" rel="stylesheet" type="text/css"/>
            <!-- Bootstrap core CSS-->
            <link href="/visualStyle/bootstrap/css/bootstrap.min.css" rel="stylesheet">
            <!-- Custom fonts for this template-->
            <link href="/visualStyle/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
            <!-- Page level plugin CSS-->
            <link href="/visualStyle/datatables/dataTables.bootstrap4.css" rel="stylesheet">
            <!-- Custom styles for this template-->
            <link href="/css/sb-admin.css" rel="stylesheet">

            <script src="../js/validator.min.js" type="text/javascript"></script>

         </head>

         <body class="fixed-nav sticky-footer bg-dark" id="page-top">
            <!-- Menus-->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
               <a class="navbar-brand" href="${pageContext.request.contextPath}/Notificacao">
                  <img src="/images/logoMiniBranco.png" style="width: 50px; height: 50px;" alt="">
                  MARKET STOCK
               </a>
               <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
               </button>
               <div class="collapse navbar-collapse" id="navbarResponsive">
                  <ul class="navbar-nav navbar-sidenav" id="exampleAccordion" style="overflow: auto;">
                     <c:choose>
                        <c:when test = "${sessionScope.tipoPessoa == 'ADM'}">
                           <!--Estoque-->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Estoque">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#estoque" data-parent="#exampleAccordion">
                                 <i class="fa fa-cubes"></i>
                                 <span class="nav-link-text">Controlar Estoque</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="estoque">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Estoque">Novo Item Estoque</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/EstoqueListar?tipoTela=ESTOQUELOTE">Consulta Estoque por Lote</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/EstoqueListar?tipoTela=ESTOQUEPRODUTO">Consulta Estoque Produto</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Pessoa-->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Pessoa">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#pessoa" data-parent="#exampleAccordion">
                                 <svg class="bi bi-person-fill" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                 <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 100-6 3 3 0 000 6z" clip-rule="evenodd"/>
                                 </svg>
                                 <span class="nav-link-text">Pessoa</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="pessoa">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Pessoa">Cadastrar Pessoa</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/PessoaTipoListar">Cadastrar Pessoa Geral</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Cadastros-->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
                                 <i class="fa fa-fw fa-wrench"></i>
                                 <span class="nav-link-text">Cadastros</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="collapseComponents">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/ProdutoListar?tipoTela=C">Produto</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Lote">Lote</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Embalagem">Embalagem</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Marca">Marca</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/UnidadeMedida">Unidade de Medida</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/SubProduto">Sub Produto</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Consulta-->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#consulta" data-parent="#exampleAccordion">
                                 <i class="fa fa-search" aria-hidden="true"></i>
                                 <span class="nav-link-text">Consulta</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="consulta">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/ProdutoListar?tipoTela=L">Produto</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/LoteListar">Lote</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/EmbalagemListar">Embalagem</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/MarcaListar">Marca</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/UnidadeMedidaListar">Unidade de Medida</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/SubProdutoListar">Sub Produto</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Pedido Compra--->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="PedidoCompra">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#pedidoCompra" data-parent="#exampleAccordion">
                                 <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                 <span class="nav-link-text">Pedido Compra</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="pedidoCompra">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/PedidoCompra">Cadastrar Pedido de Compras</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/PedidoCompraListar?tipoLista=A">Incluir Itens Pedido</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/PedidoCompraListar?tipoLista=G">Consulta Pedido Compra</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Orcamento--->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Orçamento">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#orcamento" data-parent="#exampleAccordion">
                                 <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                 <span class="nav-link-text">Orcamento</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="orcamento">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/OrcamentoListar?listaAtivos=A">Consulta Orçamentos</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Relatórios-->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Relatório">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#relatorio" data-parent="#exampleAccordion">
                                 <i class="fa fa-print" aria-hidden="true"></i>
                                 <span class="nav-link-text">Relatório</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="relatorio">
                                 <li>
                                    <a href="#" data-toggle="modal" data-target="#relEstoque">Relatório Estoque</a>                                
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/RelNotificacoes">Notificações Produto</a>                                
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/RelConfeProduto">Conferência Produto</a>                                
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/RelPedidoCompra">Conferência Produto</a>                                
                                 </li>
                                 <li>
                                    <a href="#" data-toggle="modal" data-target="#relPedidoCompra">Relatório Pedido de Compra</a>                                
                                 </li>
                              </ul>
                           </li>

                        </c:when>

                        <c:when test = "${sessionScope.tipoPessoa == 'FUN'}">
                           <!--Estoque-->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Estoque">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#estoque" data-parent="#exampleAccordion">
                                 <i class="fa fa-cubes"></i>
                                 <span class="nav-link-text">Controlar Estoque</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="estoque">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Estoque">Novo Item Estoque</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/EstoqueControle?tipoTela=L">Entrada/Saída Estoque</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/EstoqueListar?tipoTela=ESTOQUELOTE">Consulta Estoque por Lote</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/EstoqueListar?tipoTela=ESTOQUEPRODUTO">Consulta Estoque Produto</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Cadastros-->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
                                 <i class="fa fa-fw fa-wrench"></i>
                                 <span class="nav-link-text">Cadastros</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="collapseComponents">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/ProdutoListar?tipoTela=C">Produto</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Lote">Lote</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Embalagem">Embalagem</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Marca">Marca</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/UnidadeMedida">Unidade de Medida</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/SubProduto">Sub Produto</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Consulta-->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#consulta" data-parent="#exampleAccordion">
                                 <i class="fa fa-search" aria-hidden="true"></i>
                                 <span class="nav-link-text">Consulta</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="consulta">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/ProdutoListar?tipoTela=L">Produto</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/LoteListar">Lote</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/EmbalagemListar">Embalagem</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/MarcaListar">Marca</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/UnidadeMedidaListar">Unidade de Medida</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/SubProdutoListar">Sub Produto</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Pedido Compra--->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="PedidoCompra">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#pedidoCompra" data-parent="#exampleAccordion">
                                 <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                 <span class="nav-link-text">Pedido Compra</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="pedidoCompra">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/PedidoCompra">Cadastrar Pedido de Compras</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/PedidoCompraListar?tipoLista=A">Incluir Itens Pedido</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/PedidoCompraListar?tipoLista=G">Consulta Pedido Compra</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Relatórios-->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Relatório">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#relatorio" data-parent="#exampleAccordion">
                                 <i class="fa fa-print" aria-hidden="true"></i>
                                 <span class="nav-link-text">Relatório</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="relatorio">
                                 <li>
                                    <a href="#" data-toggle="modal" data-target="#relEstoque">Relatório Estoque</a>                                
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/RelNotificacoes">Notificações Produto</a>                                
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/RelConfeProduto">Conferência Produto</a>                                
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/RelPedidoCompra">Conferência Produto</a>                                
                                 </li>
                                 <li>
                                    <a href="#" data-toggle="modal" data-target="#relPedidoCompra">Relatório Pedido de Compra</a>                                
                                 </li>
                              </ul>
                           </li>
                        </c:when>  

                        <c:when test = "${sessionScope.tipoPessoa == 'FOR'}">
                           <!--Orcamento--->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Orçamento">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#orcamento" data-parent="#exampleAccordion">
                                 <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                 <span class="nav-link-text">Orcamento</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="orcamento">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/Orcamento">Cadastrar Orçamento</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/OrcamentoListar?listaAtivos=A">Incluir Itens Orçamento</a>
                                 </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/OrcamentoListar?listaAtivos=L">Consulta Orçamentos</a>
                                 </li>
                              </ul>
                           </li>

                           <!--Pedido Compra--->
                           <li class="nav-item" data-toggle="tooltip" data-placement="right" title="PedidoCompra">
                              <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#pedidoCompra" data-parent="#exampleAccordion">
                                 <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                 <span class="nav-link-text">Pedido Compra</span>
                              </a>
                              <ul class="sidenav-second-level collapse" id="pedidoCompra">
                                 <li>
                                    <a href="${pageContext.request.contextPath}/PedidoCompraListar?tipoLista=A">Consulta Pedido Compra</a>
                                 </li>
                              </ul>
                           </li>
                        </c:when>

                        <c:otherwise>

                        </c:otherwise>
                     </c:choose>

                  </ul>
                  <ul class="navbar-nav sidenav-toggler">
                     <li class="nav-item">
                        <a class="nav-link text-center" id="sidenavToggler">
                           <i class="fa fa-fw fa-angle-left"></i>
                        </a>
                     </li>
                  </ul>
                  <ul class="navbar-nav ml-auto">

                     <c:choose>
                        <c:when test = "${sessionScope.tipoPessoa != 'FOR'}">
                           <li class="nav-item dropdown">
                              <a class="nav-link dropdown-toggle mr-lg-2" id="alertsDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                 <i class="fa fa-fw fa-bell"></i>
                                 <span class="d-lg-none">Notificações</span>
                                 <span class="indicator text-warning d-none d-lg-block">
                                    <i class="fa fa-fw fa-circle"></i>
                                 </span>
                              </a>

                              <div class="dropdown-menu" aria-labelledby="alertsDropdown">
                                 <h6 class="dropdown-header">Notificações</h6>
                                 <div class="dropdown-divider"></div>
                                 <c:forEach var="notificacoes" items="${lNotificacoes}">
                                    <a class="dropdown-item " style="margin-right: 100px" href="#">
                                       <span class="text-danger">
                                          <strong>
                                             <i class="fa fa-long-arrow-right fa-fw"></i>${notificacoes.notificacao}</strong>
                                       </span>
                                       <span class="small float-right text-muted"></span>
                                       <div class="dropdown-message">${notificacoes.nomeProduto}</div>
                                    </a>
                                 </c:forEach>
                              </div>
                           </li>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                     </c:choose>
                     <li class="nav-item">
                        <a href="../index.jsp"><img src="../images/casaMiniBranco.png" alt="Voltar para inicio"/></a>
                     </li>

                     <li class="nav-item">
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="" onclick="deslogar()" name="sair" id="sair">
                           <i class="fa fa-fw fa-sign-out"></i>Logout
                        </button>
                     </li>
                  </ul>
               </div>
            </nav>  

            <!-- Rel Estoque -->
            <div class="modal fade bs-example-modal-lg" id="relEstoque" tabindex="-1" role="dialog" aria-labelledby="relEstoque" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Relatório</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form data-toggle="validator" name="relEstoque" action="${pageContext.request.contextPath}/RelEstoque" method="POST"><!--enctype="multipart/form-data"-->
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Lote</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idLote" id="idLote" tabindex="3" data-error="Selecione um Lote!">
                                    <option value="0">Selecione</option>
                                    <c:forEach var="lote" items="${lLotes}">
                                       <option value="${lote.idLote}">
                                          ${lote.numLote}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Produto</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idProduto" id="idProduto" tabindex="3" data-error="Selecione um Produto!">
                                    <option value="0">Selecione</option>
                                    <c:forEach var="produto" items="${lProdutos}">
                                       <option value="${produto.idProduto}">
                                          ${produto.nomeProduto}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                        </div>
                        <div class="modal-footer">  
                           <input type="submit" class="btn btn-dark" value="Imprimir"/>
                           <input type="reset" onclick="LimparModal()" class="btn btn-danger" value="Limpar"/>
                        </div>

                     </form>
                  </div>
               </div>
            </div>
            <!--Fim do Rel Estoque-->
            <!-- Rel PedidoCompra -->
            <div class="modal fade bs-example-modal-lg" id="relPedidoCompra" tabindex="-1" role="dialog" aria-labelledby="relEstoque" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Relatório Pedido Compra</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form data-toggle="validator" name="relEstoque" action="${pageContext.request.contextPath}/RelPedidoCompra" method="POST"><!--enctype="multipart/form-data"-->
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Pedido Compra</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idPedido" id="idPedido" tabindex="3" data-error="Selecione um Pedido!">
                                    <option value="0">Selecione</option>
                                    <c:forEach var="pedido" items="${lPedidoCompras}">
                                       <option value="${pedido.idPedidoCompra}">
                                          ${pedido.descricaoPedido}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>                           
                        </div>
                        <div class="modal-footer">  
                           <input type="submit" class="btn btn-dark" value="Imprimir"/>
                           <input type="reset" onclick="LimparModal()" class="btn btn-danger" value="Limpar"/>
                        </div>

                     </form>
                  </div>
               </div>
            </div>
            <!--Fim do Rel PedidoCompra-->

            <!-- Bootstrap core JavaScript-->
            <script src="/visualStyle/jquery/jquery.min.js"></script>
            <script src="/visualStyle/bootstrap/js/bootstrap.bundle.min.js"></script>
            <!-- Core plugin JavaScript-->
            <script src="/visualStyle/jquery-easing/jquery.easing.min.js"></script>
            <!-- Page level plugin JavaScript-->
            <script src="/visualStyle/datatables/jquery.dataTables.js"></script>
            <script src="/visualStyle/datatables/dataTables.bootstrap4.js"></script>
            <!-- Custom scripts for all pages-->
            <script src="/js/sb-admin.min.js"></script>
            <!-- Custom scripts for this page-->
            <script src="/js/sb-admin-datatables.min.js"></script>
            <script src="/js/sb-admin-charts.min.js"></script>

            <script>
                              function deslogar() {
                                 //console.log("entrei funcao sair");
                                 window.location.href = "${pageContext.request.contextPath}/index.jsp";
                              }
            </script>  
         </body>
      </html>
   </c:when>
   <c:otherwise>
      <script>
         window.location.replace("index.jsp");
      </script>
   </c:otherwise>
</c:choose>

