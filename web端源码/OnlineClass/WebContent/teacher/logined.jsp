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
	$("#logoutMe").click(function(){
	if(confirm("是否继续")){

   location.href = "${pageContext.request.contextPath}/RegistAndLogin?method=logoutSuccess";
   }
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
			<li><a class="cur" href="" class="tip"><span>首页</span></a></li>
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
                 		<li class="" id="logoutMe">退出</li>
                 		<li class="">欢迎用户${teacherUser.TName}</li>
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

   
    


			
	  </div>
	</div>

	</body>
</html>