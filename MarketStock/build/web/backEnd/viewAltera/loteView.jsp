<%-- 
    Document   : loteView
    Created on : 22/06/2020, 19:38:52
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
                           <i class="fa fa-table"></i> Lotes
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="10" aling-="center">Lista de Lotes</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Núm. Lote</th>
                                       <th class="th-sm" colspan="1">Data de Fabricação</th>                      
                                       <th class="th-sm" colspan="1">Data de Vencimento</th>
                                       <th class="th-sm" colspan="1">Dias Vencimento</th>
                                       <th class="th-sm" colspan="1">Descrição</th>
                                       <th class="th-sm" colspan="1">Situação</th>
                                       <th class="th-sm" colspan="1">Editar</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="lote" items="${lLotes}">
                                       <tr>
                                          <td align="center">${lote.idLote}</td>
                                          <td align="center">${lote.numLote}</td>
                                          <td align="center">${lote.dataFabricacao}</td>
                                          <td align="center">${lote.dataVencimento}</td>
                                          <td align="center">${lote.quantDiaVencimento}</td>
                                          <td align="center">${lote.descLote}</td>
                                          <td align="center">${lote.situacao == 'A' ? 'Ativo': 'Inativo'}</td>
                                          <td align="center" colspan="2">    
                                             <a href="${pageContext.request.contextPath}/LoteExclur?idLote=${lote.idLote}"><button class="btn  <c:out value="${lote.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${lote.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                             <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#cadastrar-lote-modal" onclick="CarregarLote(${lote.idLote})">Alterar</button></a>                                
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
            <div class="modal fade bs-example-modal-lg" id="cadastrar-lote-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarLote" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Lote</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="loteCadastraAltera" action="${pageContext.request.contextPath}/LoteCadastraAltera" method="POST">
                        <div class="modal-body">                                     

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idLoteT" name="idLote"/>
                              </div>
                           </div>

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Núm. Lote</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="numLote" name="numLote" data-error="Preencha o campo número do lote!" required>
                                 <div class="help-block with-errors"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Data de Fabricação</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="date" id="dataFabricacao" name="dataFabricacao" data-error="Preencha o campo data de fabricação!" required/>
                                 <div class="help-block with-errors"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Data de Vencimento</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="date" id="dataVencimento" name="dataVencimento" data-error="Preencha o campo data de fabricação!" required/>
                                 <div class="help-block with-errors"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Descrição</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="descLote" name="descLote" data-error="Preencha o campo descrição!" required/>
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
            <!--Alterar-->
            <script type="text/javascript">
               function CarregarLote(pnIdLote) {
                  console.log("Entrou");
                  var idL = pnIdLote;
                  var x;
                  console.log("Lote = " + idL);
                  $(document).ready(function () {
                     $.getJSON('LoteCarregar', {idLote: idL}, function (respostaServlet) {
                        $('#idLoteT').val(respostaServlet.idLote);
                        x = respostaServlet.idLote;
                        console.log("Lote = " + x);
                        $('#numLote').val(respostaServlet.numLote);
                        $('#dataFabricacao').val(respostaServlet.dataFabricacao);
                        $('#dataVencimento').val(respostaServlet.dataVencimento);
                        $('#descLote').val(respostaServlet.descLote);
                     });
                  });
               }
            </script>    
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#numLote').empty();
                  $('#dataFabricacao').empty();
                  $('#dataVencimento').empty();
                  $('#descLote').empty();
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