<%-- 
    Document   : orcamentoControle
    Created on : 07/07/2020, 08:57:42
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
            <link href="../css/style.css" rel="stylesheet" type="text/css"/>
         </head>
         <body>
            <%@include file="painelControle.jsp" %>
            <div class="content-wrapper">
               <div style="margin: 10px;">
                  <form class="formulario" data-toggle="validator" name="cadastroLote" action="${pageContext.request.contextPath}/OrcamentoCadastraAltera" method="POST">
                     <h3>Cadastro de Orcamento</h3>
                     <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <input type="text" name="idOrcamento" class="form-control" placeholder="Indentificador Orçamento" value="0" readonly="readonly"/>
                           </div>
                           <div class="form-group">
                              <input type="text" name="descricaoOrcamento" class="form-control" placeholder="Descrição de Orçamento" value="" data-error="Informe a Descrição do Orçamento!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <label class="col-sm-12 col-md-10 col-form-label">Valor Total Orçamento</label>
                              <input type="text" name="valorTotal" class="form-control" value="" readonly/>
                              <!--<div class="help-block with-errors" id="corDataError"></div>-->
                           </div>
                           <!--Verificar para colocar uma combo com as opções já cadastradas-->
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Fornecedor</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idFornecedor" id="idFornecedor" tabindex="3" data-error="Selecione um Fornecedor" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="fornecedor" items="${lFornecedores}">
                                       <option value="${fornecedor.idFornecedor}">
                                          ${fornecedor.razaoSocial}
                                       </option>
                                    </c:forEach>
                                 </select>
                              </div>
                           </div>
                        </div>
                        <div class="form-inputButton">
                           <input type="submit" name="btnSubmit" class="btn btn-success" value="Cadastrar Orçamento" />
                           <input type="reset" name="btnSubmit" class="btn btn-danger" value="Limpar" />
                        </div>
                     </div>
                  </form>
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

