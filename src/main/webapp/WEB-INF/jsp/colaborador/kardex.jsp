<%-- 
    Document   : kardex
    Created on : 12/05/2017, 11:22:04 AM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>

<%response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Expires", "0");%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib uri="/WEB-INF/tld/listLibrary.tld" prefix="listpasg" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../fragmentos/cabeceraKardex.jsp" %>
        <title>Kardex del colaborador</title>
    </head>

    <body id="home">

        <div class="blog-masthead hidden-print">
            <div class="container">
                <nav class="blog-nav">
                    <a class="blog-nav-item active" href="#home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Inicio</a>
                    
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.section'))}" >
                    <a class="blog-nav-item hidden-xs" href="#personales">Personales</a>
            </c:if>
                    
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.section'))}" >
                    <a class="blog-nav-item hidden-xs" href="#domicilio">Domicilio</a>
            </c:if>
                    
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.section'))}" >
                    <a class="blog-nav-item hidden-xs" href="#laborales">Laborales</a>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.section'))}" >
                    <a class="blog-nav-item hidden-xs" href="#bancarios">Bancarios</a>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.section'))}" >
                    <c:if test="${model.datosBeneficiario != null}">
                        <a class="blog-nav-item hidden-xs" href="#beneficiario">Beneficiario</a>  
                    </c:if>
            </c:if>

                    <a class="blog-nav-item hidden-xs" href="#documentosAlta">Documentos</a>
                    <a class="blog-nav-item" onclick="window.close();" href="#"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>&nbsp;Salir</a>
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
                            <h1 class="headland"><span class="glyphicon glyphicon-pawn" aria-hidden="true"></span><b>&nbsp;&nbsp;&nbsp;Colaborador</b></h1>
                        </div>
                        <div class="col-xs-12">
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.nombre'))}" >
                                <h2 class="headland-two"><i>${model.datosPersonales.nombre.toUpperCase()} ${model.datosPersonales.apellidoPaterno.toUpperCase()} ${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno.toUpperCase())}</i></h2>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">                
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.section'))}" >
                <section class="section" id="personales">
                    <div class="sec-header">Datos personales
                        <div class="btn-group" role="group" aria-label="...">
                            <a type="button" class="btn" href="${pageContext.request.contextPath}/colaboradores/${model.agremiado.idAgremiado}/kardex/pdf.htm" target="_blank">
                                <span class="glyphicon glyphicon-print hidden-print" aria-hidden="true" title="Imprimir Kardex"></span>
                            </a>
                            <button type="button" class="btn">
                                <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                    <div class="sec-body">
                        <div class="row">
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.fechaNa'))}" >   
                                <div class="col-xs-12 col-sm-6 col-md-5">
                                    <div class="descriptor">
                                        <span class="title">Fecha de Nacimiento</span>
                                        <span class="content" id="fchDNcmnt" >${model.fechaNacimiento}</span>                                    
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-6 col-md-2">
                                    <div class="descriptor">
                                        <span class="title">Años</span>
                                        <span class="content" id="nhs">${model.datosPersonales.edad}</span>
                                        <a class="isOk" title="Valor establecido correctamente"></a>
                                    </div>
                                </div>                                        
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.curp'))}" >   
                                <div class="col-xs-12 col-sm-12 col-md-5">
                                    <div class="descriptor">
                                        <span class="title">CURP</span>
                                        <span class="content" id="crp">${model.datosPersonales.curp}</span>

                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.rfc'))}" >   
                                <div class="col-xs-12 col-sm-5">
                                    <div class="descriptor">
                                        <span class="title">RFC</span>
                                        <span class="content" id="rfc">${model.datosPersonales.rfc}</span>

                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.telefono'))}" >   
                                <div class="col-xs-12 col-sm-7">
                                    <div class="descriptor">
                                        <span class="title">Teléfono</span>
                                        <span class="content" id="tlfn">${model.datosPersonales.telefono}</span>

                                    </div>
                                </div>
                            </c:if>                            
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.email'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-5">
                                    <div class="descriptor">
                                        <span class="title">Email</span>
                                        <span class="content" id="crrLctrnc">${model.datosPersonales.email}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.lugarNacimiento'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-7">
                                    <div class="descriptor">
                                        <span class="title">Lugar de Nacimiento</span>
                                        <span class="content" id="lgrDNcmnt">${model.datosPersonales.lugarNacimiento}</span>

                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.nacionalidad'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-5">
                                    <div class="descriptor">
                                        <span class="title">Nacionalidad</span>
                                        <span class="content" id="ncnldd">${model.datosPersonales.nacionalidad}</span>

                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.escolaridad'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-7">
                                    <div class="descriptor">
                                        <span class="title">Escolaridad</span>
                                        <span class="content" id="sclrdd">${model.datosPersonales.escolaridad}</span>

                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.estadoCivil'))}" >
                                <div class="col-xs-12 col-sm-5">
                                    <div class="descriptor">
                                        <span class="title">Estado civil</span>
                                        <span class="content" id="sltr">${model.datosPersonales.estadoCivil}</span>

                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-7">
                                    <div class="descriptor">
                                        <span class="title">Regimen matrimonial</span>
                                        <span class="content" id="rgmnMtrmnl">${model.datosPersonales.regimenMatrimonial != null ? datosPersonales.regimenMatrimonial : 'No aplica'}</span>

                                    </div>
                                </div>
                            </c:if>                            
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.hijos'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-5">
                                    <div class="descriptor">
                                        <span class="title">Hijos</span>
                                        <span class="content" id="hjs">${model.datosPersonales.hijos}</span>

                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.sexo'))}" >   
                                <div class="col-xs-12 col-sm-6 col-md-7">
                                    <div class="descriptor">
                                        <span class="title">Sexo</span>
                                        <span class="content" id="hjs">${model.datosPersonales.sexo}</span>

                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="sec-footer"></div>
                </section>
            </c:if>
            
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.section'))}" >                                   
                <section class="section" id="domicilio">
                    <div class="sec-header">Datos del domicilio
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="button" class="btn">
                                <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                    <div class="sec-body">
                        <div class="row">                            
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.calle'))}" >
                                <div class="col-xs-12 col-sm-8 col-md-8">
                                    <div class="descriptor">
                                        <span class="title">Calle</span>
                                        <span class="content" id="cll">${model.datosDomicilio.calle}</span>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.numero'))}" >
                                <div class="col-xs-12 col-sm-4 col-md-4">
                                    <div class="descriptor">
                                        <span class="title">Número</span>
                                        <span class="content" id="nmrXtrrNtrr">${model.datosDomicilio.numero}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.colonia'))}" >
                                <div class="col-xs-12 col-sm-8 col-md-8">
                                    <div class="descriptor">
                                        <span class="title">Colonia</span>
                                        <span class="content" id="cln">${model.datosDomicilio.colonia}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.codigoPostal'))}" >
                                <div class="col-xs-12 col-sm-4 col-md-4">
                                    <div class="descriptor">
                                        <span class="title">Código Postal</span>
                                        <span class="content" id="cdgPstl" >${model.datosDomicilio.codigoPostal}</span>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.ciudad'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-6">
                                    <div class="descriptor">
                                        <span class="title">Ciudad</span>
                                        <span class="content" id="cdd">${model.datosDomicilio.ciudad}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.municipio'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-6">
                                    <div class="descriptor">
                                        <span class="title">Municipio</span>
                                        <span class="content" id="mncp">${model.datosDomicilio.municipio}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.estado'))}" >
                                <div class="col-xs-12 col-sm-5 col-md-5">
                                    <div class="descriptor">
                                        <span class="title">Estado</span>
                                        <span class="content" id="std">${model.datosDomicilio.estado}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.tipoVivienda'))}" >
                                <div class="col-xs-12 col-sm-7 col-md-7">
                                    <div class="descriptor">
                                        <span class="title">Tipo de vivienda</span>
                                        <span class="content" id="tpDVvnd">${model.datosDomicilio.tipoVivienda}</span>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.fachada'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-6">
                                    <div class="descriptor">
                                        <span class="title">Fachada</span>
                                        <span class="content" id="fchd">${model.datosDomicilio.fachada}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.colorFachada'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-6">
                                    <div class="descriptor">
                                        <span class="title">Color</span>
                                        <span class="content" id="clr">${model.datosDomicilio.colorFachada}</span>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.entreCalles'))}" >
                                <div class="col-xs-12 col-sm-7 col-md-7">
                                    <div class="descriptor">
                                        <span class="title">Entre calles</span>
                                        <span class="content lengthText" id="ntrClls">${model.datosDomicilio.entreCalles}</span>

                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.tipoVia'))}" >
                                <div class="col-xs-12 col-sm-5 col-md-5">
                                    <div class="descriptor">
                                        <span class="title">Tipo de vía</span>
                                        <span class="content lengthText" id="tpDV">${model.datosDomicilio.tipoVia}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.referencia'))}" >
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                        <div class="descriptor">
                                            <span class="title">Referencia</span>
                                            <span class="content lengthText" id="rfrnc" >${model.datosDomicilio.referencia}</span>

                                        </div>
                                    </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="sec-footer"></div>
                </section>
            </c:if>
               
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.section'))}" >
                <section class="section" id="laborales">
                    <div class="sec-header">Datos laborales
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="button" class="btn">
                                <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                    <div class="sec-body">
                        <div class="row">
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.cliente'))}" >
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="descriptor">
                                    <span class="title">Cliente</span>
                                    <span class="content" id="clnt">${model.datosLaborales.clienteObj.nombreEmpresa}</span>
                                    <a class="isOk" title="Valor establecido correctamente"></a>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.contratista'))}" >
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="descriptor">
                                    <span class="title">Contratista</span>
                                    <span class="content" id="clnt">${model.datosLaborales.contratistaObj.nombreEmpresa}</span>
                                    <a class="isOk" title="Valor establecido correctamente"></a>
                                </div>
                            </div>
                        </c:if>
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="descriptor">
                                    <span class="title">Sindicato</span>
                                    <span class="content" id="clnt">${(model.datosLaborales.sindicatoObj==null)?"No Aplica":model.datosLaborales.sindicatoObj.nombreCorto}</span>
                                    <a class="isOk" title="Valor establecido correctamente"></a>
                                </div>
                            </div>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.fechaAlta'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="descriptor">
                                    <span class="title">Fecha de ingreso</span>
                                    <span class="content" id="ltmss">${model.datosLaborales.fechaInicioContrato}</span>                                    
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.sueldoMensual'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-8">
                                <div class="descriptor">
                                    <c:if test="${model.datosLaborales.percepcionBasadaEnSUP == false}">
                                    <span class="title">Sueldo Mensual</span>
                                    <span class="content" id="sldMnsl">$ ${model.datosLaborales.sueldoMensual} MXN</span>
                                    </c:if>
                                    <c:if test="${model.datosLaborales.percepcionBasadaEnSUP == true}">
                                        <span class="title">SUP</span>
                                    <span class="content" id="sldMnsl">${model.datosLaborales.salarioUnicoProfesional.oficio} ($ ${model.datosLaborales.salarioUnicoProfesional.pesosDiarios} MXN)</span>
                                    </c:if>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.salarioDiario'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="descriptor">
                                    <span class="title">Salario Diario</span>
                                    <span class="content" id="slrDr" >$ ${model.datosLaborales.salarioDiario} MXN</span>                                    
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.salariosImss'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Número de salarios minimos ante el IMSS</span>
                                    <span class="content" id="nmrDSlrsMnmsNtLMss">${model.datosLaborales.salariosImss}</span>
                                    <a class="isOk" title="Valor establecido correctamente"></a>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.salarioDiarioIntegrado'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Salario Diario Integrado</span>
                                    <span class="content" id="slrDrNtgrd">$ ${model.datosLaborales.salarioDiarioIntegrado} MXN </span>                                    
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.prdDPg'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="descriptor">
                                    <span class="title">Periodo De Pago</span>
                                    <span class="content" id="prdDPg">${model.datosLaborales.tipoNominaObj.tipoNomina}</span>
                                    <a class="isOk" title="Valor establecido correctamente"></a>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${model.datosLaborales.tarjetaTdu != null}">
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="descriptor">
                                    <span class="title">Tarjeta TDU</span>
                                    <span class="content" id="trjtTd">${model.datosLaborales.tarjetaTdu}</span>                                    
                                </div>   
                            </div>
                        </c:if>                                
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.credencialLaboral'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="descriptor">
                                    <span class="title">Gafete</span>
                                    <span class="content" id="gft">${model.datosLaborales.credencialLaboral}</span>                                    
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.reconocimientoAntiguedad'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-5">
                                    <div class="descriptor">
                                        <span class="title">Reconocimiento de Antiguedad</span>
                                        <span class="content" id="rcncmntDNtgdd">${model.datosLaborales.antiguedadDesde}</span>

                                    </div>
                                </div>
                            </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.numeroSeguro'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-7">
                                <div class="descriptor">
                                    <span class="title">Número de Seguro Social</span>
                                    <span class="content" id="nmrDSgrScl">&nbsp;${(model.datosLaborales.numeroSeguro==null)?"00000000":model.datosLaborales.numeroSeguro}&nbsp;</span>
                                    
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.actividadProfesional'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Actividad Profesional</span>
                                    <span class="content" id="ctvddPrfsnl">${model.datosLaborales.actividadProfesional}</span>                                    
                                </div>
                            </div>   
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.areaDepartamento'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Departamento</span>
                                    <span class="content" id="dprtmnt">${model.datosLaborales.areaDepartamento}</span>
                                    
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.lugarTrabajo'))}" >
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="descriptor">
                                    <span class="title">Lugar de Trabajo</span>
                                    <span class="content lengthText" id="lgrDTrbj">${model.datosLaborales.lugarTrabajo}</span>
                                    
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.tipoDeContrato'))}" >
                            <div class="col-xs-12 col-sm-5 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Tipo de contrato</span>
                                    <span class="content" id="tpDCntrt">${model.datosLaborales.tipoContratoObj.descripcion}</span>                                    
                                </div>
                            </div>                                  
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Periodo de contrato</span>
                                    <span class="content" id="prdDCntrt">${(model.datosLaborales.tiempoContrato !=null)?model.datosLaborales.tiempoContrato:"N/A"}</span>
                                    
                                </div>
                            </div>                                    
                        </c:if>  
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.sqmDPg'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Esquema de pago</span>
                                    <span class="content" id="prdDCntrt">${model.datosLaborales.esquemaPago.descripcion}</span>                                    
                                </div>
                            </div>
                        </c:if>                                    
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.numeroInfonavit'))}" >
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Número de credito Infonavit</span>
                                    <span class="content" id="prdDCntrt">${model.datosLaborales.numeroInfonavit != null ? model.datosLaborales.numeroInfonavit : 'No aplica'}</span>                                    
                                </div>
                            </div>
                        </c:if>
                        </div>
                    </div>
                    <div class="sec-footer"></div>
                </section>
            </c:if>   
           
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.section'))}" > 
                <section class="section" id="bancarios">
                    <div class="sec-header">Datos Bancarios
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="button" class="btn">
                                <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                    <div class="sec-body">
                        <div class="row">
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.nombreBanco'))}" >
                                <div class="col-xs-12">
                                    <div class="descriptor">
                                        <span class="title">Nombre del banco</span>
                                        <span class="content" id="cll">${model.datosBancarios.nombreBanco}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.titular'))}" >
                                <div class="col-xs-12">
                                    <div class="descriptor">
                                        <span class="title">Titular</span>
                                        <span class="content" id="nmrXtrrNtrr">${model.datosBancarios.titular}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.clabe'))}" >
                                <div class="col-xs-12 ">
                                    <div class="descriptor">
                                        <span class="title">Clabe</span>
                                        <span class="content" id="cln">${model.datosBancarios.clabe}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.numeroCuenta'))}" >
                                <div class="col-xs-12">
                                    <div class="descriptor">
                                        <span class="title">Número de cuenta</span>
                                        <span class="content" id="cdgPstl" >${model.datosBancarios.numeroCuenta}</span>                                    
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="sec-footer"></div>
                </section>
            </c:if>
                
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.section'))}" >
                <section class="section" id="beneficiario">
                    <div class="sec-header">Datos beneficiario
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="button" class="btn">
                                <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                    <div class="sec-body">
                        <div  class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="descriptor">
                                    <span class="title">El colaborador tiene beneficiario? </span>
                                    <span class="content" id="tnBnfcr">&nbsp;${model.datosBeneficiario != null ? 'Si':'No'}&nbsp;</span>
                                    <a class="isOk" title="Valor establecido correctamente"></a>
                                </div>
                            </div>
                            <c:if test="${model.datosBeneficiario != null}">                                
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.nombre'))}" >
                                    <div class="col-xs-12 col-sm-8 col-md-8">
                                        <div class="descriptor">
                                            <span class="title">Nombre</span>
                                            <span class="content" id="nmbrBnfcr">${model.datosBeneficiario.nombre}</span>                                        
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.parentesco'))}" >
                                    <div class="col-xs-12 col-sm-4 col-md-4">
                                        <div class="descriptor">
                                            <span class="title">Parentesco</span>
                                            <span class="content" id="prntscBnfcr">${model.datosBeneficiario.parentesco}</span>

                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.porcentaje'))}" >
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="descriptor">
                                            <span class="title">Porcentaje</span>
                                            <span class="content" id="prcntjBnfcr">100 %</span>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.fechaNaBen'))}" >
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="descriptor">
                                            <span class="title">Fecha de Nacimiento</span>
                                            <span class="content" id="fchDNcmntBnfcr">${model.fechaNaBen}</span>                                        
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.curp'))}" >
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="descriptor">
                                            <span class="title">CURP</span>
                                            <span class="content" id="crpBnfcr">${model.datosBeneficiario.curp}</span>                                        
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.calle'))}" >
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="descriptor">
                                            <span class="title">Calle</span>
                                            <span class="content" id="cllBnfcr">${model.datosBeneficiario.calle}</span>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.numero'))}" >
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="descriptor">
                                            <span class="title">Número</span>
                                            <span class="content" id="cllBnfcr">${model.datosBeneficiario.numero}</span>                                        
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.codigoPostal'))}" >
                                <div class="col-xs-12 col-sm-6 col-md-6">
                                    <div class="descriptor">
                                        <span class="title">Código Postal</span>
                                        <span class="content" id="cdgPstlBnfcr">${model.datosBeneficiario.codigoPostal}</span>                                        
                                    </div>
                                </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.colonia'))}" >
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="descriptor">
                                            <span class="title">Colonia</span>
                                            <span class="content" id="clnBnfcr">${model.datosBeneficiario.colonia}</span>                                        
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.ciudad'))}" >
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="descriptor">
                                            <span class="title">Ciudad</span>
                                            <span class="content" id="cddBnfcr">${model.datosBeneficiario.ciudad}</span>                                        
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.municipio'))}" >
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="descriptor">
                                            <span class="title">Municipio</span>
                                            <span class="content" id="mncpBnfcr">${model.datosBeneficiario.municipio}</span>                                        
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.estado'))}" >
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="descriptor">
                                            <span class="title">Estado</span>
                                            <span class="content" id="stdBnfcr">${model.datosBeneficiario.estado}</span>
                                        </div>
                                    </div>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                    <div class="sec-footer"></div>
                </section>
            </c:if>
                <section class="section" id="documentosAlta">
                    <div class="sec-header">Documentos del trabajador
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="button" class="btn">
                                <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                    
                    
                    <div class="sec-body">
                        <div class="row">
                            <table id="tableDocs" class="table table-hover">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Documento</th>
                                    <th>Ver</th>
                                    <th>Descargar</th>
                                </tr>
                            </thead>                         
                            <tbody id="innerTableDocs">         
                                <c:forEach items="${model.documentosDelColaborador}" var="documento">
                                        <tr>
                                            <td> <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span> </td>
                                            <td>${documento.tipoDocumentoObj.nombreDocumento}</td>
                                            <td>
                                                    <button type="button" class="btn btn-default redireccionarVentana" value="${pageContext.request.contextPath}/colaborador/ver-documentos/${documento.tipoDocumentoObj.idTipoDocumento}/${model.agremiado.idAgremiado}.htm" title="Ver documento">
                                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                                    </button>
                                            </td>
                                            <td>
                                                    <button type="button" class="btn btn-default redireccionarVentana" value="${pageContext.request.contextPath}/colaborador/descargar-documento/${documento.tipoDocumentoObj.idTipoDocumento}/${model.agremiado.idAgremiado}.htm" title="Descargar documento">
                                                        <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
                                                    </button>
                                            </td>
                                        </tr> 
                                </c:forEach>
                        </tbody>                              
                            </table>
                        </div>
                        <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-xs-12 col-md-4">
                             <button type="button" class="btn btn-danger redireccionarVentana" value="${pageContext.request.contextPath}/colaborador/documentos-zip/${model.agremiado.idAgremiado}.htm" title="Descargar todos los documentos">
                                <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>&nbsp;Descargar todos los documentos
                            </button>
                        </div>
                        <div class="col-md-4">
                           
                        </div>
                    <div class="sec-footer"></div>
                </section>
                <section class="section" id="Observaciones">
                    <div class="sec-header">Observaciones
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="button" class="btn">
                                <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                    <div class="sec-body">
                        <div class="row">                        
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="descriptor">
                                    <span class="title">Observaciones</span>
                                    <span class="content lengthText" id="bsrvcns" ><br/>${(model.agremiado.observaciones==null)?"Sin observaciones":model.agremiado.observaciones}</span>
                                    
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="sec-footer hidden-sm hidden-xs hidden-print">
                            <div class="row">
                                <div class="col-xs-12 lnbrk"></div>
                                <c:if test="${model.agremiado.statusAgremiado!=7}">
                                <div class="col-xs-12">
                                    <form id="formObservacionesColaborador" action="${pageContext.request.contextPath}/colaborador/agregar-observaciones/${model.agremiado.idAgremiado}.htm" method="post" >
                                        <div class="col-xs-11">
                                            <div class="input-group">
                                                <span class="input-group-addon" id="nmbrs">Agregar Observación</span>
                                                <input type="text" class="form-control" placeholder="Campo requerido" aria-describedby="nmbrs" name="observaciones" required title="Observación del colaborador" minlength="20" maxlength="250" autocomplete="off"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                                            </div>
                                        </div>
                                        <div class="col-xs-1">
                                            <button type="submit" class="form-control btn btn-success btn-lg center-btn" title="Guardar Observación">
                                                <b><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span></b>
                                            </button>
                                        </div>      
                                    </form>
                                </div>
                                </c:if>     
                            </div>
                    </div>
                </section>

            </div>
        </div>
        <!-- ==================================================== Sección de  footer ================================================================== -->
        <%@include file="../fragmentos/pieKardex.jsp" %>
    </body>
</html>