<%-- 
    Document   : index
    Created on : 07/03/2020, 16:18:27
    Author     : mathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>MarketStock</title>
      <link rel="icon" type="img/png" href="images/iconPretoPeq.ico"/>
      <meta name="description" content="Free Bootstrap Theme by BootstrapMade.com">
      <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">

      <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Fira+Sans|Roboto:300,400|Questrial|Satisfy">
      <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
      <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
      <link rel="stylesheet" type="text/css" href="css/animate.css">
      <link rel="stylesheet" type="text/css" href="css/styleIndex.css">
      <link href="/visualStyle/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

      <!-- =======================================================
        Theme Name: Laura
        Theme URL: https://bootstrapmade.com/laura-free-creative-bootstrap-theme/
        Author: BootstrapMade.com
        Author URL: https://bootstrapmade.com
      ======================================================= -->
   </head>

   <body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60" onload="myFunction()">
      <% session.invalidate(); %>

      <div class="header">
         <div class="bg-color">
            <header id="main-header">
               <nav class="navbar navbar-default navbar-fixed-top">
                  <div class="container">
                     <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#lauraMenu">
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">MarketStock</a>
                     </div>
                     <div class="collapse navbar-collapse" id="lauraMenu">
                        <ul class="nav navbar-nav navbar-right navbar-border">
                           <li class="active"><a href="#main-header">Home</a></li>
                           <li><a href="#about">Serviços</a></li>
                           <li><a href="#portfolio">Ambiente</a></li>
                           <li><a href="#testimonial">Sobre os Autores</a></li>
                              <%
                                 HttpSession sessaoCab = request.getSession(false);
                                 if (sessaoCab == null) {
                              %>
                           <li><a href="#" data-toggle="modal" data-target="#logar-modal">Logar</a></li>
                           <!--<li><a href="backEnd/painelControle.jsp">Painel Controle</a></li>-->
                           <%
                           } else {
                              String nomePessoa = (String) sessaoCab.getAttribute("nomePessoa");
                           %>
                           <li class="nav-item">
                              <button type="button" class="btn btn-danger" data-toggle="modal" data-target="" onclick="deslogar()" name="sair" id="sair">
                                 <% out.print(nomePessoa);%> - Sair </button>
                           </li>  
                           <%--<li><a href="#" data-toggle="modal" data-target="#logar-modal">Logar</a></li>--%>
                           <li><a href="backEnd/painelControle.jsp">Painel Controle</a></li>
                              <%
                                 }
                              %>
                        </ul>
                     </div>
                  </div>
               </nav>
            </header>
            <div class="wrapper">
               <div class="container">
                  <div class="row">
                     <div class="col-md-12 wow fadeIn delay-05s">
                        <div class="banner-text">
                           <h2>Oi, Somos o <span> MarketStock</span></h2>
                           <p>A melhor opção para o seu negócio.</p>
                        </div>
                        <div class="overlay-detail text-center">
                           <a href="#about"><img style="width: 80px; height: 80px; margin-top: 100px" src="images/logoMiniBranco.png" alt="Lupa"/></a>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <section id="about" class="section-padding wow fadeIn delay-05s">
         <div class="container">
            <div class="row">
               <div class="col-md-6 text-right">
                  <h2 class="title-text">
                     Conheça <br><span class="deco">MarketStock</span>
                  </h2>
               </div>
               <div class="col-md-6 text-left">
                  <div class="about-text">
                     <p>O projeto MarketStock é um sistema de controle de estoque para
                        supermercados, visando à facilidade na execução das tarefas dos funcionários
                        relativos ao controle de estoque.</p>
                     <p>Desenvolvido para suprir as necessidades de pequenos supermercados.</p>
                     <p>&nbsp;</p>
                     <ul class="abt-list">
                        <li>- Cadastro de Estoque</li>
                        <li>- Cadastro de Produto no Estoque</li>
                        <li>- Cadastro de Marca</li>     
                        <li>- Cadastro de Embalagem</li>
                        <li>- Cadastro de Sub Produto</li>
                        <li>- Cadastro de Lote</li>
                        <li>- Dentre outras diversas Funcionalidades!</li>
                     </ul>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <section id="portfolio" class="section-padding wow fadeInUp delay-05s">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <h2 class="title text-center"> <span class="deco">Conheça Nosso Trabalho</span> </h2>
               </div>
               <div class="col-md-12">
                  <div id="myGrid" class="grid-padding">
                     <div class="col-md-4 col-sm-4 padding-right-zero">
                        <img src="images/image1.jpeg" class="img-responsive">
                        <img src="images/image2.jpg" class="img-responsive">
                        <img src="images/image3.jpg" class="img-responsive">
                        <img src="images/image4.jpg" class="img-responsive">
                     </div>
                     <div class="col-md-4 col-sm-4 padding-right-zero">
                        <img src="images/image5.jpg" class="img-responsive">
                        <img src="images/image6.jpg" class="img-responsive">
                        <img src="images/image7.jpeg" class="img-responsive">
                        <img src="images/image8.jpg" class="img-responsive">
                     </div>
                     <div class="col-md-4 col-sm-4 padding-right-zero">
                        <img src="images/image9.jpg" class="img-responsive">
                        <img src="images/image10.jpg" class="img-responsive">
                        <img src="images/image11.jpg" class="img-responsive">
                        <img src="images/image12.jpg" class="img-responsive">
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <section id="testimonial" class="section-padding wow fadeInUp">
         <div class="container">
            <div class="row">
               <h2 class="title text-center">Um pouquinho da nossa <span class="deco">Equipe</span> !</h2>
               <div class="test-sec">
                  <div class="col-sm-2">
                     <div class="carousel-info"> 
                        <ul class="social-list">
                           <li>
                              <a href="https://www.facebook.com/gabriel.vinicius.56829/" target="_blank"><i class="fa fa-facebook-official"></i></a>
                           </li>
                           <li>
                              <a href="https://www.instagram.com/gabrielvinicius2p/" target="_blank"><i class="fa fa-instagram "></i></a>
                           </li>
                           <li>
                              <a href="https://twitter.com/gabo2p" target="_blank"><i class="fa fa-twitter"></i></a>
                           </li>
                           <li>
                              <a href="#testimonial"><i class="fa fa-linkedin-square"></i></a>
                           </li>
                        </ul>
                        <div class="pull-left"> <span class="testimonials-name">Gabriel Vinicius Cordeiro</span> <span class="testimonials-post">CEO,  Company Inc.</span> </div>
                     </div>
                  </div>
                  <div class="col-sm-2">
                     <div class="carousel-info">
                        <ul class="social-list">
                           <li>
                              <a href="https://www.facebook.com/guilherme.fernandesdellatin" target="_blank"><i class="fa fa-facebook-official"></i></a>
                           </li>
                           <li>
                              <a href="https://www.instagram.com/guilhermefernandesdellatin/" target="_blank"><i class="fa fa-instagram "></i></a>
                           </li>
                           <li>
                              <a href="#testimonial"><i class="fa fa-twitter"></i></a>
                           </li>
                           <li>
                              <a href="#testimonial"><i class="fa fa-linkedin-square"></i></a>
                           </li>
                        </ul>
                        <div class="pull-left"> <span class="testimonials-name">Guilherme Dellatin</span> <span class="testimonials-post">CEO,  Company Inc.</span> </div>
                     </div>
                  </div>
                  <div class="col-sm-2">
                     <div class="carousel-info">
                        <ul class="social-list">
                           <li>
                              <a href="https://www.facebook.com/matheus.vicentim.9" target="_blank"><i class="fa fa-facebook-official"></i></a>
                           </li>
                           <li>
                              <a href="https://www.instagram.com/vicentimmatheus/" target="_blank"><i class="fa fa-instagram "></i></a>
                           </li>
                           <li>
                              <a href="#testimonial"><i class="fa fa-twitter"></i></a>
                           </li>
                           <li>
                              <a href="#testimonial"><i class="fa fa-linkedin-square"></i></a>
                           </li>
                        </ul>
                        <div class="pull-left"> <span class="testimonials-name">Matheus dos Santos Vicentim</span> <span class="testimonials-post">CEO,  Company Inc.</span> </div>
                     </div>
                  </div>
                  <div class="col-sm-2">
                     <div class="carousel-info">
                        <ul class="social-list">
                           <li>
                              <a href="https://www.facebook.com/people/Rafael-Santos/100003199505617" target="_blank"><i class="fa fa-facebook-official"></i></a>
                           </li>
                           <li>
                              <a href="https://www.instagram.com/raafaa.saantoos/" target="_blank"><i class="fa fa-instagram "></i></a>
                           </li>
                           <li>
                              <a href="#testimonial"><i class="fa fa-twitter"></i></a>
                           </li>
                           <li>
                              <a href="#testimonial"><i class="fa fa-linkedin-square"></i></a>
                           </li>
                        </ul>
                        <div class="pull-left"> <span class="testimonials-name">Rafael Rodrigues dos Santos</span> <span class="testimonials-post">CEO,  Company Inc.</span> </div>
                     </div>
                  </div>
                  <div class="col-sm-2">
                     <div class="carousel-info">
                        <ul class="social-list">
                           <li>
                              <a href="https://www.facebook.com/romulo.fim" target="_blank"><i class="fa fa-facebook-official"></i></a>
                           </li>
                           <li>
                              <a href="https://www.instagram.com/romulofim/" target="_blank"><i class="fa fa-instagram "></i></a>
                           </li>
                           <li>
                              <a href="#testimonial"><i class="fa fa-twitter"></i></a>
                           </li>
                           <li>
                              <a href="#testimonial"><i class="fa fa-linkedin-square"></i></a>
                           </li>
                        </ul>
                        <div class="pull-left"> <span class="testimonials-name">Rômulo Césare Fim</span> <span class="testimonials-post">CEO,  Company Inc.</span> </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <footer class="footer-2 text-center-xs bg--white">
         <div class="container">
            <!--end row-->
            <div class="row">
               <div class="col-md-6">
                  <div class="footer">
                     Creéditos © Copyright Laura Theme. 
                     Todos os Direitos Reservados
                     <div class="credits">
                        Designed by Team SCAESM 
                     </div>
                  </div>
               </div>
               <div class="col-md-6 text-right">
                  <ul class="social-list">
                     <li>
                        <a href="https://www.facebook.com/marketstock.scaesm.7" target="_blank"><i class="fa fa-facebook-official"></i></a>
                     </li>
                     <li>
                        <a href="https://www.instagram.com/scaesmmarketstock/?hl=pt-br" target="_blank"><i class="fa fa-instagram"></i></a>
                     </li>
                     <li>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                     </li>
                  </ul>
               </div>

            </div>
            <!--end row-->
         </div>
      </footer>


      <!-- Large modal -->
      <div class="modal fade bs-popover-auto" id="logar-modal" tabindex="-1" role="dialog" aria-labelledby="logarPessoa" aria-hidden="true">
         <div class="modal-dialog modal-md modal-dialog-centered">
            <div class="modal-content">
               <div class="modal-header">
                  <div>
                     <img src="images/logoMiniPreto.png" style="width: 20px;" >
                     <span class="login100-form-title p-b-41" style="align-items: flex-end;">
                        Logar Market Stock
                     </span>
                     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                  </div>
                  <hr>
                  <br>
                  <!--</div>-->
                  <form action="${pageContext.request.contextPath}/PessoaLogar" id="frmLogarUsuario" name="frmLogarUsuario" accept-charset="UTF-8" role="form" class="col-md-6" method="post">
                     <div class="form-group row">
                        <label class="col-sm-12 col-md-2 col-form-label">Login:</label>
                        <div style="width: 550px">
                           <input class="form-control fct" type="text" name="login" id="login" data-error="Por favor, preencha o campo login!" required/>
                           <div class="help-block with-errors"></div>
                        </div>
                     </div>
                     <div class="form-group row">
                        <label class="col-sm-12 col-md-2 col-form-label">Senha:</label>
                        <div style="width: 550px">
                           <input class="form-control fct" type="password" name="senha" id="senha" data-error="Por favor, preencha o campo de senha!" required/>
                           <div class="help-block with-errors"></div>
                        </div>
                     </div>
                     <div class="form-group row">
                        <div style="width: 550px">                                    
                           <select class="form-control fct" name="tipoPessoa" id="tipoPessoa" tabindex="3">
                              <option>Selecione</option>
                              <option value="ADM">Administrador</option>
                              <option value="FUN">Funcionario</option>
                              <option value="FOR">Fornecedor</option>
                           </select>
                        </div>
                     </div>
                     <div class="modal-footer" align="center">                                                        
                        <input type="submit" class="btn btn-primary" value="Logar"/>
                        <input type="reset" class="btn btn-danger" value="Limpar"/>
                     </div>
                     <div class="form-group row">
                        <div class="modal-dialog">
                           <p>${msg}</p>
                        </div>
                     </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
      <!--Fim da Modal-->

      <script src="js/jquery.min.js"></script>
      <script src="js/jquery.easing.min.js"></script>
      <script src="js/bootstrap.min.js"></script>
      <script src="js/jquery.bxslider.min.js"></script>
      <script src="js/wow.js"></script>
      <script src="js/custom.js"></script>
      <script src="contactform/contactform.js"></script>

      <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
      <script src="${pageContext.request.contextPath}/js/jquery.mask.min.js" type="text/javascript"></script>
      <script src="js/app.js" type="text/javascript"></script>
   </body>

</html>

