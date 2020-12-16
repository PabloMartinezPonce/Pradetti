/* ==============================================================
   =================== Created by: PabloSagoz =================== 
   ===================== Date: November 2016 ====================
   ============================================================== */
$(document).ready(function() {
    $(".slideUpDown").click(function() {
      $( this ).parents('.sec-header').siblings().slideToggle();
      if($( this ).hasClass('glyphicon-menu-up')){
        $( this ).removeClass('glyphicon-menu-up');
        $( this ).addClass('glyphicon-menu-down');          
      }else{
      $( this ).removeClass('glyphicon-menu-down');
      $( this ).addClass('glyphicon-menu-up');          
      }
    });
    $(".blog-nav-item").click(function(){
        $( this ).siblings().removeClass('active');
        $( this ).addClass('active');
    });
    $(".downloader").tooltip();
    $(".isOk").tooltip();
    $(".isNull").tooltip();
   // ======== ======== Clientes ======= =======   
$("#btnPdrLgl").click(function(){
	$("#poderLegal").slideToggle();
});   
$("#addUserBtn").click(function(){
	$("#addUser").fadeToggle('slow',function(){
		$("#dscptrUsuario").text('Agregar un nuevo usuario al cliente');
		$("#nmbrCmpltID").val("");
		$("#crrLctrncID").val("");
	});        
	var link = $("#formUsuarioCliente").attr('action');
	$("#formUsuarioCliente").attr('action',link+'cliente/registrar-usuario.htm');
});        
$(".btnUserEdit").click(function(){
	$("#addUser").fadeIn('slow');
});
$(".ver-detalles-contratos-realizados").click(function(){
		var brother = $( this ).parent().parent();//PSCPayRoll/
		var divSlide = brother.siblings('.detalles-contratos-realizados');
		divSlide.slideToggle();
});
// ======= ========= Solicitud de Baja ====== =======
    $("#btnCptrSlctd").click(function(){
        $("#documentos").fadeToggle();
    });
    // ======= ========= Colaboradores - agregar una observaciones ======= =======
    $("#btnAdd_BsrvcnsClbrdr").click( function(){
       $("#addObservacionesColaborador").slideToggle('slow');
    });
    // ======= ========= Colaboradores - cancelar una observaciones ======= =======
    $("#cancelObs").click(function(){
       $("#addObservacionesColaborador").slideToggle('slow');
    });
        // ================= ============= Editar usuario de un cliente ================ ==================
$(".btnUserEdit").click(function(){
	$("#dscptrUsuario").text('Editar usuario del cliente');
	var parent = $( this ).parents('.usersRow');
	var idUsuario = parent.find('span.vlr').text();
	var nombreUsuario = parent.find('span.nmbrUsuario').text();
	var correoUsuario = parent.find('span.crrUsuario').text();
	var rolUsuario = parent.find('span.rlUsuario').text();
	var size = $("#selectUsuario option").size();
	for( i = 1; i<= size; i++ ){            
		$("#selectUsuario option[value=" + i +"]").removeAttr("selected");
	}
	$("#selectUsuario option[value=" + rolUsuario +"]").attr("selected",true);
	$("#vlrID").val(idUsuario);
	$("#nmbrCmpltID").val(nombreUsuario);
	$("#crrLctrncID").val(correoUsuario);
	
	var link = $("#formUsuarioCliente").attr('action');
	$("#formUsuarioCliente").attr('action',link+'cliente/editar-usuario.htm');
});
    // ==================== ======== redireccionar nueva ventana ====== =====================
    $(".redireccionarVentana").click(function () {
        redireccionarVentana($(this));
    });
});
function redireccionarVentana(btnObject) {
    var url = btnObject.val();
    window.open(url, '_blank');
}
/* ==============================================================
   =================== Created by: PabloSagoz =================== 
   ===================== Date: November 2016 ====================
   ============================================================== */