<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
     <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/t-homepage.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/t-homepage.js"></script>
          <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
<script type="text/javascript">

$(document).ready(function(){
//登陆显示事件  加载
$("#tologin").click(function(){
$("#VCodeError").html("");
	$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode",
			
			});
     $("#verifyCode").attr("src","${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode");
});
$("#tologin1").click(function(){
$("#VCodeError").html("");
	$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode",
			
			});
     $("#verifyCode").attr("src","${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode");
});
//
$(".begin").click(function() {
      location.href = "${pageContext.request.contextPath}/LessonControl?method=loadMyClass";
	});
/*
	异步注册
	*/
	     //ajax提交form表单的方式
	     $("#registF").submit(function(){
			   
	$.ajax({ 
			cache: false,
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin?method=registSubmit",
	
			data:$("#registF").serialize(),
			
				
			
			
			dataType: "json",
			async: false,
			success: function(data) {
				if(data.success)
				{
				
			 $(".tabs-1").fadeIn();
       $(".tabs-2").hide();
				
				$("#loginMSG").html(data.msg);
				$("#registMSG").html(data.msg);
				}
				else{
				$("#registMSG").html(data.msg);
				}
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
		return false;
	});
	
	
	/*
	异步登录
	*/
	     //ajax提交form表单的方式
	     $("#loginF").submit(function(){
			   
	$.ajax({ 
			cache: false,
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin?method=loginSubmit",
	
			data:$("#loginF").serialize(),
		
			
				
			
			
			dataType: "json",
			async: false,
			success: function(data) {
			
				$("#loginMSG").html("");
				$("#secondPutajax").html("");
				if(data.success)
				{
				if(data.level==1)
				{
					location.href = "${pageContext.request.contextPath}/RegistAndLogin?method=loginSuccess";
				}
				else if(data.level==0){
					location.href = "${pageContext.request.contextPath}/RegistAndLogin?method=loginStudentSuccess";
			
				}
				else if(data.level==2)
				{
					location.href = "${pageContext.request.contextPath}/RegistAndLogin?method=loginAdminSuccess";
					alert("管理员就是叼,格式错误照样登陆");
				}
				
				//alert(data.msg);
				}
				else{
					$("#loginMSG").html(data.msg);
					$("#VCodeError").html("");
	$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode",
			
			});
     $("#verifyCode").attr("src","${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode");
				}
				//$("#verifyCode").attr("src", "${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode");
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
		return false;
	});
	
	
		
	  //   $("#loginButton").click(function(){
		//	  $("#loginF").submit(function(){
			 // 	$.ajax({
			  //	  cache: false,
              //  type: "POST",
              // url:"${pageContext.request.contextPath}/RegistAndLogin",
               // data:$("#formF").serialize(),// 你的formid
             //  async: false,
             //  dataType: "json",
           //    error: function(jqXHR){     
			//   alert("发生错误：" + jqXHR.status);  
		//	},
             //   success: function(data) {
            //       alert(data.msg);
            //    }
			//  	});
		//	  });
				
	      
	   //  });



/*
*注册
*/
$("#NameR").focus(function(){
		$("#nameError").html("");
	});
$("#NameR").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"registCheckName",Name_AJAX:$("#NameR").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#nameError").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
	
	$("#Phone").focus(function(){
		$("#rphoneError").html("");
	});
$("#Phone").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"registCheckPhone",Phone_AJAX:$("#Phone").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#rphoneError").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
		$("#Key1").focus(function(){
		$("#rKeyError").html("");
	});
$("#Key1").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"registCheckKey",Key_AJAX:$("#Key1").val(),ReKey_AJAX:$("#ReKey").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#rKeyError").html(data.msg);
					if(data.twoKey)
					{
					$("#rConfirmKeyError").html("");
					$("#rConfirmKeyError").html("√");
					}
					
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
	
	$("#ReKey").focus(function(){
		$("#rConfirmKeyError").html("");
	});
$("#ReKey").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"registReCheckKey",Key_AJAX:$("#Key1").val(),ReKey_AJAX:$("#ReKey").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#rConfirmKeyError").html(data.msg);
				
					if(data.twoKey)
					{
					$("#rKeyError").html("");
					$("#rKeyError").html("√");
					}
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
/*
*登陆
*/
	$("#myVCode").focus(function(){
		$("#VCodeError").html("");
	});
	$("#myVCode").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"loginCheckVCode",fourthPut_AJAX:$("#myVCode").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#VCodeError").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
	
	$("#secondPutajax").focus(function(){
		$("#KeyError").html("");
	});
	$("#secondPutajax").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"loginCheckKey",secondPut_AJAX:$("#secondPutajax").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#KeyError").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
	$("#firstPutajax").focus(function(){
		$("#PhoneError").html("");
	});
	$("#firstPutajax").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"loginCheckPhone",firstPut_AJAX:$("#firstPutajax").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					
					$("#PhoneError").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	});
	/*
	点击验证码
	*/
	$("#verifyCode").bind("click", function() {
	$("#VCodeError").html("");
	$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode",
			
			});
     $("#verifyCode").attr("src","${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode");
});

	$("#ms").click(function(){
	

   location.href = "${pageContext.request.contextPath}/LessonControl?method=loadStudentMyClass";
   
	});
	
	$("#bc").click(function(){
	
   location.href = "${pageContext.request.contextPath}/LessonControl?method=loadBuildClass" ;
   
	});
	
	
	});  
      
</script>
     
</head>


