<%-- 
    Document   : relPedidoCompra
    Created on : 09/07/2020, 23:15:22
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

                        <center><h1>Relatório de Pedido de Compra</h1></center>
                        <thead class="thead-dark">
                           <tr>
                              <th scope="col">Produto</th>
                              <th scope="col">Qtd. Produto</th>
                              <th scope="col">Descrição do Pedido</th>
                              <th scope="col">Método de Pagamento</th>
                              <th scope="col">Nome Funcionário</th>
                           </tr>
                        </thead>
                        <tbody>
                           <c:forEach var="rel" items="${lPedidoCompras}">
                              <tr>
                                 <td>${rel.nomeProduto}</td>
                                 <td>${rel.qtdProdutoEstoque}</td><!--Quantidade do pedido-->
                                 <td>${rel.desc}</td>
                                 <td>${rel.tipoPagamento}</td>
                                 <td>${rel.nomePessoa}</td>
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