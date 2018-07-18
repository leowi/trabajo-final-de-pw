<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%>
<%@ page import="model.entity.*"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%UserService us = UserServiceFactory.getUserService(); %>
<%User user = us.getCurrentUser();%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<style>
body, html {
    height: 100%;
    margin: 0;
    background-image: url("../img/rem.png");
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

.hero-text {
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
}

</style>
</head>
	<body>
		<div class="container">
  <div class="hero-text">
    <h1 class="text-center font-weight-bold text-muted display-4"  >Welcome, <%=user.getNickname() %></h1><br>
    <h2 class="text-center font-weight-bold text-muted"  >No tienes este permiso.</h2><br>
    <a class="btn btn-primary btn-responsive btn-lg" href='#' onclick="history.back()">Atras</button></a>
  </div>
</div>
</body>
</html>