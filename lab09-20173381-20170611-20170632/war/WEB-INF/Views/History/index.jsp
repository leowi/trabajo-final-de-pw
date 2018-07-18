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
	<title>IndexJSP</title>
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
 List<Historia> users =(List<Historia>)request.getAttribute("historia");
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
  <a class="navbar-brand" href="/gmail">
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
        <a href="/gmail"><button class="button button1">Inicio</button></a>
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
</nav><hr>
<!--Fin Obcciones de la parte derecha-->
<nav class="navbar navbar-expand-lg navbar-light bg-white">
  <div class="collapse navbar-collapse" id="navbarNav">
  <ul class="navbar-nav">
      <li class="nav-item">
        <a href="/history/add"><button class="button button1">Create</button></a>
      </li>      
    </ul>
    <ul class="navbar-nav ml-auto">
    <li class="nav-item">
        <a href="/history"><button class="button button2">Historias</button></a>
      </li>
      <li class="nav-item">
        <a href="/users"><button class="button button1">Users</button></a>
      </li>
      <li class="nav-item">
        <a href="/roles"><button class="button button1">Roles</button></a>
      </li>
      <li class="nav-item">
        <a href="/resources"><button class="button button1">Resources</button></a>
      </li>
      <li class="nav-item">
        <a href="/access"><button class="button button1">Access</button></a>
      </li>
      <li class="nav-item">
        <a href="/petition"><button class="button button1">Peticiones</button></a>
      </li>
    </ul>
  </div>
</nav>
<div class="row">
    <div class="col-sm-12 bg-dark">
      <center class="cent1" >Historias Clinicas</center>
    </div>
  </div><br>
	<div class="container">
	<input class="form-control" id="myInput" type="text" placeholder="Nombre">
  <br>
	<% if (users.size() > 0) { %>
		 <table class="table table-hover">
		 	<thead>
			      <tr>
			        <th>NOMBRE</th>
			        <th></th>					        
			        <th>ARCHIVOS</th>			        
			      </tr>
			      
			</thead>
			<tbody id="myList">
				  <% for (int i = 0;i<users.size();i++) { %>
				  <% Historia u = (Historia)users.get(i); %>
				  <tr>
					<td><%=u.getName() %></td>					
					<td>
					<a href="/history/delete?id=<%= u.getId() %>" class="btn btn-outline-danger btn-sm RbtnMargin float-right">Delete</button></a>
				   	<a href="/history/view?id=<%= u.getId() %>" class="btn btn-outline-secondary btn-sm RbtnMargin float-right">View</button></a>
				   	<a href="/history/update?id=<%= u.getId() %>" class="btn btn-outline-info btn-sm RbtnMargin float-right">Edit</button></a>
			    	</td>
			    	<td>			    	
				   	<a href="/upload/view?id=<%= u.getId() %>" class="btn btn-outline-secondary btn-sm RbtnMargin float-right">View</button></a>
				   	<a href="/upload/add?id=<%= u.getId() %>" class="btn btn-outline-info btn-sm RbtnMargin float-right">Create</button></a>
			    	</td>			    	
			    	</tr>
			    	<% } %>
					<% } %>
					</tbody>
					</table>
</div>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myList li").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
<!--Inicio del script para que cambie de color el boton cuando el puntero esta sobre el-->
<script>
  $('.button1').hover(function() {            
            $(this).toggleClass("button2");
        });
</script>
<!--Fin del script-->
</body>
</html>