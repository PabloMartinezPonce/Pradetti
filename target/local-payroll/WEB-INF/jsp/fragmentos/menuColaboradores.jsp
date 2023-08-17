<%-- 
    Document   : menuColaboradores
    Created on : 10/11/2016, 12:55:17 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="controlsUp">

    <div class="btn-group " role="group" aria-label="...">                
        <button type="button" class="btn btn-default redireccionar" value="${pageContext.request.contextPath}/agremiado/proceso-alta.htm">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Agregar colaborador
        </button>
        <button type="button" class="btn btn-default redireccionar" value="${pageContext.request.contextPath}/agremiado/agremiados.htm">
            <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Ver todos
        </button>
        <div class="btn-group" role="group">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Movimientos
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiados/Altas-en-proceso.htm">
                        Altas en proceso
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiados/Altas-solicitadas.htm">
                        Altas solicitadas
                    </a>
                </li><li role="separator" class="divider"></li>      
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiadosBajaPendiente.htm">
                        Bajas pendientes
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiadosBajaSinFirmar.htm">
                        Bajas sin firmar
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiadosBajaPorFinalizar.htm">
                        Bajas por finalizar
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiadosBajaSolicitada.htm">
                        Bajas solicitadas
                    </a>
                </li><li role="separator" class="divider"></li>
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiados-contratos-por-vencer.htm">
                        Contratos por vencer
                    </a>
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiados-solicitudes-renovacion.htm">
                        Solicitudes de renovación
                    </a>
                </li><li role="separator" class="divider"></li>
                <li>
                    <a href="#">
                        Colaboradores sin contrato
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiadosDadosDeBaja.htm">
                        Colaboradores dados de baja
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/agremiado/agremiados/Colaboradores-con-expediente-incompleto.htm">
                        Colaboradores con expediente incompleto
                    </a>
                </li>
            </ul>
        </div>                
    </div>
    <div class="btn-group hidden-xs hidden-sm" role="group">
        <form id="fomrMenuColaboradores" class="navbar-form navbar-left">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon" id="addonBcr"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></span>
                    <input id="colaboradorValue" onchange="cambio()" name="colaboradorValue" type="text" class="form-control text-criteria" placeholder="Buscar colaborador" id="addonBcr" title="Buscar por alguna información del colaborador">
                    <div class="input-group-btn">

                        <button type="button" class="btn  btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Buscar por <span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li value="Nombre"><a class="dropdown-option" onclick="enviarCriterio()">Nombre(s)</a></li>
                            <li value="RFC"><a class="dropdown-option"  onclick="enviarFormDatosDomicilio('${pageContext.request.contextPath}/agremiado/buscar-colaborador/RFC.htm', 'fomrMenuColaboradores')">RFC</a></li>
                            <li value="CURP"><a class="dropdown-option"  onclick="enviarFormDatosDomicilio('${pageContext.request.contextPath}/agremiado/buscar-colaborador/CURP.htm', 'fomrMenuColaboradores')">CURP</a></li>
                        </ul>
                    </div>
                </div> 
            </div>
        </form>
    </div>

</div>
<script>
    function enviarCriterio() {
        console.log("Hi!!!");
        var valueCrit = $('#colaboradorValue').val();
        console.log("valor === " + valueCrit);
        $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/agremiado/buscar-colaborador/Nombre/criterio/"+ valueCrit + ".htm",
        dataType: 'html',
        contentType: false,
        cache: false,
        processData: false,
        success: function () {

        }
    });
    }

</script>
