<%-- 
    Document   : fdc_dpersonales
    Created on : 21/02/2018, 01:04:32 PM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/listLibrary.tld" prefix="listpasg" %>
<!DOCTYPE html>
<html>
    <body>
   <form id="formDatosPersonales" action="${pageContext.request.contextPath}/colaborador/datosPersonales.htm" method="post" modelAttribute="datosPersonales" >
        <div class="col-xs-12">
                <input type="hidden" value="${model.datosPersonales.agremiado}" id="agremiado" name="agremiado">
                <input type="hidden" value="${model.esquema.idEsquemaPago}" id="schema" name="schemaId">
                <input type="hidden" value="${model.contratoID}" id="contratoId" name="contratoId">                
        </div>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.nombre'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="nmbrs">Nombre(s)</span>
                    <input value="${model.datosPersonales.nombre}" path="nombre" maxlength="99" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="nmbrs" name="nombre"  required title="Nombre(s) del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
        </c:if>                    
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.apellidoPaterno'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="plldPtrn">Apellido paterno</span>
                    <input value="${model.datosPersonales.apellidoPaterno}" name="apellidoPaterno" maxlength="99" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="plldPtrn" required title="Apellido paterno del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.apellidoMaterno'))}" >   
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="plldMtrn">Apellido materno</span>
                    <input value="${model.datosPersonales.apellidoMaterno}" name="apellidoMaterno" maxlength="99" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo no requerido" aria-describedby="plldMtrn" title="Apellido materno del colaborador"  pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.fechaNa'))}" >   
            <div class="col-xs-8 col-md-4">
                <div class="input-group">
                    <span class="input-group-addon" id="fchDNcmnt">Fecha de nacimiento</span>
                    <input value="${model.fechaNacimiento}" name="fechaNa" min="10" max="10" minlength="10"maxlength="10"   type="date"  class="form-control" placeholder="Campo requerido" aria-describedby="fchDNcmnt" id="fchDNcmntID_AddColaborador" required title="Fecha de nacimiento del colaborador">
                </div>                            
            </div>
        <div class=" col-xs-6 col-xs-offset-6 col-md-2 col-md-offset-0">
            <div class="input-group">
                <input type="hidden" name="edad" value="${model.datosPersonales.edad}" id="ddID_AddColaboradorHddn">
                <input value="${model.datosPersonales.edad}" name="edadView" type="number" maxlength="5" class="form-control" autocomplete="off" aria-describedby="dd"  min="15" max="99" title="Edad del colaborador en años" id="ddID_AddColaborador" placeholder="##" readonly required>
                <span class="input-group-addon" id="dd">Años</span>
            </div>                            
        </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.sexo'))}" >   
        <div class="col-xs-12 col-md-6">
            <div class="input-group">
                <span class="input-group-addon" id="sx">Sexo</span>
                <select  path="sexo" name="sexo" class="form-control" placeholder="Campo requerido" aria-describedby="stdCvl"  required id="sxID_AddColaborador" title="Sexo del colaborador" >
                    <option ${model.datosPersonales.sexo.equalsIgnoreCase("Masculino") ? 'selected="selected"' : ''}  value="Masculino">Masculino</option>
                    <option ${model.datosPersonales.sexo.equalsIgnoreCase("Femenino") ? 'selected="selected"' : ''} value="Femenino">Femenino</option>
                </select>
            </div>                            
        </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.curp'))}" >   
        <div class="col-xs-12 col-md-6">
            <div class="input-group">
                <span class="input-group-addon" id="CURP">CURP</span>
                <input value="${model.datosPersonales.curp}" name="curp" id="curp" maxlength="18" type="text" class="form-control toUpperText" autocomplete="off" placeholder="Campo requerido" aria-describedby="CURP" required title="CURP del colaborador" minlength="18"   pattern="[A-Z]+([0-9A-Z]+)*">
            </div>
        </div>   
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.rfc'))}" >   
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="RFC">RFC</span>
                    <input value="${model.datosPersonales.rfc}" name="rfc" type="text" maxlength="15" class="form-control toUpperText" autocomplete="off" placeholder="Campo requerido" aria-describedby="RFC"  required title="RFC del colaborador" minlength="15"   pattern="[A-Z]+([0-9A-Z]+)*">
                </div>
            </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.telefono'))}" >   
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="tlfn">Teléfono</span>
                    <input value="${model.datosPersonales.telefono}" name="telefono" type="text" maxlength="10" class="form-control toUpperText" autocomplete="off" placeholder="Campo requerido" aria-describedby="tlfn"  required title="Teléfono del colaborador">
                </div>
            </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.email'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="ml">Email</span>
                    <input value="${model.datosPersonales.email}" name="email" type="email" maxlength="90" class="form-control toLowerText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="ml"  required title="Correo electrónico del colaborador"  pattern="[a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*[.][a-zA-Z]{1,10}" minlength="5">
                </div>
            </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.lugarNacimiento'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="lgrDNcmnt">Lugar de Nacimiento</span>
                    <input value="${model.datosPersonales.lugarNacimiento}" name="lugarNacimiento" maxlength="99" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="lgrDNcmnt" required title="Lugar de Nacimiento del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([,]*\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+[.]*)*">
                </div>
            </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.nacionalidad'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="ncnlidd">Nacionalidad</span>
                    <input value="${model.datosPersonales.nacionalidad}" name="nacionalidad" maxlength="50" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="ncnlidd" required title="Nacionalidad del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.escolaridad'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="sclrdd">Escolaridad</span>
                    <input value="${model.datosPersonales.escolaridad}" name="escolaridad" maxlength="50" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="sclrdd" required title="Escolaridad del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.estadoCivil'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="stdCvl">Estado civil</span>
                    <select  path="estadoCivil" name="estadoCivil" class="form-control" placeholder="Campo requerido" aria-describedby="stdCvl"  required id="stdCvlID_AddColaborador">
                        <option ${model.datosPersonales.estadoCivil.equalsIgnoreCase("Soltero(a)") ? 'selected="selected"' : ''} value="Soltero(a)">Soltero(a)</option>
                        <option ${model.datosPersonales.estadoCivil.equalsIgnoreCase("Casado(a)") ? 'selected="selected"' : ''}  value="Casado(a)">Casado(a)</option>
                        <option ${model.datosPersonales.estadoCivil.equalsIgnoreCase("Unión libre") ? 'selected="selected"' : ''} value="Unión libre">Unión libre</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-9 col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="rgmnMtrmnl">Regimen matrimonial</span>
                    <select  value="${model.datosPersonales.regimenMatrimonial}" name="regimenMatrimonial" class="form-control" placeholder="Campo requerido" aria-describedby="rgmnMtrmnl" id="rgmnMtrmnlID_AddColaborador"   required ${(model.datosPersonales.regimenMatrimonial==null)?'disabled':''}>
                        <option ${model.datosPersonales.regimenMatrimonial.equalsIgnoreCase("Bienes separados") ? 'selected="selected"' : ''} value="Bienes separados">Bienes separados</option>
                        <option ${model.datosPersonales.regimenMatrimonial.equalsIgnoreCase("Bienes mancomunados") ? 'selected="selected"' : ''} value="Bienes mancomunados">Bienes mancomunados</option>
                    </select>
                </div>
            </div>
        </c:if>
        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.hijos'))}" >
            <div class="col-xs-3 col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="hjs">Hijos</span>
                    <input ${model.datosPersonales.hijos.equalsIgnoreCase("Sí") ? 'checked="checked"' : ''} value='Sí' name="hijos" path="hijos" id="hijos" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                </div>
            </div>
        </c:if>
        <div class="col-xs-12 lnbrk"></div>      
        <div class="col-xs-12 col-md-6">
            <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
        </div>
        <div class="col-xs-12 col-md-2 col-md-offset-1">
        <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador','Editar_Colaborador_Activo')">     
            <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                <b>Guardar</b>
            </button>
        </sec:authorize>
        </div>
        <div class="col-xs-12 col-md-2 col-md-offset-1">
            <c:choose>
                <c:when test="${model.estatus == 1}">      
                        <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                            <b>Cancelar</b>
                        </button>
                </c:when>
                <c:otherwise> 
                        <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/expedientes-por-completar.htm">
                            <b>Cancelar</b>
                        </button>
                </c:otherwise>
            </c:choose>
        </div>
</form>
</body>
</html>
