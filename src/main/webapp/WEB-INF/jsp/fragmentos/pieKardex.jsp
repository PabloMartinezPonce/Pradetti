<%-- 
    Document   : pieKardex
    Created on : 11/11/2016, 12:05:09 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer>Payroll&nbsp;&#9679;&nbsp;2017&nbsp;
    <a href="#"><b>&nbsp;Ir arriba&nbsp;</b></a>
</footer>
<!-- ===============================================================  Bootstrap & JS =========================================================================== -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/frontend/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/frontend/js/bootstrap-filestyle.min.js"> </script>
<script src="${pageContext.request.contextPath}/frontend/js/detalles.js"></script>
<script src="${pageContext.request.contextPath}/frontend/js/modal.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="${pageContext.request.contextPath}/frontend/js/ie10-viewport-bug-workaround.js"></script>
<!-- modalView -->
<!-- Modal -->
<div class="modal fade" id="myModales" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
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
        <button type="button" class="btn btn-default btn-default-modal" data-dismiss="modal">Entiendo</button>
      </div>
    </div>
  </div>
</div>