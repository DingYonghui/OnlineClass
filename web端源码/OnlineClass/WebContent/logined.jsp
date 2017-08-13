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
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/YMain.css"/>
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
	});
	</script>
     
</head>

</head>
<body>
	   <div class="container">
	         <div class="head"><img src="${pageContext.request.contextPath}/image/logo.png" style="margin:20px 0px 0px 90px">
	                           <div class="ex"><p>智能学习|智能管理</p></div>
                         
	                         <span class="he-block" id="logoutMe" >退出登录</span><span class="he-block">欢迎用户${teacherUser.TName}</span> 
                           </div>

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

		     <div class="Middle">
		        <div class="Middle-above">
		        <div class="title-mid">
	              <h1 class="title">校学通Web教师端</h1>
	              <p class="s-title">管理教学平台</p>
	              <h2 class="begin">开始>></h2>
	               </div>
	           </div>
	            </div>
		     <div class="footer"></div>
       </div>
	



		   <span id="close">x</span> 
	</div>
	  <div class="bottom">
		     	<dl>
                    <dt>联系/Contact</dt>
                    <hr style=" height:0px;width:198px;border:none;border-top:1px dotted white;"/>
                    <dd><a href="">广告合作</a></dd>
                    <dd><a href="">在线留言</a></dd>
                    <dd><a href="">加入分享达人</a></dd>
                </dl>
                <dl>
                    <dt>帮助中心/help</dt>
                    <hr style=" height:0px;width:198px;border:none;border-top:1px dotted white;"/>
                    <dd><a href="">常见问题</a></dd>
                    <dd><a href="">服务条款</a></dd>
                    <dd><a href="">版权声明</a></dd>
                    <dd><a href="">技术问答</a></dd>       
                </dl>
                <dl>
                    <dt>网站服务/service</dt>
                    <hr style=" height:0px;width:198px;border:none;border-top:1px dotted white;"/>                  
                    <dd><a href="">课程需求</a></dd>
                    <dd><a href="">资源下载</a></dd>
                    <dd><a href="">网站地图</a></dd>
                       
                    
                </dl>
                <dl>
                    <dt>关注我们/Folow Us</dt>
                    <hr style=" height:0px;width:198px;border:none;border-top:1px dotted white;"/>
                    <dd><a href="">新浪微博</a></dd>
                    <dd><a href="">微信</a></dd>
                    <dd><a href="">订阅</a></dd>
                </dl>
		     </div>
		     <div class="footer">
		      <p>友情链接: <a href="">阿里云服务器</a></P>
               <p>版权所有 COPYRIGHT©1999-2013 , SUN YAT-SEN UNIVERSITY - <a href="">联系我们</a> - <a href="">网站地图</a> - 粤ICP备05008892号</p>
               <p>联系站长:CommyLeung(at)gmail.com Power by wordpress 47 queries in 0.324 sec</p></div>
       </div>
</body>
</html>