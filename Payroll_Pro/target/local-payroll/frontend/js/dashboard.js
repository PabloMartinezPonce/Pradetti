/* ==============================================================
 =================== Created by: PabloSagoz =================== 
 ===================== Date: November 2016 ====================
 ============================================================== */
$(document).ready(function () {

    $("#bigMenu").click(function () {
        slideMenu();
    });
    $("#badgeClic").click(function () {
        $("#floatNotifications").fadeToggle(200);
    });
    $("#floatNotifications").mouseleave(function () {
        $("#floatNotifications").fadeOut(200);
    });
    $("#sidebar").mouseleave(function () {
        slideMenu();
    });
    $(".onClicMe").click(function () {
        $("#titlePage").empty();
        $("#titlePage").html($(this).children('a').html());
        slideMenu();
    });
    /* ====================================== Tabla JS ======================================== */
    $("#tblLtsPrcs").DataTable({
        "fnDrawCallback": function (oSettings) {
            bindEvents();
        },
        "language": {
            "decimal": "",
            "emptyTable": "Ningún dato disponible para ser mostrado dentro de la tabla",
            "info": "Mostrando del _START_ al _END_ de _TOTAL_ registros",
            "infoEmpty": "Mostrando del 0 al 0 de 0 registros",
            "infoFiltered": "(Filtrado de _MAX_ registros en total)",
            "infoPostFix": "",
            "thousands": ",",
            "lengthMenu": "Mostrar _MENU_ Registros Por Página",
            "loadingRecords": "Cargando ...",
            "processing": "Procesando ...",
            "search": "<span class='glyphicon glyphicon-search hidden-xs' aria-hidden='true'></span>&nbsp;&nbsp;&nbsp;",
            "zeroRecords": "Ningún registro coincide con la busqueda ingresada",
            "paginate": {
                "first": "Primera",
                "last": "Última",
                "next": "Siguiente",
                "previous": "Previa"
            },
            "aria": {
                "sortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sortDescending": ": Activar para ordenar la columna de manera decendente"
            }
        }
    });
    var tblLtsPrcs_length = $("#tblLtsPrcs_length").parent();
    tblLtsPrcs_length.removeClass('col-sm-6');
    tblLtsPrcs_length.addClass('col-xs-6');
    var tblLtsPrcs_filter = $("#tblLtsPrcs_filter").parent();
    tblLtsPrcs_filter.removeClass('col-sm-6');
    tblLtsPrcs_filter.addClass('col-xs-6');
    $("#tblLtsPrcs_filter label input").attr('placeholder', 'Buscar');
    $("#tblLtsPrcs_filter label input").attr('title', 'Buscar dentro de la tabla');
    var tblLtsPrcs_info = $("#tblLtsPrcs_info").parent();
    tblLtsPrcs_info.removeClass('col-sm-5');
    tblLtsPrcs_info.addClass('col-xs-5');
    var tblLtsPrcs_paginate = $("#tblLtsPrcs_paginate").parent();
    tblLtsPrcs_paginate.removeClass('col-sm-7');
    tblLtsPrcs_paginate.addClass('col-xs-7');
    // ======== ======== Nuevo Colaborador ======== =========
    // ======== ======== Nuevo Colaborador - Datos Personales ======== ========
    $("#fchDNcmntID_AddColaborador").change(function () {
        var anios = mathYears($(this).val());
        if (isNaN(anios) || anios < 15 || anios > 99) {
            $("#ddID_AddColaborador").val('');
            $("#ddID_AddColaboradorHddn").val('');
        } else {
            $("#ddID_AddColaborador").val(anios);
            $("#ddID_AddColaboradorHddn").val(anios);
        }
    });
    $("#stdCvlID_AddColaborador").change(function () {
        var optionSelected = $(this).val();
        if (optionSelected === "Casado(a)") {
            $("#rgmnMtrmnlID_AddColaborador").removeAttr('disabled');
            $("#rgmnMtrmnlID_AddColaborador > option[value='Soltero(a)']").removeAttr('selected');
        } else {
            $("#rgmnMtrmnlID_AddColaborador").attr('disabled', 'disabled');
            $("#rgmnMtrmnlID_AddColaborador > option[value='Soltero(a)']").attr('selected', 'selected');
        }
    });
    // ======== ======== Nuevo Colaborador - Datos Laborales ======== ========
    $("#tpDCntrtsID_AddColaborador").change(function () {
        var optionSelected = parseInt($(this).val());
        if (optionSelected === 1) {
            $("#prdDCntrtID_AddColaborador").removeAttr('required');
            $("#prdDCntrtID_AddColaborador").val('');
            $("#prdDCntrtTxt_AddColaborador").val('');
            $("#prdDCntrtTxt_AddColaboradorHddn").val('');
            $("#prdDCntrtID_AddColaborador").attr('disabled', 'disabled');
        } else {
            $("#prdDCntrtID_AddColaborador").removeAttr('disabled');
            $("#prdDCntrtID_AddColaborador").attr('required', 'required');
        }
    });
    $("#sueldoSupTButon").change(function (){
        var chkd = $(this).is(':checked');
        $("#sueldoSupTButonVal").val(chkd);
        if(chkd){
            if($("#contratoId").val()!==''){                
                $("#sueldoNoSup").hide('slow');
                $("#sueldoSup").show('fast'); 
                $("#supSelect").attr('required', 'required'); 
                $("#salarioDiario").removeAttr('required');
                $("#salarioMensualQty").removeAttr('required');
                $("#salarioMensualQtyNml").removeAttr('required');
                $("#salarioDiarioSup").val('');
                supsSelectEmployee();
            }else{
                 getModalView("1","Colaboradores","No se cuenta con la información necesaria para porder consultar los SUPs registrados para este contrato");
                 saveStateSalariesEployee();
            }
        }else{
            $("#sueldoNoSup").show('fast');
            $("#sueldoSup").hide('slow');
            $("#supSelect").removeAttr('required');
            $("#supSelect").empty();
            $("#salarioDiario").attr('required', 'required'); 
            $("#salarioMensualQty").attr('required', 'required'); 
            $("#salarioMensualQtyNml").attr('required', 'required'); 
            $("#salarioDiarioSup").val('');
        }
        $("#salarioDiarioIntegrado").val('');
        $("#salarioDiarioIntegradoHddn").val('');
        $("#salariosImss").val('');
        $("#salariosImssHddn").val('');
        $("#salarioDiario").val('');
        $("#salarioMensualQty").val('');
        $("#salarioMensualQtyNml").val('');
    });
    $("#supSelect").change(function(){
        var chooseSelect = $("#supSelect option:selected").attr('title');
        $("#salarioDiarioSup").val(chooseSelect);
    });
    $("#fechaInicioContratoColaborador").change(function (){
            $("#prdDCntrtID_AddColaborador").val('');
            $("#prdDCntrtTxt_AddColaborador").val('');   
            $("#prdDCntrtTxt_AddColaboradorHddn").val('');   
            $("#rcncmtDNtgddToggleBtn").attr('checked', false);
            $("#rcncmtDNtgddToggleBtn").bootstrapToggle('off');
            $("#rcncmtDNtgddDate").val('');     
            $("#rcncmtDNtgddDate").removeAttr('required');
            $("#rcncmtDNtgddDate").attr('disabled', 'disabled'); 
            $("#rcncmtDNtgddText").val('');     
            $("#rcncmtDNtgddTextHddn").val('');     
    });
    $("#prdDCntrtID_AddColaborador").change(function(){ //Si desenfocamos el elemento con el id fchB
                
                var fechaA = $("#fechaInicioContratoColaborador").val(); //Asignación del valor del elemento con id fchA a una variable
                var fechaB = $("#prdDCntrtID_AddColaborador").val(); //Asignación del valor del elemento con id fchB a una variable
                
                if(fechaA < fechaB){ //Si la fechaA es menos a la fechaB
                    if(fechaB !== '' &&  fechaA !== ''){ //Si la fechaB y la fechaA son diferente de blanco ''
                        var fields = fechaA.split("-"); //Se utiliza el metodo split para la fechaA con seprador - y se almacena en una variable
                        var fields2 = fechaB.split("-"); //Se utiliza el metodo split para la fechaA con seprador - y se almacena en una variable
                        var calyear = fields[0]; //se le asigna el valor de la posición 0 de fields a una variable
                        var calmon = fields[1]; //se le asigna el valor de la posición 1 de fields a una variable
                        var calday = fields[2]; //se le asigna el valor de la posición 2 de fields a una variable
                        var curyear = fields2[0]; //se le asigna el valor de la posición 0 de fields2 a una variable
                        var curmon = fields2[1]; //se le asigna el valor de la posición 1 de fields1 a una variable
                        var curday = fields2[2]; //se le asigna el valor de la posición 2 de fields2 a una variable

                        var curd = new Date(curyear,curmon-1,curday); //se le asigna el valor del formato de fecha con los parametros adquiridos de la fecha principal a la variable curd
                        var cald = new Date(calyear,calmon-1,calday); //se le asigna el valor del formato de fecha con los parametros adquiridos de la fecha principal a la variable cald

                        var dife = dateDiff(curd,cald); //Se le asigna el resultado de la fucnión datediff con los parametros curd,cald a la variable dife
                        
                        var result = ""; //Se declará una vriable con un cadena vacía
                        
                        if(dife[0] === 1 ){ //Sí el valor de dife en su posición 0 es igual a 1 (años)
                            result = dife[0]+' año, '; //Entonces a la cadena de texto vacía se le agrega el valor + año
                        }else{
                            result = dife[0]+' años, '; //si no a la cadena de texto vacía se le agrega el valor + años
                        }
                        
                        if(dife[1] === 1 ){ //Si el valor de dife en su posición 1 es igual a 1 (meses)
                            result = result + dife[1] +' mes, y '; //Entonces a la cadena de texto con el valor asignado en años se le agrega el valor + mes
                        }else{
                            result = result + dife[1] +' meses, y '; //Si no a la cadena de texto con el valor asignado en años se le agrega el valor + meses
                        }
                        
                        if(dife[2] === 1){ //Si el valor de dife en su posición 2 es igual a 1 (dias)
                            result = result + dife[2]+' día';// Entonces a la cadena de texto con el valor asignado en años y meses se le agrega el valor + día
                        }else{
                            result = result + dife[2]+' días'; //Si no a la cadena de texto con el valor asignado en años y meses se le agrega el valor + día
                        }                        
                        
                        $("#prdDCntrtTxt_AddColaborador").val(result); //Se imprime la variable result con todas su valores concatenados
                        $("#prdDCntrtTxt_AddColaboradorHddn").val(result); 
                    }
                }else{ //Si la fechaA no es menos a la fechaB
                    $("#prdDCntrtTxt_AddColaborador").val("Fecha de termino debe ser mayor a la fecha de ingreso"); //Imprime mensaje indicando que no se puede hacer el calculo
                    $("#prdDCntrtTxt_AddColaboradorHddn").val(''); 
                     $("#prdDCntrtID_AddColaborador").val('');
                }
            }); //Fin de la función desenfocar el elemento con id fchB
            
    $("#fechaFinPeriodo").change(function(){ //Si desenfocamos el elemento con el id fchB
                
                var fechaA = $("#fechaInicioPeriodo").val(); //Asignación del valor del elemento con id fchA a una variable
                var fechaB = $("#fechaInicioPeriodo").val(); //Asignación del valor del elemento con id fchB a una variable
                var result = "";
                
                var cliente = $("#selectClienteOption").val();
                var contratista = $("#selectBoxContratistasWithDocument").val();
                var fecha_inicio = $("#fechaInicioPeriodo").val();
                var fecha_fin = $("#fechaFinPeriodo").val();
                console.log("cliente: " + cliente);
                console.log("contratista: " + contratista);
                console.log("fecha inicio: " + fecha_inicio);
                console.log("fecha fin: " + fecha_fin);
                
                
                if(fechaA < fechaB){ //Si la fechaA es menos a la fechaB  
                        $("#prdDCntrtTxt_AddColaborador").val(result); 
                        $("#prdDCntrtTxt_AddColaboradorHddn").val(result); 
                }else{ //Si la fechaA no es menos a la fechaB
                    $("#prdDCntrtTxt_AddColaborador").val("Fecha de termino debe ser mayor a la fecha de ingreso"); //Imprime mensaje indicando que no se puede hacer el calculo
                    $("#prdDCntrtTxt_AddColaboradorHddn").val(''); 
                     $("#prdDCntrtID_AddColaborador").val('');
                }
            }); //Fin de la función desenfocar el elemento con id fchB
            
    $("#rcncmtDNtgddToggleBtn").change(function() {
        var chkd = $(this).is(':checked');
        $("#rcncmtDNtgddTButonVal").val(chkd);
        if(chkd){
            $("#rcncmtDNtgddDate").removeAttr('disabled');
            $("#rcncmtDNtgddDate").attr('required', 'required');     
          
      }else{
          $("#rcncmtDNtgddDate").val('');
            $("#rcncmtDNtgddText").val('');     
            $("#rcncmtDNtgddTextHddn").val('');     
            $("#rcncmtDNtgddDate").removeAttr('required');
            $("#rcncmtDNtgddDate").attr('disabled', 'disabled');           
      }
    });
    $("#trjtTDTgglBtn").change(function (){
        var chkd = $(this).is(':checked');
        if(chkd){
        $("#trjtTDTTButonVal").val('Sí');
        }else{
        $("#trjtTDTTButonVal").val('No');
        }
    });
    $("#crdnclLbrlTgglBtn").change(function (){
        var chkd = $(this).is(':checked');
        if(chkd){
        $("#crdnclLbrlButonVal").val('Sí');
        }else{
        $("#crdnclLbrlButonVal").val('No');
        }
    });
    $("#rcncmtDNtgddDate").change(function(){ //Si desenfocamos el elemento con el id fchB
        var fechaA = $("#rcncmtDNtgddDate").val(); //Asignación del valor del elemento con id fchA a una variable
        var fechaB = $("#fechaInicioContratoColaborador").val(); //Asignación del valor del elemento con id fchB a una variable

        if(fechaA < fechaB){ //Si la fechaA es menos a la fechaB
            if(fechaB !== '' &&  fechaA !== ''){ //Si la fechaB y la fechaA son diferente de blanco ''
                var fields = fechaA.split("-"); //Se utiliza el metodo split para la fechaA con seprador - y se almacena en una variable
                var fields2 = fechaB.split("-"); //Se utiliza el metodo split para la fechaA con seprador - y se almacena en una variable
                var calyear = fields[0]; //se le asigna el valor de la posición 0 de fields a una variable
                var calmon = fields[1]; //se le asigna el valor de la posición 1 de fields a una variable
                var calday = fields[2]; //se le asigna el valor de la posición 2 de fields a una variable
                var curyear = fields2[0]; //se le asigna el valor de la posición 0 de fields2 a una variable
                var curmon = fields2[1]; //se le asigna el valor de la posición 1 de fields1 a una variable
                var curday = fields2[2]; //se le asigna el valor de la posición 2 de fields2 a una variable

                var curd = new Date(curyear,curmon-1,curday); //se le asigna el valor del formato de fecha con los parametros adquiridos de la fecha principal a la variable curd
                var cald = new Date(calyear,calmon-1,calday); //se le asigna el valor del formato de fecha con los parametros adquiridos de la fecha principal a la variable cald

                var dife = dateDiff(curd,cald); //Se le asigna el resultado de la fucnión datediff con los parametros curd,cald a la variable dife

                var result = ""; //Se declará una vriable con un cadena vacía

                if(dife[0] === 1 ){ //Sí el valor de dife en su posición 0 es igual a 1 (años)
                    result = dife[0]+' año, '; //Entonces a la cadena de texto vacía se le agrega el valor + año
                }else{
                    result = dife[0]+' años, '; //si no a la cadena de texto vacía se le agrega el valor + años
                }

                if(dife[1] === 1 ){ //Si el valor de dife en su posición 1 es igual a 1 (meses)
                    result = result + dife[1] +' mes, y '; //Entonces a la cadena de texto con el valor asignado en años se le agrega el valor + mes
                }else{
                    result = result + dife[1] +' meses, y '; //Si no a la cadena de texto con el valor asignado en años se le agrega el valor + meses
                }

                if(dife[2] === 1){ //Si el valor de dife en su posición 2 es igual a 1 (dias)
                    result = result + dife[2]+' día';// Entonces a la cadena de texto con el valor asignado en años y meses se le agrega el valor + día
                }else{
                    result = result + dife[2]+' días'; //Si no a la cadena de texto con el valor asignado en años y meses se le agrega el valor + día
                }                        

                $("#rcncmtDNtgddText").val(result); //Se imprime la variable result con todas su valores concatenados
                $("#rcncmtDNtgddTextHddn").val(result); 
            }
        }else{ //Si la fechaA no es menos a la fechaB
            $("#rcncmtDNtgddText").val("Fecha de antiguedad debe ser menor a la fecha de ingreso"); //Imprime mensaje indicando que no se puede hacer el calculo                    $("#rcncmtDNtgddText").val("Fecha de antiguedad debe ser menor a la fecha de ingreso"); //Imprime mensaje indicando que no se puede hacer el calculo
            $("#rcncmtDNtgddTextHddn").val(''); 
             $("#rcncmtDNtgddDate").val('');
        }
    }); //Fin de la función desenfocar el elemento con id fchB
    // ======== ======== Nuevo Colaborador - Datos Beneficiario ======== ========
    $("#sgrDVdID_AddColaborador").change(function () {
        var selection = $(this).prop('checked');
        selectionBeneficiario(selection);
    });
    // ========== ========= Tabs ========== ==========
    $("#tabDatosPersonales").click(function () {
        fadeOutTab();
        $(this).parent().addClass('active');
        $("#formDatosPersonales").slideDown('slow');
    });
    $("#tabDatosDomicilio").click(function () {
        fadeOutTab();
        $(this).parent().addClass('active');
        $("#formDatosDomicilio").slideDown('slow');
    });
    $("#tabDatosLaborales").click(function () {
        fadeOutTab();
        $(this).parent().addClass('active');
        $("#formDatosLaborales").slideDown('slow');
    });
    $("#tabDatosBeneficiario").click(function () {
        fadeOutTab();
        $(this).parent().addClass('active');
        $("#divFormDatosBeneficiario").slideDown('slow');
    });
    $("#tabDatosBancarios").click(function () {
        fadeOutTab();
        $(this).parent().addClass('active');
        $("#formDatosBancarios").slideDown('slow');
    });
    $("#tabDatosExpediente").click(function () {
        fadeOutTab();
        $(this).parent().addClass('active');
        $("#divFormDatosExpediente").slideDown('slow');
    });
    // ======= ======= Bajas pendientes ====== ======
    $(".btn-lmnrSlctd").click(function () {
        $("#viewFormEliminarSolicitud").slideToggle('slow');
    });
    $("#btnCnclrRchzBj").click(function () {
        $("#viewFormEliminarSolicitud").slideUp('slow');
    });
    // ======= ======= Bajas por finalizar ====== ======
    $(".btn-sbrRchvsBjsFnlzr").click(function () {
        $("#divFormDocumentosBajaFinalizar").slideToggle('slow');
    });
    $("#btnCnclrBjFnlzr").click(function () {
        $("#divFormDocumentosBajaFinalizar").slideUp('slow');
    });
    // ======= ======= Colaboradores sin contrato ====== ======
    $(".btn-sbrCntrtClbrdrsSnCntrt").click(function () {
        $("#divFormSubirContrato").slideToggle('slow');
    });
    $("#btnCnclrClbrdrsSnCntrt").click(function () {
        $("#divFormSubirContrato").slideUp('slow');
    });
    // ======= ======= Registrar una incidencia ====== ======= 
    $("#fchDLNcdncID").click(function () {
        $("#fchDRgstrID").val(setDateNow());
    });
    // ======= ======= Nuevo Rol ======= ========
    $("#origenPermisos").click(function () {
        $('#origenPermisos option:selected').remove().appendTo('#destinoPermisos');
    });
    $("#destinoPermisos").click(function () {
        $('#destinoPermisos option:selected').remove().appendTo('#origenPermisos');
    });
    $(".centerMiddle").click(function () {
        if ($('#origenPermisos option').length > 0) {
            $('#origenPermisos option').each(function () {
                $(this).remove().appendTo('#destinoPermisos');
            });
        } else {
            $('#destinoPermisos option').each(function () {
                $(this).remove().appendTo('#origenPermisos');
            });
        }
    });
    // ======= ======== Contrato Entre Empresas ======== =========
    //Funcion que se activa con el clic del elemento origenServicios
    $("#origenServicios").click(function () {
        //Remueve el elemento seleccionado con el id origenServicios y se lo asigna al elemento con el id destinoServicios
        $('#origenServicios option:selected').remove().appendTo('#destinoServicios');
    });
    //Funcion que se activa con el clic del elemento destinoServicios
    $("#destinoServicios").click(function () {
        //Remueve el elemento seleccionado con el id destinoServicios y se lo asigna al elemento con el id origenServicios
        $('#destinoServicios option:selected').remove().appendTo('#origenServicios');
    });
    //Funcion que se activa con el clic del elemento con el id centerMiddleService
    $(".centerMiddleService").click(function () {
        //Valida que el valor del elemento con el id origenServicios sea mayor a 0
        if ($('#origenServicios option').length > 0) {
            //Recorre los valores del elemento con el id origenServicios
            $('#origenServicios option').each(function () {
                //Remueve los valores con el id origenServicios y los asigna al elemento con el id destinoServicios
                $(this).remove().appendTo('#destinoServicios');
            }); 
        } else { //Si origenServicios es menor a 0
            //Recorre los valores del elemento con el id destinoServicios
            $('#destinoServicios option').each(function () {
                //Remueve los valores con el id destinoServicios y los asigna al elemento con el id origenServicios
                $(this).remove().appendTo('#origenServicios');
            });
        }   
    });
    // ======= ======== Contrato Entre Empresas con Contrato Excluido ======== =========
    $("#origenContratos").click(function () {
        $('#origenContratos option:selected').remove().appendTo('#destinoContratos');
    });
    $("#destinoContratos").click(function () {
        $('#destinoContratos option:selected').remove().appendTo('#origenContratos');
    });
    $(".centerMiddleContratos").click(function () {
        if ($('#origenContratos option').length > 0) {
            $('#origenContratos option').each(function () {
                $(this).remove().appendTo('#destinoContratos');
            }); 
        } else { 
            $('#destinoContratos option').each(function () {
                $(this).remove().appendTo('#origenContratos');
            });
        }   
    });
    // ======= ======= Registrar un nuevo documento ======= ========
    //Función encargada de remover el elemento seleccionado de origenEsquemas y asignarselo a destinoEsquemas
    $("#origenEsquemas").click(function () {
        $('#origenEsquemas option:selected').remove().appendTo('#destinoEsquemas');
    });
    //Funcion encargada de remover el elemento seleccionado de destinoEsquemas y asignarselo a origenEsquemas
    $("#destinoEsquemas").click(function () {
        $('#destinoEsquemas option:selected').remove().appendTo('#origenEsquemas');
    });
    //Función encargada de pasar todos los elementos de origenEsquemas a destinoEsquemas y biseversa
    $(".centerMiddleEsquemas").click(function () {
        if ($('#origenEsquemas option').length > 0) {
            $('#origenEsquemas option').each(function () {
                $(this).remove().appendTo('#destinoEsquemas');
            });
        } else {
            $('#destinoEsquemas option').each(function () {
                $(this).remove().appendTo('#origenEsquemas');
            });
        }
    });
    // ======== ======== Renovar Contrato  ======== ========
    $("#mdfccnDSlrID").change(function () {
        var selection = $(this).prop('checked');
        selectionSalario(selection);
    });
    $("#tpDCntrtsID_RnvrContrt").change(function () {
        var optionSelected = parseInt($(this).val());
        if (optionSelected == 1) {
            $("#prdDCntrtID_RnvrContrt").attr('disabled', 'disabled');
            $("#prdDCntrtID_RnvrContrt > option[value='1']").attr('selected', 'selected');
        } else {
            $("#prdDCntrtID_RnvrContrt").removeAttr('disabled');
            $("#prdDCntrtID_RnvrContrt > option[value='1']").removeAttr('selected');
        }
    });
      // ======= ========= Registro de un cliente ======= ========
    $("#tabDatosCliente").click(function () {
        slideRegistroCliente();
        $(this).parent().addClass('active');
        $("#divFormDatosCliente").slideDown('slow');
    });
    $("#tabInstrumentoNotarial").click(function () {
        slideRegistroCliente();
        $(this).parent().addClass('active');
        $("#divFormInstrumentoNotarial").slideDown('slow');
    });
    $("#tabDomicilioFiscal").click(function () {
        slideRegistroCliente();
        $(this).parent().addClass('active');
        $("#divDomicilioFiscal").slideDown('slow');
    });
    $("#tabDomicilioNorificacion").click(function () {
        slideRegistroCliente();
        $(this).parent().addClass('active');
        $("#divDomicilioNotificacion").slideDown('slow');
    });
    $("#tnPdrLgl").change(function () {
        $("#secPoderLegal").slideToggle('slow');
        $("#cprDtsPdrLgl").slideToggle('slow');
        if ($(this).prop("checked") === true) {
            $("#rpsntntPLID").attr('required', 'required');
            $("#rfcPLID").attr('required', 'required');
            $("#nstrmntPLID").attr('required', 'required');
            $("#vlmnPLID").attr('required', 'required');
            $("#fechaVolumenPLID").attr('required', 'required');
            $("#nmbrNtrPLID").attr('required', 'required');
            $("#drccnNtrPLID").attr('required', 'required');
            $("#nmrDNtrPLID").attr('required', 'required');
            $("#rchvPLID").attr('required', 'required');
        } else {
            $("#rpsntntPLID").removeAttr('required');
            $("#rfcPLID").removeAttr('required');
            $("#nstrmntPLID").removeAttr('required');
            $("#vlmnPLID").removeAttr('required');
            $("#fechaVolumenPLID").removeAttr('required');
            $("#nmbrNtrPLID").removeAttr('required');
            $("#drccnNtrPLID").removeAttr('required');
            $("#nmrDNtrPLID").removeAttr('required');
            $("#rchvPLID").removeAttr('required');
        }
    });
    $("#cprDtsPdrLgl").click(function () {
        $("#rpsntntPLID").val($('#rprsntntID').val());
        $("#rfcPLID").val($('#rfcID').val());
        $("#nstrmntPLID").val($('#nstrmntID').val());
        $("#vlmnPLID").val($('#vlmnID').val());
        $("#nmbrNtrPLID").val($('#nmbrNtrID').val());
        $("#drccnNtrPLID").val($('#drccnNtrID').val());
        $("#nmrDNtrPLID").val($('#nmrDNtrID').val());
    });
    // ====== ========= Registro de un contratista ======= =======
    $("#tabDatosContratista").click(function () {
        slideRegistroContratista();
        $(this).parent().addClass('active');
        $("#divFormDatosContratista").slideDown('slow');
    });
    $("#tabInstrumentoNotarialRgstrCntrtst").click(function () {
        slideRegistroContratista();
        $(this).parent().addClass('active');
        $("#divFormInstrumentoNotarialRgstrCntrtst").slideDown('slow');
    });
    $("#tabDomicilioFiscalRgstrCntrtst").click(function () {
        slideRegistroContratista();
        $(this).parent().addClass('active');
        $("#divFormDomicilioFiscalRgstrCntrtst").slideDown('slow');
    });
    $("#tabDomicilioNotificacionRgstrCntrtst").click(function () {
        slideRegistroContratista();
        $(this).parent().addClass('active');
        $("#divFormDomicilioNotificacionRgstrCntrtst").slideDown('slow');
    });
    // ====== ======== Generar un nuevo contrato
    $("#slctClntsNvCntrt").change(function () {
        var valor = $("#slctClntsNvCntrt option:selected").attr("id");
        $("#rfcClntsNvCntrt").val(valor);
    });
    // ====== ======== Lista de incidencias ======= =======
    $("#btnBscrLstNcdncs").click(function () {
        $("#divTblLstNcdnc").fadeIn('slow');
    });
    $(".editNcdnc").click(function () {
        var txt = $("#divVerIncidencia").css('display');
        if (txt !== "none") {
            $("#divVerIncidencia").slideUp('slow');
        }
        $("#divFormEditarIncidencia").slideToggle('slow');
    });
    $(".showNcdnc").click(function () {
        var txt = $("#divFormEditarIncidencia").css('display');
        if (txt !== "none") {
            $("#divFormEditarIncidencia").slideUp('slow');
        }
        $("#divVerIncidencia").slideToggle('slow');
    });
    $("#btnOutEditarIncidencia").click(function () {
        $("#divFormEditarIncidencia").slideUp('slow');
    });
    $("#btnOkDetalleIncidencia").click(function () {
        $("#divVerIncidencia").slideUp('slow');
    });
    // ====== ======== Crear un nuevo contrato ========== =========
    _aggremntArea = $("#aggremntArea");
    _aggremntArea.mouseout(function () {
        startCrsr = parseInt(_aggremntArea.prop("selectionStart"));
        endCrsr = parseInt(_aggremntArea.prop("selectionEnd"));
        viewDoc();
    });
    _aggremntArea.keyup(function (e) {
        if (e.which == 13) {
            addEnter();
        }
        viewDoc();
    });
    $(".btn-component-colaborator").click(function () {
        insertText($(this).val());
        viewDoc();
    });
    $(".btn-component-client").click(function () {
        insertText($(this).val());
        viewDoc();
    });
    $(".btn-component-agreement").click(function () {
        insertText($(this).val());
        viewDoc();
    });
    $(".btn-format-agreement").click(function () {
        fancyText($(this).val());
        viewDoc();
    });
    $(".btn-alignment-agreement").click(function () {
        alignmentText($(this).val());
        viewDoc();
    });
    $(".btn-component-special-component").click(function () {
        insertSpecialComponent($(this).val());
        viewDoc();
    });
    // ==================== ======== redireccionar ====== =====================
    $(".redireccionar").click(function () {
        redireccionar($(this));
    });
    // ==================== ======== redireccionar nueva ventana ====== =====================
    $(".redireccionarVentana").click(function () {
        redireccionarVentana($(this));
    });
    // ==================== ======== redireccionamiento dropdown menu ============== ======================
    $(".dropdown-option").click(function () {
        var inputText = $(".text-criteria").val();
        if (inputText.trim().length >= 3) {
            var parent = $(this).parent("li");
            var ref = parent.attr('value');
            ref += "" + $(".text-criteria").val() + ".htm";
            $(location).attr('href', ref);
        } else {
            $(".text-criteria").attr("placeholder", "Campo Requerido");
            $(".text-criteria").focus();
        }
    });
    $("#tpDNcdncID_ncdnc").change(function () {
        var titulo = $("#tpDNcdncID_ncdnc option:selected").attr('title');
        $("#tpDNcdncID_ncdnc_txt").text(titulo);
    });
    // =========================== ========== Agregar clientes al usuario ================== ==================
    $("#buttonClntsPrGrgr").click(function () {
        var opcionSelecionada = $("#optionClntsPrGrgr option:selected");
        if (opcionSelecionada.text().length === 0) {
            getModalView(2, "Sistema", "Por favor selecciona un cliente valido");
        } else {
            var idsCliente = $("#clntsPrGrgr").val();
            if (idsCliente.length === 0) {
                idsCliente = opcionSelecionada.val();
            } else {
                idsCliente += "," + opcionSelecionada.val();
            }
            $("#clntsPrGrgr").val(idsCliente);
            $("#listClntsPrGrgr").append("<button type='button' value='" + opcionSelecionada.val() + "' class='btn btn-default btn-group-xs removeClntsPrGrgr' title='Eliminar acceso al cliente'>" + opcionSelecionada.text() + "</button>");
            eventoEliminarClienteDelUsuario();
            $("#optionClntsPrGrgr option[value='" + opcionSelecionada.val() + "']").remove();
        }
    });
    $("#selectAllOptions").mouseenter(function () {
        $("#destinoPermisos option").each(function () {
            $("#destinoPermisos option").prop("selected", true);
        });
    });
    $("#selectAllOptions").mouseenter(function () {
        $("#destinoDoctos option").each(function () {
            $("#destinoDoctos option").prop("selected", true);
        });
    });
    //funcion que se ejecuta cuando detecte la presencia del puntero del ratón
    $("#selectAllOptions").mouseenter(function () {
        //Recorre las opciones de los elementos con el id destinoServicios
        $("#destinoServicios option").each(function () {
            //Selecciona todos los elementos del id destinoServicios
            $("#destinoServicios option").prop("selected", true);
        }); //Fin del for each
    }); //Fin de la funcion
    //Función encargada de seleccionar todos los elementos de destinoEsquemas
    $("#selectAllOptions").mouseenter(function () {
        $("#destinoEsquemas option").each(function () {
            $("#destinoEsquemas option").prop("selected", true);
        });
    });
    $("#selectAllOptions").mouseenter(function () {
        $("#destinoContratos option").each(function () {
            $("#destinoContratos option").prop("selected", true);
            
        });
    });
    // =========================== ========== Agregar cliente/contratista al contrato ================== ==================
    $("#buttonClntsCntrstsPrGrgr").click(function () {
        var opcionSelecionada = $("#optionClntsCntrstsPrGrgr option:selected");
        if (opcionSelecionada.text().length === 0) {
            getModalView(2, "Sistema", "Por favor selecciona una opción valida");
        } else {
            var idsCliente = $("#clntsCntrstsPrGrgr").val();
            if (idsCliente.length === 0) {
                idsCliente = opcionSelecionada.val();
            } else {
                idsCliente += "," + opcionSelecionada.val();
            }
            $("#clntsCntrstsPrGrgr").val(idsCliente);
            $("#listClntsCntrstsPrGrgr").append("<button type='button' value='" + opcionSelecionada.val() + "' class='btn btn-default btn-group-xs removeClntsCntrstsPrGrgr' title='Eliminar acceso al cliente'>" + opcionSelecionada.text() + "</button>");
            eventoEliminarClienteContratistaDelContrato();
            $("#optionClntsCntrstsPrGrgr option[value='" + opcionSelecionada.val() + "']").remove();
        }
    });
    $(".toolTip").tooltip();
    $("#aggremntArea").mouseenter(function () {
        $("#cntrlsCrrNvCntrt_Format").slideDown('slow');
    });
    $("#accordion").mouseenter(function () {
        $("#cntrlsCrrNvCntrt_Format").fadeOut('slow');
    });
    $("#viewDocument").mouseenter(function () {
        $("#cntrlsCrrNvCntrt_Format").fadeOut('slow');
    });
    $(".sideNavSis").mouseenter(function () {
        $("#cntrlsCrrNvCntrt_Format").fadeOut('slow');
    });
    $("#cntrlsCrrNvCntrt_Format").mouseenter(function () {
        $(this).fadeIn('fast');
    });
    $(".btn-popover").mouseenter(function () {
        $(this).popover('show');
    });
    $(".btn-popover").mouseleave(function () {
        $(this).popover('hide');
    });
    $("#sgrDVdID_AplModfSal").change(function () {
        var selection = $(this).prop('checked');
        AplicarModificacionSalario(selection);
    });
    // ================================= Transformar el texto de un input ================== ===============
    String.prototype.capitalize = function () {
        return this.toLowerCase().replace(/\b\w/g, function (m) {
            return m.toUpperCase();
        });
    };

    String.prototype.firstLetterUpper = function () {
        return this.charAt(0).toUpperCase() + this.slice(1).toLowerCase();
    };

    var myCapitalizeText = $('.capitalizeText');
    var myFirstLetterText = $('.firstLetterText');
    var myToUpperText = $('.toUpperText');
    var myToLowerText = $('.toLowerText');

    myCapitalizeText.focusout(function () {
//        $(this).val($(this).val().capitalize());
        $(this).val($(this).val().firstLetterUpper());
    });
    myFirstLetterText.focusout(function () {
        $(this).val($(this).val().firstLetterUpper());
    });
    myToUpperText.focusout(function () {
        $(this).val($(this).val().toUpperCase());
    });
    myToLowerText.focusout(function () {
        $(this).val($(this).val().toLowerCase());
    });

    $("#squmDPgID_AddColaborador").change(function (){        
        var str = "";
        $( "#squmDPgID_AddColaborador option:selected" ).each(function() {
          str += $( this ).text()+"";
        });
        if( str.localeCompare('Sindical') === 0 ){
            $("#nssDlClbrdr").removeAttr('required');
            $("#salarioDiario").removeAttr('required');
            $("#salarioDiario").attr('placeholder','No disponible');
            $("#nssDlClbrdr").attr('placeholder','No disponible');
            $("#salarioDiario").attr('disabled','disabled');
            $("#nssDlClbrdr").attr('disabled','disabled');
        }else{
            $("#salarioDiario").attr('required','required');
            $("#nssDlClbrdr").attr('required','required');
            $("#salarioDiario").attr('placeholder','Campo requerido');
            $("#nssDlClbrdr").attr('placeholder','Campo requerido');
            $("#nssDlClbrdr").removeAttr('disabled');
            $("#salarioDiario").removeAttr('disabled');
        }
            $("#nssDlClbrdr").val('');
            $("#salarioDiario").val('');
    });

    $(".trimTxt").focusout(function (){
        var inTxt = $( this );
        var txt = inTxt.val().trim();
        inTxt.val(txt);
    });
    $("#tipoSupRegistrados").change(function(){ // funcion especifica del id tipoSupRegistrados para detectar el cambio en el select
        
        var selectTag = $("#tipoSupRegistrados option:selected"); // variable que guarda la seleccion del id tipoSupRegistrados
                
        if(selectTag.val().length===0){ //Si el valor de selectTag es igual a 0 se le asigna a las variables: tipo_id_sup y tipoSupChng blanco
            $("#tipo_id_sup").val('');
            $("#tipoSupChng").val('');
        }else{ //Si el valor de selectTag no es igual a 0 se le asigna a la variable tipo_id_sup el valor y a la variable tipoSupChng la descipción
            $("#tipo_id_sup").val(selectTag.val());
            $("#tipoSupChng").val(selectTag.text());            
        }
    });
    $('#fchStart').focusout(function(){ //funcion encargada de verificar si el id fchStart, una vez adentro se este saliendo del foco
        //Variables asignadas al valor de los id's: fchStart y fchEnd 
        var fchStart = $("#fchStart").val();
        var fchEnd = $("#fchEnd").val();

            //Valida si la fecha de inicio es mayor a la fecha termino y manda una ventana emergente  y se le asigna el valor de blanco al id fchStart
            if(fchEnd !== ''){
                if(fchStart > fchEnd){
                    getModalView(1,'Sistema','La fecha de termino no puede ser menor que la fecha de inicio.');
                    $("#fchStart").val('');
                }
            }
        
    });
    $('#fchEnd').focusout(function(){ //funcion encargada de verificar si el id fchEnd, una vez adentro de y se este saliendo del foco
        //Variables asignadas al valor de los id's: fchStart y fchEnd
        var fchStart = $("#fchStart").val();
        var fchEnd = $("#fchEnd").val();
        
            //Valida si la fecha inicio es mayor a la fecha termino y manda una ventana emergente y se le asigna el valor de blanco al id fchStart
            if(fchStart > fchEnd){
            getModalView(1,'Sistema','La fecha de termino no puede ser menor que la fecha de inicio.');
            $("#fchStart").val('');
         }
        
    });
    
    $('#isAService').change(function(){ //Función encargada de ejecutarse cuando el objeto con el id isAService cambia
        var isAservice = $('#isAService').val(); // se declara la variable isAservice y se le asigna el objeto con el id isAService
    
        if(isAservice === "No"){ //Valida si el servicio tiene como valor No
            $('#isAService').val("Sí"); //cambia el valor de servicio por un Sí
        }else{ //si no se cumple la condicion de que el servicio sea igual a No
            $('#isAService').val("No"); //cambia el valor de servicio por un No
        } 
    });
    
    $('#isConfigurable').change(function(){ //Función encargada de ejecutarse cuando el objeto con el id isConfigurable cambie
        var isConfigurable = $('#isConfigurable').val(); // se declara una variable y se le asigna el valor del objeto con el id isConfigurable
        
        if(isConfigurable === "No"){ // Valida si la variable isConfigurable es igual al valor de No
            $('#isConfigurable').val("Sí"); // le asigna el valor de Sí al objeto con el id isConfigurable
        }else{
            $('#isConfigurable').val("No"); // le asigna el valor de No al objeto con el id isConfigurable
        }
    });
   $("#percentFA").click(function (){
       var sueldoAhorro = $("#sueldoAhorro").text();
       var percentFA = $("#percentFA").val();
       var result = Math.round(((parseFloat(sueldoAhorro)*(parseFloat(percentFA)/100)))*100)/100;
       if(isNaN(result )){
           result = "";
       }
       $("#resultFA").val(  result  );
       $("#resultFAHddn").val(  result  ); 
   });
   
   $("#percentFA").keyup(function (){
       var sueldoAhorro = $("#sueldoAhorro").text();
       var percentFA = $("#percentFA").val();
       var result = Math.round(((parseFloat(sueldoAhorro)*(parseFloat(percentFA)/100)))*100)/100;
       if(isNaN(result )){
           result = "";
       }
       $("#resultFA").val(  result  ); 
       $("#resultFAHddn").val(  result  ); 
   });
   $("#salarioMensualQtyNml").keyup(function (){
       $("#salarioMensualQty").val('');     
       var result = Math.round(((parseFloat($(this).val())*(30.40)))*100)/100;
       if(isNaN(result )){
           result = "";
       }
       $("#salarioMensualQty").val(  result  );   
   });
 });
