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
	 <link rel="stylesheet"  type="text/css" href="css/login.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script> 
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script> 
          
<script type="text/javascript">

$(document).ready(function(){


//$("ROL").bind('input propertychange',function(){
//alert("0.0a");
	//if($("ROL").val()=="1")
	//{
	//alert("0.0");
	//$("#tabs-2").css("display","block");
	//	$("#tabs-1").css("display","none");
		//$(".mark").css("height","470px");
	//}
//});
/*
*注册
*/
$("#NameR").focus(function(){
		$("#nameError1").html("");
	});
$("#NameR").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"registCheckName",Name_AJAX:$("#NameR").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#nameError1").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
	
	$("#Phone").focus(function(){
		$("#rPhoneError11").html("");
	});
$("#Phone").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"registCheckPhone",Phone_AJAX:$("#Phone").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#rPhoneError11").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
		$("#Key1").focus(function(){
		$("#rKeyError1").html("");
	});
$("#Key1").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"registCheckKey",Key_AJAX:$("#Key1").val(),ReKey_AJAX:$("#ReKey").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#rKeyError1").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
	
	$("#ReKey").focus(function(){
		$("#rConfirmKeyError1").html("");
	});
$("#ReKey").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"registReCheckKey",Key_AJAX:$("#Key1").val(),ReKey_AJAX:$("#ReKey").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#rConfirmKeyError1").html(data.msg);
				
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
		$("#VCodeError1").html("");
	});
	$("#myVCode").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"loginCheckVCode",fourthPut_AJAX:$("#myVCode").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#VCodeError1").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
	
	$("#secondPutajax").focus(function(){
		$("#KeyError1").html("");
	});
	$("#secondPutajax").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"loginCheckKey",secondPut_AJAX:$("#secondPutajax").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#KeyError1").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	}); 
	$("#firstPutajax").focus(function(){
		$("#PhoneError1").html("");
	});
	$("#firstPutajax").blur(function(){ 
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin",
			data:{method:"loginCheckPhone",firstPut_AJAX:$("#firstPutajax").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					$("#PhoneError1").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	});
	});
</script>
     
</head>

<body>
	<div class="mark"></div>

	<div class="block">
	   <ul class="list"><li class="tabs-1"><a href="#tabs-1">登录</a></li><li class="tabs-2"><a href="#tabs-2">注册</a></li></ul>
<input type="hidden" id="ROL" name="pageChange" value="${requestScope.rOl}" />

      <!--登录界面-->
		 <div id="tabs-1">
		    <form action="${pageContext.request.contextPath}/RegistAndLogin" method="post">
		      <input type="hidden" name="method" value="login" />
		        <label>   <font color="red">${requestScope.msg}</font></label>
		      <p class="place" ></p>
		
		      <label>手机号：</label><input id="firstPutajax" type="text"   value="${Pform.firstPut}" name="firstPut" />
		      <label class="labelError" color="red"><font color="red" id="PhoneError1" ></font></label>
	          <p class="place"></p>

		      <label>密码 ：</label>&nbsp;&nbsp;<input id="secondPutajax" type="password" value="${Pform.secondPut}" name="secondPut" placeholder="6到12位的数字字母组合"/>
		      <label class="labelError" color="red"><font color="red"  id="KeyError1" ></font></label>
		      <p class="place"></p>

		      <label>权限：</label>&nbsp;&nbsp;
		              <select   name="thirdPut">
	                        <option>===请选择===</option>
	                        <option>老师</option>
                            <option>学生</option>
                            <option>管理员</option>
                      </select>
		      <p class="place"></p>

		      <label>验证码：</label><input id="myVCode" value="${Pform.fourthPut}" style="width:70px" type="text" name="fourthPut"/><image id="verifyCode" src="${pageContext.request.contextPath}/RegistAndLogin?method=loginVCode" class="get-code-btn">
		      <label class="labelError" color="red"><font color="red"  id="VCodeError1" ></font></label>
		      <p class="place"></p>

		      <input type="submit" value="Submit" />
           	</form>
		</div>



       <!--注册界面-->
		<div id="tabs-2">
          <form action="${pageContext.request.contextPath}/RegistAndLogin" method="post">
	  <input type="hidden" name="method" value="regist" />
		<p class="demo"></p>
		<label>昵称姓名：</label><input id="NameR" type="text" name="firstPut" />
		    <label class="labelError"  color="red"><font color="red"  id="nameError1" ></font></label>
		<p class="demo"></p>
		<label>手机号：&nbsp;&nbsp;&nbsp;</label><input id="Phone" type="text" name="secondPut" />
	    <label class="labelError"  color="red"><font color="red"  id="rPhoneError11" ></font></label>
	    <p id="demo"></p>
		<label>登陆密码：</label><input id="Key1" type="password" name="thirdPut" placeholder="6到12位的数字字母组合">
		 <label class="labelError"  color="red"><font color="red"  id="rKeyError1" ></font></label>
		<p class="demo"></p>
	    <label>确认密码：</label><input id="ReKey" type="password" name="fourthPut" >
	     <label class="labelError"  color="red"><font color="red"  id="rConfirmKeyError1" ></font></label>
		<p class="demo"></p>
		<label>权限：&nbsp;&nbsp;&nbsp;</label>&nbsp;&nbsp;
		              <select   name="fifthPut">
	                        <option>===请选择===</option>
	                        <option>老师</option>
                            <option>学生</option>
                            
                      </select>
		      <p class="place"></p>
		<label>验证码：&nbsp;&nbsp;&nbsp;</label><input style="width:70px" type="text" name="sixthPut"/><input id="get-code" type="submit" value="获取验证码" class="get-code-btn">
		<p class="demo"></p>
		<input type="submit" value="Submit" />
	   </form>


		</div>
	</div>
</body>
</html>