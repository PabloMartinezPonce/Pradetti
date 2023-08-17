<%-- 
    Document   : datosDelColaborador
    Created on : 6/02/2018, 11:24:46 AM
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
                                <sec:authorize access="hasAnyRole('Reporte_De_Datos_De_Los_Colaboradores')">
                                 <div class="row">
                                     <div class="col-xs-12 col-md-8">
                                         <div class="row">
                                             <div class="col-xs-12">
                                                 <h2>Datos de los colaboradores</h2>
                                             </div>
                                            <div class="col-xs-12 lnbrk"></div>
                                            <form method="post" action="${pageContext.request.contextPath}/reporte/datos-del-colaborador.htm">
                                                <div class="col-xs-12">                                                
                                                    <div class="input-group">
                                                      <span class="input-group-addon" id="rls">Estado de los colaboradores</span>
                                                        <select name="estado" class="form-control" placeholder="Campo requerido" aria-describedby="rls"  required>
                                                          <option value=""></option>
                                                          <c:forEach items="${model.estados}" var="estado">
                                                              <option value="${estado.idStatusAgremiado}">${estado.status}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">                                                
                                                    <div class="input-group">
                                                        <span class="input-group-addon" id="rls">&nbsp;&nbsp;&nbsp;&nbsp;Cliente&nbsp;&nbsp;&nbsp;</span>
                                                        <select name="cliente" class="form-control" placeholder="Campo requerido" aria-describedby="rls">
                                                          <option value=""></option>
                                                          <c:forEach items="${model.clientes}" var="cliente">
                                                              <option value="${cliente.idCliente}">${cliente.nombreEmpresa}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">                                                
                                                    <div class="input-group">
                                                      <span class="input-group-addon" id="rls">Contratista</span>
                                                        <select name="contratista" class="form-control" placeholder="Campo requerido" aria-describedby="rls">
                                                          <option value=""></option>
                                                          <c:forEach items="${model.contratistas}" var="contratista">
                                                              <option value="${contratista.idContratista}">${contratista.nombreEmpresa}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 lnbrk"></div>
                                                <div class="col-xs-12 col-sm-4 col-sm-offset-1">
                                                    <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                                                        <b>Generar reporte</b>
                                                    </button>
                                                </div>
                                                <div class="col-xs-12 col-sm-4">
                                                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/reporte/reportes.htm">
                                                        <b>Regresar</b>
                                                    </button>
                                                </div>
                                            </form>
                                         </div>
                                     </div>
                                 </div>
                                </sec:authorize>              
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
