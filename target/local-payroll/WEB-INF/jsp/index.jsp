<%-- 
    Document   : inicioSesion
    Created on : 26/10/2016, 01:02:02 PM
    Author     : HEM 
--%>
<%response.setHeader("pragma", "no-cache");              
  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
  response.setHeader("Expires", "0");%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html>
<html>
    <head>        
       <%@include file="fragmentos/cabecera.jsp" %>
        <title>Payroll - Dashboard</title>   
      <link rel="icon" href="${pageContext.request.contextPath}/frontend/img/ico.ico" type="image/x-icon" />
      <link rel="shortcut icon" href="${pageContext.request.contextPath}/frontend/img/ico.ico" type="image/x-icon" />    
    </head>
    <body>
           <!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
           <%@include file="fragmentos/menu.jsp" %>
           <!-- =============================================================== cuerpo de la página ============================================================================ -->
           <div id="sidebody-bg-img">
                    <div class="col-xs-12 main" id="sidebody">
                        <h1 class="page-header" id="titlePage">
                            <span class="glyphicon glyphicon-tower" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Dashboard
                        </h1>
<sec:authorize access="hasAnyRole('Colaboradores')">                        
                        <h2 class="sub-header">Apartados que requieren atención</h2>
                        <div class="col-xs-12" id="bodyNotifications">
                            
        <sec:authorize access="hasAnyRole('Altas_En_Proceso')"> 
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/Altas-en-proceso.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/AltasEnProceso.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications altaenproceso">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/altas-en-proceso.htm">Altas en proceso</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
         
        <sec:authorize access="hasAnyRole('Altas_Solicitadas')">                                                
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/altas-solicitadas.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/AltasSolicitadas.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications vobo">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/altas-solicitadas.htm">Altas solicitadas</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                                  
        <sec:authorize access="hasAnyRole('Bajas_Pendientes')">      
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/bajas-pendientes.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/BajasPendientes.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications bajapendiente">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/bajas-pendientes.htm">Bajas pendientes</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                   
        <sec:authorize access="hasAnyRole('Bajas_Sin_Firmar')">      
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/baja-sin-firmar.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/bajasSolicitas.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications bajasinfirmar">0</span>
                                        </div>
                                    </div>  
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/baja-sin-firmar.htm">Bajas sin firmar</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                            
        <sec:authorize access="hasAnyRole('Bajas_Por_Finalizar')">   
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/bajas-por-finalizar.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/BajasPorFinalizar.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications bajaporfinalizar">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/bajas-por-finalizar.htm">Bajas por finalizar</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                                        
        <sec:authorize access="hasAnyRole('Bajas_Solicitadas')">  
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/bajas-solicitadas.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/bajasSolicitas.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications bajasolicitada">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/bajas-solicitadas.htm">Bajas solicitadas</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                       
        <sec:authorize access="hasAnyRole('Contratos_Por_Vencer')">             
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/contratos-por-vencer.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/ContratosPorVencer.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications porVencer">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/contratos-por-vencer.htm">Contratos por vencer</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                            
        <sec:authorize access="hasAnyRole('Renovaciones_Solicitadas')">                           
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/renovaciones-solicitadas.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/SolicitudesDeRenovacion.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications renovacionsolicitada">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/renovaciones-solicitadas.htm">Renovaciones solicitadas</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                            
        <sec:authorize access="hasAnyRole('Solicitudes_De_Renovacion')"> 
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/solicitudes-de-renovacion.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/SolicitudesDeRenovacion.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications solicitudderenovacion">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/solicitudes-de-renovacion.htm">Solicitudes de renovación</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize> 
            
        <sec:authorize access="hasAnyRole('Expedientes_Sin_Contrato')">              
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/expedientes-sin-contrato.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/ExpedientesSinContrato.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications expedientesincontrato">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/expedientes-sin-contrato.htm">Expedientes sin contrato</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                            
        <sec:authorize access="hasAnyRole('Expedientes_Por_Completar')">  
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/expedientes-por-completar.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/ExpedientesPorCompletar.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications expedienteincompleto">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/expedientes-por-completar.htm">Expedientes por completar</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                            
        <sec:authorize access="hasAnyRole('Bajas')">  
                            <section class="containerNotifications col-sm-3">
                                <div class="headerNotifications">
                                    <div class="numberNotifications">
                                        <a class="numberLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/colaboradores-dados-de-baja.htm">
                                            <img class="imgsMdls img-circle" alt="imagen de notificación" src="${pageContext.request.contextPath}/frontend/img/ExpedientesConObservaciones.png"/>
                                        </a>
                                        <div class="badgeNotifications">
                                            <span class="badgeTextNotifications baja">0</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="footerNotifications">
                                    <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <div class="sectionNotifications">
                                        <a class="sectionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </div>
                                    </sec:authorize>
                                    <div class="descNotifications">
                                        <span class="descriptionNotifications">
                                            <a class="descriptionLinkNotifications" href="${pageContext.request.contextPath}/colaboradores/colaboradores-dados-de-baja.htm">Colaboradores dados de baja</a>
                                        </span>
                                    </div>
                                </div>
                            </section>
        </sec:authorize>
                       
                        </div>
</sec:authorize>
    </div>
                      <!-- ============================================================ FIN =====  cuerpo de la página ======================================================================== -->                      
                      <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
                      <%@include file="fragmentos/pie.jsp" %>
                      <!-- ==================================================== ventana ================================================================== -->
           <c:if test="${model.mostrarVentana}">
               <script>
                   getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
               </script>
           </c:if>
                    </div>
    </body>
</html>