// =========================== ========================= Funciones JS =================== =================
// =========================== ========================= Funciones JS =================== =================
// =========================== ========================= Funciones JS =================== =================
function slideMenu() {
    if ($("#bigMenu b").hasClass('hidelogo')) {
        $("#bigMenu b").removeClass('hidelogo');
        $("#bigMenu b").css('font-size', '27px');
        $("#bigMenu b").css('margin-left', '8px');
        $("#bigMenu b").css('font-weight', '');
    } else {
        $("#bigMenu b").addClass('hidelogo');
        $("#bigMenu b").css('font-size', '36px');
        $("#bigMenu b").css('font-weight', '900');
        $("#bigMenu b").css('margin-left', '8px');
    }
    $("#logoHeader").fadeToggle('slow');
    $("#sidebar").slideToggle('fast', function () {
        $("#logoFooter").fadeToggle('slow');
    });
}
function mathYears(fecha) {
    var aFecha1 = fecha.split('-');
    var fFecha1 = Date.UTC(aFecha1[0], aFecha1[1], aFecha1[2]);
    var aFecha2 = new Date();
    var fFecha2 = Date.UTC(aFecha2.getFullYear(), (parseInt(aFecha2.getUTCMonth()) + 1), aFecha2.getDate());
    var dif = fFecha2 - fFecha1;
    var dias = Math.floor(dif / (1000 * 60 * 60 * 24));
    var anios = Math.floor(dias / 365);
    return anios;
}
function selectionBeneficiario(selec) {
    if (selec) {
        $("#formDatosBeneficiario").fadeIn("slow");
        $("#nmbrDlBnfcrID_AddColaborador").removeAttr('disabled');
        $("#prntscID_AddColaborador").removeAttr('disabled');
        $("#fchNcmntID_AddColaborador").removeAttr('disabled');
        $("#crpID_AddColaborador").removeAttr('disabled');
        $("#cllID_AddColaborador").removeAttr('disabled');
        $("#nmrID_AddColaborador").removeAttr('disabled');
        $("#cdgPstlID_AddColaborador").removeAttr('disabled');
        $("#clnID_AddColaborador").removeAttr('disabled');
        $("#cddID_AddColaborador").removeAttr('disabled');
        $("#mncpID_AddColaborador").removeAttr('disabled');
        $("#stdID_AddColaborador").removeAttr('disabled');
        $("#btnGrdrID_AddColaborador").removeAttr('disabled');
        $("#btnCnclrID_AddColaborador").removeAttr('disabled');
    } else {
        $("#nmbrDlBnfcrID_AddColaborador").attr('disabled', 'disabled');
        $("#nmbrDlBnfcrID_AddColaborador").val('');
        $("#prntscID_AddColaborador").attr('disabled', 'disabled');
        $("#prntscID_AddColaborador").val('');
        $("#fchNcmntID_AddColaborador").attr('disabled', 'disabled');
        $("#fchNcmntID_AddColaborador").val('');
        $("#crpID_AddColaborador").attr('disabled', 'disabled');
        $("#crpID_AddColaborador").val('');
        $("#cllID_AddColaborador").attr('disabled', 'disabled');
        $("#cllID_AddColaborador").val('');
        $("#nmrID_AddColaborador").attr('disabled', 'disabled');
        $("#nmrID_AddColaborador").val('');
        $("#cdgPstlID_AddColaborador").attr('disabled', 'disabled');
        $("#cdgPstlID_AddColaborador").val('');
        $("#clnID_AddColaborador").attr('disabled', 'disabled');
        $("#clnID_AddColaborador").val('');
        $("#cddID_AddColaborador").attr('disabled', 'disabled');
        $("#cddID_AddColaborador").val('');
        $("#mncpID_AddColaborador").attr('disabled', 'disabled');
        $("#mncpID_AddColaborador").val('');
        $("#stdID_AddColaborador").attr('disabled', 'disabled');
        $("#stdID_AddColaborador").val('');
        $("#btnGrdrID_AddColaborador").attr('disabled', 'disabled');
        $("#btnCnclrID_AddColaborador").attr('disabled', 'disabled');
        $("#formDatosBeneficiario").fadeOut("slow");
    }
}
function fadeOutTab() {
    if ($("#tabDatosPersonales").parent().hasClass('active')) {
        $("#tabDatosPersonales").parent().removeClass('active');
        $("#formDatosPersonales").slideUp('fast');
    } else if ($("#tabDatosDomicilio").parent().hasClass('active')) {
        $("#tabDatosDomicilio").parent().removeClass('active');
        $("#formDatosDomicilio").slideUp('fast');
    } else if ($("#tabDatosLaborales").parent().hasClass('active')) {
        $("#tabDatosLaborales").parent().removeClass('active');
        $("#formDatosLaborales").slideUp('fast');
    } else if ($("#tabDatosBeneficiario").parent().hasClass('active')) {
        $("#tabDatosBeneficiario").parent().removeClass('active');
        $("#divFormDatosBeneficiario").slideUp('fast');
    } else if ($("#tabDatosBancarios").parent().hasClass('active')) {
        $("#tabDatosBancarios").parent().removeClass('active');
        $("#formDatosBancarios").slideUp('fast');
    } else {
        $("#tabDatosExpediente").parent().removeClass('active');
        $("#divFormDatosExpediente").slideUp('fast');
    }
}
function setDateNow() {
    var yyyy = new Date().getFullYear();
    var M = (new Date().getMonth()) + 1;
    var MM = ((M) < 10) ? "0" + M : M;
    var D = new Date().getDate();
    var dd = ((D) < 10) ? "0" + D : D;
    var yyyyMMdd = yyyy + "-" + MM + "-" + dd;
    var H = new Date().getHours();
    var hh = ((H) < 10) ? "0" + H : H;
    var Ms = new Date().getMinutes();
    var mm = ((Ms) < 10) ? "0" + Ms : Ms;
    return yyyyMMdd + "T" + hh + ":" + mm;
}
function selectionSalario(selec) {
    if (selec) {
        $(".hideInOutRnvrCntrt").fadeIn("slow");
        $("#sldMnslID").removeAttr('disabled');
        $("#slrDrID").removeAttr('disabled');
        $("#sldMnslID").attr('required', 'required');
        $("#slrDrID").attr('required', 'required');
        $("#nmrSlrsMnmsID").attr('required', 'required');
        $("#slrDrNtgrdID").attr('required', 'required');
    } else {
        $("#sldMnslID").attr('disabled', 'disabled');
        $("#slrDrID").attr('disabled', 'disabled');
        $("#sldMnslID").removeAttr('required');
        $("#slrDrID").removeAttr('required');
        $("#nmrSlrsMnmsID").removeAttr('required');
        $("#slrDrNtgrdID").removeAttr('required');
        $("#sldMnslID").val('');
        $("#slrDrID").val('');
        $(".hideInOutRnvrCntrt").fadeOut("slow");
    }
}
function slideRegistroCliente() {
    if ($("#tabDatosCliente").parent().hasClass('active')) {
        $("#tabDatosCliente").parent().removeClass('active');
        $("#divFormDatosCliente").slideUp('fast');
    } else if ($("#tabInstrumentoNotarial").parent().hasClass('active')) {
        $("#tabInstrumentoNotarial").parent().removeClass('active');
        $("#divFormInstrumentoNotarial").slideUp('fast');
    } else if ($("#tabDomicilioFiscal").parent().hasClass('active')) {
        $("#tabDomicilioFiscal").parent().removeClass('active');
        $("#divDomicilioFiscal").slideUp('fast');
    } else {
        $("#tabDomicilioNorificacion").parent().removeClass('active');
        $("#divDomicilioNotificacion").slideUp('fast');
    }
}
function slideRegistroContratista() {
    if ($("#tabDatosContratista").parent().hasClass('active')) {
        $("#tabDatosContratista").parent().removeClass('active');
        $("#divFormDatosContratista").slideUp('fast');
    } else if ($("#tabInstrumentoNotarialRgstrCntrtst").parent().hasClass('active')) {
        $("#tabInstrumentoNotarialRgstrCntrtst").parent().removeClass('active');
        $("#divFormInstrumentoNotarialRgstrCntrtst").slideUp('fast');
    } else if ($("#tabDomicilioFiscalRgstrCntrtst").parent().hasClass('active')) {
        $("#tabDomicilioFiscalRgstrCntrtst").parent().removeClass('active');
        $("#divFormDomicilioFiscalRgstrCntrtst").slideUp('fast');
    } else {
        $("#tabDomicilioNotificacionRgstrCntrtst").parent().removeClass('active');
        $("#divFormDomicilioNotificacionRgstrCntrtst").slideUp('fast');
    }
}
var _aggremntArea;
var startCrsr = 0;
var endCrsr = 0;
function isSelectionText() {
    return startCrsr != endCrsr;
}
function insertText(newText) {
    startCrsr = parseInt(_aggremntArea.prop("selectionStart"));
    endCrsr = parseInt(_aggremntArea.prop("selectionEnd"));
    var agreementText = _aggremntArea.val();
    var starText = agreementText.substring(0, startCrsr);
    var endText = agreementText.substring(endCrsr, agreementText.length);
    agreementText = starText + newText + endText;
    _aggremntArea.val(agreementText);
    startCrsr = endCrsr = endCrsr + newText.length;
    _aggremntArea.prop("selectionEnd", endCrsr);
    _aggremntArea.focus();
}
function fancyText(delimiter) {
    if (isSelectionText()) {
        startCrsr = parseInt(_aggremntArea.prop("selectionStart"));
        endCrsr = parseInt(_aggremntArea.prop("selectionEnd"));
        var agreementText = _aggremntArea.val();
        var starText = agreementText.substring(0, startCrsr);
        var fancyText = agreementText.substring(startCrsr, endCrsr);
        var endText = agreementText.substring(endCrsr, agreementText.length);
        fancyText = "<" + delimiter + ">" + fancyText + "</" + delimiter + ">";
        agreementText = starText + fancyText + endText;
        _aggremntArea.val(agreementText);
        startCrsr = endCrsr = ("<" + delimiter + ">").length + endCrsr + ("</" + delimiter + ">").length;
        _aggremntArea.prop("selectionEnd", endCrsr);
        _aggremntArea.focus();
    }
}
function insertSpecialComponent(newText) {
    startCrsr = parseInt(_aggremntArea.prop("selectionStart"));
    endCrsr = parseInt(_aggremntArea.prop("selectionEnd"));
    var agreementText = _aggremntArea.val();
    var starText = agreementText.substring(0, startCrsr);
    var endText = agreementText.substring(endCrsr, agreementText.length);
    newText = "<span class='specialComponent'>" + newText + '</span>';
    agreementText = starText + newText + endText;
    _aggremntArea.val(agreementText);
    startCrsr = endCrsr = endCrsr + newText.length;
    _aggremntArea.prop("selectionEnd", endCrsr);
    _aggremntArea.focus();
}
function alignmentText(option) {
    if (isSelectionText()) {
        startCrsr = parseInt(_aggremntArea.prop("selectionStart"));
        endCrsr = parseInt(_aggremntArea.prop("selectionEnd"));
        var agreementText = _aggremntArea.val();
        var starText = agreementText.substring(0, startCrsr);
        var fancyText = agreementText.substring(startCrsr, endCrsr);
        var endText = agreementText.substring(endCrsr, agreementText.length);
        var clss;
        switch (option) {
            case 'L':
                clss = '<p class="text-left">';
                break;
            case 'C':
                clss = '<p class="text-center">';
                break;
            case 'R':
                clss = '<p class="text-right">';
                break;
            case 'J':
                clss = '<p class="text-justify">';
                break;
            default:
                clss = '<p class="text-nowrap">';
                break;
        }
        fancyText = clss + fancyText + "</p>";
        agreementText = starText + fancyText + endText;
        _aggremntArea.val(agreementText);
        startCrsr = endCrsr = (clss).length + endCrsr + ("</p>").length;
        _aggremntArea.prop("selectionEnd", endCrsr);
        _aggremntArea.focus();
    }
}
function viewDoc() {
    $("#viewDocument").empty();
    var viewText = '<p class="text-justify">' + _aggremntArea.val() + '</p>';
    $("#viewDocument").append(viewText);
}
function addEnter() {
    startCrsr = parseInt(_aggremntArea.prop("selectionStart"));
    endCrsr = parseInt(_aggremntArea.prop("selectionEnd"));
    var agreementText = _aggremntArea.val();
    var starText = agreementText.substring(0, startCrsr);
    var fancyText = '<br/>';
    var endText = agreementText.substring(endCrsr, agreementText.length);
    agreementText = starText + fancyText + endText;
    _aggremntArea.val(agreementText);
    startCrsr = endCrsr = endCrsr + ("<br/>").length;
    _aggremntArea.prop("selectionEnd", endCrsr);
    _aggremntArea.focus();
}
function redireccionar(btnObject) {
    var url = btnObject.val();
    $(location).attr('href', url);
}
function redireccionarVentana(btnObject) {
    var url = btnObject.val();
    window.open(url, '_blank');
}

