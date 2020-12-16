<%-- 
    Document   : crearNuevoContratoContratistas
    Created on : 4/01/2017, 05:38:38 PM
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
<h2 class="selectAction">${(model.edicion)?"Edición de un contrato para clientes":"Nuevo contrato para clientes"}</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <form method="post" id="formCrearNuevoContratoClientes" action="${pageContext.request.contextPath}/sistema/datos-contrato-contratista.htm">
        <div class="row">
            <div class="col-xs-12 col-sm-6">
                <div class="input-group">
                    <input type="hidden" name="idContrato" value="${model.contrato.idContrato}" id="idContrato">
                    <span class="input-group-addon" id="nmbrCntrt" title="Nombre del contrato">
                      &nbsp;&nbsp;&nbsp;<span class="" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;<b>Nombre del contrato</b>                      
                    </span>
                    <input value="${model.contrato.nombre}" type="text" id="nmbrCntrtID" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="nmbrCntrt" name="nmbrCntrt" required title="Nombre del contrato" maxlength="60" autocomplete="off" minlength="5"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
            <div class="col-xs-12 col-sm-6">
                <div class="input-group">
                  <span class="input-group-addon" id="dcmntRqrd">El documento se encuentra activo</span>
                  <input id="estatus" ${(model.contrato.activo)?'checked="checked"' : ''} name="estatus"type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                </div>                    
            </div>                
            <div class="col-xs-12 lnbrk"></div>
            <div class="col-xs-6 col-xs-offset-1 sc-toolbar-container toolbar" id="cntrlsCrrNvCntrt_Format" hidden>
                <div class="btn-group " role="group" aria-label="...">                                  
                    <button type="submit" class="btn btn-success btn-group btn-group-lg toolTip" title="Guardar">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                    </button>
                </div>
                <div class="btn-group " role="group" aria-label="...">
                    <button type="button" class="btn btn-default btn-group btn-group-lg btn-format-agreement toolTip" title="Negritas" value="b">
                        <span class="glyphicon glyphicon-bold" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-default btn-group btn-group-lg btn-format-agreement toolTip" title="Cursiva" value="i">
                        <span class="glyphicon glyphicon-italic" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-default btn-group btn-group-lg btn-format-agreement toolTip" title="Subrayado" value="u">
                        <span class="glyphicon glyphicon-text-color" aria-hidden="true"></span>
                    </button>
                </div>
                <div class="btn-group " role="group" aria-label="...">
                    <button type="button" class="btn btn-default btn-group btn-group-lg btn-alignment-agreement toolTip" title="Alinear a la izquierda" value="L">
                        <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-default btn-group btn-group-lg btn-alignment-agreement toolTip" title="Alinear al centro" value="C">
                        <span class="glyphicon glyphicon-align-center" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-default btn-group btn-group-lg btn-alignment-agreement toolTip" title="Alinear a la derecha" value="R">
                        <span class="glyphicon glyphicon-align-right" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-default btn-group btn-group-lg btn-alignment-agreement toolTip" title="Justificar" value="J">
                        <span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
                    </button>
                </div>
            </div>
            <div class="col-xs-12 lnbrk"></div>
        </div>            
        <div class="row">
            <div class="col-xs-9" id="agreementDoc">   
                <div class="form-group">
                  <label for="contract"><h3><span class="glyphicon glyphicon-console" aria-hidden="true"></span>&nbsp;Contrato</h3></label>
                  <textarea required name="contrato" class="form-control" rows="27" cols="90" id="aggremntArea" placeholder="Ingresa aquí el texto que contrendrá el contrato">${model.contrato.contrato}</textarea>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <h3><span class="glyphicon glyphicon-file" aria-hidden="true"></span>&nbsp;Contrato</h3>
                    </div>
                    <div class="col-xs-12">
                        <div id="viewDocument"></div>
                    </div>
                </div>
            </div>
            <div class="col-xs-3">
                <h3 class="centerMiddle">Campos dinámicos</h3>
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                  <div class="panel panel-default">
                    <div class="panel-heading panel-heading-colaborator" role="tab" id="headingOne">
                      <h4 class="panel-title panel-title-colaborator">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                          <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Del contratista
                        </a>
                      </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                      <div class="panel-body">
                        <c:forEach items="${model.DatosDelContratista}" var="contratista">
                            <button type="button" class="btn btn-component-colaborator" value="{{${contratista.key}}}">
                                ${contratista.value}
                            </button>                             
                        </c:forEach>
                      </div>
                    </div>
                  </div>
                  <div class="panel panel-default">
                    <div class="panel-heading panel-heading-client" role="tab" id="headingTwo">
                      <h4 class="panel-title panel-title-client">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                          <span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Del contratante
                        </a>
                      </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                      <div class="panel-body"> 
                        <c:forEach items="${model.DatosDelContratante}" var="contratante">
                        <button type="button" class="btn btn-component-client" value="{{${contratante.key}}}">
                            ${contratante.value}
                        </button>                            
                        </c:forEach>
                      </div>
                    </div>
                  </div>
                  <div class="panel panel-default">
                    <div class="panel-heading panel-heading-agreement" role="tab" id="headingThree">
                      <h4 class="panel-title panel-title-agreement">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                          <span class="glyphicon glyphicon-modal-window" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Del contrato
                        </a>
                      </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                      <div class="panel-body">
                        <c:forEach items="${model.DatosDelContratoEntreEmpresas}" var="contrato">
                            <button type="button" class="btn btn-component-agreement" value="{{${contrato.key}}}">
                                ${contrato.value}
                            </button>                             
                        </c:forEach>
                      </div>
                    </div>
                  </div>
                  <div class="panel panel-default">
                    <div class="panel-heading panel-heading-special" role="tab" id="headingZero">
                      <h4 class="panel-title panel-title-special">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseZero" aria-expanded="true" aria-controls="collapseZero">
                          <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Campos Especiales
                        </a>
                      </h4>
                    </div>
                    <div id="collapseZero" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                      <div class="panel-body">
                        <button type="button" class="btn btn-component-special-component" value="{{FIRMA_DEL_CONTRATISTA_Y_DEL_CONTRATANTE}}">
                            Firmas del contratista y <br> del contratante
                        </button> 
                        <button type="button" class="btn btn-component-special-component" value="{{FIRMA_DEL_TESTIGO_DEL_CONTRATISTA}}">
                            Firma del testigo del contratista
                        </button>
                        <button type="button" class="btn btn-component-special-component" value="{{FRIMA_DEL_TESTIGO_DEL_CONTRATANTE}}">
                            Firma del testigo del contratante
                        </button> 
                      </div>
                    </div>
                  </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="col-xs-12 lnbrk"></div>  
<div id="agremment-view">
</div> 
<div class="col-xs-12 lnbrk"></div>     
</div>
 <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
           <script>
               startTimeOutContratoParaClientes();
           </script>
           <c:if test="${model.mostrarVentana}">
               <script>
                   getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
               </script>
           </c:if>
            
    </body>
</html>
