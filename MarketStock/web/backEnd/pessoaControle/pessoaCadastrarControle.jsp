<%-- 
    Document   : pessoa
    Author     : matheus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
   <c:when test = "${sessionScope.idPessoa != null}">
      <!DOCTYPE html>
      <html>
         <head>
            <meta charset="utf-8">
            <title>MarketStock</title>
            <link rel="icon" type="img/png" href="/images/iconPretoPeq.ico"/>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">

            <link href="../css/style.css" rel="stylesheet" type="text/css"/>
         </head>

         <body>
            <!--Tras a estrutura para a tela-->
            <%@include file="../painelControle.jsp" %>

            <div class="content-wrapper">
               <div style="margin: 10px;">

                  <form method="Post" action="${pageContext.request.contextPath}/PessoaCadastraAltera" data-toggle="validator">
                     <h3>Cadastro Pessoa</h3>

                     <div class="row" style="margin: 50px;">
                        <div class="col-md-6">
                           <div class="form-group">
                              <input type="text" name="idPessoa"        class="form-control" placeholder="Indentificador" value="0" readonly="readonly"/>
                           </div>
                           <div class="form-group">
                              <input type="text" name="cpfCnpjPessoa" maxlength="14" type="text" id="cpfCnpj" onkeydown="javascript: fMasc(this, mCPF);" class="form-control" placeholder="CPF/CNPJ" data-rule="minlen:4" data-error="Informe o CPF/CNPJ!" required/>
                              <!--<div class="validation"></div>-->
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="text" name="nomePessoa"      class="form-control" placeholder="Nome Completo" value="" data-error="Informe o Nome!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="text" name="rgLePessoa"      class="form-control" placeholder="RG/LE" value="" data-error="Informe o RG!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="date" name="dataNascPessoa"  class="form-control" placeholder="Data de Nascimento" value="" data-error="Informe a Data de Nascimento!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="text" name="emailPessoa"     class="form-control" placeholder="E-mail" value="" data-error="Informe o Email!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="text" name="numTelefone"     class="form-control" placeholder="Telefone" value="" data-error="Informe o Numero do Telefone!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <label class="form-group">Tipo</label>
                              <select class="form-control fct" tabindex="4" name="tipoPessoa" id="tipoPessoa" tabindex="3" onChange="tipo()" required>
                                 <option>Selecione</option>
                                 <option value="ADM">Administrador</option>
                                 <option value="FUN">Funcionario</option>
                                 <option value="FOR">Fornecedor</option>
                              </select>
                           </div>
                        </div>
                        <div class="col-md-6">
                           <div class="form-group">
                              <input type="text" name="login"           class="form-control" placeholder="Usuário" value="" data-error="Informe o Login!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="password" name="senha"       class="form-control" placeholder="Senha" value="" data-error="Informe o Senha!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="password" name="confirmarSenhaPessoa" class="form-control" placeholder="Confirmar Senha" value="" data-error="Informe a Confirmação de Senha!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <h4>Endereço</h4>
                           <div class="form-group">
                              <input type="text" name="logradouro" class="form-control" placeholder="Logradouro" data-error="Informe o Endereço!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="text" name="numero" class="form-control" placeholder="Número" value="" data-error="Informe o Número!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="text" name="complemento" class="form-control" placeholder="Complemento" value="" data-error="Informe o Complemento!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group">
                              <input type="text" name="bairro" class="form-control" placeholder="Bairro" value="" data-error="Informe o Bairro!" required/>
                              <div class="help-block with-errors" id="corDataError"></div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Estado: </label>
                              <div class="col-sm-12 col-md-10">                                    
                                 <select class="form-control fct" tabindex="4" name="idEstado" id="idEstado" tabindex="3" onchange="BuscarCidadesPorEstado()" data-error="Selecione o Estado!" required>
                                    <option value="null">Selecione</option>
                                    <c:forEach var="estado" items="${lEstados}">
                                       <option value="${estado.idEstado}">
                                          ${estado.ufEstado}
                                       </option>
                                    </c:forEach>
                                 </select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Cidade:</label>
                              <div class="col-sm-12 col-md-10">
                                 <select class="form-control" name="idCidade" id="idCidade" tabindex="5" data-error="Selecione a Cidade!" required></select>
                                 <div class="help-block with-errors" id="corDataError"></div>
                              </div>
                           </div>
                        </div>
                        <div class="form-inputButton">
                           <input type="submit" name="btnSubmit" class="btn btn-success" value="Cadastrar Pessoa" />
                           <input type="reset" name="btnSubmit" class="btn btn-danger" value="Limpar" />
                        </div> 
                     </div>
                  </form>
               </div>
            </div>
         </body><!-- This templates was made by Colorlib (https://colorlib.com) -->

         <script type="text/javascript">
            $(document).ready(BuscarCidadesPorEstado());
            cidade = '';
            function BuscarCidadesPorEstado() {
               $('#idCidade').empty(); //..limpa as cidades que porventura já tenham sido pesquisadas.
               idEst = $('#idEstado').val();
               if (idEst !== 'null')
               {
                  //console.log(idEst);
                  url = "CidadeBuscarPorEstado?idEstado=" + idEst;
                  //console.log(url);
                  $.getJSON(url, function (result) {
                     //alert(result);
                     $.each(result, function (index, value) {
                        $('#idCidade').append(
                                '<option id="cidade' + value.idCidade + '"value="' + value.idCidade + '">' + value.nomeCidade + '</option>'
                                );
                        if (cidade !== '') {
                           $('#cidade' + cidade).prop({selected: true});
                        } else {
                           $('#cidade_').prop({selected: true});
                        }

                     });
                  }
                  ).fail(function (obj, textStatus, error) {
                     alert('Erro do servidor: ' + textStatus + ', ' + error);
                  });
               }
            }
         </script>

         <script>
            function fMasc(objeto, mascara) {
               obj = objeto;
               masc = mascara;
               setTimeout("fMascEx()", 1);
            }

            function fMascEx() {
               obj.value = masc(obj.value);
            }

            function mCPF(cpf) {
               cpf = cpf.replace(/\D/g, "");
               cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
               cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
               cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
               return cpf;
            }
         </script>
      </html>
   </c:when>
   <c:otherwise>
      <script>
         window.location.replace("index.jsp");
      </script>
   </c:otherwise>
</c:choose>