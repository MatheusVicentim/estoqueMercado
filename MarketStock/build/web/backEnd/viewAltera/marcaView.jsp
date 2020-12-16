<%-- 
    Document   : marcaView
    Created on : 26/06/2020, 22:14:23
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
                           <i class="fa fa-table"></i> Marcas
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="4" aling-="center">Lista de Marcas</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Descriçã da Marca</th>
                                       <th class="th-sm" colspan="1">Situação</th>
                                       <th class="th-sm" colspan="1">Editar</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="marca" items="${lMarcas}">
                                       <tr>
                                          <td align="center">${marca.idMarca}</td>
                                          <td align="center">${marca.descricaoMarca}</td>
                                          <td align="center">${marca.situacao == 'A' ? 'Ativo': 'Inativo'}</td>
                                          <td align="center" colspan="1">    
                                             <a href="${pageContext.request.contextPath}/MarcaExcluir?idMarca=${marca.idMarca}"><button class="btn  <c:out value="${marca.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${marca.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                             <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#cadastrar-marca-modal" onclick="CarregarMarca(${marca.idMarca})">Alterar</button></a>                                
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
            <div class="modal fade bs-example-modal-lg" id="cadastrar-marca-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarMarca" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Marca</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="marcaCadastraAltera" action="${pageContext.request.contextPath}/MarcaCadastraAltera" method="POST">
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idMarca" name="idMarca"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Descrição da Marca</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="descricaoMarca" name="descricaoMarca" data-error="Informe o campo número do lote!" required/>
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
               function CarregarMarca(pnIdMarca) {
                  console.log("Entrou");
                  var idM = pnIdMarca;
                  console.log("Marca = " + idM);
                  $(document).ready(function () {
                     $.getJSON('MarcaCarregar', {idMarca: idM}, function (respostaServlet) {
                        $('#idMarca').val(respostaServlet.idMarca);
                        $('#descricaoMarca').val(respostaServlet.descricaoMarca);
                     });
                  });
               }
            </script>    
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#descricaoMarca').empty();
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
