<%-- 
    Document   : estoqueLoteView
    Created on : 04/07/2020, 16:09:35
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
                           <i class="fa fa-table"></i> Estoque por Produto
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="7" aling-="center">Lista Produtos no Estoque</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID Produto</th>
                                       <th class="th-sm" colspan="1">Nome Produto</th>
                                       <th class="th-sm" colspan="1">Num. Lote</th>
                                       <th class="th-sm" colspan="1">Qtd. Produto</th>                      
                                       <th class="th-sm" colspan="1">Data de Vencimento</th>
                                       <th class="th-sm" colspan="1">Dias Vencimento</th>
                                       <th class="th-sm" colspan="1">Saída</th>
                                       <th class="th-sm" colspan="1">Entrada</th>                                 
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="estoque" items="${lEstoques}">
                                       <tr>
                                          <td align="center">${estoque.idProduto}</td>
                                          <td align="center">${estoque.nomeProduto}</td>
                                          <td align="center">${estoque.numLote}</td>                                    
                                          <td align="center">${estoque.qtdProdutoEstoque}</td>
                                          <td align="center">${estoque.dtaVencimento}</td>                                    
                                          <%--<td align="center">${estoque.dtaVencimento}</td>--%>
                                          <td align="center" class="
                                              <c:choose>
                                                 <c:when test="${estoque.diasVencimento < 10}">text-danger</c:when>
                                                 <c:when test="${estoque.diasVencimento < 30}">text-warning</c:when>
                                                 <c:otherwise>text-dark'</c:otherwise>
                                              </c:choose>
                                              ">${estoque.diasVencimento}
                                          </td>
                                          <td align="center">
                                             <a href="#"><button class="btn btn-outline-danger" data-toggle="modal" data-target="#saida-estoque-modal" onclick="CarregarRetirada(${estoque.idProduto}, ${estoque.idLote})">Saída</button></a>
                                          </td>
                                          <td align="center">
                                             <a href="#"><button class="btn btn-outline-dark" data-toggle="modal" data-target="#entrada-estoque-modal" onclick="CarregarRetirada(${estoque.idProduto}, ${estoque.idLote})">Entrada</button></a>
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

            <%--Modal Saída Estoque--%>
            <div class="modal fade bs-example-modal-lg" id="saida-estoque-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarLote" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Saída Estoque</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="pessoaTipoCadastra" action="${pageContext.request.contextPath}/EstoqueControle?acao=S" method="POST"> 

                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Produto</label>
                              <div class="col-sm-12 col-md-10">
                                 <input type="text" class="form-control fct" name="nomeProduto" id="nomeProduto" readonly="readonly"/>
                              </div>
                           </div>
                           <table class="table">
                              <tr >
                                 <td>Identificador Produto</td>
                                 <td>Núm. Lote</td>
                              </tr>
                              <tr>
                                 <td><input type="text" class="form-control fct" name="idProduto" id="idProdutoT" class="form-control" readonly="readonly"/></td>
                                 <td><input type="text" class="form-control fct" name="numLote"   id="numLote"   class="form-control" readonly="readonly"/></td>
                              </tr>
                           </table>
                           <div class="esconder"><input id="idLoteT" name="idLote"/></div>

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Qtd. Movimentação</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="qtdMovimentado" name="qtdMovimentado" data-error="Informe o campo com a qtd. à retirar!" required>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>

                           <div class="modal-footer">
                              <input type="submit" class="btn btn-success" value="Retirar"/>
                              <input type="button" onclick="LimparModal()" class="btn btn-danger" value="Limpar"/>
                           </div>
                        </div>
                     </form>
                  </div>
               </div>
            </div>

            <%--Modal Entrada Estoque--%>
            <div class="modal fade bs-example-modal-lg" id="entrada-estoque-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarLote" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Entrada Estoque</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="pessoaTipoCadastra" action="${pageContext.request.contextPath}/EstoqueControle?acao=E" method="POST"> 

                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input type="text" class="form-control fct" name="nomeProduto" id="nomeProdutoEntrada" readonly="readonly"/>
                              </div>
                           </div>
                           <table class="table">
                              <tr >
                                 <td>Identificador Produto</td>
                                 <td>Núm. Lote</td>
                              </tr>
                              <tr>
                                 <td><input type="text" class="form-control fct" name="idProduto" id="idProdutoEntrada" class="form-control" readonly="readonly"/></td>
                                 <td><input type="text" class="form-control fct" name="numLote"   id="numLoteEntrada"   class="form-control" readonly="readonly"/></td>
                              </tr>
                           </table>
                           <div class="esconder"><input id="idLoteEntrada" name="idLote"/></div>

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Qtd. Entrar</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="qtdMovimentado" name="qtdMovimentado" data-error="Preencha o campo com a qtd. à Entrar!" required>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>

                           <div class="modal-footer">
                              <input type="submit" class="btn btn-success" value="Entrar"/>
                              <input type="button" onclick="LimparModal()" class="btn btn-danger" value="Limpar"/>
                           </div>
                        </div>
                     </form>
                  </div>
               </div>
            </div>

            <%--Script Carregar--%>
            <script type="text/javascript">
               function CarregarRetirada(pnIdProduto, pnIdLote) {
                  console.log("Entrou");
                  var idP = pnIdProduto;
                  var idL = pnIdLote;
                  console.log("Lote = " + idL);
                  console.log("Produto = " + idP);

                  $(document).ready(function () {
                     $.getJSON('EstoqueCarregar', {idLote: idL, idProduto: idP}, function (respostaServlet) {
                        $('#idProdutoT').val(respostaServlet.produto.idProduto);
                        $('#nomeProduto').val(respostaServlet.produto.nomeProduto);
                        $('#idLoteT').val(respostaServlet.lote.idLote);
                        $('#numLote').val(respostaServlet.lote.numLote);

                        $('#idProdutoEntrada').val(respostaServlet.produto.idProduto);
                        $('#nomeProdutoEntrada').val(respostaServlet.produto.nomeProduto);
                        $('#idLoteEntrada').val(respostaServlet.lote.idLote);
                        $('#numLoteEntrada').val(respostaServlet.lote.numLote);
                     });
                  });
               }
            </script> 

            <%--Script Limpar--%>                  
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#qtdRetirada').empty();
                  //$('#dataFabricacao').empty();
                  //$('#dataVencimento').empty();
                  //$('#descLote').empty();
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