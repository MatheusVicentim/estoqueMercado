<%-- 
    Document   : estoqueProdutoView
    Created on : 04/07/2020, 16:09:04
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
                     <div class="card mb-3">
                        <div class="card-header">
                           <i class="fa fa-table"></i> Estoque por Produto
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="6" aling-="center">Lista Produtos no Estoque</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID Produto</th>
                                       <th class="th-sm" colspan="1">Nome Produto</th>
                                       <th class="th-sm" colspan="1">Qtd. Produto</th>                      
                                          <%--<th class="th-sm" colspan="1">Data de Vencimento</th>--%>
                                       <th class="th-sm" colspan="1">Dias Vencimento</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="estoque" items="${lEstoques}">
                                       <tr>
                                          <td align="center">${estoque.idProduto}</td>
                                          <td align="center">${estoque.nomeProduto}</td>
                                          <td align="center">${estoque.qtdProdutoEstoque}</td>
                                          <%--<td align="center">${estoque.dtaVencimento}</td>--%>
                                          <td align="center" class="
                                              <c:choose>
                                                 <c:when test="${estoque.diasVencimento < 10}">text-danger</c:when>
                                                 <c:when test="${estoque.diasVencimento < 30}">text-warning</c:when>
                                                 <c:otherwise>text-dark'</c:otherwise>
                                              </c:choose>
                                              ">${estoque.diasVencimento}
                                          </td>


                                          <%--
                                          <td align="center" colspan="2">    
                                           <a href="${pageContext.request.contextPath}/LoteExclur?idLote=${lote.idLote}"><button class="btn  <c:out value="${lote.situacao == 'A' ? 'btn-outline-danger': 'btn-outline-success'}"/>"><c:out value="${lote.situacao == 'A' ? 'Inativar': 'Ativar'}"/></button></a>
                                            <a href="#"><button class="btn btn-outline-warning" data-toggle="modal" data-target="#cadastrar-lote-modal" onclick="CarregarLote(${lote.idLote})">Alterar</button></a>                                
                                          </td> 
                                          --%>
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
         </body>
      </html>
   </c:when>
   <c:otherwise>
      <script>
         window.location.replace("index.jsp");
      </script>
   </c:otherwise>
</c:choose>