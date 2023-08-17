<%-- 
    Document   : cambiarAcceso
    Created on : 1/02/2017, 06:28:04 PM
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
<h2 class="selectAction">Mi usuario</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <div class="col-xs-12 col-sm-5 col-sm-offset-3">
        <div class="row">
            <div class="col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon" id="">Nombre</span>
                        <label class="lblInputText">&nbsp;&nbsp;${model.usuario.nombre.toUpperCase()}&nbsp;&nbsp;</label>
                    </div>            
            </div>
            <div class="col-xs-12 lnbrk"></div>   
            <div class="col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon" id="">&nbsp;Correo&nbsp;</span>
                        <label class="lblInputText">&nbsp;&nbsp;${model.usuario.correoElectronico.toUpperCase()}&nbsp;&nbsp;</label>
                    </div>            
            </div>            
            <div class="col-xs-12 lnbrk"></div>   
            <div class="col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon" id="">&nbsp;&nbsp;&nbsp;&nbsp;Rol&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <label class="lblInputText">&nbsp;&nbsp;${model.rol.nombre.toUpperCase()}&nbsp;&nbsp;</label>
                    </div>            
            </div>
            <div class="col-xs-12 lnbrk"></div> 
            <form method="post" action="${pageContext.request.contextPath}/sistema/mi--acceso.htm">  
                <div class="col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon" id="">&nbsp;&nbsp;&nbsp;&nbsp;Nueva Contraseña&nbsp;&nbsp;&nbsp;</span>
                            <input type="password" name="valueOne" minlength="6" autocomplete="off" required>
                        </div>            
                </div>
                <div class="col-xs-12 lnbrk"></div>  
                    <div class="col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon" id="">Confirmar Contraseña</span>
                                <input type="password" name="valueTwo" minlength="6" autocomplete="off" required>
                            </div>            
                    </div>
                    <div class="col-xs-12 lnbrk"></div>   
                    <div class="col-xs-12">
                    <div class="col-xs-12 col-sm-6 col-sm-offset-4">
                        <button id="selectAllOptions" type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                            <b>Guardar</b>
                        </button>
                    </div>      
                    </div>
            </form>
            <div class="col-xs-12 lnbrk"></div>   
        </div>
      
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