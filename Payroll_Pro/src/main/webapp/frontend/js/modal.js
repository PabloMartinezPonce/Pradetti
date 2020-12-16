/* ==============================================================
   =================== Created by: PabloSagoz =================== 
   ===================== Date: Diciembre 2016 ====================
   ============================================================== */
 function getModalView( icon, title, description){
        var it = $("#iconTitle");
        var mt = $("#modalTitle");
        var mb = $("#modalBody");
        var option = parseInt(icon);
        switch(option){
            case 0:
                it.removeClass();
                it.addClass('glyphicon');
                it.addClass('glyphicon-thumbs-up');
                it.addClass('modal-ok');
                mt.removeClass();
                mt.addClass('modal-ok');
            break;
            case 1:
                it.removeClass();
                it.addClass('glyphicon');
                it.addClass('glyphicon-thumbs-down');
                it.addClass('modal-fail');
                mt.removeClass();
                mt.addClass('modal-fail');
            break;
           default:
                it.removeClass();
                it.addClass('glyphicon');
                it.addClass('glyphicon-hand-right');
                it.addClass('modal-info');
                mt.removeClass();
                mt.addClass('modal-info');
           break;
        }
        mt.empty();
        if(title.length>46){
            title = title.substring(0, 43);
            title= title.concat('...');
        }
        mt.append(title);
        mb.empty();
        mb.append(description);
       $('#myModales').modal('show');
   }
   /* ==============================================================
   =================== Created by: PabloSagoz =================== 
   ===================== Date: Diciembre 2016 ====================
   ============================================================== */