function enviarArchivos(url, component) {
    showform();
    var flag = true;
    $("#" + component + " input").each(function () {
        if ($(this).prop("type") === "file" && $(this).val() !== null && $(this).val() !== "" && flag) {
            var tipoArchivo = $(this).attr("id");
            var formData = new FormData();
            flag = false;
            formData.append("file", this.files[0]);
            formData.append("tipoArchivo", tipoArchivo);
            $.ajax({
                async: false,
                type: "POST",
                url: url,
                data: formData,
                imeType: "multipart/form-data",
                contentType: false,
                cache: false,
                processData: false,
                success: function () {
                    showform(tipoArchivo);
                    flag = true;
                }
            });
        }
    });
}

function showform(idDoc) {
    $("#tableDocs tr").each(function () {
        if ($(this).prop("id") === idDoc) {
            $(this).fadeIn("slow");
        }

    });
}

function showRowsStart(params) {
    $("#tableDocs tr").each(function () {
        if (compareList(parseInt($(this).prop("id")), params)) {
            $(this).fadeIn("slow");
        }

    });
}

function compareList(idDoc, params) {
    for (i = 0; i < params.length; i++) {
        if (params[i] === idDoc) {
            return true;
        }
    }
    return false;
}

function bindEvents() {
    $(".redireccionar-td").unbind("click");
    $(".redireccionarVentana-td").unbind("click");
    $(".deleteDocument-td").unbind("click");
    $(".deleteDocumentBaja-td").unbind("click");
    $(".btnFrmNvrRcbPrCrr-td").unbind("click");
    // ==================== ======== redireccionar ====== =====================
    $(".redireccionar-td").click(function () {
        redireccionar($(this));
    });
    // ==================== ======== redireccionar nueva ventana ====== =====================
    $(".redireccionarVentana-td").click(function () {
        redireccionarVentana($(this));
    });
    // ==================== ======== enviar por correo electronico ====== =====================
    $(".btnFrmNvrRcbPrCrr-td").click(function () {
        console.log("hola mundo");
        var scope = $(this);
        var url = scope.attr('value');
        var idRecibo = scope.attr('itemid') ;
        scope.prop("disabled",true);
         getModalViewWait( 0, 'Recibo de nómina', 'Enviando recibo '+idRecibo);
        $.post(url,{idRecibo:idRecibo},function(data){}).done(function (){}).fail(function(xhr, textStatus, errorThrown) {}).complete(function() {
			scope.prop("disabled", false);
                                                                 hideModalViewWait();
		});
    });
    //=============== ============ Activar boton eliminar documento despues de una petición ajax ======== Colaboradores ========
    $(".deleteDocument-td").click(function (){
    var scope = $(this);
    var tipo = scope.attr('id');
    var id = scope.val();
    var contextPathPageID = $("#contextPathPage").val();
    var url = contextPathPageID+"/colaborador/borrar-documento/"+tipo+"/"+id+".htm";
     getModalView(0,"Colaboradores","Eliminando el documento, por favor espere un momento...");
    $.post(url,{},function(data){}).done(function (){})
                 .fail(function(xhr, textStatus, errorThrown) {                     
                     getModalView(1,"Colaboradores","UPS!! Ocurrio un error al intentar eliminar el documento.");
                 })
                 .complete(function() {
                                                    hideModalViewWait();
                                                     getModalView(0,"Colaboradores","El documento fue eliminado con exito.");
		}).always(function() {
                                            var tableBody = $("#innerTableDocs");
                                            tableBody.fadeOut('fast');
                                            tableBody.empty();
                                                queryDocumentsOnRegistry(id,tipo);
                                              });
});
    //=============== ============ Activar boton eliminar documento despues de una petición ajax ======== Colaboradores Baja========
    $(".deleteDocumentBaja-td").click(function (){
    var scope = $(this);
        var url  = scope.val();
         getModalView(0,"Colaboradores","Eliminando el documento de baja, por favor espere un momento...");
        $.post(url,{},function(data){}).done(function (){})
                     .fail(function(xhr, textStatus, errorThrown) {                     
                         getModalView(1,"Colaboradores","UPS!! Ocurrio un error al intentar eliminar el documento de baja.");
                     })
                     .complete(function() {
                                                        hideModalViewWait();
                                                         getModalView(0,"Colaboradores","El documento de baja fue eliminado con exito.");
                    }).always(function() {
                                                var tableBody = $("#innerTableDocs");
                                                tableBody.fadeOut('fast');
                                                tableBody.empty();
                                                    var idAgremiado = $("#idAgremiado").val();
                                                    var idSolicitudBaja = $("#idSolicitudBaja").val();
                                                    var posicion = $("#posicion").val();
                                                    queryDocumentsOnBaja(idAgremiado,idSolicitudBaja,posicion);
                                                  });
});
}

