/* ==============================================================
 =================== Created by: PabloSagoz =================== 
 ===================== Date: Febrero 2017 ====================
 ============================================================== */
$(document).ready(function () {
    $(".btnFrmNvrRcbPrCrr").click(function(event){
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
    $("#btnNvrRcbsLsClbrdrs").click(function (){
        var scope = $(this);
        var url = scope.attr('value');
        var cliente = $("#cliente").val();
        var diaRegistroDesde = $("#diaRegistroDesde").val();
        var diaRegistroHasta = $("#diaRegistroHasta").val();
        scope.prop("disabled",true);        
         getModalViewWait( 0, 'Recibos de nómina', 'Enviando los recibos a cada uno de los colaboradores, por favor espere un momento...');
         $.post(url,{cliente:cliente,diaRegistroDesde:diaRegistroDesde,diaRegistroHasta:diaRegistroHasta},
         function(data){}).done(function (){})
                 .fail(function(xhr, textStatus, errorThrown) {
                     getModalView(1,"Recibos de nómina","Ocurrio un problema al momento de intentar enviar los recibos.");
                 })
                 .complete(function() {
			scope.prop("disabled", false);
                                                                 hideModalViewWait();
                                                                  getModalView(0,"Recibos de nómina","Los recibos fueron enviados con éxito.");
		});
    });
    $("#btnGnrrRcbsZp").click(function (){
        var scope = $(this);
        var url = scope.attr('value');
        var cliente = $("#cliente").val();
        var diaRegistroDesde = $("#diaRegistroDesde").val();
        var diaRegistroHasta = $("#diaRegistroHasta").val();
        scope.prop("disabled",true);        
         getModalViewWait( 0, 'Recibos de nómina', 'Generando los recibos, almacenandolos dentro del archivo zip y enviandolos a tu correo electrónico, por favor espere un momento...');
         $.post(url,{cliente:cliente,diaRegistroDesde:diaRegistroDesde,diaRegistroHasta:diaRegistroHasta},
         function(data){}).done(function (){})
                 .fail(function(xhr, textStatus, errorThrown) {
                     getModalView(1,"Recibos de nómina","Ocurrio un problema al momento de intentar enviar los recibos dentro del archivo zip.");
                 })
                 .complete(function() {
			scope.prop("disabled", false);
                                                                 hideModalViewWait();
                                                                  getModalView(0,"Recibos de nómina","El archivo zip fue enviado con éxito.");
		});
    });
    $("#btnNvrmRcbsPDF").click(function (){
        var scope = $(this);
        var url = scope.attr('value');
        var cliente = $("#cliente").val();
        var diaRegistroDesde = $("#diaRegistroDesde").val();
        var diaRegistroHasta = $("#diaRegistroHasta").val();
        scope.prop("disabled",true);        
         getModalViewWait( 0, 'Recibos de nómina', 'Creando los recibos en un solo archivo .pdf y enviando a tu correo electrónico, por favor espere un momento... ');
         $.post(url,{cliente:cliente,diaRegistroDesde:diaRegistroDesde,diaRegistroHasta:diaRegistroHasta},
         function(data){}).done(function (){})
                 .fail(function(xhr, textStatus, errorThrown) {
                     getModalView(1,"Recibos de nómina","Ocurrio un problema al momento de intentar enviar el documento");
                 })
                 .complete(function() {
			scope.prop("disabled", false);
                                                                 hideModalViewWait();
                                                                  getModalView(0,"Recibos de nómina","Documento enviado por favor revisa tu bandeja de entrada.");
		});
    });
    $("#salarioDiario").focusout(function (){
        var scope = $(this);
        var url =  $("#contextPathPage").val();
        var cliente = $("#selectClientes").val();
        var sd = $("#salarioDiario").val();        
        var idagremiado = $("#idagremiado").val();    
        var btnGuardar = $("#btnGrdrDtsLbrls");
         if(parseInt(sd.length) >= 3){
                btnGuardar.prop("disabled", true);
                url = url+"/colaborador/contratistas-del-cliente.htm";
                scope.prop("disabled",true); 
                scope.prop("value",""); 
                scope.prop("placeholder","Calculando...");        
                 getModalViewWait( 0, 'Colaboradores', 'Calculando los salarios<br><br>Por favor espere...');
                 $.post(url,{cliente:cliente,sd:sd,idagremiado:idagremiado},function(data){}).done(function (){})
                         .fail(function(xhr, textStatus, errorThrown) {
                                hideModalViewWait();
                             getModalView(1,"Colaboradores","Ocurrio un problema al momento de intentar calcular los salarios");
                            scope.prop("value",'');      
                            scope.prop("placeholder","ERROR...");        
                            btnGuardar.prop("disabled", false);
                         })
                         .complete(function(jqXHR) {                     
                                var responseText = jQuery.parseJSON(jqXHR.responseText);
                                scope.prop("disabled", false);
                                scope.prop("value",sd);      
                                $('#salarioDiarioIntegrado').val(responseText.salarioDiarioIntegrado);
                                $('#salarioDiarioIntegradoHddn').val(responseText.salarioDiarioIntegrado);
                                $('#salariosImss').val(responseText.salariosImss);
                                $('#salariosImssHddn').val(responseText.salariosImss);
                                hideModalViewWait();
                                btnGuardar.prop("disabled", false);
                        });
            }else{
                scope.prop("value",""); 
                scope.prop("placeholder","Valor incorrecto");      
                scope.focus();
            }
    });
    //Cambio de los contratistas por el cliente seleccionado en datos laborales de un colaborador
    $("#selectClientes").change(function() {
        var selectClientes = $("#selectClientes").val();
        var contextPathPageID = $("#contextPathPage").val();
        $("#salarioDiario").val('');
        $("#salarioDiario").attr('placeholder','Campo requerido');
        contextPathPageID = contextPathPageID+"/colaborador/contratistas-del-cliente/"+selectClientes+".htm";
        var btnGuardar = $("#btnGrdrDtsLbrls");
        btnGuardar.prop("disabled", true);
        getModalViewWait( 0, 'Colaboradores', 'Obteniendo la lista de Contratistas<br><br>Por favor espere...');
        $.post(contextPathPageID,{},function(data){}).done(function (){})
             .fail(function(xhr, textStatus, errorThrown) {
                            hideModalViewWait();
                getModalView(1,"Colaboradores","Ocurrio un problema al tratar de obtener la lista de Contratistas");
                btnGuardar.prop("disabled", false);
             })
             .complete(function(jqXHR) {                     
                var response = jQuery.parseJSON(jqXHR.responseText);
                $("#selectBoxContratistas").slideUp('slow');
                $("#selectBoxContratistas").empty();
                $.each(response, function(i, item){
                    $("#selectBoxContratistas").append('<option value="'+i+'">'+response[i]+'</option>');
                });                        
                $("#selectBoxContratistas").slideDown('fast');
                hideModalViewWait();
                btnGuardar.prop("disabled", false);
        });
    });   
$("#selectClienteOption").change(function() {
        getByClienteMyContratos();
    });
$("#selectClientOptionPeriod").change(function() {
        getMyContratosByCliente();
    }); 
    $("#selectBoxContratistasWithDocument").change(function() {
         fillingDataStepSchemas();
    });
    // envio de documentos al controlador
    $(".subirArchivosRegistro").click(function (){            
            var btn = $(this);
            btn.removeClass('btn-success');
            btn.attr("title","cargar archivo");
            var btnID = $(this).val();
            var cadena = $("#_"+btnID+"_").val();
            if(cadena){
                var input = $("#_"+btnID+"_");
                var contextPathPageID = $("#contextPathPage").val();
                var btnVerificar = $("#btnVrfcrClbrdr");
                btnVerificar.prop("disabled", true);
                var formData = new FormData();
                var agremiado = $("#idAgremiado").val();
                //obtenemos un array con los datos del archivo
                var file = $("#_"+btnID+"_")[0].files[0];
                //obtenemos el nombre del archivo
                var fileName = file.name;
                //obtenemos el tamaño del archivo
                var fileSize = file.size;
                if(fileSize>0){
                    getModalViewWait( 0, 'Colaboradores', 'Procesando archivos, por favor espere...');
                    var tipoDoc = btnID;
                    formData.append('tipo',btnID);
                    formData.append('archivo',file);
                    formData.append('idAgremiado',agremiado);
                    formData.append('name',fileName);
                    var url = contextPathPageID+"/colaborador/documento.htm";                    
                    btnVerificar.prop("disabled", true);
                    var request = $.ajax({    url:url,
                                       type: 'POST',
                                        data: formData,
                                        cache: false,
                                        contentType: false,
                                        processData: false}).done(function( data, textStatus, jqXHR ) {
                                            btnVerificar.prop("disabled", false); 
                                            btn.addClass('btn-success');
                                            btn.attr("title","ARCHIVO CARGADO CORRECTAMENTE");
                                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                                            getModalView(1,"Colaboradores","Ocurrio un problema al intentar almacenar el documento "+fileName);
                                            btn.removeClass('btn-success');
                                            btn.attr("title","cargar archivo");
                                        }).always(function() { 
                                            queryDocumentsOnRegistry(agremiado,tipoDoc);
                                            input.val("");
                                            input.attr("placeholder","Documento Almacand0 correctamente");
                                        });                  

                    }else{
                        getModalView(1,"Colaboradores","Por favor selecciona un archivo valido");                           
                    }                  
                hideModalViewWait();
                btnVerificar.prop("disabled", false); 
            }else{
                    getModalView(1,"Colaboradores","Por favor selecciona un archivo");                
            }
        });

    $(".subirArchivosContrato").click(function (){            
            var btn = $(this);
            btn.removeClass('btn-success');
            btn.attr("title","cargar archivo");
            var btnID = $(this).val();
            var cadena = $("#_"+btnID+"_").val();
            if(cadena){
                var input = $("#_"+btnID+"_");
                var contextPathPageID = $("#contextPathPage").val();
                var btnVerificar = $("#btnVrfcrClbrdr");
                btnVerificar.prop("disabled", true);
                var formData = new FormData();
                var agremiado = $("#idAgremiado").val();
                //obtenemos un array con los datos del archivo
                var file = $("#_"+btnID+"_")[0].files[0];
                //obtenemos el nombre del archivo
                var fileName = file.name;
                //obtenemos el tamaño del archivo
                var fileSize = file.size;
                if(fileSize>0){
                    getModalViewWait( 0, 'Colaboradores', 'Procesando archivos, por favor espere...');
                    var tipoDoc = btnID;
                    formData.append('tipo',btnID);
                    formData.append('archivo',file);
                    formData.append('idAgremiado',agremiado);
                    formData.append('name',fileName);
                    var url = contextPathPageID+"/colaborador/documento.htm";                    
                    btnVerificar.prop("disabled", true);
                    var request = $.ajax({    url:url,
                                       type: 'POST',
                                        data: formData,
                                        cache: false,
                                        contentType: false,
                                        processData: false}).done(function( data, textStatus, jqXHR ) {
                                            btnVerificar.prop("disabled", false); 
                                            btn.addClass('btn-success');
                                            btn.attr("title","ARCHIVO CARGADO CORRECTAMENTE");
                                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                                            getModalView(1,"Colaboradores","Ocurrio un problema al intentar almacenar el documento "+fileName);
                                            btn.removeClass('btn-success');
                                            btn.attr("title","cargar archivo");
                                        }).always(function() { 
                                            queryDocumentsOnRegistry(agremiado,tipoDoc);
                                            input.val("");
                                            input.attr("placeholder","Documento Almacando correctamente");
                                        });                  

                    }else{
                        getModalView(1,"Colaboradores","Por favor selecciona un archivo valido");                           
                    }                  
                hideModalViewWait();
                btnVerificar.prop("disabled", false); 
            }else{
                    getModalView(1,"Colaboradores","Por favor selecciona un archivo");                
            }
        });
        
    $(".subirArchivosImss").click(function (){            
            var btn = $(this);
            btn.removeClass('btn-success');
            btn.attr("title","cargar archivo");
            var btnID = $(this).val();
            var cadena = $("#_"+btnID+"_").val();
            if(cadena){
                var input = $("#_"+btnID+"_");
                var contextPathPageID = $("#contextPathPage").val();
                var btnVerificar = $("#btnVrfcrClbrdr");
                btnVerificar.prop("disabled", true);
                var formData = new FormData();
                var agremiado = $("#idAgremiado").val();
                //obtenemos un array con los datos del archivo
                var file = $("#_"+btnID+"_")[0].files[0];
                //obtenemos el nombre del archivo
                var fileName = file.name;
                //obtenemos el tamaño del archivo
                var fileSize = file.size;
                if(fileSize>0){
                    getModalViewWait( 0, 'Colaboradores', 'Procesando archivos, por favor espere...');
                    var tipoDoc = btnID;
                    formData.append('tipo',btnID);
                    formData.append('archivo',file);
                    formData.append('idAgremiado',agremiado);
                    formData.append('name',fileName);
                    var url = contextPathPageID+"/colaborador/documento.htm";                    
                    btnVerificar.prop("disabled", true);
                    var request = $.ajax({    url:url,
                                       type: 'POST',
                                        data: formData,
                                        cache: false,
                                        contentType: false,
                                        processData: false}).done(function( data, textStatus, jqXHR ) {
                                            btnVerificar.prop("disabled", false); 
                                            btn.addClass('btn-success');
                                            btn.attr("title","ARCHIVO CARGADO CORRECTAMENTE");
                                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                                            getModalView(1,"Colaboradores","Ocurrio un problema al intentar almacenar el documento "+fileName);
                                            btn.removeClass('btn-success');
                                            btn.attr("title","cargar archivo");
                                        }).always(function() { 
                                            queryDocumentsOnRegistry(agremiado,tipoDoc);
                                            input.val("");
                                            input.attr("placeholder","Documento Almacando correctamente");
                                        });                  

                    }else{
                        getModalView(1,"Colaboradores","Por favor selecciona un archivo valido");                           
                    }                  
                hideModalViewWait();
                btnVerificar.prop("disabled", false); 
            }else{
                    getModalView(1,"Colaboradores","Por favor selecciona un archivo");                
            }
        });
        
    $(".docBajas").click(function (){            
            var btn = $(this);
            btn.removeClass('btn-success');
            btn.attr("title","cargar archivo");
            var btnID = $(this).val();
            var cadena = $("#_"+btnID+"_").val();
            if(cadena){
                var input = $("#_"+btnID+"_");
                var contextPathPageID = $("#contextPath").val();
                var idAgremiado = $("#idAgremiado").val();
                var idSolicitudBaja = $("#idSolicitudBaja").val();
                var posicion = $("#posicion").val();
                var btnVerificar = $("#btnVrfcrClbrdr");
                btnVerificar.prop("disabled", true);
                var formData = new FormData();
                //obtenemos un array con los datos del archivo
                var file = $("#_"+btnID+"_")[0].files[0];
                //obtenemos el nombre del archivo
                var fileName = file.name;
                //obtenemos el tamaño del archivo
                var fileSize = file.size;
                if(fileSize>0){
                    getModalViewWait( 0, 'Colaboradores', 'Procesando archivos, por favor espere...');
                    formData.append('tipo',btnID);
                    formData.append('archivo',file);
                    formData.append('idAgremiado',idAgremiado);
                    formData.append('name',fileName);
                    formData.append('idSolicitudBaja',idSolicitudBaja);
                    formData.append('posicion',posicion);
                    var url = contextPathPageID+"/colaborador/documento-baja.htm";                    
                    btnVerificar.prop("disabled", true);
                    var request = $.ajax({    url:url,
                                       type: 'POST',
                                        data: formData,
                                        cache: false,
                                        contentType: false,
                                        processData: false}).done(function( data, textStatus, jqXHR ) {
                                            btnVerificar.prop("disabled", false); 
                                            btn.addClass('btn-success');
                                            btn.attr("title","ARCHIVO CARGADO CORRECTAMENTE");
                                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                                            getModalView(1,"Colaboradores","Ocurrio un problema al intentar almacenar el documento "+fileName);
                                            btn.removeClass('btn-success');
                                            btn.attr("title","cargar archivo");
                                        }).always(function() { 
                                            setTimeout(queryDocumentsOnBaja(idAgremiado,idSolicitudBaja,posicion), 3000);
                                            input.val("");
                                            input.attr("placeholder","Documento Almacand0 correctamente");
                                        });                  

                    }else{
                        getModalView(1,"Colaboradores","Por favor selecciona un archivo valido");                           
                    }                  
                hideModalViewWait();
                btnVerificar.prop("disabled", false); 
            }else{
                    getModalView(1,"Colaboradores","Por favor selecciona un archivo");                
            }
        });
    //solicitud de elimnación de un documento al controlador
    $(".deleteDocument").click(function (){
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
    
    $(".deleteDocumentBaja").click(function (){
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
                                                    setTimeout(queryDocumentsOnBaja(idAgremiado,idSolicitudBaja,posicion), 3000);
                                                  });
    });
    //Se ejecuta la petición para actualizar los colaboradores pendientes por estado
    getStats();
});
//Se obtienen los contratos realizados por el cliente seleccionado    
function getByClienteMyContratos(){
        var selectClientes = $("#selectClienteOption").val();
        var selectContratistas = $("#selectBoxContratistasWithDocument");
        selectContratistas.empty();
        if(!(selectClientes === "")){
                var contextPathPageID = $("#contextPathPage").val();
                contextPathPageID = contextPathPageID+"/colaborador/contratos-cliente/"+selectClientes+".htm";
                var btnGuardar = $("#btnGrdr");
                btnGuardar.prop("disabled", true);
                getModalViewWait( 0, 'Colaboradores', 'Obteniendo la lista de Contratistas<br><br>Por favor espere...');
                $.post(contextPathPageID,{},function(data){}).done(function (){})
                     .fail(function(xhr, textStatus, errorThrown) {
                                    hideModalViewWait();
                        getModalView(1,"Colaboradores","Ocurrio un problema al tratar de obtener la lista de Contratistas");
                        selectContratistas.empty();
                        btnGuardar.prop("disabled", false);
                        $(".infoContrato").fadeOut("slow");
                     })
                     .complete(function(jqXHR) {
                         var cntExists = $("#cnt").val() !== undefined;
                         var cntID = $("#cnt").val();
                        delContrato = jQuery.parseJSON(jqXHR.responseText);
                        selectContratistas.empty();
                        if(delContrato.length>0){
                            $.each(delContrato, function(i, item){
                                if(item.nombreContrato===null){
                                    item.nombreContrato = "[Por Definir]";                        
                                }
                                if(cntExists){
                                    if(parseInt(cntID)  === item.idContratista){
                                        selectContratistas.append('<option value="'+item.idContratoEmpresas+'">'+item.nombreContrato+' / '+item.contratista+'</option>');
                                    }
                                }else{     
                                    selectContratistas.append('<option value="'+item.idContratoEmpresas+'">'+item.nombreContrato+' / '+item.contratista+'</option>');
                                }
                            });        
                            if( selectContratistas.val().length>0){
                                fillingDataStepSchemas();
                                $(".infoContrato").fadeIn("slow");
                            }
                            hideModalViewWait();
                            btnGuardar.prop("disabled", false);
                        }else{                            
                            hideModalViewWait();             
                            cleaningDataStepSchemas();
                            selectContratistas.append('<option value="">EL CLIENTE NO TIENE CONTRATOS REGISTRADOS</option>');
                        }
                });
            }else{                
                cleaningDataStepSchemas();
            }
    
}

//Se obtienen los contratos realizados por el cliente seleccionado    
function getMyContratosByCliente(){
        var selectClientes = $("#selectClienteOption").val();
        var selectContratistas = $("#selectBoxContratistasWithDocument");
        selectContratistas.empty();
        if(!(selectClientes === "")){
                var contextPathPageID = $("#contextPathPage").val();
                contextPathPageID = contextPathPageID+"/colaborador/contratos-cliente/"+selectClientes+".htm";
                var btnGuardar = $("#btnGrdr");
                btnGuardar.prop("disabled", true);
                getModalViewWait( 0, 'Colaboradores', 'Obteniendo la lista de Contratistas<br><br>Por favor espere...');
                $.post(contextPathPageID,{},function(data){}).done(function (){})
                     .fail(function(xhr, textStatus, errorThrown) {
                                    hideModalViewWait();
                        getModalView(1,"Colaboradores","Ocurrio un problema al tratar de obtener la lista de Contratistas");
                        selectContratistas.empty();
                        btnGuardar.prop("disabled", false);
                        $(".infoContrato").fadeOut("slow");
                     })
                     .complete(function(jqXHR) {
                         var cntExists = $("#cnt").val() !== undefined;
                         var cntID = $("#cnt").val();
                        delContrato = jQuery.parseJSON(jqXHR.responseText);
                        selectContratistas.empty();
                        if(delContrato.length>0){
                            $.each(delContrato, function(i, item){
                                if(item.nombreContrato===null){
                                    item.nombreContrato = "[Por Definir]";                        
                                }
                                if(cntExists){
                                    if(parseInt(cntID)  === item.idContratista){
                                        selectContratistas.append('<option value="'+item.idContratoEmpresas+'">'+item.nombreContrato+' / '+item.contratista+'</option>');
                                    }
                                }else{     
                                    selectContratistas.append('<option value="'+item.idContratoEmpresas+'">'+item.nombreContrato+' / '+item.contratista+'</option>');
                                }
                            });        
                            if( selectContratistas.val().length>0){
                                fillingDataStepSchemas();
                                $(".infoContrato").fadeIn("slow");
                            }
                            hideModalViewWait();
                            btnGuardar.prop("disabled", false);
                        }else{                            
                            hideModalViewWait();             
                            cleaningDataStepSchemas();
                            selectContratistas.append('<option value="">EL CLIENTE NO TIENE CONTRATOS REGISTRADOS</option>');
                        }
                });
            }else{                
                cleaningDataStepSchemas();
            }
}

function saveContratoParaColaboradores(){
    var frmAction = $("#formCrearNuevoContratoColaboradores").attr('action');
    var idContrato = $("#idContrato").val();
    var nmbrCntrt = $("#nmbrCntrtID").val();
    var estatus = $("#estatus").val();
    var contrato = $("#aggremntArea").val();
    if(estatus.localeCompare("on")){     
        estatus = 'Sí';
    }else{
        estatus = "No";
    }
    
    if((frmAction.trim().length > 10)&&(nmbrCntrt.trim().length > 10)&&(contrato.trim().length > 100)){
            getModalView(0,"Sistema","Autoguardar<br> el contrato se esta guardando, por favor espere un momento...");
        $.post(frmAction,{idContrato:idContrato,nmbrCntrt:nmbrCntrt,estatus:estatus,contrato:contrato},
         function(data){}).done(function (){})
                 .fail(function(xhr, textStatus, errorThrown) {                     
                     getModalView(1,"Sistema","UPS!! Ocurrio un error al intentar ejecutar el autoguardado");
                 })
                 .complete(function() {
                                                    hideModalViewWait();
                                                     getModalView(0,"Sistema","El contrato se autoguardo correctamente, ya puede continuar con la edición del mismo.");
                                                     startTimeOutContratoParaColaboradores();
		});
    }    
}
function saveContratoParaClientes(){
    var frmAction = $("#formCrearNuevoContratoClientes").attr('action');
    var idContrato = $("#idContrato").val();
    var nmbrCntrt = $("#nmbrCntrtID").val();
    var estatus = $("#estatus").val();
    var contrato = $("#aggremntArea").val();
    if(estatus.localeCompare("on")){     
        estatus = 'Sí';
    }else{
        estatus = "No";
    }
    
    if((frmAction.trim().length > 10)&&(nmbrCntrt.trim().length > 10)&&(contrato.trim().length > 100)){
            getModalView(0,"Sistema","Autoguardar<br> el contrato se esta guardando, por favor espere un momento...");
        $.post(frmAction,{idContrato:idContrato,nmbrCntrt:nmbrCntrt,estatus:estatus,contrato:contrato},
         function(data){}).done(function (){})
                 .fail(function(xhr, textStatus, errorThrown) {                     
                     getModalView(1,"Sistema","UPS!! Ocurrio un error al intentar ejecutar el autoguardado");
                 })
                 .complete(function() {
                                                    hideModalViewWait();
                                                     getModalView(0,"Sistema","El contrato se autoguardo correctamente, ya puede continuar con la edición del mismo.");
                                                     startTimeOutContratoParaClientes();
		});
    }    
}
var timeOuts = [];
function startTimeOutContratoParaColaboradores(){
    stopTimeOutContratoParaColaboradores();
    timeOuts.push(setTimeout(function (){
        saveContratoParaColaboradores();
    }, 1.62e+6 ));
}
function stopTimeOutContratoParaColaboradores(){
    for (var i = 0; i < timeOuts.length; i++) {
        clearTimeout(timeOuts[i]);
    }
    timeOuts = [];
}
function startTimeOutContratoParaClientes(){
    stopTimeOutContratoParaClientes();
    timeOuts.push(setTimeout(function (){
        saveContratoParaClientes();
    }, 1.62e+6 ));
}
function stopTimeOutContratoParaClientes(){
    for (var i = 0; i < timeOuts.length; i++) {
        clearTimeout(timeOuts[i]);
    }
    timeOuts = [];
}
//funcion encargada de consultar los documentos 
function  queryDocumentsOnRegistry(idAgremiado,tipoDoc){
            var tableBody = $("#innerTableDocs");     
            tableBody.fadeOut('fast','linear',function (){
                        var contextPathPageID = $("#contextPathPage").val();
                        var originalContext = contextPathPageID;
                        contextPathPageID = contextPathPageID+"/colaborador/documentos-del-colaborador.htm";
                        var tableBody = $("#innerTableDocs");
                        $.post(contextPathPageID,{idAgremiado:idAgremiado,doc:tipoDoc},function(data){}).done(function (){})
                             .fail(function(xhr, textStatus, errorThrown) {
                                getModalView(1,"Colaboradores","Ocurrio un problema al tratar de obtener los documentos que ya posee el colaborador");
                             })
                             .complete(function(jqXHR) {                     
                                var response = jQuery.parseJSON(jqXHR.responseText);                              
                                tableBody.empty();
                                $.each(response, function(i, item){
                                         for (x in response[i]) {
                                             var txt = "<tr>"+
                                                                "<td> <span   class='glyphicon glyphicon-ok-circle' aria-hidden='true'><\/span> <\/td>"+
                                                                "<td>"+response[i][x]+"<\/td>"+
                                                                "<td>"+
                                                                        "<button type='button' class='btn btn-default redireccionarVentana-td' value='"+originalContext+"/colaborador/ver-documentos/"+x+"/"+idAgremiado+".htm' title='Ver documento'>"+
                                                                            "<span class='glyphicon glyphicon-eye-open' aria-hidden='true'><\/span>"+
                                                                        "<\/button>"+
                                                                    "<\/td>"+
                                                                "<td>"+
                                                                    "<button type='button' class='btn btn-default deleteDocument-td' id="+x+" value='"+idAgremiado+"' title='Eliminar documento'>"+
                                                                        "<span class='glyphicon glyphicon-trash' aria-hidden='true'><\/span>"+
                                                                    "<\/button>"+
                                                                "<\/td>"+
                                                            "<\/tr>";
                                           tableBody.append(txt);
                                        }
                                });                        

                        bindEvents();
                        }).always(function() {                
                            tableBody.fadeIn('slow');
                         });
            });
}
//funcion encargada de consultar los documentos de baja
function  queryDocumentsOnBaja(idAgremiado,idSolicitudBaja,posicion){
            var tableBody = $("#innerTableDocs");     
            var modulo = $("#modulo").val();     
            tableBody.fadeOut('fast','linear',function (){
                    var contextPathPageID = $("#contextPath").val();
                        var originalContext = contextPathPageID;
                        contextPathPageID = contextPathPageID+"/colaborador/documentos-de-la-solicitud-de-baja.htm";
                        var tableBody = $("#innerTableDocs");
                        $.post(contextPathPageID,{idAgremiado:idAgremiado,solicitudBaja:idSolicitudBaja,posicion:posicion,modulo:modulo},function(data){}).done(function (){})
                             .fail(function(xhr, textStatus, errorThrown) {
                                getModalView(1,"Colaboradores","Ocurrio un problema al tratar de obtener los documentos que ya posee la solicitud de baja");
                             })
                             .complete(function(jqXHR) {                     
                                var response = jQuery.parseJSON(jqXHR.responseText);                              
                                tableBody.empty();
                                $.each(response, function(i, item){
                                         for (x in response[i]) {
                                             var txt = "<tr>"+
                                                                "<td> <span   class='glyphicon glyphicon-ok-circle' aria-hidden='true'><\/span> <\/td>"+
                                                                "<td>"+response[i][x]+"<\/td>"+
                                                                "<td>"+
                                                                        "<button type='button' class='btn btn-default redireccionarVentana-td' value='"+originalContext+"/colaborador/ver-documento-de-baja/"+posicion+"/"+idAgremiado+"/"+idSolicitudBaja+"/"+x+".htm' title='Ver documento'>"+
                                                                            "<span class='glyphicon glyphicon-eye-open' aria-hidden='true'><\/span>"+
                                                                        "<\/button>"+
                                                                    "<\/td>"+
                                                                "<td>"+
                                                                    "<button type='button' class='btn btn-default deleteDocumentBaja-td' value='"+originalContext+"/colaborador/borrar-documento-de-baja/"+posicion+"/"+idAgremiado+"/"+idSolicitudBaja+"/"+x+".htm' title='Eliminar documento'>"+
                                                                        "<span class='glyphicon glyphicon-trash' aria-hidden='true'><\/span>"+
                                                                    "<\/button>"+
                                                                "<\/td>"+
                                                            "<\/tr>";
                                           tableBody.append(txt);
                                        }
                                });                        

                        bindEvents();
                        }).always(function() {                
                            tableBody.fadeIn('slow');
                         });
            });
}
function getStats(){
    var contex = $("#contextPath") .val();
    getPendientesTotales(contex);
}
function  getPendientesTotales(context){
    var url = context+"/colaboradores/rest/pendientes-totales.json";
    $.get(url,{},function(data){}).done(function (){}) .fail(function(xhr, textStatus, errorThrown) {}).complete(function(jqXHR) {
            var response = jQuery.parseJSON(jqXHR.responseText);
            $.each(response, function(i, item){   
                        switch (i) {
                            case "renovación.solicitada":
                                var a = $(".renovacionsolicitada");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                            case "alta.en.proceso":
                                var a = $(".altaenproceso");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                            case "baja.solicitada":
                                var a = $(".bajasolicitada");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                            case "expediente.sin.contrato":
                                var a = $(".expedientesincontrato");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                            case "baja.pendiente":
                                var a = $(".bajapendiente");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                            case "baja.por.finalizar":
                                var a = $(".bajaporfinalizar");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                            case "solicitud.de.renovación":
                                var a = $(".solicitudderenovacion");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                            case "baja":
                                var a = $(".baja");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                            case "expediente.incompleto":
                                var a = $(".expedienteincompleto");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                            case "v.ob.o":
                                var a = $(".vobo");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;     
                            case "pendientes.totales":
                                var a = $(".pendientestotales");
                                a.slideUp('slow');
                                a.empty();
                                a.text(item);
                                a.slideDown('fast');
                              break;
                          case "baja.sin.firmar":
                                var a = $(".bajasinfirmar");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                              break;
                    }
               });
        }).always(function() {            
            getContratosPorVencer(context);
        });
}
function getContratosPorVencer(contex){
    var url = contex+"/colaboradores/rest/contratos-por-terminar.json";
        $.post(url,{},function(data){}).done(function (){}) .fail(function(xhr, textStatus, errorThrown) {}).complete(function(jqXHR) {
            var response = jQuery.parseJSON(jqXHR.responseText);
            $.each(response, function(i, item){
                            var a = $(".porVencer");
                                a.slideUp('slow',function (){
                                    a.empty();
                                    a.text(item);
                                    a.slideDown('slow');                                    
                                });
                            var ttls = $(".pendientestotales");
                            var ttlsInt = parseInt(ttls.text());
                            var cntrt = parseInt(item);
                           ttlsInt = + ttlsInt + cntrt;
                            ttls.empty();
                            ttls.text(ttlsInt);
               });
        });
    
}
var delContrato;
function fillingDataStepSchemas(){
    var selectContratistaContratos = $("#selectBoxContratistasWithDocument").val();
    if(selectContratistaContratos.length>0){      
            var find = false;
            $.each(delContrato, function(i, item){
                if(parseInt(item.idContratoEmpresas)===parseInt(selectContratistaContratos)){
                    $("#clienteName").text(item.cliente);
                    $("#contratistaName").text(item.contratista);
                    $("#contratoName").text(item.nombreContrato);
                    $("#fechaContrato").text(item.fechaContrato);
                    find = true;
                    return false;
                }
            });    
            if(find){
                var contextPathPageID = $("#contextPathPage").val();
                var selectContrato = $("#selectBoxContratistasWithDocument").val();
                var contextPathPageSups = contextPathPageID+"/colaborador/sups-contrato/"+selectContrato+".htm";
                var contextPathPageCds = contextPathPageID+"/colaborador/cd-contrato/"+selectContrato+".htm";
                $.post(contextPathPageSups,{},function(data){}).done(function (){})
                        .fail(function(xhr, textStatus, errorThrown) {
                            getModalView(2,"Colaboradores","Ocurrio un problema al tratar de obtener la lista de salarios unicos profesionales");
                            $("#listSUPS > li").remove();
                        })
                        .complete(function(jqXHR) {                     
                           var listSUPs = jQuery.parseJSON(jqXHR.responseText);
                           $("#listSUPS > li").remove();
                           if(listSUPs.length===0){
                                $("#listSUPS").append('<li class="list-group-item list-group-item-warning">No cuenta con Salarios</li>');
                           }else{
                            $.each(listSUPs, function(i, item){
                                    $("#listSUPS").append('<li class="list-group-item list-group-item-info"><span class="badge">$ '+item.pesosDiarios+' MXN</span>'+item.oficio+'</li>');
                             });  
                         }
               });
               $.post(contextPathPageCds,{},function (data) {}).done(function(){})
                       .fail(function(xhr,textStatus, errorThrown){
                            getModalView(2,"Colaboradores","Ocurrio un problema al tratar de obtener la lista del catalogo documental");
                             $("#listCDs > li").remove();
                        })
                       .complete(function(jqXHR) {                     
                           var listCDs = jQuery.parseJSON(jqXHR.responseText);
                            $("#listCDs > li").remove();
                           if(listCDs.length===0){
                                $("#listCDs").append('<li class="list-group-item list-group-item-warning">No cuenta con documentos</li>');
                           }else{
                                $.each(listCDs, function(i, item){
                                    $("#listCDs").append('<li class="list-group-item list-group-item-info">'+item.nombreDocumento+'</li>');
                             });
                         }
            });
        }
    }else{
        cleaningDataStepSchemas();
    }
}
function cleaningDataStepSchemas(){
    $(".infoContrato").fadeOut("fast");        
    $("#listSUPS > li").remove();
    $("#listCDs > li").remove();
    $("#clienteName").text('');
    $("#contratistaName").text('');
    $("#contratoName").text('');
    $("#fechaContrato").text('');
}
function saveStateSalariesEployeeAjax(){
    $("#sueldoSupTButon").bootstrapToggle('off');
}
function supsSelectEmployee(){    
    var contextPathPageID = $("#contextPathPage").val();
    var selectContrato = $("#contratoId").val();    
    var contextPathPageSups = contextPathPageID+"/colaborador/sups-contrato/"+selectContrato+".htm";
    $.post(contextPathPageSups,{},function(data){}).done(function (){})
            .fail(function(xhr, textStatus, errorThrown) {
                getModalView(1,"Colaboradores","Ocurrio un problema al tratar de obtener la lista de salarios unicos profesionales");
                saveStateSalariesEployeeAjax();
            })
            .complete(function(jqXHR) {                     
               var listSUPs = jQuery.parseJSON(jqXHR.responseText);    
       var prevVal = $("#supSelect").val();
            $("#supSelect").empty();
               if(listSUPs.length===0){
                getModalView(2,"Colaboradores","El contrato no cuenta con SUP's");
                saveStateSalariesEployeeAjax();
               }else{
                   if(prevVal===null){
                        $("#supSelect").append('<option title="" selected="selected" value=""></option>');
                    }
                $.each(listSUPs, function(i, item){
                    if((prevVal!==null)&&(parseInt(prevVal)===item.idSalarioUnicoProfesional)){
                        $("#supSelect").append('<option selected="selected"  title="'+item.pesosDiarios+'" value="'+item.idSalarioUnicoProfesional+'">'+item.oficio+'</option>');                        
                    }else{
                        $("#supSelect").append('<option title="'+item.pesosDiarios+'" value="'+item.idSalarioUnicoProfesional+'">'+item.oficio+'</option>');
                    }
                 });                   
                $("#salarioDiarioSup").val($("#supSelect option:selected").attr('title'));
             }
   });
}
function existsSUP(){
                $("#supSelect").attr('required', 'required'); 
                $("#salarioDiario").removeAttr('required');
                $("#salarioMensualQty").removeAttr('required');
                $("#salarioMensualQtyNml").removeAttr('required');
                $("#salarioDiarioSup").val('');
                $("#salarioDiarioIntegrado").val('');
                $("#salarioDiarioIntegradoHddn").val('');
                $("#salariosImss").val('');
                $("#salariosImssHddn").val('');
                $("#salarioDiario").val('');
                $("#salarioMensualQty").val('');
                $("#salarioMensualQtyNml").val('');
                supsSelectEmployee();
}
/* ==============================================================
 =================== Created by: PabloSagoz =================== 
 ===================== Date: Febrero 2017 ====================
 ============================================================== */


