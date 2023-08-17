<%-- 
    Document   : contratosGenerados
    Created on : 15/11/2016, 12:06:48 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
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
 <title>Payroll - Sistema</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-knight" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sistema      
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
<%@include file="../fragmentos/menuSistema.jsp" %>
<div class="col-xs-12 lnbrk"></div>
<h2 class="selectAction">Contratos Generados</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <div class="col-xs-12">
        <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Fecha del contrato</th>
                    <th>Contratista</th>
                    <th>Contratante</th>
                    <th>Contrato</th>
                    <th>&nbsp;Opciones&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                    <sec:authorize access="hasAnyRole('Contrados_Generados_Contratista_Cliente')">
                    <c:forEach  items="${model.contratos}" var="contrato">
                            <tr>
                                    <td>${contrato.idContratoEmpresas}</td>
                                    <td><b><fmt:formatDate value="${contrato.fecha}" pattern="yyyy - MM - dd" /></b></td>
                                    <c:forEach items="${model.contratistas}" var="contratista">
                                        <c:if test="${contrato.contratistaObj == contratista.idContratista}">
                                            <td>${contratista.nombreEmpresa}</td>                               
                                        </c:if>                        
                                    </c:forEach>
                                    <c:forEach items="${model.clientes}" var="cliente">
                                    <c:if test="${contrato.clienteObj == cliente.idCliente}">
                                        <td>${cliente.nombreEmpresa}</td>
                                    </c:if>
                                    </c:forEach>
                                    <td>${(contrato.urlDocumento!=null)?"Firmado":"Sin Firmar"}</td>
                                    <td>
                                        <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                        <sec:authorize access="hasAnyRole('Editar_Contratos_Generados')">
                                            <form class="inline" method="post" action="${pageContext.request.contextPath}/sistema/editar-datos-del-contrato-generado.htm">
                                                <input type="hidden" name="contratoEmpresas" value="${contrato.idContratoEmpresas}">
                                                <button type="submit" class="btn btn-default" title="Editar">
                                                  <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                </button>
                                            </form>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole('Cargar_Contrato_Generado_Contratista_Cliente')">
                                            <c:choose>
                                                <c:when test="${contrato.urlDocumento!=null}">
                                                    <form class="inline" action="">
                                                        <button type="button" class="btn btn-default redireccionarVentana-td toolTip" title="Descargar contrato firmado" value="${pageContext.request.contextPath}/sistema/contrato-entre-empresas/${contrato.idContratoEmpresas}/pdf.htm">
                                                            <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>
                                                        </button>
                                                    </form>                                                    
                                                </c:when>
                                                <c:otherwise>
                                                    <form class="inline" action="">
                                                        <button type="button" class="btn btn-success redireccionar-td toolTip" title="Cargar contrato firmado" value="${pageContext.request.contextPath}/sistema/cargar-contrato-entre-empresas/${contrato.idContratoEmpresas}/file.htm">
                                                            <span class="glyphicon glyphicon-open-file" aria-hidden="true"></span>
                                                        </button>
                                                    </form>                                                     
                                                </c:otherwise>
                                            </c:choose>
                                        </sec:authorize>     
                                        </div>
                                    </td>
                            </tr>                
                    </c:forEach>
                </sec:authorize>
            </tbody>
        </table> 
    </div>             
</div>
<div class="col-xs-12 lnbrk"></div>     
</div>
 <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
           <c:if test="${model.mostrarVentana}">
               <script>
                   getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
               </script>
           </c:if>
            
    </body>
</html>