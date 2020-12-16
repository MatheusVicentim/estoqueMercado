<%-- 
    Document   : orcamentoItensOrcamentoView
    Created on : 06/07/2020, 22:31:09
    Author     : mathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
   <c:when test = "${sessionScope.idPessoa != null}">
      <!DOCTYPE html>
      <html>
         <head>
            <link href="../../css/myCss.css" rel="stylesheet" type="text/css"/>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>MarketStock</title>
            <link rel="icon" type="img/png" href="/images/iconPretoPeq.ico"/>
         </head>
         <body>
            <%@include file="../painelControle.jsp"%>
            <link href="../../css/myCss.css" rel="stylesheet" type="text/css"/>
            <div class="content-wrapper">
               <div style="margin: 10px;">
                  <div class="container">
                     <div class="card mb-3">
                        <div class="card-header">
                           <i class="fa fa-table"></i> Orçameno
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="7" aling-="center">Orçamentos</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Descrição</th>
                                       <th class="th-sm" colspan="1">Fornecedor</th>                      
                                       <th class="th-sm" colspan="1">Valor Orçamento</th>
                                          <c:choose>
                                             <c:when test = "${sessionScope.tipoPessoa == 'FOR'}">
                                             <th class="th-sm" colspan="1">Adicionar Itens</th>
                                             </c:when>
                                             <c:otherwise>
                                             </c:otherwise>
                                          </c:choose>
                                       <th class="th-sm" colspan="1">Consuntar Orçamento</th>                                 
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="orcamento" items="${lOrcamentos}">
                                       <tr>
                                          <td align="center">${orcamento.idOrcamento}</td>
                                          <td align="center">${orcamento.descricaoOrcamento}</td>
                                          <td align="center">${orcamento.fornecedor.nomePessoa}</td>
                                          <td align="center">${orcamento.valorTotal}</td>
                                          <c:choose>
                                             <c:when test = "${sessionScope.tipoPessoa == 'FOR'}">
                                                <td align="center" colspan="1">    
                                                   <a href="#"><button class="btn btn-outline-success" data-toggle="modal" data-target="#orcamento-itens-modal" onclick="CarregarOrcamento(${orcamento.idOrcamento})">Incluir Item</button></a>                                
                                                </td>
                                             </c:when>
                                             <c:otherwise>
                                             </c:otherwise>
                                          </c:choose>
                                          <td align="center" colspan="1">    
                                             <a href="${pageContext.request.contextPath}/ItensOrcamentoListar?idOrcamento=${orcamento.idOrcamento}"><button class="btn btn-outline-dark">Consultar Itens</button></a>
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
            <div class="modal fade bs-example-modal-lg" id="orcamento-itens-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarItensOrcamento" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Itens Orcamento</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="itensOrcamentoCadastraAltera" action="${pageContext.request.contextPath}/ItensOrcamentoCadastraAltera" method="POST">
                        <div class="modal-body">   

                           <table class="table">
                              <tr >
                                 <td>Orcamento</td>
                                 <td>Valor Orçamento</td>
                              </tr>
                              <tr>
                                 <td><input type="text" class="form-control fct" name="descricaoOrcamento" id="descricaoOrcamento" class="form-control" readonly="readonly"/></td>
                                 <td><input type="text" class="form-control fct" name="valorTotal"         id="valorTotal"         class="form-control" readonly="readonly"/></td>
                              </tr>
                           </table>
                           <div class="esconder"><input id="idOrcamento" name="idOrcamento"/></div>

                           <div class="esconder">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idItensOrcamento" value="0" name="idItensOrcamento"/>
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
               function CarregarOrcamento(pnIdOrcamento) {
                  console.log("Entrou");
                  var idO = pnIdOrcamento;
                  //var sTipoPessoa = psTipoPessoa;
                  console.log("Orcamento = " + idO);
                  $(document).ready(function () {
                     $.getJSON('OrcamentoCarregar', {idOrcamento: idO}, function (respostaServlet) {
                        $('#idOrcamento').val(respostaServlet.idOrcamento);
                        $('#descricaoOrcamento').val(respostaServlet.descricaoOrcamento);
                     });
                  });
               }
            </script>
            <!--LimpaTela-->
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#nomeProduto').empty(0);
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
