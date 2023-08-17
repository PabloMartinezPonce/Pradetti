<%-- 
    Document   : informacionIncompleta
    Created on : 24/01/2018, 04:27:21 PM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
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

    <title>Información Incompleta</title>
    <style>
    .loader {
        border: 8px solid #f3f3f3;
        border-radius: 50%;
        border-top: 8px solid black;
        border-bottom: 8px solid black;
        width: 27px;
        height: 27px;
        -webkit-animation: spin 5s linear infinite;
        animation: spin 5s linear infinite;
        margin: 0pX;
    }

    @-webkit-keyframes spin {
      0% { -webkit-transform: rotate(0deg); }
      100% { -webkit-transform: rotate(360deg); }
    }

    @keyframes spin {
      0% { transform: rotate(0deg); }
      100% { transform: rotate(360deg); }
    }
</style>
  </head>

  <body onload="setTimeout(backInView, 2708)">
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
                                   <span class="glyphicon glyphicon-ice-lolly-tasted" aria-hidden="true"></span>&nbsp;¡UPS!&nbsp;
                          </span>
                      </div>
                      <div class="col-xs-12">
                        <h3 class="heading">
                            Imposible completar acción, <b>Información Incompleta</b>
                          </h3>
                        <h3 class="description">
                            <a href="${pageContext.request.contextPath}/">
                                <span class="glyphicon glyphicon-hand-left" aria-hidden="true"></span> 
                            </a>                            
                          </h3>
                          <div class="loader"></div>
                      </div>
                  </div>
              </div>
            </div>
        </div>

    </div> <!-- /container -->
    <footer>
        <div class="myFooter"> Payroll by&nbsp;&nbsp;<a href="http://www.pradettisanti.com.mx/" target="_blank">PradettiSanti Consulting</a>&nbsp;&nbsp;&copy; 2018</div>
    </footer>
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <!-- Latest compiled and minified JavaScript -->
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/frontend/js/ie10-viewport-bug-workaround.js"></script>
    
    <script>
        function backInView() {
            window.history.back();
        }
    </script>
  </body>
</html>
