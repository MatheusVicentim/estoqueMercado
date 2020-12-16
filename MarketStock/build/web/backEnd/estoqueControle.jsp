<%-- 
    Document   : estoqueControle
    Created on : 10/03/2020, 20:56:40
    Author     : mathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
   <c:when test = "${sessionScope.idPessoa != null}">
      <!DOCTYPE html>
      <html>
         <head>
            <link href="../css/style.css" rel="stylesheet" type="text/css"/>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>MarketStock</title>
            <link rel="icon" type="img/png" href="/images/iconPretoPeq.ico"/>
         </head>
         <body>
            <%@include file="painelControle.jsp" %>
            <div class="content-wrapper">
               <div style="margin: 10px;">

                  <div class="container contact-form">
                     <div class="contact-image">
                        <img src="/images/caixa.png" alt="rocket_contact"/>
                     </div>
                     <form method="Post" data-toggle="validator" action="${pageContext.request.contextPath}/EstoqueCadastroAltera">
                        <h3>Cadastro Estoque</h3>
                        <div class="row">
                           <div class="col-md-6">
                              <div class="form-group">
                                 <input type="text" name="idEstoque" class="form-control" placeholder="Estoque" value="0" readonly="readonly"/>
                              </div>

                              <div class="form-group row">
                                 <label class="col-sm-12 col-md-10 col-form-label">Produto</label>
                                 <div class="col-sm-12 col-md-100">                                    
                                    <select class="form-control fct" name="idProduto" id="idProduto" tabindex="3" data-error="Selecione um Produto!" required>
                                       <option value="">Selecione</option>
                                       <c:forEach var="produto" items="${lProdutos}">
                                          <option value="${produto.idProduto}">
                                             ${produto.nomeProduto}
                                          </option>
                                       </c:forEach>
                                    </select>
                                    <div class="help-block with-errors" id="corDataError"></div>
                                 </div>
                              </div>

                              <div class="form-group row">
                                 <label class="col-sm-12 col-md-10 col-form-label">Lote</label>
                                 <div class="col-sm-12 col-md-100">                                    
                                    <select class="form-control fct" name="idLote" id="idLote" tabindex="3" data-error="Selecione um Lote!" required>
                                       <option value="">Selecione</option>
                                       <c:forEach var="lote" items="${lLotes}">
                                          <option value="${lote.idLote}">
                                             ${lote.numLote}
                                          </option>
                                       </c:forEach>
                                    </select>
                                    <div class="help-block with-errors" id="corDataError"></div>
                                 </div>
                              </div>
                           </div>

                           <div class="col-md-6">
                              <div class="form-group row">
                                 <label class="col-sm-12 col-md-5 col-form-label" for="quantEstoque">Quant. Movimentação</label>
                                 <div class="col-sm-12 col-md-6">
                                    <input class="form-control fct" type="number" id="quantEstoque" name="quantEstoque" data-error="Informe a Quantidade!" required/>
                                    <div class="help-block with-errors" id="corDataError"></div>
                                 </div>
                              </div>

                              <div class="form-group row ">
                                 <label class="col-xl-12 col-form-label" for="tipoEstoque">Descrição</label>
                                 <div class="col-lg-12">
                                    <textarea name="tipoEstoque" id="tipoEstoque" class="form-control" placeholder="Descricao do Estoque" style="width: 100%" data-error="Informe o Tipo do Estoque!" required></textarea>
                                    <div class="help-block with-errors" id="corDataError"></div>
                                 </div>
                              </div>
                           </div>

                           <div class="form-inputButton">
                              <input type="submit" name="btnSubmit" class="btn btn-success btn-lg" value="Realizar Operação" />
                           </div>
                        </div>
                     </form>
                  </div>
               </div>
            </div>
         </body>
      </html>
   </c:when>
   <c:otherwise>
      <script>
         window.location.replace("index.jsp");
      </script>
   </c:otherwise>
</c:choose>

