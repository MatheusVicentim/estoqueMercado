<%-- 
    Document   : loteControle
    Created on : 05/05/2020, 21:40:44
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
            <link href="../css/style.css" rel="stylesheet" type="text/css"/>
            <title>MarketStock</title>
            <link rel="icon" type="img/png" href="/images/iconPretoPeq.ico"/>
         </head>
         <body>
            <%@include file="painelControle.jsp" %>
            <div class="content-wrapper">
               <div style="margin: 10px;">
                  <form class="formulario" data-toggle="validator" name="cadastroLote" action="${pageContext.request.contextPath}/LoteCadastraAltera" method="POST">
                     <h3>Cadastro Lote</h3>
                     <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <input type="text" name="idLote" class="form-control" placeholder="Indentificador Lote" value="0" readonly="readonly"/>
                           </div>
                           <div class="form-group row">
                              <div class="col-sm-12 col-md-10">
                                 <input type="text" name="numLote" class="form-control" placeholder="Lote" value="" placeholder="Lote" data-error="Informe o Número do Lote!" required/>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group">
                              <table>
                                 <tr >
                                    <td>Data de Fabricação</td>
                                    <td>Data de Validade</td>
                                 </tr>
                                 <tr>
                                    <td><input type="date" name="dataFabricacao" class="form-control" placeholder="Data Fabricação" data-error="Informe a Data de Fabricação" value="" required/>
                                       <div class="help-block with-errors" id="corDataError"></div>
                                    </td>
                                    <td><input type="date" name="dataVencimento" class="form-control" placeholder="Data Vencimento" data-error="Informe a Data de Fabricação" value="" required/>
                                       <div class="help-block with-errors" id="corDataError"></div>  
                                    </td>
                                 </tr>
                              </table>
                           </div>
                        </div>
                        <div class="col-md-6">
                           <div class="form-group row">
                              <div class="col-md-12">
                                 <textarea name="descLote" class="form-control" placeholder="Descricao do Lote" style="width: 100%; height: 150px;" data-error="Preencha o campo descrição do lote!" required></textarea>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                        </div>

                        <div class="form-inputButton">
                           <input type="submit" name="btnSubmit" class="btn btn-success" value="Cadastrar Lote"/>
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
