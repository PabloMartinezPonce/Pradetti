<%-- 
    Document   : pie
    Created on : 10/11/2016, 10:03:57 AM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
      </div> <!-- Fin de sidebody -->
    </div> <!-- Fin de <div class="row"> -->
</div> <!-- Fin de <div class="container-fluid"> -->
           <!-- ==================================================== Sección de las notificaciones flotantes ================================================================== -->
           <!-- ==================================================== Identificador del div ====== floatNotifications ============================================================ -->
           <div id="floatNotifications" class="hidden-xs">
               <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
        <sec:authorize access="hasAnyRole('Altas_En_Proceso')"> 
               <section class="containerFloatNotifications" title="Altas en proceso">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeAltasEnProceso">
                           <a class="numberIDFloatNotifications altaenproceso" href="${pageContext.request.contextPath}/colaboradores/altas-en-proceso.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
                       
        <sec:authorize access="hasAnyRole('Altas_Solicitadas')"> 
               <section class="containerFloatNotifications" title="Altas solcitadas">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeAltasSolicitadas">
                           <a class="numberIDFloatNotifications vobo" href="${pageContext.request.contextPath}/colaboradores/altas-solicitadas.htm" >0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
                       
        <sec:authorize access="hasAnyRole('Bajas_Pendientes')">      
               <section class="containerFloatNotifications" title="Bajas pendientes">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeBajasPendientes">
                           <a class="numberIDFloatNotifications bajapendiente" href="${pageContext.request.contextPath}/colaboradores/bajas-pendientes.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>               
                       
        <sec:authorize access="hasAnyRole('Bajas_Por_Finalizar')">   
               <section class="containerFloatNotifications" title="Bajas por finalizar">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeBajasPorFinalizar">
                           <a class="numberIDFloatNotifications bajaporfinalizar" href="${pageContext.request.contextPath}/colaboradores/bajas-por-finalizar.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
                       
        <sec:authorize access="hasAnyRole('Bajas_Sin_Firmar')">   
               <section class="containerFloatNotifications" title="Bajas sin firmar">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeBajasSolicitadas">
                           <a class="numberIDFloatNotifications bajasinfirmar" href="${pageContext.request.contextPath}/colaboradores/baja-sin-firmar.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
               
        <sec:authorize access="hasAnyRole('Bajas_Solicitadas')">  
               <section class="containerFloatNotifications" title="Bajas solicitadas">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeBajasSolicitadas">
                           <a class="numberIDFloatNotifications bajasolicitada" href="${pageContext.request.contextPath}/colaboradores/bajas-solicitadas.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
                       
        <sec:authorize access="hasAnyRole('Contratos_Por_Vencer')">  
               <section class="containerFloatNotifications" title="Contratos por vencer">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeContratosPorVencer">
                           <a class="numberIDFloatNotifications porVencer" href="${pageContext.request.contextPath}/colaboradores/contratos-por-vencer.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
            
        <sec:authorize access="hasAnyRole('Renovaciones_Solicitadas')">               
               <section class="containerFloatNotifications" title="Renovaciones solicitadas">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeSolicitudesDeRenovacion">
                           <a class="numberIDFloatNotifications renovacionsolicitada" href="${pageContext.request.contextPath}/colaboradores/renovaciones-solicitadas.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
            
        <sec:authorize access="hasAnyRole('Solicitudes_De_Renovacion')">  
               <section class="containerFloatNotifications" title="Solicitudes de renovación">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeSolicitudesDeRenovacion">
                           <a class="numberIDFloatNotifications solicitudderenovacion" href="${pageContext.request.contextPath}/colaboradores/solicitudes-de-renovacion.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
            
        <sec:authorize access="hasAnyRole('Expedientes_Sin_Contrato')">  
               <section class="containerFloatNotifications" title="Expedientes sin contrato">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeExpedientesSinContrato">
                           <a class="numberIDFloatNotifications expedientesincontrato" href="${pageContext.request.contextPath}/colaboradores/expedientes-sin-contrato.htm">0</a>
                       </div>
                   </div>
               </section>
