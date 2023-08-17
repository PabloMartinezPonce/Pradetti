<%-- 
    Document   : generarContrato
    Created on : 15/11/2016, 11:26:42 AM
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
    <title>Generar contrato</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <c:choose>
        <c:when test="${model.edicion}">
            <span class="glyphicon glyphicon-knight" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sistema 
        </c:when>
        <c:otherwise>
            <span class="glyphicon glyphicon-king" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Contratistas 
        </c:otherwise>
    </c:choose>     
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">
<c:choose>
          <c:when  test="${model.edicion}">       
                <%@include file="../fragmentos/menuSistema.jsp" %>           
          </c:when>
          <c:otherwise>
                <%@include file="../fragmentos/menuContratistas.jsp" %>                 
          </c:otherwise>
      </c:choose>
<div class="col-xs-12 lnbrk"></div>
<h2 class="selectAction">${(model.edicion)?"Editar un contrato":"Generar un nuevo contrato"}</h2>
  <div class="row">
      <c:choose>
          <c:when test="${model.edicion}">
                <form action="${pageContext.request.contextPath}/sistema/contrato.htm" method="post" modelAttribute="contratoempresas">              
          </c:when>
          <c:otherwise>              
                <form action="${pageContext.request.contextPath}/contratista/contrato.htm" method="post" name="formGnrrNvCntrt" modelAttribute="contratoempresas" > 
          </c:otherwise>
      </c:choose>
        <div class="col-xs-12">
            <h3 class="subheading">Datos del Contrato</h3>
            <input type="hidden" value="${model.contrato.idContratoEmpresas}" name="idContratoEmpresas">
        </div> 
        <div class="col-xs-12 col-md-8">
            <h4>&nbsp;<span class="glyphicon glyphicon-king" aria-hidden="true" title="Contratista"></span>&nbsp;&nbsp;&nbsp;<b>${model.contratista.nombreEmpresa.toUpperCase()}</b></h4>
            <input type="hidden" name="idContratista" value="${model.contratista.idContratista}">
        </div>
        <div class="col-xs-12 col-md-4">
            <h4>RFC:&nbsp;<b>${model.contratista.rfc}</b></h4>
        </div>
        <div class="col-xs-12 col-md-8">
            <div class="input-group">
              <span class="input-group-addon" id="clnt">Cliente</span>
              <c:choose>
                  <c:when test="${model.edicion}">
                      &nbsp;&nbsp;<b>${model.cliente.nombreEmpresa.toUpperCase()}</b>
                      <input type="hidden" value="${model.cliente.idCliente}" name="clienteObj">
                      <input type="hidden" value="${model.contratista.idContratista}" name="contratistaObj">
                  </c:when>
                  <c:otherwise>
                        <select id="slctClntsNvCntrt" name="idCliente" class="form-control" placeholder="Campo requerido" aria-describedby="clnt"  required>
                            <option></option>
                            <c:forEach items="${model.clientes}" var="cliente">
                                <c:if test="${cliente.status}">
                                    <option value="${cliente.idCliente}" id="${cliente.rfc}">${cliente.nombreEmpresa}</option>  
                                 </c:if>
                            </c:forEach>
                          </select>                      
                  </c:otherwise>
              </c:choose>
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="rfc">RFC</span>
              <input value="${model.cliente.rfc}" type="text" class="form-control" placeholder="Campo requerido" aria-describedby="rfc"  required title="RFC del cliente" disabled id="rfcClntsNvCntrt" minlength="15" maxlength="15"   pattern="[A-Z]+([0-9A-Z]+)*">
            </div>
        </div>                   
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="fch">Fecha</span>
              <input value="${model.contrato.fecha}" type="date" class="form-control" placeholder="Campo requerido" aria-describedby="fch" name="fechaDelContrato" required title="Fecha del contrato">
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="prdDPg">Periodo de pago</span>
              <input value="${model.contrato.periodo}" type="text" class="form-control firstLetterText" placeholder="Campo requerido" aria-describedby="prdDPg" name="periodo" required title="Periodo de pago" maxlength="100" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="cmsn">Comisión</span>
              <input value="${model.contrato.comision}" type="text" class="form-control firstLetterText" placeholder="Campo requerido" aria-describedby="cmsn" name="comision" required title="Comisión" maxlength="100" autocomplete="off" minlength="1"   pattern="[0-9]+" min="1" max="100"> 
               <span class="input-group-addon" id="cmsn">%</span>
            </div>
        </div>               
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="fch">Informes</span>
              <input value="${model.contrato.informes}" type="text" class="form-control firstLetterText" placeholder="Escribir con letra" aria-describedby="fch" name="informes" required title="Periodo de entrega de los informes" maxlength="100" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="prdDPg">Evaluaciones</span>
              <input value="${model.contrato.evaluaciones}" type="text" class="form-control firstLetterText" placeholder="Escribir con letra" aria-describedby="prdDPg" name="evaluaciones" required title="Periodo de evaluaciones" maxlength="100" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="cmsn">Depósito</span>
              <input value="${model.contrato.deposito}" type="text" class="form-control firstLetterText" placeholder="Escribir con letra" aria-describedby="cmsn" name="deposito" required title="Pago a efectuar por el contratante" maxlength="100" autocomplete="off" minlength="1"    pattern="[0-9]+" min="1" max="500">
               <span class="input-group-addon" id="cmsn">Horas</span>
            </div>
        </div>
        <div class="col-xs-12 col-md-6">
            <div class="input-group">
              <span class="input-group-addon" id="cmsn">Tiempo del contrato</span>
              <input value="${model.contrato.tiempoDelContrato}"  type="text" class="form-control firstLetterText" placeholder="Escribir con letra" aria-describedby="cmsn" name="tiempoDelContrato" required title="Tiempo del presente contrato"  maxlength="100" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
           </div>
        </div>
        <div class="col-xs-12 col-md-6">
            <div class="input-group">
              <span class="input-group-addon" id="cmsn">Nombre del contrato</span>
              <input  value="${model.contrato.nombreContrato}" disabled  type="text" class="form-control" placeholder="Escribir con letra" aria-describedby="cmsn"  title="Nombre del presente contrato" autocomplete="off" >
              <input  value="${model.contrato.nombreContrato}"  type="hidden" name="nombreContrato">
           </div>
        </div>
        <div class="col-xs-12 lnbrk"></div>
        <div class="row">
               <div class="col-xs-6">
        <div class="col-xs-12">
            <h3 class="subheading">Testigo del contratista</h3>
        </div>
        <div class="col-xs-12">
            <div class="input-group">
              <span class="input-group-addon" id="nmbrCntst">Nombre</span>
              <input value="${model.contrato.tcontratistaNombre}" type="text" class="form-control capitalizeText" placeholder="Campo requerido" aria-describedby="nmbrCntst" name="tcontratistaNombre" required title="Nombre completo del testigo del contratista" maxlength="250" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div>
        <div class="col-xs-12">
            <div class="input-group">
              <span class="input-group-addon" id="cpcnCntst">Ocupación</span>
              <input value="${model.contrato.tcontratistaOcupacion}" type="text" class="form-control firstLetterText" placeholder="Campo requerido" aria-describedby="cpcnCntst" name="tcontratistaOcupacion" required title="Ocupación del testigo del contratista" maxlength="120" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div> 
        <div class="col-xs-12">
            <div class="input-group">
              <span class="input-group-addon" id="dmclCntst">Originario</span>
              <input value="${model.contrato.tcontratistaOrigen}" type="text" class="form-control capitalizeText" placeholder="Campo requerido" aria-describedby="dmclCntst" name="tcontratistaOrigen" required title="Origen del testigo del contratista" maxlength="350" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([,]*\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+[.]*)*">
            </div>
        </div> 
               </div>
               <div class="col-xs-6">
        <div class="col-xs-12">
            <h3 class="subheading">Testigo del contratante</h3>
        </div>
        <div class="col-xs-12">
            <div class="input-group">
              <span class="input-group-addon" id="nmbrClnt">Nombre</span>
              <input value="${model.contrato.tclienteNombre}" type="text" class="form-control capitalizeText" placeholder="Campo requerido" aria-describedby="nmbrClnt" name="tclienteNombre" required title="Nombre completo del testigo del contratante" maxlength="250" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div>
        <div class="col-xs-12">
            <div class="input-group">
              <span class="input-group-addon" id="cpcnClnt">Ocupación</span>
              <input value="${model.contrato.tclienteOcupacion}" type="text" class="form-control firstLetterText" placeholder="Campo requerido" aria-describedby="cpcnClnt" name="tclienteOcupacion" required title="Ocupación del testigo del contratante" maxlength="120" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div> 
        <div class="col-xs-12">
            <div class="input-group">
              <span class="input-group-addon" id="dmclClnt">Originario</span>
              <input value="${model.contrato.tclienteOrigen}" type="text" class="form-control capitalizeText" placeholder="Campo requerido" aria-describedby="dmclClnt" name="tclienteOrigen" required title="Origen completo del testigo del contratante" maxlength="350" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([,]*\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+[.]*)*">
            </div>
        </div>
               </div>
        </div>            
        <div class="col-xs-12">
            <h3 class="subheading">Salarios profesionales únicos</h3>
        </div>          
        <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-6"><h4 class="titulo-h4">SPUs disponibles</h4></div>
            <div class="col-xs-6"><h4 class="titulo-h4">SPUs del contrato</h4></div>
            <div class="col-xs-5">
                <div class="containerListPermisos">
                    <select name="origenSPU" id="origenPermisos" multiple="multiple" size="${model.permisos.size()}">
                        <c:forEach items="${model.spus}"  var="spu">
                            <option value="${spu.idSalarioUnicoProfesional}">${spu.oficio}&nbsp;($&nbsp;${spu.pesosDiarios}&nbsp;MXN)</option>                            
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-xs-2 centerMiddle" title="Pasar todos los SPUs">
                <h2><span class="glyphicon glyphicon-transfer" aria-hidden="true"></span></h2> 
            </div>
            <div class="col-xs-5">
                <div class="containerListPermisos">   
                    <select name="destinoSPU" id="destinoPermisos" multiple="multiple" size="4">
                        <c:forEach items="${model.contrato.salarioUnicoProfesionalList}"  var="spuh">
                            <option value="${spuh.idSalarioUnicoProfesional}">${spuh.oficio}&nbsp;($&nbsp;${spuh.pesosDiarios}&nbsp;MXN)</option>
                        </c:forEach></select>
                </div>
            </div>
        </div>
            </div>
        </div>
        <!-- -->
        <div class="col-xs-12">
            <h3 class="subheading">Servicios</h3>
        </div>          
        <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-12">
         <div class="row">
            <div class="col-xs-6"><h4 class="titulo-h4">Servicios disponibles</h4></div>
            <div class="col-xs-6"><h4 class="titulo-h4">Servicios del contrato</h4></div>
            <div class="col-xs-5">
                <div class="containerListPermisos">
                    <select name="origenServicios" id="origenServicios" multiple="multiple" size="${model.servicios.size()}">
                        <c:forEach items="${model.servicios}"  var="servicios">
                            <option value="${servicios.idCatalogo}">${servicios.nombreDocumento}&nbsp;</option>                            
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-xs-2 centerMiddleService" title="Pasar todos los Servicios">
                <h2><span class="glyphicon glyphicon-transfer" aria-hidden="true"></span></h2> 
            </div>
            <div class="col-xs-5">
                <div class="containerListPermisos">   
                    <select name="destinoServicios" id="destinoServicios" multiple="multiple">
                        <c:forEach items="${model.contrato.catalogoDocumentalList}"  var="catdoc">
                            <option value="${catdoc.idCatalogo}">${catdoc.nombreDocumento}&nbsp;</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
            </div>
        </div>
        <!-- -->
        <div class="col-xs-12">
            <h3 class="subheading">Periodos para el fondo de ahorro</h3>
        </div>          
        <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-12">
         <div class="row">
            <div class="col-xs-6"><h4 class="titulo-h4">Periodos disponibles</h4></div>
            <div class="col-xs-6"><h4 class="titulo-h4">Periodos del contrato</h4></div>
            <div class="col-xs-5">
                <div class="containerListPermisos">
                    <select name="origenServicios" id="origenServicios" multiple="multiple" size="${model.servicios.size()}">
                        <c:forEach items="${model.servicios}"  var="servicios">
                            <option value="${servicios.idCatalogo}">${servicios.nombreDocumento}&nbsp;</option>                            
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-xs-2 centerMiddleService" title="Pasar todos los Servicios">
                <h2><span class="glyphicon glyphicon-transfer" aria-hidden="true"></span></h2> 
            </div>
            <div class="col-xs-5">
                <div class="containerListPermisos">   
                    <select name="destinoServicios" id="destinoServicios" multiple="multiple">
                        <c:forEach items="${model.contrato.catalogoDocumentalList}"  var="catdoc">
                            <option value="${catdoc.idCatalogo}">${catdoc.nombreDocumento}&nbsp;</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
            </div>
        </div>
        <!-- -->
        <div class="col-xs-12">
            <h3 class="subheading">Contratos</h3>
        </div>          
        <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-12">
         <div class="row">
            <div class="col-xs-6"><h4 class="titulo-h4">Contratos disponibles</h4></div>
            <div class="col-xs-6"><h4 class="titulo-h4">Contratos asignados</h4></div>
            <div class="col-xs-5">
                <div class="containerListPermisos">
                    <select name="origenContratos" id="origenContratos" multiple="multiple" size="${model.contratos.size()}">
                        <c:forEach items="${model.contratos}"  var="contratos">
                            <option value="${contratos.idContrato}">${contratos.nombre}&nbsp;</option>                            
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-xs-2 centerMiddleContratos" title="Pasar todos los Contratos">
                <h2><span class="glyphicon glyphicon-transfer" aria-hidden="true"></span></h2> 
            </div>
            <div class="col-xs-5">
                <div class="containerListPermisos">   
                    <select name="destinoContratos" id="destinoContratos" multiple="multiple">
                        <c:forEach items="${model.contrato.contratosList}"  var="contratoList">
                            <option title="${contratoList.nombre}" value="${contratoList.idContrato}">${contratoList.nombre}&nbsp;</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
            </div>
        </div>
        <!-- -->
        <div class="col-xs-12">
            <h3 class="subheading">Generar mediante el contrato</h3>
        </div>
        <div class="col-xs-12 col-md-9">
            <div class="input-group">
              <span class="input-group-addon" id="dmclClnt">Contratos Disponibles</span>
                    <select id="slctClntsNvCntrt" name="idPlantillaContrato" class="form-control" placeholder="Campo requerido" aria-describedby="clnt"  required>
                        <option></option>
                        <c:forEach items="${model.contratista.contratoList}" var="contrato">
                            <c:if test="${contrato.activo&&(!contrato.contratoDeColaborador)}">
                                <option value="${contrato.idContrato}">${contrato.nombre}</option>  
                             </c:if>
                        </c:forEach>
                      </select>              
            </div>
        </div>
            <sec:authorize access="hasAnyRole('Ver_Todo','Contrato_Contratista_Cliente')">  
                    <c:if test="${model.edicion}">
                      <div class="col-xs-12 col-md-3">
                          <div class="input-group">
                            <span class="input-group-addon" id="dmclClnt">Contrato</span>
                              <button type="button" class="btn btn-default redireccionarVentana toolTip" title="Descargar contrato sin firmas" value="${pageContext.request.contextPath}/sistema/contrato-contratista-cliente/${model.contrato.idContratoEmpresas}/pdf.htm">
                                  <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>
                              </button>                      
                          </div>            
                      </div>
                  </c:if>
            </sec:authorize>
<div class="col-xs-12 lnbrk"></div>
        <div class="col-xs-12 col-sm-4">
            <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
        </div>
            <div class="col-xs-12 col-sm-4">
                    <sec:authorize access="hasAnyRole('Ver_Todo','Contrato_Contratista_Cliente','Editar_Contratos_Generados')">  
                        <button id="selectAllOptions" type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                            <b>Guardar</b>
                        </button>
                    </sec:authorize>
            </div>
        <div class="col-xs-12 col-sm-4">
            
      <c:choose>
          <c:when test="${model.edicion}">   
              <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/contratos-generados-contratista-cliente.htm">
                    <b>Cancelar</b>
                </button>           
          </c:when>
          <c:otherwise>                
                <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/contratista/contratistas.htm">
                    <b>Cancelar</b>
                </button>
          </c:otherwise>
      </c:choose>
        </div> 
    </form>
    </div>
<div class="col-xs-12 lnbrk"></div>    
</div>
 <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
                       
    </body>
</html>
    