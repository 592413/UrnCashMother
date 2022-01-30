"use strict";
$('.collapse-icon').on('click', function (e) {



});


$(document).on('click', '.panel-heading span.clickable', function (e) {
    var $this = $(this);
    if (!$this.hasClass('panel-collapsed')) {
        $this.parents('.panel').find('.panel-body').slideUp();
        $this.addClass('panel-collapsed');
        $this.find('i').removeClass('glyphicon-minus').addClass('glyphicon-plus');
    } else {
        $this.parents('.panel').find('.panel-body').slideDown();
        $this.removeClass('panel-collapsed');
        $this.find('i').removeClass('glyphicon-plus').addClass('glyphicon-minus');
    }
});

  /*if($('this').find('.flight-stop-header')){
      $('this').toggleClass('slow');
      $(this).find('i').removeClass('fa-minus').addClass('fa-plus');
  }else {
      $('.flight-stop-list').slideDown();
      $(this).find('i').removeClass('fa-plus').addClass('fa-minus');
  }
});*/
