<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.*"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%
	UserService us = UserServiceFactory.getUserService();
%>
<%
	User user = us.getCurrentUser();
%>
<%
	List<Role> roles = (List<Role>) request.getAttribute("roles");
%>
<%
	String mensaje = (String) request.getAttribute("mensaje");
%>
<%
	Patient p = (Patient) request.getAttribute("patient");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Agregar Cita Medica</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
	<!--Inicio del codigo del logo de facebook-->
	<div class="row" style="background-color: #000000; color: #908F99;">
		<div class="col-sm-10">Calle Jerusalén N° 506 - Yanahuara. (5,97
			km) 054 Arequipa Contact: (054) 271946</div>
		<div class="col-sm-1">
			<a
				href="https://www.facebook.com/pages/biz/medical_health-arequipa-054/ArguiDent-Aqp-300624626728028/"
				target="_blank" class="float-right"><img
				src="../img/logoFace.png" width="20" height="20"></a>
		</div>
	</div>
	<!--Fin del codigo del logo de facebook-->
	<div class="container">
		<!--Inicio del logo de arguident-->
		<nav class="navbar navbar-expand-lg navbar-light bg-white"> <a
			class="navbar-brand" href="#"> <img src="../img/arguident2.jpg"
			width="90" height="70" alt="logo de arguident"> Arguident
		</a> <!--Fin del logo de arguident--> <!--Inicio Obcciones de la parte derecha-->
		<div class="container-fluid">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a href="/gmail"><button
								class="button button1">Inicio</button></a></li>
					<li class="nav-item"><a href="/patients"><button
								class="button button1">Pacientes</button></a></li>
					<li class="nav-item"><a href="/medical_appointments"><button
								class="button button2">Citas Medicas</button></a></li>
					<li class="nav-item"><a href="#"><button
								class="button button1">Servicios</button></a></li>
					<li class="nav-item"><a href="#"><button
								class="button button1">Consultas</button></a></li>
					<li class="nav-item"><a href="#"><button
								class="button button1">Calendario</button></a></li>
					<li class="nav-item"><a href="#"><button
								class="button button1">Instalaciones</button></a></li>
					<li class="nav-item"><a href="/petitions/add"><button
								class="button button1">Sign in</button></a></li>
					<%
						if (user == null) {
					%>
					<li class="nav-item"><a
						href="<%=us.createLoginURL("/gmail")%>"><button
								class="button button1">Login</button></a></li>
					<%
						}
					%>
					<%
						if (user != null) {
					%>
					<li class="nav-item"><a
						href="<%=us.createLogoutURL("/gmail")%>"><button
								class="button button1">Logout</button></a></li>
					<%
						}
					%>
					<%
						if (user != null) {
					%>
					<li class="nav-item"><a href="/users"><button
								class="button button1">Acl</button></a></li>
					<%
						}
					%>
				</ul>
			</div>

		</div>
		</nav>
		<!--Fin Obcciones de la parte derecha-->
		<div class="row">
			<div class="col-sm-12 bg-dark">
				<center class="cent1">Agregar Cita Medica</center>
			</div>
		</div>
		<br>
		<div class="container">
			<br> <br>

			<form method="post" action="/medical_appointments/add">
				<table class="table">
					<tbody>
						<tr>
							<td><label>DNI del paciente: </label> <input type="text"
								name="DNI" class="form-control" required placeholder="90909090"></td>
							<td><label>Motivo de la cita medica: </label> <input
								type="text" name="reason" class="form-control" required
								placeholder="motivo de la cita medica"></td>
							<td><label>Fecha de la cita medica: </label> <input
								type="date" name="appointment_date" class="form-control"
								required></td>

							<td><label>Estado de la cita: </label> <select name="status"
								class="form-control">
									<option value="true">Activa</option>
									<option value="false">Inactiva</option>
							</select></td>
						</tr>

					</tbody>
				</table>
				<br>
				<div class="trans text-center">
					<a href="/medical_appointments" class="btn btn-info btn-responsive">Atras
						</button>
					</a>
					<button type="submit" class="btn btn-info btn-responsive">Guardar</button>
				</div>
				<br> <br>
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


