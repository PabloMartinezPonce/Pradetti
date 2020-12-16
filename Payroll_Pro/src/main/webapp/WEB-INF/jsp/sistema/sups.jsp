<%-- 
    Document   : sups
    Created on : 7/12/2017, 04:33:05 PM
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
<h2 class="selectAction">Salarios únicos profesionales existentes</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer"> 
    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>&nbsp;&nbsp;Oficio&nbsp;&nbsp;</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;Descripción&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;Sector&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th>&nbsp;&nbsp;Pesos&nbsp;&nbsp;</th>
                <th>&nbsp;&nbsp;Fechas&nbsp;&nbsp;</th>
                <sec:authorize access="hasAnyRole('Agregar_Editar_SPU_TiposSPU')">
                <th>&nbsp;Opciones&nbsp;</th>
                </sec:authorize>
            </tr>
        </thead>
        <sec:authorize access="hasAnyRole('SPU_TiposSPU')">
        <tbody>
            <c:forEach items="${model.sups}" var="sup" >
                <tr>
                    <td style="vertical-align:middle">${sup.oficio}</td>
                    <td style="vertical-align:middle">${sup.descripcion}</td>
                    <td style="vertical-align:middle">
                        <c:forEach items="${model.tipossup}" var="tipo">
                            <c:if test="${tipo.idTipoSalarioUnicoProfesional==sup.tipoSUP}">
                                <b>${tipo.sector}</b>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td style="vertical-align:middle">$&nbsp;${sup.pesosDiarios}&nbsp;MXN</td>
                    <td><h6>
                        <table>
                            <tr>
                                <th>Fecha&nbsp;Inicio&nbsp;:&nbsp;</th>
                                <td><fmt:formatDate value="${sup.fechaInicio}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                            <tr>
                                <th>Fecha&nbsp;Termino&nbsp;:&nbsp;</th>
                                <td><fmt:formatDate value="${sup.fechaFin}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </table></h6>
                    </td>
                    <sec:authorize access="hasAnyRole('Agregar_Editar_SPU_TiposSPU')">
                    <td>
                       <form method="post" action="${pageContext.request.contextPath}/sistema/edicion-de-sup.htm">
                         <input type="hidden" name="salarioUnicoProfesional" value="${sup.idSalarioUnicoProfesional}">
                         <button type="submit" class="btn btn-default" title="Editar SUP">
                           <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                         </button>
                       </form>                        
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </tbody>
        </sec:authorize>
    </table> 
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