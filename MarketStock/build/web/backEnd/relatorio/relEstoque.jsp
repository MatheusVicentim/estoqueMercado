<%-- 
    Document   : relEstoque
    Created on : 09/07/2020, 20:25:33
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
                     <table class="table">

                        <center><h1>Relatório do Estoque</h1></center>
                        <thead class="thead-dark">
                           <tr>
                              <th scope="col">#</th>
                              <th scope="col">Produto</th>
                              <th scope="col">#</th>
                              <th scope="col">Num. Lote</th>
                              <th scope="col">Qtd.Produto</th>
                              <th scope="col">Data Vencimento</th>
                              <th scope="col">Dias p Vencimento</th>
                           </tr>
                        </thead>
                        <tbody>
                           <c:forEach var="rel" items="${lEstoques}">
                              <tr>
                                 <td>${rel.idProduto}</td>
                                 <td>${rel.nomeProduto}</td>
                                 <td>${rel.idLote}</td>
                                 <td>${rel.numLote}</td>
                                 <td>${rel.qtdProdutoEstoque}</td>
                                 <td>${rel.dtaVencimento}</td>
                                 <td>${rel.diasVencimento}</td>
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