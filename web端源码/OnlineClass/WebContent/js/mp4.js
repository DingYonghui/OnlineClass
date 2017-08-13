$(document).ready(function() {
  $("#li01").click(function() {
       $(".li01").show();
       $(".li02").hide();
       $(".li03").hide();
       $("#li01").addClass("cur");
       $("#li02").removeClass("cur");
       $("#li03").removeClass("cur");
  });
   $("#li02").click(function() {
       $(".li02").show();
       $(".li01").hide();
       $(".li03").hide();
       $("#li02").addClass("cur");
       $("#li01").removeClass("cur");
       $("#li03").removeClass("cur");
  });
    $("#li03").click(function() {
       $(".li03").show();
       $(".li02").hide();
       $(".li01").hide();
       $("#li03").addClass("cur");
       $("#li01").removeClass("cur");
       $("#li02").removeClass("cur");
  });




$(function(){
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
});

});