<%-- 
    Document   : vistaPrincipal
    Created on : 2/02/2018, 12:21:39 PM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
--%>
<%response.setHeader("pragma", "no-cache");              
  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
  response.setHeader("Expires", "0");%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html>
<html>
    <head>        
       <%@include file="../fragmentos/cabecera.jsp" %>
        <title>Payroll - Reportes</title>   
      <link rel="icon" href="${pageContext.request.contextPath}/frontend/img/ico.ico" type="image/x-icon" />
      <link rel="shortcut icon" href="${pageContext.request.contextPath}/frontend/img/ico.ico" type="image/x-icon" />    
    </head>
    <body>
           <!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
           <%@include file="../fragmentos/menu.jsp" %>
           <!-- =============================================================== cuerpo de la página ============================================================================ -->
           <div id="sidebody-bg-img">
                    <div class="col-xs-12 main" id="sidebody">
                        <h1 class="page-header" id="titlePage">
                            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Reportes
                        </h1>
                        
                        <div class=" col-xs-12 col-md-10 col-md-offset-2 col-lg-9 col-lg-offset-3" id="frameContainer">
                                 <div class="row">
                                     <div class="col-xs-12 col-md-8">                                         
                                        <sec:authorize access="hasAnyRole('Reportes')">
                                            <div class="row">
                                             <div class="col-xs-12">
                                                 <h2>Lista de reportes disponibles en Payroll</h2>
                                             </div>
                                             <div class="col-xs-12">
                                                 <div class="listReport">                                                     
                                                    <sec:authorize access="hasAnyRole('Reporte_De_Datos_De_Los_Colaboradores')">
                                                     <div class="itemReport">
                                                        <h4 class="itemReportH">
                                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;<b>Datos del Colaborador</b>
                                                        </h4>
                                                      <p class="itemReportB">Devuelve todos los datos pertenecientes a un grupo de colaboradores que se encuentran en el mismo estado, con el mismo cliente y/o contratista.</p>                                                    
                                                      <a href="${pageContext.request.contextPath}/reporte/datos-del-colaborador.htm" class="itemReportLink">
                                                          <span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span>
                                                      </a>
                                                     </div>
 <!--                                                   </sec:authorize>
                                                     <div class="itemReport">
                                                        <h4 class="itemReportH">
                                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;<b>Fondo de ahorro</b>
                                                        </h4>
                                                      <p class="itemReportB">Devulve el reporte de Fondo de ahorro de los colaboradores que se encuentren con el mismo cliente y/o contrstista por rango de fechas.</p>                                                    
                                                      <a href="${pageContext.request.contextPath}/reporte/fondo-de-ahorro.htm" class="itemReportLink">
                                                          <span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span>
                                                      </a>
                                                     </div>
                                                  </div>
                                             </div>
                                         </div>
                                        </sec:authorize> -->
                                     </div>
                                 </div>
                        </div>
                    </div>
                      <!-- ============================================================ FIN =====  cuerpo de la página ======================================================================== -->                      
                      <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
                      <%@include file="../fragmentos/pie.jsp" %>
                      <!-- ==================================================== ventana ================================================================== -->
           <c:if test="${model.mostrarVentana}">
               <script>
                   getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
               </script>
           </c:if>
                    </div>
    </body>
</html>

