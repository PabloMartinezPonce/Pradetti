<%-- 
    Document   : vistaContratoCreado
    Created on : 2/01/2017, 05:11:30 PM
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
    <%@include file="../fragmentos/cabeceraKardex.jsp" %>
    <title>Vista de Contrato</title>
  </head>

  <body id="home">

    <div class="blog-masthead hidden-print">
      <div class="container">
        <nav class="blog-nav">
          <a class="blog-nav-item active" href="#home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Inicio</a>
          <a class="blog-nav-item" href="#" onclick="window.close();"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>&nbsp;Salir</a>
        </nav>
      </div>
    </div>
    <div class="container bodie">
        <div class="row">
         <div class="col-xs-2">
            <div class="row">
                <img alt="logo pradetti" src="${pageContext.request.contextPath}/frontend/img/logo.png" id="logoPradetti"/>
            </div>
        </div>
        <div class="col-xs-10">
            <div class="row">
                <div class="col-xs-12">
                    <h1 class="headland"><span class="glyphicon glyphicon-knight" aria-hidden="true"></span><b>&nbsp;&nbsp;&nbsp;Sistema</b></h1>
                </div>
                <div class="col-xs-12">
                    <h2 class="headland-two">
                        Contrato&nbsp;<i>DEMO</i>
                    </h2>
                </div>
            </div>
          </div>
        </div>
        <div class="row">
            <section class="section" >
                <div class="sec-header">Número de contrato&nbsp;:&nbsp;${model.contrato.idContrato}
                    <div class="btn-group" role="group" aria-label="...">
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="descriptor">
                                <span class="title">Nombre</span>
                                <span class="content lengthText" id="nmbr" ><b>&nbsp;${model.contrato.nombre}&nbsp;</b></span>                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="descriptor">
                                <span class="title">Creado el </span>
                                <span class="content lengthText" id="nmbr" >&nbsp;<b><fmt:formatDate value="${model.contrato.creado}" pattern="dd/MM/yyyy HH:mm" /></b>&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="descriptor">
                                <span class="title">Última vez que se modificó</span>
                                <span class="content lengthText" id="nmbr" >&nbsp;<b><fmt:formatDate value="${model.contrato.modificado}" pattern="dd/MM/yyyy HH:mm" /></b>&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">El contrato se encuentrá</span>
                                <span class="content lengthText" id="nmbr" >&nbsp;<b>${(model.contrato.activo)?"Activo":"Inactivo"}</b>&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="descriptor">
                                <span class="title">El contrato se encuentra disponible para </span>
                                <span class="content lengthText" >&nbsp;<b>${(model.contrato.contratoDeColaborador)?"Colaboradores":"Clientes"}</b>&nbsp;</span>                                
                            </div>
                        </div>
                            <div class="col-xs-12"><br>
                            <div class="descriptor">
                                <span class="title">Contrato DEMO</span>                              
                            </div>
                        </div>                        
                        
                    </div>
                </div>
                                <div class="sec-footer" id="viewDocument">${model.contrato.contrato}</div>
            </section>          
            
                       
        </div>
    </div>
    <!-- ==================================================== Sección de  footer ================================================================== -->
           <%@include file="../fragmentos/pieKardex.jsp" %>
           <c:if test="${model.mostrarVentana}">
               <script>
                   getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
               </script>
           </c:if>
  </body>
</html>