; (function ($) {
    $.extend({
        'nicenav': function (con) {
            con = typeof con === 'number' ? con : 400;
            var $lis = $('#nav>li'), $h = $('#buoy')
            $lis.hover(function () {
                $h.stop().animate({

                    'left': $(this).offsetParent().context.offsetLeft
                }, con);
            }, function () {
                $h.stop().animate({
                    'left': '-157px'
                }, con);
            })
        }
    });
}(jQuery));