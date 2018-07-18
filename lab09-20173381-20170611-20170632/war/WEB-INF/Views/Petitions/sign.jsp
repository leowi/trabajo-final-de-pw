<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%UserService us = UserServiceFactory.getUserService(); %>
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
  <title>Sign in</title>
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

    <div class="row">
      <div class="col-sm-4"></div>
      <div class="col-sm-4">
        <center>
          
          <form action="/petitions/add" method="get">
          <h2 class="">Please sign in</h2>

          <label for="inputNombre" class="sr-only">Name</label>
          <input type="text" id="inputNombre" class="form-control" name="name" placeholder="Nombre" required autofocus><br>

          <label for="inputEmail" class="sr-only">Email address</label>
          <input type="email" id="inputEmail" class="form-control" name="email" placeholder="Email address" required><br>
          
          <label for="inputGender" class="sr-only">Gender</label>
            <select name="gender" id="inputGender" class="form-control">
              <option value="Hombre">Hombre</option>
             <option value="Mujer">Mujer</option></select><br>

          <label for="inputBirthdate" class="sr-only">Birthdate</label>
          <input type="date" name="birth" id="inputBirthdate" class="form-control" placeholder="Birthdate" required><br>

          <label for="inputDni" class="sr-only">Dni</label>
          <input type="text" name="DNI" id="inputDni" class="form-control" pattern="[0-9]{8}" placeholder="12345678" title="Ingrese ocho digitos" required><br>

          <label for="inputPhone" class="sr-only">Phone</label>
          <input type="text" name="phone" id="inputPhone" class="form-control" required pattern="[0-9]{9}" placeholder="123456789" title="Ingrese nueve digitos"><br>

          <label for="inputAddress" class="sr-only">Address</label>
          <input type="text" name="address" id="inputAddress" class="form-control" placeholder="ej. Calle Aleatoria 322" ><br>

          <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
          </form>
        </center>
      </div>
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