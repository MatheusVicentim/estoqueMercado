<%-- 
    Document   : embalagemView
    Created on : 25/06/2020, 07:33:50
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
                           <i class="fa fa-table"></i> Embalagem
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="7" aling-="center">Lista de Embalagem</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Tipo da Embalagem</th>
                                       <th class="th-sm" colspan="1">Quant. Embalagem</th>                      
                                       <th class="th-sm" colspan="1">Cód. Barras</th>
                                       <th class="th-sm" colspan="1">Descriição Emabalagem</th>
                                       <th class="th-sm" colspan="1">Situação</th>
                                       <th class="th-sm" colspan="1">Editar</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="embalagem" items="${lEmabalagens}">
                                       <tr>
                                          <td align="center">${embalagem.idEmbalagem}</td>
                                          <td align="center">${embalagem.tipoEmbalagem}</td>
                                          <td align="center">${embalagem.quantidadeEmbalagem}</td>
                                          <td align="center">${embalagem.codigoBarraEmbalagem}</td>
                                          <td align="center">${embalagem.descricaoEmbalagem}</td>
                                          <td align="center">${embalagem.situacao == 'A' ? 'Ativo': 'Inativo'}</td>
                                          <td align="center" colspan="1">    
                                             <a href="${pageContext.request.contextPath}/EmbalagemExcluir?idEmbalagem=${embalagem.idEmbalagem}"><button class="btn  <c:out value="${embalagem.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${embalagem.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                             <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#cadastrar-embalagem-modal" onclick="CarregarEmbalagem(${embalagem.idEmbalagem})">Alterar</button></a>                                
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
            <div class="modal fade bs-example-modal-lg" id="cadastrar-embalagem-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarLote" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Embalagem</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form data-toggle="validator" name="embalagemCadastroAltera" action="${pageContext.request.contextPath}/EmbalagemCadastroAltera" method="POST"><!--enctype="multipart/form-data"-->
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idEmbalagem" name="idEmbalagem"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Tipo de Embalagem</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="tipoEmbalagem" name="tipoEmbalagem" data-error="Informe o campo número do lote!" required/>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Qtd. Itens na Embalagem</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="quantidadeEmbalagem" name="quantidadeEmbalagem" data-error="Informe quant. embalagem!" required/>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Cód. Barras</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="codigoBarraEmbalagem" name="codigoBarraEmbalagem" data-error="Informe o campo Cód. Barras!" required/>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Descrição</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="descricaoEmbalagem" name="descricaoEmbalagem" data-error="Informe o campo descrição!" required/>
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
               function CarregarEmbalagem(pnIdEmbalagem) {
                  console.log("Entrou");
                  var idE = pnIdEmbalagem;
                  console.log("Embalagem = " + idE);
                  $(document).ready(function () {
                     $.getJSON('EmbalagemCarregar', {idEmbalagem: idE}, function (respostaServlet) {
                        $('#idEmbalagem').val(respostaServlet.idEmbalagem);
                        $('#tipoEmbalagem').val(respostaServlet.tipoEmbalagem);
                        $('#quantidadeEmbalagem').val(respostaServlet.quantidadeEmbalagem);
                        $('#codigoBarraEmbalagem').val(respostaServlet.codigoBarraEmbalagem);
                        $('#descricaoEmbalagem').val(respostaServlet.descricaoEmbalagem);
                     });
                  });
               }
            </script>    
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#tipoEmbalagem').empty();
                  $('#quantidadeEmbalagem').empty();
                  $('#codigoBarraEmbalagem').empty();
                  $('#descricaoEmbalagem').empty();
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