<body>
	<div class="container">
	 <center><font color="red" >${requestScope.msg}</font></center>
		<div class="head">
			
                 <!--logo-->
                 <div class="logo"><img src="${pageContext.request.contextPath}/image/logo.png" style="margin:0px 30px "></div>

                 <!--导航栏-->
                 <div id="bg">
	                 <div id="container">
		                 <ul id="nav">
			<li><a class="cur" href="#" class="tip"><span>首页</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadStudentMyClass" class="tip"><span>我的学生</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadBuildClass" class="tip"><span>课程建设</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadMyClass" class="tip"><span>我的课程</span></a></li>
			<li><a href="${pageContext.request.contextPath}/TeacherControl?method=loadMyInfo" class="tip"><span>个人建设</span></a></li>	
   	                    </ul>
		             <div id="buoy" ></div>
	                 </div>
	                 <script type="text/javascript">
	                 $.nicenav(300);
	                 </script>
                 </div>

                 <!--登录注册-->
                 <div class="login-regist">
                 	<ul class="list">
                 		<li class="login-btn" id="tologin1">登录</li>
                 		<li class="regist-btn">注册</li>
                 	</ul>
                 </div>

		</div>

		<div class="pic">
			<div class="title-mid">
	              <h1 class="title">校学通Web教师端</h1>
	              <p class="s-title">管理教学平台</p>
	              <h2 class="begin">开始>></h2>
	        </div>
		</div>
		<!--大图下-->
		<div>
		<div class="icon-group">
		<div class="icon"><img src="${pageContext.request.contextPath}/image/mystu1.png" id="ms"><p>管理学生</p></div>
		<div class="icon right"><img src="${pageContext.request.contextPath}/image/course1.png" id="bc"><p>课程建设</p></div>
	    </div>
	    </div>

         <!--脚部-->
	     <div class="footer">
	     	 <div class="footer-write">
		      <p>友情链接: <a href="">阿里云服务器</a></P>
               <p>版权所有 COPYRIGHT©1999-2013 , SUN YAT-SEN UNIVERSITY - <a href="${pageContext.request.contextPath}/image/weichatOld.jpg">联系我们</a> - <a href="">网站地图</a> - 粤ICP备05008892号</p>
               <p>联系站长:CommyLeung(at)gmail.com Power by wordpress 47 queries in 0.324 sec</p></div>
             </div>
        </div>
	</div>

   
    <!--登录注册页面-->
	<div class="login-regist-area">
		<div class="black"></div>
		<div class="tabs-1">
			<div class="back2" style="position:relative;left:-160px;"><img src="${pageContext.request.contextPath}/image/back2.png"/>返回</div>
			<h1>登录</h1>
               <form id="loginF">
		           <input type="hidden" name="method" value="loginSubmit" />
		              <label>   <font color="red" id="loginMSG">${requestScope.msg}</font></label>
		            <br/>	            
		            <input id="firstPutajax" type="text"   value="${Pform.firstPut}" name="firstPut" placeholder="手机号" />
		            <label class="labelError" color="red"><font color="red" id="PhoneError" ></font></label>
		            <br/>
		            <input id="secondPutajax" type="password" value="${Pform.secondPut}" name="secondPut" placeholder="密码/6到12位的数字字母组合"/>
		            <label class="labelError" color="red"><font color="red"  id="KeyError" ></font></label>	 
		            <br/>          		      
		              <select   name="thirdPut">
	                        <option value="-1">===请选择===</option>
	                        <option value="1">老师</option>
                            <option value="0">学生</option>
                            <option value="2">管理员</option>
                      </select>
                      <br/>
		            <input id="myVCode" value="${Pform.fourthPut}" placeholder="验证码" style="width:90px" type="text" name="fourthPut"/><image id="verifyCode" src="${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode" class="get-code-btn">
		              <label class="labelError" color="red"><font color="red"  id="VCodeError" ></font></label>
                     <br/>
		                <input type="submit" value=登陆" /><span class="change-re">没有帐号？去注册 <i><img src="${pageContext.request.contextPath}/image/go.png"/><i></span>
           	   
           	       </form>
			</div>


			<!--注册界面-->
			<div class="tabs-2">
				<div class="back2" style="position:relative;left:-160px;"><img src="${pageContext.request.contextPath}/image/back2.png"/>返回</div>
				<h1>注册</h1>
			    <form id="registF" >
			    <label>   <font color="red" id="registMSG">${requestScope.msg}</font></label>
	               
		           <br/>
		           <input id="NameR" type="text" name="firstPut" placeholder="姓名" />
		           <label class="labelError"  color="red"><font color="red"  id="nameError" ></font></label>
		
		           <input id="Phone" type="text" name="secondPut"  placeholder="手机号" />
	               <label class="labelError"  color="red"><font color="red"  id="rphoneError" ></font></label>
	    
		           <input id="Key1" type="password" name="thirdPut" placeholder="密码/6到12位的数字字母组合">
		           <label class="labelError"  color="red"><font color="red"  id="rKeyError" ></font></label>
		
	               <input id="ReKey" type="password" name="fourthPut"  placeholder="确认密码" />
	               <label class="labelError"  color="red"><font color="red"  id="rConfirmKeyError" ></font></label>	     <select  name="fifthPut">
	                       <option value="-1">===请选择===</option>
	                        <option value="1">老师</option>
                            <option value="0">学生</option>         
                      </select>
                      <br/>
		           <input style="width:70px" type="text" name="sixthPut" placeholder="手机验证码"/>
		           <input id="get-code" type="submit" value="获取验证码" class="get-code-btn">
		                    <input type="submit" value="注册" /><span class="change-lo" id="tologin">已有帐号，去登录 <i><img src="${pageContext.request.contextPath}/image/go.png"/><i></span>
	  
	           </form> 
	         </div>
	  </div>
	</div>

	</body>
</html>