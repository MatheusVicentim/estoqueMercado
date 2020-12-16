<%-- 
    Document   : orcamentoView
    Created on : 07/07/2020, 11:26:56
    Author     : Gabriel Vinicius
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
                           <i class="fa fa-table"></i> Orçamentos
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
                                       <th class="th-sm" colspan="1">Descrição do Orçamento</th>
                                       <th class="th-sm" colspan="1">Valor Total</th>
                                       <th class="th-sm" colspan="1">Fornecedor</th> 
                                       <th class="th-sm" colspan="1">Situação</th>
                                       <th class="th-sm" colspan="1">Editar</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="orcamento" items="${lOrcamentos}">
                                       <tr>
                                          <td align="center">${orcamento.idOrcamento}</td>
                                          <td align="center">${orcamento.descricaoOrcamento}</td>
                                          <td align="center">${orcamento.valorTotal}</td>
                                          <td align="center">${orcamento.fornecedor.razaoSocial}</td>
                                          <td align="center">${orcamento.situacao == 'A' ? 'Ativo': 'Inativo'}</td>
                                          <td align="center" colspan="2">    
                                             <a href="${pageContext.request.contextPath}/OrcamentoExcluir?idOrcamento=${orcamento.idOrcamento}"><button class="btn  <c:out value="${orcamento.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${orcamento.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                             <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#cadastrar-orcamento-modal" onclick="CarregarOrcamento(${orcamento.idOrcamento})">Alterar</button></a>                                
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
            <div class="modal fade bs-example-modal-lg" id="cadastrar-orcamento-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarLote" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Orçamento</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="orcamentoCadastraAltera" action="${pageContext.request.contextPath}/OrcamentoCadastraAltera" method="POST">
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idOrcamento" name="idOrcamento"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Descrição do Orçamento</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="descricaoOrcamento" name="descricaoOrcamento" data-error="Preencha o campo descrição!" required/>
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
               function CarregarOrcamento(pnIdOrcamento) {
                  console.log("Entrou");
                  var idO = pnIdOrcamento;
                  console.log("Orçamento = " + idO);
                  $(document).ready(function () {
                     $.getJSON('OrcamentoCarregar', {idOrcamento: idO}, function (respostaServlet) {
                        $('#idOrcamento').val(respostaServlet.idOrcamento);
                        $('#descricaoOrcamento').val(respostaServlet.descricaoOrcamento);
                     });
                  });
               }
            </script>    
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#descricaoOrcamento').empty();
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