function eventoEliminarClienteDelUsuario() {
    $(".removeClntsPrGrgr").unbind('click');
    $(".removeClntsPrGrgr").click(function () {

        var value = $("#clntsPrGrgr").val();

        var array = value.split(",");
        value = "";
        for (var i = 0; i < array.length; i++) {
            if (!(parseInt(array[i]) === parseInt($(this).val()))) {
                value += array[i] + ',';
            }
        }
        if (value.endsWith(',')) {
            value = value.substring(0, (value.length - 1));
        }
        $("#clntsPrGrgr").val(value);
        $("#optionClntsPrGrgr").append('<option value="' + $(this).val() + '">' + $(this).text() + '</option>');
        $(this).remove();
    });
}

function eventoEliminarClienteContratistaDelContrato() {
    $(".removeClntsCntrstsPrGrgr").unbind('click');
    $(".removeClntsCntrstsPrGrgr").click(function () {

        var value = $("#clntsCntrstsPrGrgr").val();

        var array = value.split(",");
        value = "";
        for (var i = 0; i < array.length; i++) {
            if (!(parseInt(array[i]) === parseInt($(this).val()))) {
                value += array[i] + ',';
            }
        }
        if (value.endsWith(',')) {
            value = value.substring(0, (value.length - 1));
        }
        $("#clntsCntrstsPrGrgr").val(value);
        $("#optionClntsCntrstsPrGrgr").append('<option value="' + $(this).val() + '">' + $(this).text() + '</option>');
        $(this).remove();
    });
}
function muestaOcultos() {
    $(".hidden-btn-action").slideDown('slow');
}

