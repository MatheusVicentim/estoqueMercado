<%-- 
    Document   : unidadeMedidaView
    Created on : 27/06/2020, 09:59:22
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
                           <i class="fa fa-table"></i> Unidade de Medidas
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="5" aling-="center">Lista de Unidade Medida</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Descrição da Unidade</th>
                                       <th class="th-sm" colspan="1">Abreviação da Unidade</th> 
                                       <th class="th-sm" colspan="1">Situação</th>
                                       <th class="th-sm" colspan="1">Editar</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="unidadeMedida" items="${lUnidadeMedidas}">
                                       <tr>
                                          <td align="center">${unidadeMedida.idUnidadeMedida}</td>
                                          <td align="center">${unidadeMedida.descricaoUnidadeMedida}</td>
                                          <td align="center">${unidadeMedida.abreviacaoUnidade}</td>
                                          <td align="center">${unidadeMedida.situacao == 'A' ? 'Ativo': 'Inativo'}</td>
                                          <td align="center" colspan="2">    
                                             <a href="${pageContext.request.contextPath}/UnidadeMedidaExcluir?idUnidadeMedida=${unidadeMedida.idUnidadeMedida}"><button class="btn  <c:out value="${unidadeMedida.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${unidadeMedida.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                             <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#cadastrar-medida-modal" onclick="CarregarMedida(${unidadeMedida.idUnidadeMedida})">Alterar</button></a>                                
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
            <div class="modal fade bs-example-modal-lg" id="cadastrar-medida-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarUnidadeMedida" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Unidade de Medida</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="unidadeMedidaCadastraAltera" action="${pageContext.request.contextPath}/UnidadeMedidaCadastraAltera" method="POST">
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idUnidadeMedida" name="idUnidadeMedida"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Descrição da Medida</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="descricaoUnidadeMedida" name="descricaoUnidadeMedida" data-error="Preencha o campo descrição!" required>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Abreviação</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="abreviacaoUnidade" name="abreviacaoUnidade" data-error="Preencha o campo abreviação!" required/>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>

                           <div class="modal-footer">      
                              <input type="submit" class="btn btn-success" value="Salvar"/>
                              <input type="submit" onclick="LimparModal()" class="btn btn-danger" value="Limpar"/>
                           </div>
                        </div>
                     </form>
                  </div>
               </div>
            </div>
            <!--Fim da Modal-->

            <!--Alterar-->
            <script type="text/javascript">
               function CarregarMedida(pnIdMedida) {
                  console.log("Entrou");
                  var idUn = pnIdMedida;
                  console.log("Lote = " + idUn);
                  $(document).ready(function () {
                     $.getJSON('UnidadeMedidaCarregar', {idUnidadeMedida: idUn}, function (respostaServlet) {
                        $('#idUnidadeMedida').val(respostaServlet.idUnidadeMedida);
                        $('#descricaoUnidadeMedida').val(respostaServlet.descricaoUnidadeMedida);
                        $('#abreviacaoUnidade').val(respostaServlet.abreviacaoUnidade);
                     });
                  });
               }
            </script>    
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#descricaoUnidadeMedida').empty();
                  $('#abreviacaoUnidade').empty();
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

