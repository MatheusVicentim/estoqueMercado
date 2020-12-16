<%-- 
    Document   : produtoControle
    Created on : 05/05/2020, 20:48:56
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
            <link href="../css/style.css" rel="stylesheet" type="text/css"/>
         </head>
         <body>
            <%@include file="painelControle.jsp" %>

            <div class="content-wrapper">
               <div style="margin: 10px;">

                  <form method="Post" action="${pageContext.request.contextPath}/ProdutoCadastraAltera" enctype="multipart/form-data" data-toggle="validator">
                     <h3>Cadastro Produto</h3>
                     <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <input type="text" name="idProduto" class="form-control" placeholder="Indentificador Produto" value="0" readonly="readonly"/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="text" name="nomeProduto" class="form-control" placeholder="Nome Produto" value="" data-error="Informe o Nome do Produto!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="text" name="codigoBarra" class="form-control" placeholder="Código de Barras Produto" value="" data-error="Informe o Código de Barras do Produto!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>

                           <!--Verificar para colocar uma combo com as opções já cadastradas-->

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Unidade de Medida</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idUnidadeMedida" id="idUnidadeMedida" tabindex="3" data-error="Selecione a Unidade de Medida!" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="unidadeMedida" items="${lUnidadeMedidas}">
                                       <option value="${unidadeMedida.idUnidadeMedida}">
                                          ${unidadeMedida.descricaoUnidadeMedida}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Embalagem</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idEmbalagem" id="idEmbalagem" tabindex="3" data-error="Selecione o Tipo de Embalagem!" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="embalagem" items="${lEmbalagens}">
                                       <option value="${embalagem.idEmbalagem}">
                                          ${embalagem.descricaoEmbalagem}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>

                           <!--
                           <div class="form-group">
                              <input type="text" name="idUnidade" class="form-control" placeholder="Unidade Medida(KG-L)" value="" />
                           </div>
                           <div class="form-group">
                              <input type="text" name="idEmbalagem" class="form-control" placeholder="Embalagem" value="" />
                           </div>
                           <div class="form-group">
                              <input type="text" name="idSubProduto" class="form-control" placeholder="Tipo Produto" value="" />
                           </div>
                           <div class="form-group">
                              <input type="text" name="idMarca" class="form-control" placeholder="Marca" value="" />
                           </div>
                        </div>
                           -->
                        </div>

                        <div class="col-md-6">
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">SubProduto</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idSubProduto" id="idSubProduto" tabindex="3" data-error="Selecione o SubProduto!" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="subProduto" items="${lSubProdutos}">
                                       <option value="${subProduto.idSubProduto}">
                                          ${subProduto.descricaoSubProduto}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Marca</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idMarca" id="idMarca" tabindex="3" data-error="Selecione a Marca!" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="marca" items="${lMarcas}">
                                       <option value="${marca.idMarca}">
                                          ${marca.descricaoMarca}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group">
                              Imagem Produto
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" type="file" name="imagemProduto" id="imagemProduto"/>
                              </div> 
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                        </div>

                        <div class="form-inputButton">
                           <input type="submit" name="btnSubmit" class="btn btn-success" value="Cadastrar Produto" />
                           <input type="reset" name="btnSubmit" class="btn btn-danger" value="Limpar" />
                        </div>
                     </div>
                  </form>
               </div>
            </div>

            <!--Script para mostrar a imagem em tela-->         
            <script>
               function readURL(input) {
                  if (input.files && input.files[0]) {
                     var reader = new FileReader();
                     reader.onload = function (e) {
                        $img = $('<img/>').attr('src', e.target.result);
                        $(input).after($img);
                     };
                     reader.readAsDataURL(input.files[0]);
                  }
               }
               <%--
               function verificaMostraBotao() {
               $('input[type=file]').each(function (index) {
               if ($('input[type=file]').eq(index).val() !== "")
               $('.hide').show();
               });
               }
               --%>
               $('body').on("change", "input[type=file]", function () {
               <%--verificaMostraBotao();--%>
                  readURL(this);
               });
               <%--
               $('.hide').on("click", function () {
                  $(document.body).append($('<input/>', {type: "file"}).change(verificaMostraBotao));
                  $('.hide').hide();
               });
               --%>
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

