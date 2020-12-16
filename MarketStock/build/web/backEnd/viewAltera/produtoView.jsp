<%-- 
    Document   : produtoView
    Created on : 16/06/2020, 08:26:40
    Author     : mathe
--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
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
            <%@include file="../painelControle.jsp" %>

            <div class="content-wrapper">
               <div style="margin: 10px;">
                  <div class="container">
                     <div class="card mb-3">
                        <div class="card-header">
                           <i class="fa fa-table"></i> Produtos
                        </div>
                        <div class="card-body">
                           <div class="table-responsive" >
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="10" aling-="center">Lista de Produtos</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">Nome Produto</th>
                                       <th class="th-sm" colspan="1">Código de Barras</th>                      
                                       <th class="th-sm" colspan="1">Imagem</th>
                                       <th class="th-sm" colspan="1">Unidade de Medida</th>
                                       <th class="th-sm" colspan="1">Embalagem</th>
                                       <th class="th-sm" colspan="1">Tipo Produto</th><!--Integral, light....-->
                                       <th class="th-sm" colspan="1">Marca</th>
                                       <th class="th-sm" colspan="1">Situação</th>
                                       <th class="th-sm" colspan="1">Editar</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="produto" items="${lProdutos}">
                                       <tr>
                                          <td align="center">${produto.idProduto}</td>
                                          <td align="center">${produto.nomeProduto}</td>
                                          <td align="center">${produto.codigoBarra}</td>
                                          <td align="center"><img id="iconeConsulta" src="${pageContext.request.contextPath}/MontaImagemProduto?idProduto=${produto.getIdProduto()}"/></td>
                                          <td align="center">${produto.unidadeMedida.abreviacaoUnidade}</td>
                                          <td align="center">${produto.embalagem.descricaoEmbalagem}</td>
                                          <td align="center">${produto.subProduto.descricaoSubProduto}</td>
                                          <td align="center">${produto.marca.descricaoMarca}</td>
                                          <td align="center">${produto.situacao == 'A' ? 'Ativo': 'Inativo'}</td>
                                          <td align="center">    
                                             <a href="${pageContext.request.contextPath}/ProdutoExcluir?idProduto=${produto.idProduto}"><button class="btn  <c:out value="${produto.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${produto.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                             <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#cadastrar-produto-modal" onclick="CarregarProduto(${produto.idProduto})">Alterar</button></a>                                
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
            <div class="modal fade bs-example-modal-lg" id="cadastrar-produto-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarProduto" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Produto</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="cadastro" action="${pageContext.request.contextPath}/ProdutoCadastraAltera" method="POST" enctype="multipart/form-data">
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idProdutoT" name="idProduto"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Nome Produto</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="nomeProduto" name="nomeProduto" data-error="Informe o campo nome!" required/>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Código de Barra</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="text" id="codigoBarra" name="codigoBarra" data-error="Informe o campo código de barras!" required/>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Imagem</label>
                              <div class="col-sm-12 col-md-10">
                                 <img id="iconeConsulta" src="${pageContext.request.contextPath}/MontaImagemProduto?idProduto=${produto.getIdProduto()}"/>
                                 <input class="form-control fct" type="file" name="imagemProduto" id="imagemProduto" value="${produto.imagemProduto}"/>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Unidade de Medida</label>
                              <div class="col-sm-12 col-md-10">                                    
                                 <select class="form-control fct" name="idUnidadeMedida" id="idUnidadeMedida" tabindex="3" data-error="Selecione a Unidade de Medida !" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="unidadeMedida" items="${lUnidadeMedidas}">
                                       <option value="${unidadeMedida.idUnidadeMedida}" ${produto.unidadeMedida.idUnidadeMedida == unidadeMedida.idUnidadeMedida ? "selected" : ""}>
                                          ${unidadeMedida.descricaoUnidadeMedida}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Embalagem</label>
                              <div class="col-sm-12 col-md-10">                                    
                                 <select class="form-control fct" name="idEmbalagem" id="idEmbalagem" tabindex="3" data-error="Selecione a Embalagem !" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="embalagem" items="${lEmbalagens}">
                                       <option value="${embalagem.idEmbalagem}" ${produto.embalagem.idEmbalagem == embalagem.idEmbalagem ? "selected" : ""}>
                                          ${embalagem.descricaoEmbalagem}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">SubProduto</label>
                              <div class="col-sm-12 col-md-10">                                    
                                 <select class="form-control fct" name="idSubProduto" id="idSubProduto" tabindex="3" data-error="Selecione o SubProduto !" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="subProduto" items="${lSubProdutos}">
                                       <option value="${subProduto.idSubProduto}" ${produto.subProduto.idSubProduto == subProduto.idSubProduto ? "selected" : ""}>
                                          ${subProduto.descricaoSubProduto}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Marca</label>
                              <div class="col-sm-12 col-md-10">                                    
                                 <select class="form-control fct" name="idMarca" id="idMarca" tabindex="3" data-error="Selecione a Marca !" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="marca" items="${lMarcas}">
                                       <option value="${marca.idMarca}" ${produto.marca.idMarca == marca.idMarca ? "selected" : ""}>
                                          ${marca.descricaoMarca}
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
               function CarregarProduto(pnIdProduto) {
                  console.log("Entrou");
                  var idP = pnIdProduto;
                  console.log("Produto = " + idP);
                  $(document).ready(function () {
                     $.getJSON('ProdutoCarregar', {idProduto: idP}, function (respostaServlet) {
                        $('#idProdutoT').val(respostaServlet.idProduto);
                        $('#nomeProduto').val(respostaServlet.nomeProduto);
                        $('#codigoBarra').val(respostaServlet.codigoBarra);

                        $('#idUnidadeMedida').val(respostaServlet.unidadeMedida.idUnidadeMedida);
                        $('#descricaoUnidadeMedida').val(respostaServlet.unidadeMedida.descricaoUnidadeMedida);

                        $('#idEmbalagem').val(respostaServlet.embalagem.idEmbalagem);
                        $('#descricaoEmbalagem').val(respostaServlet.embalagem.descricaoEmbalagem);

                        $('#idSubProduto').val(respostaServlet.subProduto.idSubProduto);
                        $('#descricaoSubProduto').val(respostaServlet.subProduto.descricaoSubProduto);

                        $('#idMarca').val(respostaServlet.marca.idMarca);
                        $('#descricaoMarca').val(respostaServlet.marca.descricaoMarca);
                     });
                  });
               }
            </script>   
            <!--Limpa Campos-->
            <script type="text/javascript">
               function LimparModal() {
                  $('.fct').val('');
                  $('#nomeProduto').empty();
                  $('#codigoBarra').empty();
                  $('#idUnidadeMedida').empty();
                  $('#idEmbalagem').empty();
                  $('#idSubProduto').empty();
                  $('#idMarca').empty();
                  $('#fotoJogador').empty();
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
