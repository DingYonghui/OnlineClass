$(document).ready(function() {
    $(".select-box").show();

    $("#ru").click(function() {
    var display =$('.conmentbox').css('display');
    if(display != 'none'){      
        $(".conmentbox").hide();
         $("td").remove();
         $("#mask").css({    
        "height":$(document).height(),    
        "width":$(document).width()    
        }).fadeIn(); 
          $(".number").fadeIn();
     }
     else {        
       $("#mask").css({    
        "height":$(document).height(),    
        "width":$(document).width()    
        }).fadeIn();  

        $(".number").fadeIn();
        } 
        });  

    $("#get").click(function() {
      if($("#num").val()!="") {
        if($("#num").val()<=10) {

        $(".select-box").animate({  /*移动*/
        	left:'-320px',
            opacity:'2.0',
            });

        
        $(".conmentbox").fadeIn();
        $("#mask").hide();
        $(".number").hide();
        var num=$("#num").val();
        for(var i=0;i<num;i++) {
        	var str_1="<tr> <td><input id=\"mc"+i+"1\" type=\"text\" placeholder=\"value\"  class=\"reg_input mput\"> </td>" +  
            "<td><input id=\"mc"+i+"2\" type=\"text\" placeholder=\"value\"  class=\"reg_input mput\"> </td>"+
            "<td><input  id=\"mc"+i+"3\" type=\"text\" placeholder=\"value\"  class=\"reg_input mput\"> </td> </tr> ";
           $("#customers").append(str_1); 
        }
       $("#num").val("");
       }
       else {
        alert("数字不能超过10");
        $("#num").val("");
       }
      }
      else{
        alert("不能为空");
      }
    });
     $("#false").click(function() {
      $("#mask").hide();
       $(".number").hide();
     }); 
    
    
});

