<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%>
<%@ page import="model.entity.*"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%UserService us = UserServiceFactory.getUserService(); %>
<% User user = us.getCurrentUser(); %>
<%List<Role> roles =(List<Role>)request.getAttribute("roles");%>
<%String mensaje=(String)request.getAttribute("mensaje"); %>

<html>
<head>
	<title>New Users</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/style.css">
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
        <a href="/users"><button class="button button2">Acl</button></a>
      </li>
      <%} %>            
    </ul>
  </div>

  </div>  
</nav>
<!--Fin Obcciones de la parte derecha-->
<div class="row">
    <div class="col-sm-12 bg-dark">
      <center class="cent1" >Agregar Usuario</center>
    </div>
  </div><br>		
<div class="container">
  <br><br>
    <form method="get" action="/users/add">
							
				<table class="table">
        		<tbody>
            		<tr>									
					<%if(mensaje!=null){ %>										
					<div class="alert alert-danger alert-dismissible fade show">
					   <button type="button" class="close" data-dismiss="alert">&times;</button>
					   <strong>Danger!</strong><%=mensaje%>
					 </div>
					 <%} %>
					<tr>
						<td><label>Nombre: </label>
						<input type="text" name="name" class="form-control" autofocus required placeholder="Dr. Jose Arias" ></td>					
						<td><label>Cargo: </label>
						<input type="text" name="position" class="form-control" required placeholder="Odontologo Principal" ></td>					
						<td><label>Especialidad: </label>
						<input type="text" name="specialty" class="form-control" required placeholder="Perodoncia e implantes"></td>
						<td><label>Genero: </label>
						<select name="gender" class="form-control">
							<option value="Hombre">Hombre</option>
							<option value="Mujer">Mujer</option></select></td>
					</tr>
					<tr>
						<td><label>Email: </label>
						<input type="text" name="email" class="form-control" required placeholder="cualquiera@gmail.com" pattern="([A-Za-z0-9_-])+([@]{1})+(gmail|unsa)+([.]{1})+(com|edu.pe)" ></td>					
						<td><label>Fecha de nacimiento: </label>
						<input type="date" name="birth" required class="form-control"></td>
						<td><label>DNI: </label>
						<input type="text" name="DNI" class="form-control" required pattern="[0-9]{8}" placeholder="12345678" title="Ingrese ocho digitos"></td>					
						<td><label>Telefono: </label>
						<input type="text" name="phone" class="form-control" required pattern="[0-9]{9}" placeholder="123456789" title="Ingrese nueve digitos"></td>											
					</tr>
					<tr>										
						<td><label>Direccion: </label>
						<input type="text" name="address" class="form-control" required placeholder="Calle Aleatoria 322" ></td>
						<td><label>Role: </label>
						<select name="role" class="form-control">
							<option selected value="0">Elige una opcion</option>
							<% for (int i = 0;i<roles.size();i++) { %>
							<% Role r = (Role)roles.get(i); %>
							<option value="<%=r.getId() %>"><%=r.getName() %></option>
							<%} %>
							</select></td>
						<td></td>
						<td></td>
					</tr>					
					</tbody>
					</table><br>
						<div class="trans text-center">
				        <a href="/users" class="btn btn-info btn-responsive">Atras</button></a>
				        <button type="submit" class="btn btn-info btn-responsive">Guardar</button>
        </div><br><br>
    </form>
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