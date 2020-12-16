<%-- 
    Document   : itensPedidoCompra
    Created on : 08/07/2020, 22:28:18
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

            <%@include file="../painelControle.jsp"%>
            <div class="content-wrapper">
               <div style="margin: 10px;">
                  <div class="container">
                     <div class="card mb-3">
                        <div class="card-header">
                           <i class="fa fa-table"></i> Itens do Pedido Compra
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="5" aling-="center">Lista de Itens do Pedido Compra</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Produto</th>                    
                                       <th class="th-sm" colspan="1">Qtd. Produto</th>
                                       <th class="th-sm" colspan="1">Situação</th>
                                          <c:choose>
                                             <c:when test = "${sessionScope.tipoPessoa != 'FOR'}">
                                             <th class="th-sm" colspan="1">Editar</th>
                                             </c:when>
                                             <c:otherwise>
                                             </c:otherwise>
                                          </c:choose>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="item" items="${lItemPedidos}">
                                       <tr>
                                          <td align="center">${item.idItensPedidoCompra}</td>
                                          <td align="center">${item.nomeProduto}</td>
                                          <td align="center">${item.qtdProduto}</td> 
                                          <td align="center">${item.situacao}</td> 
                                          <c:choose>
                                             <c:when test = "${sessionScope.tipoPessoa != 'FOR'}">
                                                <td align="center" colspan="2">    
                                                   <a href="${pageContext.request.contextPath}/ItensPedidoCompraExclur?idItens=${item.idItensPedidoCompra}"><button class="btn  <c:out value="${item.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${item.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                                   <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#alterarPedido-itens-modal" onclick="CarregarItens(${item.idItensPedidoCompra})">Alterar</button></a>                                
                                                </td> 
                                             </c:when>
                                             <c:otherwise>
                                             </c:otherwise>
                                          </c:choose>
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

            <!-- Large modal -->
            <div class="modal fade bs-example-modal-lg" id="alterarPedido-itens-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarItens" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Itens do Pedido Compra</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="itensPedidoCompraCadastraAltera" action="${pageContext.request.contextPath}/ItensPedidoCompraCadastraAltera" method="POST">
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idItensPedidoCompra" name="idItensPedidoCompra"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Nome Produto</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="nomeProduto" name="nomeProduto"/>
                              </div>
                           </div>

                           <div class="esconder"><input id="idPedidoCompra" name="idPedidoCompra"/></div>

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Qtd. Produto</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="qtdProduto" name="qtdProduto" data-error="Preencha o campo com a quantidade!" required/>
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
               function CarregarItens(pnIdItens) {
                  console.log("Entrou Itens Pedido");
                  var idI = pnIdItens;
                  console.log("ItensOrcamento = " + idI);
                  $(document).ready(function () {
                     $.getJSON('ItensPedidoCompraCarregar', {idItensPedidoCompra: idI}, function (respostaServlet) {
                        $('#idItensPedidoCompra').val(respostaServlet.idItensPedidoCompra);
                        $('#nomeProduto').val(respostaServlet.nomeProduto);
                        $('#qtdProduto').val(respostaServlet.qtdProduto);
                        $('#idPedidoCompra').val(respostaServlet.pedidoCompra.idPedidoCompra);
                     });
                  });
               }
            </script>
            <!--LimpaTela-->
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
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