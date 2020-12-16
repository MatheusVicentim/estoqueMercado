<%-- 
    Document   : itensOrcamentoView
    Created on : 07/07/2020, 20:37:59
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
                           <i class="fa fa-table"></i> Itens Orçamento
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="7" aling-="center">Lista de Itens Orçamento</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Produto</th>
                                       <th class="th-sm" colspan="1">Vlr. Produto</th>                      
                                       <th class="th-sm" colspan="1">Qtd. Produto</th>
                                       <th class="th-sm" colspan="1">Situação</th>
                                          <c:choose>
                                             <c:when test = "${sessionScope.tipoPessoa == 'FOR'}">
                                             <th class="th-sm" colspan="1">Editar</th>
                                             </c:when>
                                             <c:otherwise>
                                             </c:otherwise>
                                          </c:choose>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="item" items="${lItemOrcamentos}">
                                       <tr>
                                          <td align="center">${item.idItensOrcamento}</td>
                                          <td align="center">${item.nomeProduto}</td>
                                          <td align="center">${item.valorItensOrcamento}</td>
                                          <td align="center">${item.qtdProduto}</td> 
                                          <td align="center">${item.situacao}</td>
                                          <c:choose>
                                             <c:when test = "${sessionScope.tipoPessoa == 'FOR'}">
                                                <td align="center" colspan="2">    
                                                   <a href="${pageContext.request.contextPath}/ItensOrcamentoExclur?idItens=${item.idItensOrcamento}"><button class="btn  <c:out value="${item.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${item.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                                   <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#alterar-itens-modal" onclick="CarregarOrcamento(${item.idItensOrcamento})">Alterar</button></a>                                
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
            <div class="modal fade bs-example-modal-lg" id="alterar-itens-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarItens" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Itens Orçamento</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="loteCadastraAltera" action="${pageContext.request.contextPath}/ItensOrcamentoCadastraAltera" method="POST">
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idItensOrcamento" name="idItensOrcamento"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Nome Produto</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="nomeProduto" name="nomeProduto"/>
                              </div>
                           </div>

                           <div class="esconder"><input id="idOrcamento" name="idOrcamento"/></div>

                           <table class="table">
                              <tr >
                                 <td><label class="col-sm-12 col-md-5 col-form-label" for="valorItensOrcamento">Valor Produto</label></td>
                                 <td><label class="col-sm-12 col-md-5 col-form-label" for="qtdProduto">Quant. Produto</label></td>
                              </tr>
                              <tr>
                                 <td>
                                    <div class="col-sm-12 col-md-10">
                                       <input class="form-control fct" type="number" id="valorItensOrcamento" name="valorItensOrcamento" data-error="Informe o campo com valor do produto!" required/>
                                       <div class="help-block with-errors" id="corDataError"></div>
                                    </div>
                                 </td>
                                 <td>
                                    <div class="col-sm-12 col-md-10">
                                       <input class="form-control fct" type="number" id="qtdProduto" name="qtdProduto" data-error="Informe a Quantidade!" required/>
                                       <div class="help-block with-errors" id="corDataError"></div>
                                    </div>
                                 </td>
                              </tr>
                           </table>

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
               function CarregarOrcamento(pnIdItens) {
                  console.log("Entrou");
                  var idI = pnIdItens;
                  console.log("ItensOrcamento = " + idI);
                  $(document).ready(function () {
                     $.getJSON('ItensOrcamentoCarregar', {idOrcamento: idI}, function (respostaServlet) {
                        $('#idItensOrcamento').val(respostaServlet.idItensOrcamento);
                        $('#nomeProduto').val(respostaServlet.nomeProduto);
                        $('#qtdProduto').val(respostaServlet.qtdProduto);
                        $('#valorItensOrcamento').val(respostaServlet.valorItensOrcamento);
                        $('#idOrcamento').val(respostaServlet.orcamento.idOrcamento);
                     });
                  });
               }
            </script>
            <!--LimpaTela-->
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#valorItensOrcamento').empty();
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