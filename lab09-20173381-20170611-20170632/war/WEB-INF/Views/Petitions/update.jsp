<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.*"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<% Petition user = (Petition)request.getAttribute("user");%>
<% SimpleDateFormat birth=new SimpleDateFormat("yyyy-MM-dd");%>
<% SimpleDateFormat created=new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");%>
<%String id =(String)request.getAttribute("id");%>
<%UserService us = UserServiceFactory.getUserService(); %>
<%List<Role> roles =(List<Role>)request.getAttribute("roles");%>
<%String mensaje=(String)request.getAttribute("mensaje"); %>
<html>
<head>
	<title>Peticion</title>
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
      <center class="cent1" >Editar Usuario</center>
    </div>
  </div><br>
		<div class="container">
		<br><br>			
			<form method="get" action="/petitions/accept">
			<input type="hidden" name="id" value="<%=id %>">
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
				<td><label>Nombre</label>
				<input type="text" name="name" class="form-control" required autofocus value="<%=user.getName() %>" readonly></td>				
				<td><label>Email</label>
				<input type="text" name="email" class="form-control" required value="<%= user.getEmail() %>" readonly></td>	
				<td><label>Genero</label>
				<select name="gender" class="form-control" disabled>
				<% if(user.getGender().equals("Hombre")) { %>
					<option selected value="Hombre">Hombre</option>
					<option value="Mujer">Mujer</option>
				<%}else{ %>
					<option selected value="Mujer">Mujer</option>
					<option value="Hombre">Hombre</option>
				<%} %>
					</select></td>
				</tr>
				<tr>						
				<td><label>Fecha de nacimiento</label>
				<input type="date" name="birth" class="form-control" required value="<%= birth.format(user.getBirth()) %>" readonly></td>
				<td><label>DNI</label>
				<input type="text" name="DNI" class="form-control" required value="<%= user.getDNI() %>" required pattern="[0-9]{8}" readonly></td>
				<td><label>Telefono</label>
				<input type="text" name="phone" class="form-control" required value="<%= user.getPhone() %>" required pattern="[0-9]{9}" readonly></td>
				</tr>
								
				<tr>
				<td><label>Direccion</label>
				<input type="text" name="address" class="form-control" required value="<%= user.getAddress() %>" readonly></td>
				<td><label>role</label>
				<select name="role" class="form-control">
				<option selected value="0">--------</option>
				<% for (int i = 0;i<roles.size();i++) { %>
				<% Role r = (Role)roles.get(i); %>
				<option value="<%=r.getId() %>"><%=r.getName() %></option>
				<%} %>
				</select></td>
				
				<td><label>Fecha de creacion</label>
				<p><%= created.format(user.getCreated()) %></p></td>
				</tr>
				</table><br>
						<div class="trans text-center">
						<a href="/petitions" class="btn btn-info btn-responsive">Atras</button></a>
				        <a href="/petitions/delete?id=<%=id %>" class="btn btn-info btn-responsive">Rechazar</button></a>
				        <button type="submit" class="btn btn-info btn-responsive">Aceptar</button>
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