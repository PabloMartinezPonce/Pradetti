/* ==============================================================
   =================== Created by: PabloSagoz =================== 
   ===================== Date: Enero 2017 ====================
   ============================================================== */
 function getModalViewConfirmation( optionCase ){
        var mb = $("#textConfirmation");
        var bt = $("#btnConfirmationOk");
        var option = parseInt(optionCase);
        switch(option){
            case 0:                
                mb.empty();
                mb.append('description case 0');
                bt.val('case o');
            break;
            case 1:
                mb.empty();
                mb.append('description case 1');
                bt.val('case 1');
            break;
           default:               
                mb.empty();
                mb.append('description default');
                bt.val('case default');
           break;
        }
       $('#myModalConfirmation').modal('show');
   }
   function closeWindow(){
       var bt = $("#btnConfirmationOk");
       console.log(bt.val());  
       $('#myModalConfirmation').modal('hide');
   }
   /* ==============================================================
   =================== Created by: PabloSagoz =================== 
   ===================== Date: Enero 2017 ====================
   ============================================================== */