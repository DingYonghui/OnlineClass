$(document).ready(function() {
	
   
	$(".login-btn").click(function() {
	    $(".container").hide();
       $(".login-regist-area").show();
        $(".tabs-1").fadeIn();
	});
    
    $(".regist-btn").click(function() {
       $(".container").hide();
       $(".login-regist-area").show();
       $(".tabs-1").hide();
       $(".tabs-2").fadeIn();
    });
 
  /*有焦点时变色*/
   $(".tabs-1 input,.tabs-2 input").focus(function() {

   // $(this).css('border-color', 'white');
    $(this).css('box-shadow','2px 2px 2px black');
     }).blur(function() {

   // $(this).css('border-color', '');
    $(this).css('box-shadow','none');

  })


/* 引导登录注册界面切换*/
	$(".change-re").click(function() {
       $(".tabs-2").fadeIn();
       $(".tabs-1").hide();
	});
    $(".change-lo").click(function() {
       $(".tabs-1").fadeIn();
       $(".tabs-2").hide();
	});

  $(".back2").click(function() {
       $(".container").fadeIn();
       $(".login-regist-area").hide();
       $(".tabs-2").hide();
  });
});