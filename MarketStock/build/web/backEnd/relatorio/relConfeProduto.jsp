<%-- 
    Document   : relConfeProduto
    Created on : 09/07/2020, 22:14:26
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

                     <center><h1>Relatório de Notificações</h1></center>
                     <table class="table">
                        <thead class="thead-dark">
                           <tr>
                              <th scope="col">Nome Produto</th>
                              <th scope="col">Num. Lote</th>
                              <th scope="col">Qtd. Sistema</th>
                              <th scope="col">Dta. Validade Sistema</th>
                              <th scope="col">Dta. Validade Sistema</th>
                              <th scope="col">Qtd. Produto Estoque</th>
                           </tr>
                        </thead>
                        <tbody>
                           <c:forEach var="rel" items="${lConferencias}">
                              <tr>
                                 <td>${rel.nomeProduto}</td>
                                 <td>${rel.numLote}</td>
                                 <td>${rel.qtdProdutoEstoque}</td>
                                 <td>${rel.dtaVencimento}</td>
                                 <td>___/___/______</td>
                                 <td>__________________________</td>
                              </tr>
                           </c:forEach>
                        </tbody>
                     </table>
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