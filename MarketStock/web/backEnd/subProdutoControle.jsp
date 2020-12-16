<%-- 
    Document   : subProdutoControle
    Created on : 04/07/2020, 18:34:54
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
                  <form method="Post" action="${pageContext.request.contextPath}/SubProdutoCadastraAltera" data-toggle="validator">
                     <h3>Cadastro Subproduto</h3>
                     <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <input type="text" name="idSubProduto" class="form-control" placeholder="Indentificador Sub Produto" value="0" readonly="readonly"/>
                           </div>
                           <div class="form-group">
                              <input type="text" name="descricaoSubProduto" class="form-control" placeholder="Descrição do Sub Produto" value="" data-error="Informe a Descrição de Sub Produto!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                        </div>

                        <div class="form-inputButton">
                           <input type="submit" name="btnSubmit" class="btn btn-success" value="Cadastrar Sub Produto" />
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