</sec:authorize>
                       
        <sec:authorize access="hasAnyRole('Expedientes_Por_Completar')">              
               <section class="containerFloatNotifications" title="Expedientes por completar">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeExpedientesPorCompletar">
                           <a class="numberIDFloatNotifications expedienteincompleto" href="${pageContext.request.contextPath}/colaboradores/expedientes-por-completar.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
                       
        <sec:authorize access="hasAnyRole('Bajas')">  
               <section class="containerFloatNotifications" title="Colaboradores dados de baja">
                   <div class="borderFloatNotifications">
                       <div class="numberFloatNotifications badgeExpedientesConObservaciones">
                           <a class="numberIDFloatNotifications baja" href="${pageContext.request.contextPath}/colaboradores/colaboradores-dados-de-baja.htm">0</a>
                       </div>
                   </div>
               </section>
        </sec:authorize>
                       
           </div>
                       <footer> Payroll by&nbsp;&nbsp;<a href="http://www.pradettisanti.com.mx/" target="_blank">PradettiSanti Consulting</a>&nbsp;&nbsp;&copy;&nbsp;2017&nbsp;-&nbsp;2018</footer>
           <!-- ===============================================================  Bootstrap & JS =========================================================================== -->
           <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
           <script src="${pageContext.request.contextPath}/frontend/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
            <script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
            <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/frontend/js/bootstrap-filestyle.min.js"> </script>
            <script src="${pageContext.request.contextPath}/frontend/js/dashboard.js"></script>
            <script src="${pageContext.request.contextPath}/frontend/js/ajaxRequest.js"></script>
            <script src="${pageContext.request.contextPath}/frontend/js/modal.js"></script>
            <script src="${pageContext.request.contextPath}/frontend/js/modalWait.js"></script>
            <script src="${pageContext.request.contextPath}/frontend/js/modalConfirmation.js"></script>
            <script src="${pageContext.request.contextPath}/frontend/js/picklist.js"></script>
            <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
            <script src="${pageContext.request.contextPath}/frontend/js/ie10-viewport-bug-workaround.js"></script>
            <!-- modalView -->
            <!-- Modal -->
<div class="modal fade" id="myModales" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" title="Cerrar"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><span id="iconTitle" class="" aria-hidden="true"></span>&nbsp;
            <span id="modalTitle">

            </span>
        </h4>
      </div>
        <div class="modal-body" id="modalBody">

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default btn-default-modal" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>
        <!-- modalViewWait -->
        <!-- ModalWait -->
<div class="modal fade" id="myModalesWait" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" title="Cerrar"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><span id="iconTitleWait" class="" aria-hidden="true"></span>&nbsp;
            <span id="modalTitleWait">

            </span>
        </h4>
      </div>
        <div class="modal-body" >
            <b><span id="modalBodyWait"></span></b>
            <br><br>
           <div class="progress">
            <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar" aria-valuenow="100"   aria-valuemin="0" aria-valuemax="100" style="width:100%">
              Procesando ...
            </div>
          </div>
      </div>
      <div class="modal-footer">
          <button disabled type="button" class="btn btn-default btn-default-modal" data-dismiss="modal">Por favor espere ...</button>
      </div>
    </div>
  </div>
</div>
        <!-- modalViewConfirmation -->
        <!-- ModalConfirmation -->
<div class="modal fade" id="myModalConfirmation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><span id="iconTitleWait" class="glyphicon glyphicon-hand-right modal-info" aria-hidden="true"></span>&nbsp;
            <span id="modalTitleWait" class="modal-info">
                     Colaboradores
            </span>            
        </h4>
      </div>
        <div class="modal-body" id="textConfirmation">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button type="button" class="btn btn-primary" onclick="closeWindow()" value="" id="btnConfirmationOk">Aceptar</button>
      </div>
    </div>
  </div>
</div>
