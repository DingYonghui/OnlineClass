$(document).ready(function() {


	/*注册登录界面的切换*/
	$(".tabs-1").click(function() {
		$("#tabs-1").css("display","block");
		$("#tabs-2").css("display","none");
		$("#ROL").value.replace("0");
	});
   $(".tabs-2").click(function() {
		$("#tabs-2").css("display","block");
		$("#tabs-1").css("display","none");
		$(".mark").css("height","470px");
	
	});
 
//  $
//
//   /*判断姓名不能为空*/
//   $("#Name").focus(function() {
//           $("#nameError").css("display","none");
//   })
//   $("#Name").blur(function() {
//            if (document.getElementById('SName').value=='')
//            {
//                $("#nameError").css("display","inline");
//                
//            }
//            else {
//            	 $("#nameError").css("display","none");
//             }
//        });

});
