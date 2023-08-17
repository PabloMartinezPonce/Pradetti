<%-- 
    Document   : menuSistema
    Created on : 10/11/2016, 01:12:36 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-xs-12 sideMenuSis">
    <div class="sistemaContainer" >
        <sec:authorize access="hasAnyRole('Sistema')">
        <nav class="navbar nav-sistema hidden-xs hidden-sm">

              <!-- Collect the nav links, forms, and other content for toggling -->
              <div class="collapse navbar-collapse" id="navbar-sistema">
                <ul class="nav navbar-nav">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Bitacora</a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/sistema/mi-bitacora.htm">Mi bitacora</a></li>
                      <li role="separator" class="divider"></li>
                    <sec:authorize access="hasAnyRole('Bitacora')">
                        <li><a href="${pageContext.request.contextPath}/sistema/ver-bitacora.htm">Ver Bitacora</a></li>
                    </sec:authorize>
                     <sec:authorize access="hasAnyRole('Bitacora_Correos_Enviados')">
                        <li><a href="${pageContext.request.contextPath}/sistema/bitacora-correos.htm">Bitacora Envio Correos</a></li>
                    </sec:authorize> 
                    </ul>
                  </li>
                </ul>
            <sec:authorize access="hasAnyRole('Contrados_Generados_Contratista_Cliente','Crear_Contrato_Para_Colaboradores','Crear_Contrato_Para_Clientes','Ver_Contratos_Creados')">
                <ul class="nav navbar-nav">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Contratos</a>
                    <ul class="dropdown-menu">
                     <sec:authorize access="hasAnyRole('Contrados_Generados_Contratista_Cliente')">
                        <li><a href="${pageContext.request.contextPath}/sistema/contratos-generados-contratista-cliente.htm">Contratos generados con clientes</a></li>
                     </sec:authorize>
                      <li role="separator" class="divider"></li>
                     <sec:authorize access="hasAnyRole('Crear_Contrato_Para_Colaboradores')">
                        <li><a href="${pageContext.request.contextPath}/sistema/nuevo-contrato-colaboradores.htm">Crear contrato para Colaboradores </a></li>
                     </sec:authorize>
                     <sec:authorize access="hasAnyRole('Crear_Contrato_Para_Clientes')">
                        <li><a href="${pageContext.request.contextPath}/sistema/nuevo-contrato-contratistas.htm">Crear contrato para Clientes </a></li>
                     </sec:authorize>
                      <li role="separator" class="divider"></li>
                     <sec:authorize access="hasAnyRole('Ver_Contratos_Creados')">
                        <li><a href="${pageContext.request.contextPath}/sistema/contratos-creados.htm">Contratos creados</a></li>
                     </sec:authorize>
                     <li role="separator" class="divider"></li>
                     <sec:authorize access="hasAnyRole('Ver_Contratos_Creados')">
                        <li><a href="${pageContext.request.contextPath}/sistema/registrar-periodo-fondo-ahorro.htm">Agregar período F.A.</a></li>
                     </sec:authorize>
                     <sec:authorize access="hasAnyRole('Ver_Contratos_Creados')">
                        <li><a href="${pageContext.request.contextPath}/sistema/periodos-fondo-ahorro.htm">Períodos F.A. existentes</a></li>
                     </sec:authorize>
                    </ul>
                  </li>
                </ul>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('Documentos')">
                <ul class="nav navbar-nav" >
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Documentos</a>
                      <ul class="dropdown-menu">
                            <sec:authorize access="hasAnyRole('Catalogo_Documental')">
                                <li><a href="${pageContext.request.contextPath}/sistema/catalogo-documental.htm">Catálogo documental</a></li>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole('Documentos')">
                                <li role="separator" class="divider"></li>
                                <sec:authorize access="hasAnyRole('Registrar_Editar_Documentos')">
                                   <li><a href="${pageContext.request.contextPath}/sistema/registrar-documento.htm">Registrar un nuevo documento</a></li>
                                </sec:authorize>
                               <li><a href="${pageContext.request.contextPath}/sistema/documentos-por-modulo.htm">Documentos registrados</a></li>
                         </sec:authorize>
                      </ul>
                    </li>
                </ul>
                </sec:authorize>
               <sec:authorize access="hasAnyRole('Incidencias_Revisadas','Incidencias_Sin_Revisar')">
                      <ul class="nav navbar-nav">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Incidencias</a>
                    <ul class="dropdown-menu">
                     <sec:authorize access="hasAnyRole('Incidencias_Revisadas')">
                        <li><a href="${pageContext.request.contextPath}/sistema/incidencias-revisadas.htm">Incidencias Revisadas</a></li>
                     </sec:authorize>
                      <li role="separator" class="divider"></li>
                     <sec:authorize access="hasAnyRole('Incidencias_Sin_Revisar')">
                        <li><a href="${pageContext.request.contextPath}/sistema/incidencias-sin-revisar.htm">Incidencias Sin Revisar</a></li>
                     </sec:authorize>
                    </ul>
                  </li>
                </ul>
               </sec:authorize>
             <sec:authorize access="hasAnyRole('Recibos_Almacenados')">
                <ul class="nav navbar-nav">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Recibos</a>
                    <ul class="dropdown-menu">
                     <sec:authorize access="hasAnyRole('Recibos_Almacenados')">
                        <li><a href="${pageContext.request.contextPath}/sistema/nominas-registradas.htm">Historial de recibos</a></li>
                     </sec:authorize>
                     <sec:authorize access="hasAnyRole('Editar_Recibos_Registrados')">
                        <li><a href="${pageContext.request.contextPath}/sistema/editar-recibos-registrados.htm">Edición de recibos</a></li>
                     </sec:authorize>
                    </ul>
                  </li>
                </ul>
             </sec:authorize>
                <sec:authorize access="hasAnyRole('Agregar_Rol','Consultar_Rol','Editar_Rol')"> 
                <ul class="nav navbar-nav">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Roles</a>
                    <ul class="dropdown-menu">
                     <sec:authorize access="hasAnyRole('Agregar_Rol')">
                            <li><a href="${pageContext.request.contextPath}/sistema/nuevo-rol.htm">Nuevo rol</a></li>
                     </sec:authorize>
                      <li role="separator" class="divider"></li>
                     <sec:authorize access="hasAnyRole('Consultar_Rol')">
                        <li><a href="${pageContext.request.contextPath}/sistema/todos-los-roles.htm">Roles existentes</a></li>
                     </sec:authorize>
                    </ul>
                  </li>
                </ul>
                </sec:authorize>  
                     
            <sec:authorize access="hasAnyRole('Agregar_Usuario','Consultar_Usuario','Editar_Usuario')">
                <ul class="nav navbar-nav ">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Usuarios</a>
                    <ul class="dropdown-menu">
                     <sec:authorize access="hasAnyRole('Agregar_Usuario')">
                        <li><a href="${pageContext.request.contextPath}/sistema/registro-de-un-usuario.htm">Nuevo Usuario</a></li>
                     </sec:authorize>
                     <sec:authorize access="hasAnyRole('Consultar_Usuario')">
                        <li><a href="${pageContext.request.contextPath}/sistema/todos-los-usuarios.htm">Usuarios existentes</a></li>
                     </sec:authorize>
                        <li role="separator" class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/sistema/mi-usuario.htm">Mi usuario</a></li>
                    </ul>
                  </li>
                </ul>
            </sec:authorize>
                    
            <sec:authorize access="hasAnyRole('Agregar_Zona_Salarial','Consultar_Zona_Salarial','Editar_Zona_Salarial')">
                <ul class="nav navbar-nav">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Salariales</a>
                    <ul class="dropdown-menu">
                     <sec:authorize access="hasAnyRole('Agregar_Zona_Salarial')">
                        <li><a href="${pageContext.request.contextPath}/sistema/registrar-zona-salarial.htm">Nueva zona salarial</a></li>
                     </sec:authorize>
                     <sec:authorize access="hasAnyRole('Consultar_Zona_Salarial')">
                        <li><a href="${pageContext.request.contextPath}/sistema/zonas-salariales.htm">Zonas salariales existentes</a></li>
                     </sec:authorize>
                      <li role="separator" class="divider"></li>
                     <sec:authorize access="hasAnyRole('Agregar_Editar_SPU_TiposSPU')">
                        <li><a href="${pageContext.request.contextPath}/sistema/registrar-sup.htm">Agregar SUP</a></li>
                     </sec:authorize>
                     <sec:authorize access="hasAnyRole('SPU_TiposSPU')">
                        <li><a href="${pageContext.request.contextPath}/sistema/sup-registrados.htm">SUP existentes</a></li>
                     </sec:authorize>
                    </ul>
                  </li>
                </ul>
            </sec:authorize>
              </div><!-- /.navbar-collapse -->
          </nav>
        </sec:authorize>        
    </div>                    
</div>