function AplicarModificacionSalario(selec) {
    if (selec) {
        $("#div_ModfSalario").fadeIn("slow");
        $("#newSalary").val(true);
    } else {
        $("#div_ModfSalario").fadeOut("slow");
        $("#newSalary").val(false);
        $("#supSelect").removeAttr('required'); // valida que se retire el atributo required de supSelect
        $("#salarioMensualQty").removeAttr('required'); // alida que se retire el atributo required de salarioMensualQty
        
    }
}

function fechRenovTerminada(fecha) {
    var today = new Date();
    if (today > fecha) {
        return true;
    }
}
$('.btn-descartar').bind('contextmenu', function(e) {
    // evito que se ejecute el evento
    e.preventDefault();
    // conjunto de acciones a realizar
    getModalView(2,"Payroll","Esta opción activa el botón para descartar un expediente.");
    var btn = $(this);
    btn.css('background-color','DARKCYAN');
    btn.prop('type','submit');
});
$('.btn-revisar-incidencia').bind('contextmenu', function (e){    
    // evito que se ejecute el evento
    e.preventDefault();
    getModalView(2,"Payroll","Esta opción activa el botón para marcar la incidencia como revisada.");
    var btn = $(this);
    btn.css('background-color','DARKCYAN');
    btn.prop('type','submit');
});
function validarCargaNomina() {
   var rchvDNmn = $("#rchvID").val();
   var fchDsdID = $("#fchDsdID").val();
   var fchHstID = $("#fchHstID").val();
                    
    if(rchvDNmn !== '' && fchDsdID.length >0 && fchHstID.length >0){
        if(fchDsdID < fchHstID ){
         getModalViewWait(2,'Cargar Nómina Del Cliente','Toma en cuenta que este proceso puede tomar algo de tiempo.');
        }
    }
}
 function daysInMonth(Y, M) { //Función encargada de contar los dias en los meses
    with (new Date(Y, M, 1, 12)) { // se delclará en un nuevo Date con los parametros Y, M años y meses
      setDate(-2); //se resta 2 al setDate
      return getDate(); //se devuelve getDate 
    }
}

function dateDiff(date1, date2) { //Función que calcula la diferencia entre las dos fechas dadas recibiendo como parametros date1 y date2
     var y1 = date1.getFullYear(), m1 = date1.getMonth(), d1 = date1.getDate(), //Se asignan los valores por serparo (dias, mes y años) a variables declaradas
     y2 = date2.getFullYear(), m2 = date2.getMonth(), d2 = date2.getDate();

    //Se intercatua con los valores por separado asignados a las variables
    if (d1 < d2) //Si el dia1 es menos que el dia2
    {
        m1--; //se le resta uno al mes
        d1 += daysInMonth(y2, m2); //se incrementa el resultado de la función DaysInMonth
    }
    if (m1 < m2) //si el mes1 es menosr al mes2
    {
        y1--; //se resta uno al año
        m1 += 12; //se incrementa 12 al mes1
    }
    return [y1 - y2, m1 - m2, d1 - d2]; //Se restan los años, meses y días y re regresan los valores en un vector
}
function saveStateSalariesEployee(){
    $("#sueldoSupTButon").bootstrapToggle('off');
}
/* ==============================================================
 =================== Created by: PabloSagoz =================== 
 ===================== Date: November 2016 ====================
 ============================================================== */
                    