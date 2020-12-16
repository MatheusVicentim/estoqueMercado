<%-- 
    Document   : embalagemControle
    Created on : 07/05/2020, 21:14:51
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

                  <form method="Post" data-toggle="validator" action="${pageContext.request.contextPath}/EmbalagemCadastroAltera">
                     <h3>Cadastro de Emabalagens</h3>
                     <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <input type="text" name="idEmbalagem" class="form-control" placeholder="Indentificador Embalagem" value="0" readonly="readonly"/>
                              <div class="help-block with-errors" id="corDataError"></div>  
                           </div>
                           <div class="form-group">
                              <input type="text" name="descricaoEmbalagem" class="form-control" placeholder="Descrição da Embalagem" value="" data-error="Informe a Descrição!" required />
                              <div class="help-block with-errors" id="corDataError"></div>  
                           </div>
                           <div class="form-group">
                              <input type="text" name="codigoBarraEmbalagem" class="form-control" placeholder="Código de Barras Embalagem" value=""  data-error="Informe o Código de Barras!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>  
                           </div>
                           <div class="form-group">
                              <input type="text" name="quantidadeEmbalagem" class="form-control" placeholder="Quantidade de Itens na Embalagem" value="" data-error="Informe a Quantidade!" required />
                              <div class="help-block with-errors" id="corDataError"></div>  
                           </div>
                           <!--Fazer uma combo-->
                           <div class="form-group">
                              <input type="text" name="tipoEmbalagem" class="form-control" placeholder="Tipo de Embalagem" value="" data-error="Informe o Tipo de Embalagem!" required />
                              <div class="help-block with-errors" id="corDataError"></div>  
                           </div>
                        </div>

                        <div class="form-inputButton">
                           <input type="submit" name="btnSubmit" class="btn btn-success" value="Cadastrar Embalagem" />
                           <input type="reset" name="btnSubmit" class="btn btn-danger"" value="Limpar" />
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

