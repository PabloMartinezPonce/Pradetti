<%-- 
    Document   : registroSindicato
    Created on : 15/11/2016, 11:41:20 AM
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
<title>Payroll - Sindicatos </title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-bishop" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sindicatos       
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
<%@include file="../fragmentos/menuSindicatos.jsp" %>
<div class="col-xs-12 lnbrk"></div>
<h2 class="selectAction">${(model.edicion)?"Edición de un sindicato":"Registrar un sindicato"}</h2> 
<div class="row">
    <form name="formNgrsDNSndct" method="post" modelAttribute="sindicato" action="${pageContext.request.contextPath}/sindicato/datos-sindicato.htm" enctype="multipart/form-data">
        <div class="col-xs-12">
            <div class="input-group">
                <input type="hidden" name="idSindicato" value="${model.sindicato.idSindicato}">
                <input type="hidden" name="urlLogoIzquierda" value="${model.sindicato.urlLogoIzquierda}">
                <input type="hidden" name="urlLogoDerecha" value="${model.sindicato.urlLogoDerecha}">
              <span class="input-group-addon" id="nmbr">Nombre</span>
              <input type="text" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="nmbr" name="nombreSindicato" required title="Nombre del sindicato" value="${model.sindicato.nombreSindicato}" maxlength="500" autocomplete="off" minlength="5"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div>                  
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="crnm">Acrónimo</span>
              <input type="text" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="crnm" name="nombreCorto" required title="Nombre corto del sindicato" value="${model.sindicato.nombreCorto}" maxlength="200" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div>                  
        <div class="col-xs-12 col-md-4 col-md-offset-2">
            <div class="input-group">
              <span class="input-group-addon" id="rfc">RFC</span>
              <input type="text" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="rfc" name="rfc" required title="RFC del sindicato" value="${model.sindicato.rfc}" maxlength="15" autocomplete="off" minlength="15"   pattern="[A-Z]+([0-9A-Z]+)*">
            </div>
        </div>  
        <div class="col-xs-12 col-md-6">
            <div class="input-group">
              <span class="input-group-addon" id="prcpcns">Percepciones</span>
              <input type="text" class="form-control firstLetterText" placeholder="Campo requerido" aria-describedby="prcpcns" name="percepciones" required title="Percepciones del sindicato" value="${model.sindicato.percepciones}" maxlength="800" autocomplete="off" minlength="5"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div>   
        <div class="col-xs-12 col-md-6">
            <div class="input-group">
              <span class="input-group-addon" id="prcpcns">Deducciones</span>
              <input type="text" class="form-control firstLetterText" placeholder="Campo requerido" aria-describedby="prcpcns" name="deducciones" required title="Deducciones del sindicato" value="${model.sindicato.deducciones}" maxlength="800" autocomplete="off" minlength="5"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div> 
            <c:choose>
                <c:when test="${model.edicion}">
                    <div class="col-xs-12 col-sm-6">
                      <div class="input-group">
                        <span class="input-group-addon" id="status">El sindicato se encuentra activo dentro del sistema</span>
                        <input ${(model.sindicato.status) ? 'checked="checked"' : ''} value='Sí' name="statusSindicato" path="status" id="status" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                      </div>
                 </div>
                </c:when>
                <c:otherwise>
                      <div class="col-xs-12">
                      <div class="input-group">
                        <input value='Sí' name="statusSindicato"  type="hidden"  class="form-control">
                      </div>
                 </div>
                </c:otherwise>
            </c:choose>
        <div class="col-xs-12 lnbrk"></div>    
        <div class="col-xs-12" id="formControlsColaborador">
            <div class="row" >
                <table class="table table-hover">
                    <tr>
                        <th>Logo</th>
                        <th>Requerido</th>
                        <th>Existe</th>
                    </tr>
                    <tr>
                        <td>
                            <c:if test="${(model.sindicato.urlLogoIzquierda != null )}">
                                <button type="button" class="btn btn-default redireccionarVentana" value="${pageContext.request.contextPath}/sindicato/${model.sindicato.idSindicato}/logo/Izquierdo.htm" title="Ver">
                                    <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>
                                </button>
                            </c:if>
                        </td>
                        <td>Izquierdo</td>
                        <td><b>${(model.sindicato.urlLogoIzquierda != null )?"Sí":"No"}</b></td>
                        <td>
                            <input type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="rchvIzquierdo" ${(model.sindicato.urlLogoIzquierda == null )?"required":""} id="rchvIzquierdo" accept=".jpg,.jpeg|images/*"
                                   data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="${(model.sindicato.urlLogoIzquierda == null )?"Sin archivo seleccionado":model.sindicato.urlLogoIzquierda.substring(22)}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <c:if test="${(model.sindicato.urlLogoDerecha != null )}">
                                <button type="button" class="btn btn-default redireccionarVentana" value="${pageContext.request.contextPath}/sindicato/${model.sindicato.idSindicato}/logo/Derecho.htm" title="Ver">
                                    <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>
                                </button>
                            </c:if>
                        </td>
                        <td>Derecho</td>
                        <td><b>${(model.sindicato.urlLogoDerecha != null)?"Sí":"No"}</b></td>
                        <td>
                            <input  type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="rchvDerecho" ${(model.sindicato.urlLogoDerecha == null)?"required":""}  id="rchvDerecho" accept=".jpg,.jpeg|images/*"
                            data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="${(model.sindicato.urlLogoDerecha == null )?"Sin archivo seleccionado":model.sindicato.urlLogoDerecha.substring(22)}">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-xs-12 col-sm-4">
            <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
        </div>
            <div class="col-xs-12 col-sm-4">
        <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Sindicatos')">    
                <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                    <b>Guardar</b>
                </button>
        </sec:authorize>
            </div>
        <div class="col-xs-12 col-sm-4">
            <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sindicato/sindicatos.htm">
                <b>Cancelar</b>
            </button>
        </div>
</form>
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