<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.google.appengine.api.users.User"%>
 <%@ page import="com.google.appengine.api.users.UserService"%>
    <%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
    <% UserService us = UserServiceFactory.getUserService(); %>
	 <% User user = us.getCurrentUser(); %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
  <title>Home</title>
 
</head>
<body>
  <!--Inicio del codigo del logo de facebook-->
  <div class="row" style="background-color:#000000;color:#908F99;">
    <div class="col-sm-10">
Calle Jerusalén N° 506 - Yanahuara. (5,97 km)
054 Arequipa Contact: (054) 271946</div>
    <div class="col-sm-1"><a href="https://www.facebook.com/pages/biz/medical_health-arequipa-054/ArguiDent-Aqp-300624626728028/" target="_blank" class="float-right"><img src="../img/logoFace.png" width="20" height="20"></a></div>
  </div>
  <!--Fin del codigo del logo de facebook-->
  <div class="container">
    <!--Inicio del logo de arguident-->
    <nav class="navbar navbar-expand-lg navbar-light bg-white">
     
  <a class="navbar-brand" href="#">
    <img src="../img/arguident2.jpg" width="90" height="70" alt="logo de arguident">
    Arguident
  </a>
  <!--Fin del logo de arguident-->
  <!--Inicio Obcciones de la parte derecha-->
  <div class="container-fluid">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button> 
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a href="#"><button class="button button1">Inicio</button></a>
      </li>
      <li class="nav-item">
        <a href="#"><button class="button button1">Servicios</button></a>
      </li>
      <li class="nav-item">
        <a href="#"><button class="button button1">Consultas</button></a>
      </li>
      <li class="nav-item">
        <a href="#"><button class="button button1">Calendario</button></a>
      </li>
      <li class="nav-item">
        <a href="#"><button class="button button1">Instalaciones</button></a>
      </li>
      <li class="nav-item">
        <a href="/history"><button class="button button1">Historias clinicas</button></a>
      </li>
      <li class="nav-item">
        <a href="/patients"><button class="button button1">Pacientes</button></a>
      </li>
      <li class="nav-item">
        <a href="/medical_appointments"><button class="button button1">Citas Medicas</button></a>
      </li>
      <li class="nav-item">
        <a href="/petitions/add"><button class="button button1">Sign in</button></a>
      </li>
      <%if(user == null){ %>
      <li class="nav-item">
        <a href="<%=us.createLoginURL("/gmail")%>"><button class="button button1">Login</button></a>
      </li>
      <%} %>	
      <%if(user != null){ %>
      <li class="nav-item">
        <a href="<%=us.createLogoutURL("/gmail") %>"><button class="button button1">Logout</button></a>
      </li>
      <%} %>
      <%if(user != null){ %>
      <li class="nav-item">
        <a href="/users"><button class="button button1">Acl</button></a>
      </li>
      <%} %>            
    </ul>
  </div>

  </div>  
</nav>
<!--Fin Obcciones de la parte derecha-->

  </div>
  <!--Inicio de Imagenes de presentacion parte media-->
  
  <div id="demo" class="carousel slide" data-ride="carousel">
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
  </ul>
  <div class="carousel-inner">
    <div class="carousel-item active text-center p-4">
      <img src="../img/image1.jpg" alt="Los Angeles" width="1100" height="500">
      <div class="carousel-caption">
        <h1>hola</h1>
        <p>sad</p>        
      </div>
      
    </div>
    <div class="carousel-item">
      <img src="../img/image2.jpg" alt="Chicago" width="1100" height="500">
      
    </div>
    <div class="carousel-item">
      <img src="../img/image3.jpg" alt="New York" width="1100" height="500">
      
    </div>
  </div>
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>

<!--Fin de Imagenes de presentacion parte media-->
<div class="container-fluid">
  <div class="row justify-content-center align-items-center">
    <div class="border border-white">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore.<center><img src="../img/magi.jpg" width="200" height="200" class="d-inline-block align-top" alt="logo de arguident"></center></div>
    <div class="border border-white">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore.<center><img src="../img/magi2.jpg" width="200" height="200" class="d-inline-block align-top" alt="logo de arguident"></center></div>
    <div class="border border-white">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore.<center><img src="../img/pika.jpg" width="200" height="200" class="d-inline-block align-top" alt="logo de arguident"></center></div>
    <div class="border border-white">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore.<center><img src="../img/chicle.jpg" width="200" height="200" class="d-inline-block align-top" alt="logo de arguident"></center></div>
  </div>
</div>


<!--Inicio del script para que cambie de color el boton cuando el puntero esta sobre el-->
<script>
  $('.button1').hover(function() {            
            $(this).toggleClass("button2");
        });
</script>
<!--Fin del script-->
</body>
</html>

