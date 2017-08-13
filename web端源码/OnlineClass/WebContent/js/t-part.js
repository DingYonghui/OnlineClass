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

});


