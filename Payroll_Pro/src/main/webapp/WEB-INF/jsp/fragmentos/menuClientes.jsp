<%-- 
    Document   : menuClientes
    Created on : 10/11/2016, 01:06:42 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="controlsUp">   
    <sec:authorize access="hasAnyRole('Ver_Todo','Clientes')">
        <div class="btn-group " role="group" aria-label="...">
            <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Clientes')">
                <button type="button" class="btn btn-default redireccionar hidden-xs" value="${pageContext.request.contextPath}/cliente/registro-cliente.htm">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Agregar cliente
                </button>    
            </sec:authorize>
            <button type="button" class="btn btn-default redireccionar" value="${pageContext.request.contextPath}/cliente/todos-los-clientes.htm">
            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Ver todos
        </button>               
        </div>
            <sec:authorize access="hasAnyRole('Ver_Todo','Buscar_Entre_Todos_Los_Clientes')">
            <div class="btn-group hidden-xs hidden-sm" role="group">
                <form class="navbar-form navbar-left">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon" id="addonBcr"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></span>
                            <input type="text" class="form-control text-criteria" placeholder="Buscar Cliente" id="addonBcr" title="Buscar por alguna información del cliente">                      
                            <div class="input-group-btn">
                                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Buscar por <span class="caret"></span></button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li value="${pageContext.request.contextPath}/cliente/buscar-por/Nombre/dato/"><a class="dropdown-option" href="#" title="Nombre de la empresa" >Nombre de la empresa</a></li>
                                    <li value="${pageContext.request.contextPath}/cliente/buscar-por/Represetante/dato/"><a class="dropdown-option" href="#" title="Represetante legal">Representante legal</a></li>
                                    <li value="${pageContext.request.contextPath}/cliente/buscar-por/RFC/dato/"><a  class="dropdown-option"href="#" title="RFC">RFC</a></li>
                                </ul>
                              </div>
                        </div> 
                    </div>
                  </form>
            </div> 
            </sec:authorize>
    </sec:authorize>
</div>