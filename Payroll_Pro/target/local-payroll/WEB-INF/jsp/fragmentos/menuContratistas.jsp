<%-- 
    Document   : menuContratista
    Created on : 10/11/2016, 01:09:01 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="controlsUp">
<sec:authorize access="hasAnyRole('Ver_Todo','Contratistas')">    
    <div class="btn-group " role="group" aria-label="...">
<sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Contratistas')">  
            <button type="button" class="btn btn-default redireccionar hidden-xs" value="${pageContext.request.contextPath}/contratista/registro-contratista.htm">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Agregar contratista
            </button>
</sec:authorize>
      <button type="button" class="btn btn-default redireccionar" value="${pageContext.request.contextPath}/contratista/todos-los-contratistas.htm">
        <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Ver todos
    </button>               
    </div>
    <div class="btn-group hidden-xs hidden-sm" role="group">
        <form class="navbar-form navbar-left">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon" id="addonBcr"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></span>
                    <input type="text" class="form-control text-criteria" placeholder="Buscar Contratista" id="addonBcr" title="Buscar por alguna información del contratista">
                <div class="input-group-btn">
                    <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Buscar por <span class="caret"></span></button>
                    <ul class="dropdown-menu dropdown-menu-right">
                          <li value="${pageContext.request.contextPath}/contratista/buscar-por/Nombre/dato/"><a class="dropdown-option"  href="#">Nombre de la empresa</a></li>
                          <li value="${pageContext.request.contextPath}/contratista/buscar-por/Represetante/dato/"><a class="dropdown-option"  href="#">Representante legal</a></li>
                          <li value="${pageContext.request.contextPath}/contratista/buscar-por/RFC/dato/"><a class="dropdown-option"  href="#">RFC</a></li>
                        </ul>
                  </div>
                </div> 
            </div>
          </form>
    </div>
</sec:authorize>    
</div>
