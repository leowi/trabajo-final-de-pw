<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List"%>
    <%@ page import="model.entity.*"%>
    <%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%UserService us = UserServiceFactory.getUserService(); %>
<% User user = us.getCurrentUser(); %>
<!DOCTYPE html>
<html lang="es">
<head>
	<title>CreateJSP</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
<%
 Historia u =(Historia)request.getAttribute("historia");
%>
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
  <div class="collapse navbar-collapse" id="navbarNav">
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
        <a href="#"><button class="button button1">Sign in</button></a>
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
        <a href="/users"><button class="button button2">Acl</button></a>
      </li>
      <%} %>            
    </ul>	
  </div>

  </div>  
</nav>
<div class="row">
    <div class="col-sm-12 bg-dark">
      <center class="cent1" >Ver Historia</center>
    </div>
  </div><br>
<!--Fin Obcciones de la parte derecha-->
	<div class="container">
	<br><br>
			<table class="table">
				<tbody>
			      <tr>
			        <td><label>Nombre del Paciente</label><input type="text" class="form-control" value="<%= u.getName() %>" readonly></td>
			        <td><label>Fecha de Nacimiento</label><input type="date" name="birthdate" class="form-control" value="<%= u.getBirthdate() %>" readonly></td>
			        <td><label>Genero</label><input type="text" class="form-control" value="<%= u.getSex() %>" readonly>
					</td>
			      </tr>
			      <tr>
			        <td><label>Nombre del Profecional</label><input type="text" name="namepro" class="form-control" value="<%= u.getNamepro() %>" readonly></td>
			        <td><label>Fecha de Consulta</label><input type="date" name="date" class="form-control" value="<%= u.getDate() %>" readonly></td>
			        <td></td>
			      </tr>
	    		</tbody>
    		</table>
    		<br><br>
    		<table class="table">
    			<tbody>
    				<tr>
    					<td><label>MOTIVO DE CONSULTA</label><textarea class="form-control" rows="5" cols="10" readonly><%= u.getRfc() %></textarea></td>
    					<td><label>ANTECEDENTES</label><br>
							<label>Antecedentes Familiares</label><input type="text" class="form-control" value="<%= u.getFh() %>" readonly>
							<label>Antecedentes Personales</label><input type="text" class="form-control" value="<%= u.getPh() %>" readonly></td>
    				</tr>	
    				<tr>
    					<td><label>DIAGNÓSTICO</label><br>
							<label>Diagnóstico Presuntivo</label><input type="text" class="form-control" value="<%= u.getPd() %>" readonly>
							<label>Diagnóstico Definitivo</label><input type="text" class="form-control" value="<%= u.getDd() %>" readonly></td>
						<td><label>PLAN DE TRABAJO</label><textarea class="form-control" rows="5" cols="10" readonly><%= u.getWp() %></textarea></td>
    				</tr>
    				<tr>
    					<td><label>PRONÓSTICO</label><input type="text" class="form-control" value="<%= u.getFore() %>" readonly></td>
    					<td><label>TRATAMIENTO/RECOMENDACIONES</label><input type="text"  class="form-control" value="<%= u.getRecommend() %>" readonly></td>
    				</tr>
    			</tbody>
    		</table>
    		<center>
				<table style="width: 80%">
				<label><h3>Odontograma</h3></label><br><br>
					<tr>
						<td><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmRJt0M1VkOpPPuDLxCa7oUnyEZRN5G3z9BQGYQ88O_eJy3jhSUQ" class="img-thumbnail" alt="Odontograma" width="364" height="296"></td>
						<td>
							<label>Especificaciones</label><textarea class="form-control" rows="5" cols="35" readonly><%= u.getSpe() %></textarea><br><br>
							<label>Observaciones</label><textarea class="form-control" rows="5" cols="35" readonly><%= u.getObs() %></textarea>
						</td>
					</tr>					
			  </table>
			  </center><br><br>
			  <div class="trans text-center">
			  <a href="/history" class="btn btn-info btn-responsive">Atras</button></a>			  
			  </div><br><br>
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