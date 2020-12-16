<%-- 
    Document   : relNotificacao
    Created on : 09/07/2020, 21:58:53
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
                              <th scope="col">#</th>
                              <th scope="col">Nome Produto</th>
                              <th scope="col">Num. Lote</th>
                              <th scope="col">Situação do Produto</th>
                           </tr>
                        </thead>
                        <tbody>
                           <c:forEach var="rel" items="${lNotificacoes}">
                              <tr>
                                 <td>${rel.idNotificacao}</td>
                                 <td>${rel.nomeProduto}</td>
                                 <td>${rel.numLote}</td>
                                 <td>${rel.descNotificacao}</td>
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