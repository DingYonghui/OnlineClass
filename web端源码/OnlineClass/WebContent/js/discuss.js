$(document).ready(function() {

	$("input.btn-answer").click(function() {
      $(this).parent().find(".answer").slideDown("slow");
  //    $(this).parent().find(".answer").val("");
        $(this).parent().find("#submitResponse").fadeIn();
        $(this).parent().find("#false").fadeIn();
        
	});
	$("input#submitResponse").click(function() {
       $(this).parent().find(".answer").slideUp("slow");
        //if($(this).parent().find(".answer").val()=="") {
        //  alert("不能发表空内容");
          // $(this).parent().find("#submitResponse").fadeOut();
               //$(this).parent().find("#false").fadeOut();
       // }
         // else  {
           //    $(this).parent().find(".answer").val("");
               $(this).parent().find("#submitResponse").fadeOut();
               $(this).parent().find("#false").fadeOut();
        //  }
        
	});
	$("input#false").click(function() {
         $(this).parent().find(".answer").slideUp("slow");
          // $(this).parent().find(".answer").val("");
           $(this).parent().find("#submitResponse").fadeOut();
              $(this).parent().find("#false").fadeOut();
   	});
});