<%-- 
    Document   : pedidoCompraView
    Created on : 08/07/2020, 23:53:13
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
                           <i class="fa fa-table"></i> Pedidos de Compra
                           ${gsMensagem}
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="6" aling-="center">Lista de Orçamentos</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Descrição do PedidoCompra</th>
                                       <th class="th-sm" colspan="1">Dta. Pedido Compra</th>
                                       <th class="th-sm" colspan="1">Funcionario</th> 
                                       <th class="th-sm" colspan="1">Tipo Pagamento</th> 
                                       <th class="th-sm" colspan="1">Situação</th>
                                       <th class="th-sm" colspan="1">Editar</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="pedidoCompra" items="${lPedidoCompras}">
                                       <tr>
                                          <td align="center">${pedidoCompra.idPedidoCompra}</td>
                                          <td align="center">${pedidoCompra.descricaoPedido}</td>
                                          <td align="center">${pedidoCompra.dtaPedido}</td>
                                          <td align="center">${pedidoCompra.funcionario.nomePessoa}</td>
                                          <td align="center">${pedidoCompra.tipoPagamento.metodoPagamento}</td>
                                          <td align="center">${pedidoCompra.situacao == 'A' ? 'Ativo': 'Inativo'}</td>
                                          <td align="center" colspan="2">    
                                             <a href="${pageContext.request.contextPath}/PedidoCompraExcluir?idPedidoCompra=${pedidoCompra.idPedidoCompra}"><button class="btn  <c:out value="${pedidoCompra.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${pedidoCompra.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                             <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#cadastrar-pedido-modal" onclick="CarregarPedido(${pedidoCompra.idPedidoCompra})">Alterar</button></a>                                
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

            <!-- Large modal -->
            <div class="modal fade bs-example-modal-lg" id="cadastrar-pedido-modal" tabindex="-1" role="dialog" aria-labelledby="alteraPedidoCompra" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Pedidos de Compra</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="orcamentoCadastraAltera" action="${pageContext.request.contextPath}/PedidoCompraCadastraAltera" method="POST">
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idPedidoCompra" name="idPedidoCompra"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Descrição do Pedido</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="descricaoPedido" name="descricaoPedido" data-error="Preencha o campo descrição!" required/>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Tipo de Pagamento</label>
                              <div class="col-sm-12 col-md-10">                                    
                                 <select class="form-control fct" name="idTipoPagamento" id="idTipoPagamento" tabindex="3" data-error="Selecione a Unidade de Medida !" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="tipoPagamento" items="${lTipoPagamentos}">
                                       <option value="${tipoPagamento.idTipoPagamento}">
                                          ${tipoPagamento.metodoPagamento}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
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


            <!--Alterar-->
            <script type="text/javascript">
               function CarregarPedido(pnIdPedido) {
                  console.log("Entrou");
                  var idP = pnIdPedido;
                  console.log("pedido Compra = " + idP);
                  $(document).ready(function () {
                     $.getJSON('PedidoCompraCarregar', {idPedidoCompra: idP}, function (respostaServlet) {
                        $('#idPedidoCompra').val(respostaServlet.idPedidoCompra);
                        $('#descricaoPedido').val(respostaServlet.descricaoPedido);
                        $('#idTipoPagamento').val(respostaServlet.tipoPagamento.idTipoPagamento);
                        $('#metodoPagamento').val(respostaServlet.tipoPagamento.metodoPagamento);

                     });
                  });
               }
            </script>

            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#descricaoPedido').empty();
                  $('#tipoPagamento').empty();
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
