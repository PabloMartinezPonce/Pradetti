/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function($) {

  $.fn.pickList = function(options) {

    var opts = $.extend({}, $.fn.pickList.defaults, options);

    this.controll = function() {
      var pickThis = this;

      pickThis.find(".pAdd").on('click', function() {
        var p = pickThis.find(".pickData option:selected");
        p.clone().appendTo(pickThis.find(".pickListResult"));
        p.remove();
      });

      pickThis.find(".pAddAll").on('click', function() {
        var p = pickThis.find(".pickData option");
        p.clone().appendTo(pickThis.find(".pickListResult"));
        p.remove();
      });

      pickThis.find(".pRemove").on('click', function() {
        var p = pickThis.find(".pickListResult option:selected");
        p.clone().appendTo(pickThis.find(".pickData"));
        p.remove();
      });

      pickThis.find(".pRemoveAll").on('click', function() {
        var p = pickThis.find(".pickListResult option");
        p.clone().appendTo(pickThis.find(".pickData"));
        p.remove();
      });
    };

    this.getValues = function() {
      var objResult = [];
      this.find(".pickListResult option").each(function() {
        objResult.push($(this).val());
      });
      return objResult;
    };

    this.init = function() {
      this.controll();
    };

    this.init();
    return this;
  };

  $.fn.pickList.defaults = {
    add: 'Add',
    addAll: 'Add All',
    remove: 'Remove',
    removeAll: 'Remove All'
  };


}(jQuery));

var pick = $("#pickList").pickList({
});


