<%-- 
    Document   : tipoPessoaControle
    Created on : 27/06/2020, 15:22:07
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
            <link href="../../css/myCss.css" rel="stylesheet" type="text/css"/>
            <div class="content-wrapper">
               <div style="margin: 10px;">
                  <div class="container">
                     <div class="card mb-3">
                        <div class="card-header">
                           <i class="fa fa-table"></i> Pessoas
                        </div>
                        <div class="card-body">
                           <div class="table-responsive">
                              <table  class="data-table stripe hover nowrap" id="dataTable">
                                 <thead>
                                    <tr>
                                       <th colspan="6" aling-="center">Pessoas Prospect</th>
                                    </tr>
                                    <tr>
                                       <th class="th-sm" colspan="1">ID</th>
                                       <th class="th-sm" colspan="1">CPF/CNPJ</th>
                                       <th class="th-sm" colspan="1">Nome</th>                      
                                       <th class="th-sm" colspan="1">Tipo Pessoa</th>
                                       <th class="th-sm" colspan="1">Situação</th>
                                       <th class="th-sm" colspan="1">Editar</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach var="pessoa" items="${lPessoaTipos}">
                                       <tr>
                                          <td align="center">${pessoa.idPessoa}</td>
                                          <td align="center">${pessoa.cpfCnpjPessoa}</td>
                                          <td align="center">${pessoa.nomePessoa}</td>
                                          <td align="center">${pessoa.tipoPessoa}</td>    
                                          <td align="center">${pessoa.statusPessoa == 'P' ? 'Prospect' : 'Já Cadastrado'}</td>

                                          <td align="center" colspan="2">    
                                             <a href="#"><button class="btn btn-outline-primary" data-toggle="modal" data-target="#cadastrar-fornecedor-modal" onclick="CarregarPessoa(${pessoa.idPessoa})">Cadastrar Tipo Pessoa</button></a>                                
                                          </td> 
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


            <!-- Large modal Fornecedor / Funcionario-->
            <div class="modal fade bs-example-modal-lg" id="cadastrar-fornecedor-modal" tabindex="-1" role="dialog" aria-labelledby="cadastrarLote" aria-hidden="true">
               <div class="modal-dialog modal-lg modal-dialog-centered">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h4 class="modal-title" id="myLargeModalLabel">Pessoa</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                     </div>
                     <form  data-toggle="validator" name="pessoaTipoCadastra" action="${pageContext.request.contextPath}/PessoaTipoCadastra" method="POST">
                        <div class="modal-body">                                     
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">ID</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="idPessoa" name="idPessoa"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">Nome</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="nomePessoa" name="nomePessoa"/>
                              </div>
                           </div>
                           <div class="form-group row">
                              <label class="col-sm-12 col-md-2 col-form-label">CPF/CNPJ</label>
                              <div class="col-sm-12 col-md-10">
                                 <input class="form-control fct" readonly="readonly" type="text" id="cpfCnpjPessoa" name="cpfCnpjPessoa"/>
                              </div>
                           </div>

                           <div class="esconder"><input id="tipoPessoa" name="tipoPessoa"/></div>

                           <!--Fornecedor-->
                           <div id="fornedorMostra" class="esconder">
                              <div class="form-group row">
                                 <label class="col-sm-12 col-md-2 col-form-label">Empresa</label>
                                 <div class="col-sm-12 col-md-10">
                                    <input class="form-control fct" type="text" id="razaoSocial" name="razaoSocial"/>
                                 </div>
                              </div>
                              <div class="form-group row">
                                 <label class="col-sm-12 col-md-2 col-form-label">Tel. Empresa</label>
                                 <div class="col-sm-12 col-md-10">
                                    <input class="form-control fct" type="text" id="telefoneEmpresa" name="telefoneEmpresa" data-mask="(00)00000-0000"/>
                                 </div>
                              </div>
                              <div class="form-group row">
                                 <label class="col-sm-12 col-md-4 col-form-label">Responsavel pelo Fornecedor</label>
                                 <div class="col-sm-12 col-md flex-lg-row">
                                    <input class="form-control fct" type="text" id="responsavelFornecedor" name="responsavelFornecedor"/>
                                 </div>
                              </div>
                           </div>

                           <!--Funcionario-->
                           <div  id="funcionarioMostra" class="esconder">
                              <div class="form-group row">
                                 <label class="col-sm-12 col-md-2 col-form-label">Carteira de Trabalho</label>
                                 <div class="col-sm-12 col-md-10">
                                    <input class="form-control fct" type="text" id="matriculaFuncionario" name="matriculaFuncionario"/>
                                 </div>
                              </div>

                              <div class="form-group row">
                                 <label class="col-sm-12 col-md-2 col-form-label">Setor</label>
                                 <div class="col-sm-12 col-md-10">                                    
                                    <select class="form-control fct" name="idCargo" id="idCargo" tabindex="3">
                                       <option value="">Selecione</option>
                                       <c:forEach var="cago" items="${lCargos}">
                                          <option value="${cago.idCargo}">
                                             ${cago.descricaoCargo}
                                          </option>
                                       </c:forEach>
                                    </select>
                                 </div>
                              </div>

                              <div class="form-group row">
                                 <label class="col-sm-12 col-md-2 col-form-label">Cargo</label>
                                 <div class="col-sm-12 col-md-10">                                    
                                    <select class="form-control fct" name="idSetor" id="idSetor" tabindex="3">
                                       <option value="">Selecione</option>
                                       <c:forEach var="setor" items="${lSetores}">
                                          <option value="${setor.idSetor}">
                                             ${setor.descricaoSetor}
                                          </option>
                                       </c:forEach>
                                    </select>
                                 </div>
                              </div>
                           </div>

                           <div class="modal-footer">
                              <input type="submit" class="btn btn-success" value="Salvar"/>
                              <input type="button" onclick="LimparModal('FOR')" class="btn btn-danger" value="Limpar"/>
                           </div>
                        </div>
                     </form>
                  </div>
               </div>
            </div>

            <!--Alterar-->
            <script type="text/javascript">
               function CarregarPessoa(pnIdPessoa) {
                  console.log("Entrou");
                  var idP = pnIdPessoa;
                  //var sTipoPessoa = psTipoPessoa;
                  var sTipoPessoa;
                  console.log("Pessoa = " + idP);
                  $(document).ready(function () {
                     $.getJSON('PessoaCarregar', {idPessoa: idP, statusPessoa: 'P'}, function (respostaServlet) {
                        $('#idPessoa').val(respostaServlet.IdPessoa);
                        $('#cpfCnpjPessoa').val(respostaServlet.cpfCnpjPessoa);
                        $('#nomePessoa').val(respostaServlet.nomePessoa);
                        $('#tipoPessoa ').val(respostaServlet.tipoPessoa);
                        sTipoPessoa = respostaServlet.tipoPessoa.toString();
                        console.log("Teste " + sTipoPessoa);

                        if (sTipoPessoa === "FUN") {
                           document.getElementById("fornedorMostra").className = 'esconder';
                           document.getElementById("funcionarioMostra").className = '';
                           console.log("Funcionario");
                        }
                        ;
                        if (sTipoPessoa === "FOR") {
                           document.getElementById("funcionarioMostra").className = 'esconder';
                           document.getElementById("fornedorMostra").className = '';
                           console.log("Fornecedor");
                        }
                        ;

                     });
                  });
               }
            </script>    

            <script type="text/javascript">
               function LimparModal(psPessoa) {
                  if (psPessoa === 'FOR') {
                     $('#razaoSocial').val('');
                     $('#responsavelFornecedor').val('');
                     $('#telefoneEmpresa').val('');
                  } else {
                     $('#matriculaFuncionario').val('');
                     $('#setor').val('');
                     $('#cargo').val('');
                  }

               }
            </script>


            <script type="text/javascript">
               function tipo() {
                  var select = document.getElementById('tipoPessoa');
                  var option = select.options[select.selectedIndex];

                  document.getElementById('value').value = option.value;
                  document.getElementById('text').value = option.text;

                  alert(select);
                  return;
               }

               update();
            </script>
         </body>
      </html>
   </c:when>
   <c:otherwise>
      <script>
         window.location.replace("index.jsp");
      </script>
   </c:otherwise>
</c:choose>