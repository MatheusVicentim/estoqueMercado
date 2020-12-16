<%-- 
    Document   : pedidoCompraView
    Created on : 08/07/2020, 19:42:55
    Author     : mathe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
   <c:when test = "${sessionScope.idPessoa != null}">
      <!DOCTYPE html>
      <html>
         <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>MarketStock</title>
            <link rel="icon" type="img/png" href="/images/iconPretoPeq.ico"/>
         </head>
         <body>

         <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Lote</title>
         </head>
         <body>
            <%@include file="../painelControle.jsp"%>
            <div class="content-wrapper">
               <div style="margin: 10px;">
                  <div class="container">
                     <div class="card mb-3">
                        <div class="card-header">
                           <i class="fa fa-table"></i> Pedido de Compras
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="5" aling-="center">Lista de Pedido Compras</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Descrição</th>
                                       <th class="th-sm" colspan="1">Data do Pedido</th>                      
                                       <th class="th-sm" colspan="1">Funcionario</th>
                                          <c:choose>
                                             <c:when test = "${sessionScope.tipoPessoa != 'FOR'}">
                                             <th class="th-sm" colspan="1">Incluir Itens</th>
                                             </c:when>
                                             <c:otherwise>
                                             </c:otherwise>
                                          </c:choose>
                                       <th class="th-sm" colspan="1">Consultar Itens</th>                                       
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="pedidoCompras" items="${lPedidoCompras}">
                                       <tr>
                                          <td align="center">${pedidoCompras.idPedidoCompra}</td>
                                          <td align="center">${pedidoCompras.descricaoPedido}</td>
                                          <td align="center">${pedidoCompras.dtaPedido}</td>
                                          <td align="center">${pedidoCompras.funcionario.nomePessoa}</td>
                                          <c:choose>
                                             <c:when test = "${sessionScope.tipoPessoa != 'FOR'}">
                                                <td align="center" colspan="1">    
                                                   <a href="#"><button class="btn btn-outline-success" data-toggle="modal" data-target="#pedidoCompra-itens-modal" onclick="CarregarPedidoCompra(${pedidoCompras.idPedidoCompra})">Incluir Item</button></a>                                
                                                </td>
                                             </c:when>
                                             <c:otherwise>
                                             </c:otherwise>
                                          </c:choose>
                                          <td align="center" colspan="1">    
                                             <a href="${pageContext.request.contextPath}/ItensPedidoCompraListar?idPedidoCompra=${pedidoCompras.idPedidoCompra}"><button class="btn btn-outline-dark">Consultar Itens</button></a>
                                          </td>
                                       </tr>
                                    </c:forEach>
                                 </tbody>
                              </table>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>

            <!-- Large modal Cadastro Itens -->
            <div class="modal fade bs-example-modal-lg" id="pedidoCompra-itens-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarItensOrcamento" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Itens Pedido Compra</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="itensOrcamentoCadastraAltera" action="${pageContext.request.contextPath}/ItensPedidoCompraCadastraAltera" method="POST">
                        <div class="modal-body">   

                           <table class="table">
                              <tr >
                                 <td>Pedido Compra</td>
                                 <td>Data Pedido</td>
                              </tr>
                              <tr>
                                 <td><input type="text" class="form-control fct" name="descricaoPedido" id="descricaoPedido" class="form-control" readonly="readonly"/></td>
                                 <td><input type="text" class="form-control fct" name="dtaPedido"       id="dtaPedido"       class="form-control" readonly="readonly"/></td>
                              </tr>
                           </table>
                           <div class="esconder"><input id="idPedidoCompra" name="idPedidoCompra"/></div>

                           <div class="esconder">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idItensPedidoCompra" name="idItensPedidoCompra" value="0"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Produto</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="nomeProduto" id="nomeProduto" tabindex="3" data-error="Selecione o Produto!" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="produto" items="${lProdutos}">
                                       <option value="${produto.nomeProduto}">
                                          ${produto.nomeProduto}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Qtd. Produto</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="number" id="qtdProduto" name="qtdProduto" data-error="Preencha o campo com qtd. produto!" required/>
                                 <div class="help-block with-errors"></div>
                              </div>
                           </div>

                           <div class="modal-footer">
                              <input type="submit" class="btn btn-success" value="Salvar"/>
                              <input type="reset" onclick="LimparModal()" class="btn btn-danger" value="Limpar"/>
                           </div>
                        </div>
                     </form>
                  </div>
               </div>
            </div>
            <!--Fim da Modal-->

            <!--carregar Itens Orcamento-->
            <script type="text/javascript">
               function CarregarPedidoCompra(pnIdPedidoCompra) {
                  console.log("Entrou");
                  var idP = pnIdPedidoCompra;
                  //var sTipoPessoa = psTipoPessoa;
                  console.log("Orcamento = " + idP);
                  $(document).ready(function () {
                     $.getJSON('PedidoCompraCarregar', {idPedidoCompra: idP}, function (respostaServlet) {
                        $('#idPedidoCompra').val(respostaServlet.idPedidoCompra);
                        $('#descricaoPedido').val(respostaServlet.descricaoPedido);
                        $('#dtaPedido').val(respostaServlet.dtaPedido);
                     });
                  });
               }
            </script>
            <!--LimpaTela-->
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#nomeProduto').empty(0);
                  $('#qtdProduto').empty();
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

