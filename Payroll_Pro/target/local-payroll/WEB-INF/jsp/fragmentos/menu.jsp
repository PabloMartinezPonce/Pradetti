<%-- 
    Document   : menu
    Created on : 10/11/2016, 09:05:08 AM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <nav class="navbar navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="true">
                        <span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
                    </button>
                    <a class="navbar-brand" id="bigMenu" href="#">
                        <img src="${pageContext.request.contextPath}/frontend/img/pradetti_90px.png" alt="logoB" id="logoHeader">
                        <b class="hidden-xs">Payroll</b>
                    </a>
                </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <sec:authorize access="hasAnyRole('Colaboradores')">
                                <li class=" hidden-xs"><a href="#">&nbsp;&nbsp;&nbsp;<span id="badgeClic" class="badge pendientestotales"></span></a></li>
                                </sec:authorize>
                                <li class="onClicMe hidden-sm hidden-md hidden-lg"><a href="${pageContext.request.contextPath}/index.htm">
                                    <span class="glyphicon glyphicon-tower" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Dashboard
                                    </a>
                                </li>
                                <sec:authorize access="hasAnyRole('Ver_Todo','Clientes')">
                                    <li class="onClicMe hidden-sm hidden-md hidden-lg"><a href="${pageContext.request.contextPath}/cliente/clientes.htm">
                                        <span class="glyphicon glyphicon-queen" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Clientes
                                        </a>
                                    </li>
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('Colaboradores')">
                                    <li class="onClicMe hidden-sm hidden-md hidden-lg"><a href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                            <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores
                                        </a>
                                    </li>
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('Ver_Todo','Contratistas')">
                                    <li class="onClicMe hidden-sm hidden-md hidden-lg"><a href="${pageContext.request.contextPath}/contratista/contratistas.htm">
                                            <span class="glyphicon glyphicon-king" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Contratistas
                                        </a>
                                    </li>
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('Ver_Todo','Sindicatos')">
                                    <li class="onClicMe hidden-sm hidden-md hidden-lg">
                                        <a href="${pageContext.request.contextPath}/sindicato/sindicatos.htm">
                                            <span class="glyphicon glyphicon-bishop" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sindicatos
                                        </a>
                                    </li>       
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('Sistema')">
                                    <li class="onClicMe hidden-xs hidden-sm hidden-md hidden-lg"><a href="${pageContext.request.contextPath}/sistema/opciones-del-sistema.htm">
                                        <span class="glyphicon glyphicon-knight" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sistema
                                        </a>
                                    </li>
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('Reportes')">
                                    <li class="onClicMe hidden-sm hidden-md hidden-lg">
                                        <a href="${pageContext.request.contextPath}/reporte/reportes.htm">
                                            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Reportes
                                        </a>
                                    </li>
                                </sec:authorize>
                                <li class=" hidden-sm hidden-md hidden-lg"><a href="${pageContext.request.contextPath}/j_spring_security_logout">
                                    <span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;Salir
                                    </a></li>
                                <li class="dropdown hidden-xs">
                                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Opciones <span class="caret"></span></a>
                                  <ul class="dropdown-menu">
                                    <li><a class="text-center">&nbsp;&nbsp;&nbsp;<strong>&CenterDot;Versión 1.60.1&CenterDot;</strong></a></li>
                                      <li class="hidden-xs hidden-sm hidden-md"><a href="${pageContext.request.contextPath}/sistema/mi-usuario.htm"><span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Mi usuario</a></li>
                                      <li class="hidden-xs hidden-sm hidden-md"><a href="${pageContext.request.contextPath}/sistema/mi-bitacora.htm"><span class="glyphicon glyphicon-hourglass" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Mi Bitacora</a></li>
                                      <li role="separator" class="divider"></li>
                                <sec:authorize access="hasAnyRole('Colaboradores')">
                                      <li class="hidden-xs hidden-sm hidden-md"><a href="${pageContext.request.contextPath}/sistema/mis-notificaciones.htm"><span class="glyphicon glyphicon-bell" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Notificaciones</a></li>
                                      <li role="separator" class="divider"></li>
                                </sec:authorize>
                                    <li class="hidden-xs hidden-sm hidden-md"><a href="${pageContext.request.contextPath}/sistema/cambiar-mi-acceso.htm"><span class="glyphicon glyphicon-copyright-mark" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Cambiar contraseña</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="${pageContext.request.contextPath}/j_spring_security_logout" class="text-center"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;<strong>Salir</strong></a></li>
                                    <li role="separator" class="divider"></li>
                                  </ul>
                              </li>
                            </ul> 
                        </div>
            </div>
        </nav>
            <div class="container-fluid">
                <div class="row">
                    <div class="hidden-xs sidebar" id="sidebar">
                        <ul class="nav nav-sidebar ">
                            <li class="onClicMe">
                                <a href="${pageContext.request.contextPath}/index.htm" class="btn btn-default btn-lg sideMenu">
                                <span class="glyphicon glyphicon-tower" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Dashboard</a> 
                            </li>
                          </ul>
                        <sec:authorize access="hasAnyRole('Ver_Todo','Clientes')">
                                <ul class="nav nav-sidebar ">
                                    <li class="onClicMe">
                                        <a  href="${pageContext.request.contextPath}/cliente/clientes.htm" class="btn btn-default btn-lg sideMenu">
                                            <span class="glyphicon glyphicon-queen" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Clientes</a> 
                                    </li>
                                </ul>      
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('Colaboradores')">
                            <ul class="nav nav-sidebar ">
                              <li class="onClicMe">
                                  <a  href="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm" class="btn btn-default btn-lg sideMenu">
                                  <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores</a> 
                              </li>
                            </ul>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('Ver_Todo','Contratistas')">
                                <ul class="nav nav-sidebar ">
                                    <li class="onClicMe">
                                        <a  href="${pageContext.request.contextPath}/contratista/contratistas.htm" class="btn btn-default btn-lg sideMenu">
                                            <span class="glyphicon glyphicon-king" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Contratistas</a> 
                                    </li>
                                </ul>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('Ver_Todo','Sindicatos')">
                            <ul class="nav nav-sidebar ">
                                <li class="onClicMe">
                                    <a href="${pageContext.request.contextPath}/sindicato/sindicatos.htm" class="btn btn-default btn-lg sideMenu">
                                    <span class="glyphicon glyphicon-bishop" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sindicatos</a> 
                                </li>
                            </ul>   
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('Sistema')">
                            <ul class="nav nav-sidebar ">
                                <li class="onClicMe">
                                    <a href="${pageContext.request.contextPath}/sistema/opciones-del-sistema.htm" class="btn btn-default btn-lg sideMenu">
                                    <span class="glyphicon glyphicon-knight" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sistema</a> 
                                </li>
                            </ul>   
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('Reportes')">
                            <ul class="nav nav-sidebar ">
                                <li class="onClicMe">
                                    <a  href="${pageContext.request.contextPath}/reporte/reportes.htm"  class="btn btn-default btn-lg sideMenu">
                                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Reportes</a> 
                                </li>
                            </ul>   
                        </sec:authorize>
                         <img src="${pageContext.request.contextPath}/frontend/img/pradetti_90px.png" alt="logoB" id="logoFooter"/>
                    </div>
                    
