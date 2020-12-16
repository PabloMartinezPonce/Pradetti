<%-- 
    Document   : accesoDenegado
    Created on : 26/10/2016, 02:03:30 PM
    Author     : HEM
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>    
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="Payroll - Sistema de administración de nómina por Pradetti Santi Consulting">
    <meta name="author" content="PabloSagoz"> 
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="${pageContext.request.contextPath}/frontend/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!-- custom style for this page -->
    <link href="${pageContext.request.contextPath}/frontend/css/numbers.css" rel="stylesheet">

    <title>Acceso denegado</title>
  </head>

  <body>
    <div class="container">
        
        <div id="body">            
            <div>
              <div class="text-center">
                  <div class="row">                    
                      <div class="col-xs-12 col-sm-4">
                          <img src="${pageContext.request.contextPath}/frontend/img/logo.png" alt="logo pradetti">
                      </div>
                      <div class="col-xs-12 col-sm-8">
                        
                      <h2><b>Payroll</b></h2>
                          <span class="mega-number">                     
                                   <span class="glyphicon glyphicon-hand-up element-animation" aria-hidden="true"></span>
                          </span>
                      </div>
                      <div class="col-xs-12">
                        <h3 class="heading">
                            <span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
                            <b>Acceso denegado</b>
                            <span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span><br/>No cuentas con los permisos necesarios para acceder a esta parte de payroll.
                          </h3>
                        <h3 class="description">
                            <a href="${pageContext.request.contextPath}/">
                                <span class="glyphicon glyphicon-hand-left" aria-hidden="true"></span> Volver
                            </a>                            
                          </h3>
                      </div>
                  </div>
              </div>
            </div>
        </div>

    </div> <!-- /container -->
    <footer>
        <div class="myFooter"> Payroll by&nbsp;&nbsp;<a href="http://www.pradettisanti.com.mx/" target="_blank">PradettiSanti Consulting</a>&nbsp;&nbsp;&copy; 2017</div>
    </footer>
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <!-- Latest compiled and minified JavaScript -->
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/frontend/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
