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
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/Main.css"/>
     <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/t-homepage.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/t-homepage.js"></script>
          
<script type="text/javascript">

$(document).ready(function(){
/*
	异步注册
	*/
	     //ajax提交form表单的方式
	     $("#regist").submit(function(){
			   
	$.ajax({ 
			cache: false,
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/RegistAndLogin?method=registSubmit",
	
			data:$("#registF").serialize(),
			
				
			
			
			dataType: "json",
			async: false,
			success: function(data) {
				
				
				alert(data.msg);
				
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
				
				
				alert(data.msg);
				
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
	
	
	});  
      
</script>
     
</head>

<body>
	   <div class="container">
	         <div class="head"><h1>校学通</h1>
	                           <p  class="ex">智能学习|智能管理</p>

	                           <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="regist">注册</span>
                               <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="login">登录</span> </div>
	         <div class="Menu">
	               <ul class="list">
		     			<li><a href="t-homepage.html">首页</a></li>
		     			<li><a href="t-mystudent.html">我的学生</a></li>
		     			<li><a href="t-course.html">课程建设</a></li>
		     			<li><a href="t-myclass.html">我的课程</a></li>
		     			<li><a href="t-message.html">个人信息</a></li>
		     		</ul></div>
		     <div class="Middle">
		     	<img src="image/HomePage.jpg" style="width:1100px;height:600px"/>
		     	<div class="Middle_left">
		     		<div class="Middle_left_above"></div>
		     		<div class="Middle_left_bottom"></div>
		     	</div>
		     	<div class="Middle_right"></div>
		     </div>
		     <div class="footer"></div>
       </div>
	

<!--注册登录-->
<div id="mask"></div>

<!--  <div class="mark"></div> -->
	<div class="block">
	   <ul class="list-tab"><li class="tabs-1"><a href="#tabs-1">登录</a></li><li class="tabs-2"><a href="#tabs-2">注册</a></li></ul>
           <input type="hidden" id="ROL" name="pageChange" value="${requestScope.rOl}" />

      <!--登录界面-->
		 <div id="tabs-1">
		    <form  id="loginF" >
		      <input type="hidden" name="method" value="loginSubmit" />
		        <label>   <font color="red">${requestScope.msg}</font></label>
		      <p class="place" ></p>
		
		      <label>手机号：</label><input id="firstPutajax" type="text"   value="${Pform.firstPut}" name="firstPut" />
		      <label class="labelError" color="red"><font color="red" id="PhoneError" ></font></label>
	          <p class="place"></p>

		      <label>密码 ：</label>&nbsp;&nbsp;<input id="secondPutajax" type="password" value="${Pform.secondPut}" name="secondPut" placeholder="6到12位的数字字母组合"/>
		      <label class="labelError" color="red"><font color="red"  id="KeyError" ></font></label>
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
		      <label class="labelError" color="red"><font color="red"  id="VCodeError" ></font></label>
		      <p class="place"></p>
		     
			
			<input type="submit"  id="loginButton" value="创建" />
           	</form>
		</div>


<!--注册界面-->
		<div id="tabs-2">
          <form id="registF">
	  <input type="hidden" name="method" value="regist" />
		<p class="demo"></p>
		<label>昵称姓名：</label><input id="NameR" type="text" name="firstPut" />
		    <label class="labelError"  color="red"><font color="red"  id="nameError" ></font></label>
		<p class="demo"></p>
		<label>手机号：&nbsp;&nbsp;&nbsp;</label><input id="Phone" type="text" name="secondPut" />
	    <label class="labelError"  color="red"><font color="red"  id="rphoneError" ></font></label>
	    <p id="demo"></p>
		<label>登陆密码：</label><input id="Key1" type="password" name="thirdPut" placeholder="6到12位的数字字母组合">
		 <label class="labelError"  color="red"><font color="red"  id="rKeyError" ></font></label>
		<p class="demo"></p>
	    <label>确认密码：</label><input id="ReKey" type="password" name="fourthPut" >
	     <label class="labelError"  color="red"><font color="red"  id="rConfirmKeyError" ></font></label>
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
		<input type="submit" value="注册" />
	   </form>


		</div>

		   <span id="close">x</span> 
	</div>
</body>
</html>