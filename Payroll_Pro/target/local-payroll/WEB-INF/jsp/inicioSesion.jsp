<%-- 
    Document   : inicioSesion
    Created on : 26/10/2016, 01:02:02 PM
    Author     : HEM 
--%>
<! DOCTYPE html>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<html>
    <head>
        <meta http-equiv="X-UA-Comptible" content="IE-edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Payroll - Login de acceso - PradettiSanti Consulting">
        <meta name="author" content="PabloSagoz">
        <title>Payroll Login</title>        
       <!-- Latest compiled and minified CSS -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
      <link href="${pageContext.request.contextPath}/frontend/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
      <!-- custom style for this page -->
      <link href="${pageContext.request.contextPath}/frontend/css/login.css" rel="stylesheet">
      <link rel="icon" href="${pageContext.request.contextPath}/frontend/img/ico.ico" type="image/x-icon" />
      <link rel="shortcut icon" href="${pageContext.request.contextPath}/frontend/img/ico.ico" type="image/x-icon" />

    </head>
    <body>
        <div class="container">
        <div id="body">            
            <div>
              <div class="text-center">
                  <img src="${pageContext.request.contextPath}/frontend/img/logo.png" alt="logo pradetti">
                  <h1 class="text-center"><b>Payroll </b></h1>
                  <h3 class="form-signin-heading">Por favor ingrese sus credenciales</h3>
                  
                        <form method='POST' action = "j_spring_security_check" id="form_login" class="form-signin" role="form">
                            

                                    <div class="input-group">
                                        <span class="input-group-addon" id="addonEmail"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></span>
                                        <input name="j_username" id="username" type="email" class="form-control" placeholder="Correo Electrónico" aria-describedby="addonEmail" required autofocus pattern="[a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*[.][a-zA-Z]{1,10}" minlength="5">
                                      </div>
                                     <div class="input-group">
                                        <span class="input-group-addon" id="addonPwd"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
                                        <input  name="j_password" id="password" type="password" class="form-control" placeholder="Contraseña" aria-describedby="addonPwd" required >
                                      </div>
                                    <input class="btn btn-lg btn-primary btn-block" value="Acceder" type="submit" id="btnFormSubmit">
                                
                        </form>
                         <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">                               
                             <span id="badAccess">
                                <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>&nbsp;&nbsp;
                                Las credenciales ingresadas son incorrectas
                            </span>
                        </c:if>
              </div>
            </div>
        </div>

    </div>
        <footer>
            <div class="myFooter"> Payroll by&nbsp;&nbsp;<a href="http://www.pradettisanti.com.mx/" target="_blank">PradettiSanti Consulting</a>&nbsp;&nbsp;&copy; 2017</div>
        </footer>
          <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
          <!-- Latest compiled and minified JavaScript -->
          <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="${pageContext.request.contextPath}/frontend/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
