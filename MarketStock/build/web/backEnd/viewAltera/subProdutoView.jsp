<%-- 
    Document   : subProdutoView
    Created on : 06/07/2020, 22:11:08
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
                           <i class="fa fa-table"></i> Sub Produto
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="4" aling-="center">Lista de Sub Produto</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Descrição do Sub Produto</th>
                                       <th class="th-sm" colspan="1">Situação</th>
                                       <th class="th-sm" colspan="1">Editar</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="subProduto" items="${lSubProdutos}">
                                       <tr>
                                          <td align="center">${subProduto.idSubProduto}</td>
                                          <td align="center">${subProduto.descricaoSubProduto}</td>
                                          <td align="center">${subProduto.situacao == 'A' ? 'Ativo': 'Inativo'}</td>
                                          <td align="center" colspan="2">    
                                             <a href="${pageContext.request.contextPath}/SubProdutoExcluir?idSubProduto=${subProduto.idSubProduto}"><button class="btn  <c:out value="${subProduto.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${subProduto.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                             <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#cadastrar-subProduto-modal" onclick="CarregarSubProduto(${subProduto.idSubProduto})">Alterar</button></a>                                
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
            <div class="modal fade bs-example-modal-lg" id="cadastrar-subProduto-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarUnidadeMedida" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Sub Produto</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="subProdutoCadastraAltera" action="${pageContext.request.contextPath}/SubProdutoCadastraAltera" method="POST">
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idSubProduto" name="idSubProduto"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Descrição do Sub Produto</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="descricaoSubProduto" name="descricaoSubProduto" data-error="Informe o campo descrição!" required>
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
               function CarregarSubProduto(pnIdSubProduto) {
                  console.log("Entrou");
                  var idSub = pnIdSubProduto;
                  console.log("SubProduto = " + idSub);
                  $(document).ready(function () {
                     $.getJSON('SubProdutoCarregar', {idSubProduto: idSub}, function (respostaServlet) {
                        $('#idSubProduto').val(respostaServlet.idSubProduto);
                        $('#descricaoSubProduto').val(respostaServlet.descricaoSubProduto);
                     });
                  });
               }
            </script>    
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#descricaoSubProduto').empty();
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

