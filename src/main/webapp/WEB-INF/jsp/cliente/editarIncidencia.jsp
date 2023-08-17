<%-- 
    Document   : editarIncidencia
    Created on : 7/12/2016, 09:47:38 AM
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
        <title>Editar Inicidencia</title>
    </head>
    <body>
       <!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
        <%@include file="../fragmentos/menu.jsp" %>
        <!-- ==================================================== cuerpo de la página ================================================================== -->
    <div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-queen" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Clientes      
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
<%@include file="../fragmentos/menuClientes.jsp" %>
<h2 class="selectAction">Edición de incidencia</h2>
    <div class="col-xs-12">
        <span title="Cliente" class="glyphicon glyphicon-queen" aria-hidden="true"></span>&nbsp;&nbsp;<span id="nmbrDlClnt">${model.incidencia.clienteObj.nombreEmpresa}</span>
    </div>
    <div class="col-xs-12">
        RFC&nbsp;&nbsp;&nbsp;<span id="">${model.incidencia.clienteObj.rfc}</span>
    </div>       
    <div class="col-xs-12">
        <span title="Colaborador" class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;<span ><b>${model.incidencia.agremiadoObj.datosPersonales.nombre}&nbsp;${model.incidencia.agremiadoObj.datosPersonales.apellidoPaterno}&nbsp;${((model.incidencia.agremiadoObj.datosPersonales.apellidoMaterno==null)?"":model.incidencia.agremiadoObj.datosPersonales.apellidoMaterno)}&nbsp;</b></span>
    </div>
    <div class="col-xs-12">
        RFC&nbsp;&nbsp;&nbsp;<span id="">${model.incidencia.agremiadoObj.datosPersonales.rfc}</span>
    </div> 
<div class="col-xs-12 lnbrk"></div>
<div class="row" id="divFrmEditarIncidencia">
    <h3>Nombre del usuario</h3>
    <div class="row">
        
        <form method="post" name="frmDtrNNcdnc" action="${pageContext.request.contextPath}/cliente/actualizar-incidencia.htm" modelAttribute="incidencia">
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                        <input type="hidden" name="idIncidencia" value="${model.incidencia.idIncidencia}">
                        <input type="hidden" name="agremiado" value="${model.incidencia.agremiadoObj.idAgremiado}">
                        <input type="hidden" name="tipoInc" value="${model.incidencia.tipoIncidenciaObj.idTipoIncidencia}">
                        <input type="hidden" name="idCliente" value="${model.incidencia.clienteObj.idCliente}">
                        <input type="hidden" name="idAgremiado" value="${model.incidencia.agremiadoObj.idAgremiado}">
                      <span class="input-group-addon" id="fchDRgstr_ncdnc">Fecha de registro</span>
                      <input type="text" value="${model.incidencia.fechaRegistro}" class="form-control" placeholder="Campo requerido" aria-describedby="fchDRgstr" name="fchDRgstr" id="fchDRgstrID_ncdnc"  readonly title="Fecha de registro de la incidencia">
                    </div>                
                </div> 
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="fchDLNcdnc_ncdnc">Fecha de la incidencia</span>
                      <input type="date" value="${model.incidencia.fechaIncidencia}"  class="form-control" placeholder="Campo requerido" aria-describedby="fchDLNcdnc" name="fchDLNcdnc" id="fchDLNcdncID_ncdnc" required title="Fecha en que ocurrió la incidencia">
                    </div>                 
                </div> 
                <div class="col-xs-8 col-sm-8">
                    <div class="input-group">
                      <span class="input-group-addon" id="tpDNcdnc_ncdnc">Tipo de incidencia</span>
                      <select name="tipoIncidenciaOb" class="form-control" placeholder="Campo requerido" aria-describedby="tpDNcdnc"  required id="tpDNcdncID_ncdnc" disabled>
                      <c:forEach items="${model.tipoIncidencias}" var="tipoIncidencia" >
                          <c:choose>
                              <c:when test="${tipoIncidencia.idTipoIncidencia == model.incidencia.tipoIncidenciaObj.idTipoIncidencia}">
                                  <option title="(${(tipoIncidencia.criterio)?"+":"-"}) ${(tipoIncidencia.dias)?" Día":" $"}" selected value="${tipoIncidencia.idTipoIncidencia}">${tipoIncidencia.descripcion}</option>                          
                              </c:when>
                              <c:otherwise>
                                    <option  title="(${(tipoIncidencia.criterio)?"+":"-"}) ${(tipoIncidencia.dias)?" Día":" $"}"  value="${tipoIncidencia.idTipoIncidencia}">${tipoIncidencia.descripcion}</option>                          
                              </c:otherwise>
                          </c:choose>                  
                      </c:forEach>
                    </select>
                    </div>                
                </div> 
                <div class="col-xs-4 col-sm-4">
                    <div class="input-group">
                      <span class="input-group-addon" id="cntdd_ncdnc">Cantidad</span>
                      <input type="text" value="${model.incidencia.cantidad}"  class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="cntdd" name="cantidad" id="cntddID_ncdnc" required title="Cantidad correpondiente a la incidencia" minlength="1" maxlength="10" autocomplete="off"  pattern="\d+(\.\d{2})*">
                      <c:forEach items="${model.tipoIncidencias}" var="tipoIncidencia" >
                          <c:if test="${tipoIncidencia.idTipoIncidencia == model.incidencia.tipoIncidenciaObj.idTipoIncidencia}">
                                <span class="input-group-addon" id="tpDNcdncID_ncdnc_txt">(${(tipoIncidencia.criterio)?"+":"-"}) ${(tipoIncidencia.dias)?" Día":" $"}</span>
                           </c:if>
                      </c:forEach>
                    </div>                  
                </div> 
                <div class="col-xs-12">
                    <div class="input-group">
                      <span class="input-group-addon" id="cmntrs_ncdnc">Comentarios</span>
                      <input type="text" value="${model.incidencia.comentarios}"  class="form-control firstLetterText" placeholder="Comentarios correpondientes a la incidencia" aria-describedby="cmntrs" name="comentarios" id="cmntrsID_ncdnc"  title="Comentarios correpondientes a la incidencia" maxlength="250" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+([,]*\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*"> 
                    </div>                  
                </div>
                <div class="col-xs-12 lnbrk"></div>
                <div class="col-xs-12 col-sm-3 col-sm-offset-2">
                    <sec:authorize access="hasAnyRole('Ver_Todo','Editar_Incidencias_Del_Cliente')">
                    <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                        <b>Guardar</b>
                    </button>
                    </sec:authorize>
                </div>
                <div class="col-xs-12 col-md-3 col-sm-offset-1">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/cliente/incidencias/${model.incidencia.clienteObj.idCliente}.htm" id="btnOutEditarIncidencia">
                        <b>Salir</b>
                    </button>
                </div>
        </form>
        
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