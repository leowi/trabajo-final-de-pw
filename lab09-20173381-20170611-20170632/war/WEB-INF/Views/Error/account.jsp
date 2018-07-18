<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%UserService us = UserServiceFactory.getUserService(); %>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="../estilo.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
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

  <title>Error</title>
  </head>
  <body>

    <div class="container">
      <div class="hero-text">
        <h2 class="text-center font-weight-bold text-muted"  >Debes iniciar sesion para continuar.</h2><br>
        <a class="btn btn-primary btn-responsive btn-lg" href="<%= us.createLoginURL("/gmail")%>">Login</button></a>        
      </div>
    </div>

</body>
</html>