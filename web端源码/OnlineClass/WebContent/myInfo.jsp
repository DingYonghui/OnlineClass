<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/YMain.css"/>
     <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/t-message.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
	<script type="text/javascript">
	
$(document).ready(function(){




$('#mydep').get(0).selectedIndex="${requestScope.Did}";


	$("#logoutMe").click(function(){
	if(confirm("是否继续")){

   location.href = "${pageContext.request.contextPath}/RegistAndLogin?method=logoutSuccess";
   }
	});
	
	$("#submitDep").click(function(){
	//更改系
	if(confirm("确认修改?")){

   location.href = "${pageContext.request.contextPath}/TeacherControl?method=changeDep&Did="+$("#mydep").val();
   }
	});
	});

	</script>
</head>
<body>
	   <div class="container">
	          <div class="head"><img src="${pageContext.request.contextPath}/image/logo.png" style="margin:20px 0px 0px 90px">
	                           <div class="ex"><p>智能学习|智能管理</p></div>
                         
	                       <!--    <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="regist">注册</span>
                               <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="login">登录</span>  -->
                            
                                      <span class="he-block" id="logoutMe" >退出登录</span><span class="he-block">欢迎用户${teacherUser.TName}</span>
                           </div>

	           <!--导航栏-->
   <div id="bg">
	<div id="container">
		<ul id="nav">
			<li><a  href="${pageContext.request.contextPath}/teacher/logined.jsp"><span>首页</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadStudentMyClass"><span>我的学生</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadBuildClass" ><span>课程建设</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadMyClass"><span>我的课程</span></a></li>
			<li><a class="cur" href="${pageContext.request.contextPath}/TeacherControl?method=loadMyInfo"><span>个人建设</span></a></li>
			
		</ul>
		<div id="buoy" ></div>
	</div>
	
	<script type="text/javascript">
	$.nicenav(300);
	</script>
</div>
		     <div class="Middle">
		     	<div class="Middle_left">
		     		<h4>完善个人信息</h4>
		     		<hr style="width:700px ; border-bottom: 1px solid #d0d6d9; margin:10px 60px"/>

		     		<div class="messagebox">
		     		<center>  <label>   <font color="red"  id="msgLabel1">${requestScope.msg} </font></label></center>
		    <form action="${pageContext.request.contextPath}/TeacherControl" method="post">
		      <input type="hidden" name="method" value="editTeacherInfo"/>
		      <p class="place"></p>
		      <label>账号：</label><input type="text" name="pk_TPhone" value="${teacherUser.pk_TPhone}" placeholder="请输入昵称"/>
	          <p class="place"></p>

		      <label>密码：</label><input type="password" name="TKey"  value="${teacherUser.TKey}" placeholder="">
		      <p class="place"></p>
<!--<label>性别：</label>
                    <input type="radio" name="sex" value="secret" /> 保密
                    <input type="radio" name="sex" value="male" /> 男
                    <input type="radio" name="sex" value="female" /> 女  -->
               <label>姓名：</label><input type="text" name="TName" value="${teacherUser.TName}" />
                     <p class="place"></p>
					<button type="submit"    class=" btn-block">提交</button>
                
           	</form>
           	
		</div>
		<br/>
		我的系：<select class="myclass"  id="mydep" name="chooseClass" >
                        <option value="0"   >互联网系</option> 
                        <option value="1"   >金融系</option> 
                        <option value="2"   >工管系</option> 
                        <option value="3"   >外语系</option> 
                        <option value="4"   >法律系</option> 
                        <option value="5"  >财传系</option> 
                        <option value="6"   >应数系</option> 
                        <option value="7"   >公管系</option> 
                        <option value="8"   >信管系</option> 
                        <option value="9"   >会计系</option> 
                        <option value="10"   >保险系</option> 
                        <option value="11"   >经贸系</option> 
                        <option value="12"   >劳经系</option> 
                   </select>
                   <button id="submitDep">提交</button>
		     	</div>
		     	<div class="Middle_right">
		     	      </div>
		     </div>
		     <div class="footer"></div>
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