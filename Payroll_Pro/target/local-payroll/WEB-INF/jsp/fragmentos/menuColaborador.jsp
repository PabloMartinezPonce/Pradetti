<%-- 
    Document   : menuColaborador
    Created on : 3/05/2017, 04:45:45 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>
<div class="controlsUp">
<sec:authorize access="hasAnyRole('Colaboradores')">
    <div class="btn-group " role="group" aria-label="...">        
        <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador')">        
            <!--<button type="button" class="btn btn-default redireccionar" value="${pageContext.request.contextPath}/colaboradores/nuevo-colaborador.htm">-->
            <button type="button" class="btn btn-default redireccionar" value="${pageContext.request.contextPath}/colaborador/registrar-colaborador.htm">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaborador
            </button>
        </sec:authorize>
        <button type="button" class="btn btn-default redireccionar" value="${pageContext.request.contextPath}/colaboradores/activos.htm">
            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores Activos
        </button>
        <div class="btn-group" role="group">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Movimientos
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
        <sec:authorize access="hasAnyRole('Altas_En_Proceso')"> 
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/altas-en-proceso.htm">
                        Altas en proceso
                    </a>
                </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('Altas_Solicitadas')"> 
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/altas-solicitadas.htm">
                        Altas solicitadas
                    </a>
                </li>
        </sec:authorize>
                <li role="separator" class="divider"></li> 
        <sec:authorize access="hasAnyRole('Bajas_Pendientes')">      
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/bajas-pendientes.htm">
                        Bajas pendientes
                    </a>
                </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('Bajas_Sin_Firmar')">  
        <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/baja-sin-firmar.htm">
                        Bajas sin firmar
                    </a>
        </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('Bajas_Por_Finalizar')">   
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/bajas-por-finalizar.htm">
                        Bajas por finalizar
                    </a>
                </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('Bajas_Solicitadas')">   
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/bajas-solicitadas.htm">
                        Bajas solicitadas
                    </a>
                </li>
        </sec:authorize>
                <li role="separator" class="divider"></li>
        <sec:authorize access="hasAnyRole('Contratos_Por_Vencer')">   
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/contratos-por-vencer.htm">
                        Contratos por vencer
                    </a>
                </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('Renovaciones_Solicitadas')">   
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/renovaciones-solicitadas.htm">
                        Renovaciones solicitadas
                    </a>
                </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('Solicitudes_De_Renovacion')">   
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/solicitudes-de-renovacion.htm">
                        Solicitudes de renovación
                    </a>
                </li>
        </sec:authorize>
                <li role="separator" class="divider"></li>
        <sec:authorize access="hasAnyRole('Expedientes_Sin_Contrato')">  
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/expedientes-sin-contrato.htm">
                        Expedientes sin contrato
                    </a>
                </li>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('Expedientes_Por_Completar')">  
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/expedientes-por-completar.htm">
                        Expedientes por completar
                    </a>
                </li>
        </sec:authorize>
                <li role="separator" class="divider"></li>
        <sec:authorize access="hasAnyRole('Bajas')">  
                <li>
                    <a href="${pageContext.request.contextPath}/colaboradores/colaboradores-dados-de-baja.htm">
                        Colaboradores dados de baja
                    </a>
                </li>
        </sec:authorize>
            </ul>
        </div>                
    </div>
</sec:authorize>                       
</div>