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
                  <form class="formulario" data-toggle="validator" name="cadastroLote" action="${pageContext.request.contextPath}/PedidoCompraCadastraAltera" method="POST">
                     <h3>Cadastro de Pedido de Compra</h3>
                     <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <input type="text" name="idPedidoCompra" class="form-control" placeholder="Indentificador PedidoCompra" value="0" readonly="readonly"/>
                           </div>
                           <div class="form-group">
                              <input type="text" name="descricaoPedido" class="form-control" placeholder="Descrição de Pedido de Compra" value="" data-error="Informe a Descrição do Pedido de Compra!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>  
                           <div class="form-group">
                              <label class="col-sm-12 col-md-10 col-form-label">Data do Pedido</label>
                              <input type="date" name="dtaPedido" class="form-control" placeholder="Data do Pedido" data-error="Informe a Data do Pedido" value="" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>

                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Funcionario</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idFuncionario" id="idFuncionario" tabindex="3" data-error="Selecione um Funcionario" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="funcionario" items="${lFuncionarios}">
                                       <option value="${funcionario.idFuncionario}">
                                          ${funcionario.nomePessoa}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-10 col-form-label">Tipo de Pagamento</label>
                              <div class="col-sm-12 col-md-100">                                    
                                 <select class="form-control fct" name="idTipoPagamento" id="idTipoPagamento" tabindex="3" data-error="Selecione um Tipo de Pagamento" required>
                                    <option value="">Selecione</option>
                                    <c:forEach var="tipoPagamento" items="${lTiposPagamentos}">
                                       <option value="${tipoPagamento.idTipoPagamento}">
                                          ${tipoPagamento.metodoPagamento}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                        </div>
                        <div class="form-inputButton">
                           <input type="submit" name="btnSubmit" class="btn btn-success" value="Cadastrar Pedido de Compra" />
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

