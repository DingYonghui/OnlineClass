<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
   <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
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
  
  $("#submitInfo").click(function(){
  $("#formMe").submit();
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
		<div class="head">
			
                 <!--logo-->
                 <div class="logo"><img src="${pageContext.request.contextPath}/image/logo.png" style="margin:0px 30px "></div>

                 <!--导航栏-->
                 <div id="bg">
	                 <div id="container">
		                 <ul id="nav">
			               <li><a  href="${pageContext.request.contextPath}/teacher/logined.jsp" class="tip"><span>&nbsp;&nbsp;&nbsp;&nbsp;首页</span></a></li>
			               <li><a href="${pageContext.request.contextPath}/LessonControl?method=loadStudentMyClass" class="tip"><span>我的学生</span></a></li>
			               <li><a href="${pageContext.request.contextPath}/LessonControl?method=loadBuildClass" class="tip"><span>课程建设</span></a></li>
			               <li><a href="${pageContext.request.contextPath}/LessonControl?method=loadMyClass" class="tip"><span>我的课程</span></a></li>
<li><a class="cur" href="${pageContext.request.contextPath}/TeacherControl?method=loadMyInfo"><span>个人建设</span></a></li>		
   	                    </ul>
		             <div id="buoy" ></div>
	                 </div>
	                 <script type="text/javascript">
	                 $.nicenav(300);
	                 </script>
                 </div>

                 <!--登录注册-->
                 <div class="message">
                 	<ul class="message-li">
                 		<li class="">退出</li>
                 		<li class="">欢迎用户${teacherUser.TName}</li>
                 	</ul>
                 </div>
		</div>
     

         <!--路径-->
        <div class="pic">
           <div class="pic-tip">
            <div class="path">  
                  <a href="">课程建设</a>
                  <i >\</i> 
            </div>
            <h2>设计自己的课程</h2>
            <div class="intro">教师可以在这里编辑自己的个人信息</div>
          </div>
         </div>

          <div class="Middle">
		     	<div class="Middle_left">
		     		<h4>完善个人信息</h4>
		     		<hr style="width:700px ; border-bottom: 1px solid #d0d6d9; margin:10px 60px"/>

		     		<div class="messagebox">
              <label>   <font color="red"  id="msgLabel1">${requestScope.msg} </font></label>
		      <form  id="formMe" action="${pageContext.request.contextPath}/TeacherControl" method="post">
		        <input type="hidden" name="method" value="editTeacherInfo"/>
		      <p class="place"></p>
		      <label>账号：&nbsp;</label><input type="text" class="aa" name="pk_TPhone" value="${teacherUser.pk_TPhone}" placeholder="请输入昵称"/>
	          <p class="place"></p>

		      <label>密码：&nbsp;</label><input type="password" name="TKey" class="aa" value="${teacherUser.TKey}" placeholder="">
		      <p class="place"></p>

		      
               <label>姓名：&nbsp;</label><input type="text" name="TName" value="${teacherUser.TName}" />
                     <p class="place"></p>

               <label>选择系别：</label>
               
              
           
               <br/><br/>
                 <span id="submitInfo" hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block">完成</span>
                 
           	</form>
           	<br/>
           	 <div class="styled-select">
               <select class="select"  id="mydep" name="chooseClass" >
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
                   <button class ="aabutton" hidefocus="true" data-dismiss="modal" aria-role="button"  id="submitDep">提交</button>
                 </div>
		</div>
		     	</div>

		     	<div class="Middle_right"></div>
		     </div>


 
		         <!--脚部-->
        <div class="footer">
         <div class="footer-write">
              <p>友情链接: <a href="">阿里云服务器</a></P>
               <p>版权所有 COPYRIGHT©1999-2013 , SUN YAT-SEN UNIVERSITY - <a href="">联系我们</a> - <a href="">网站地图</a> - 粤ICP备05008892号</p>
               <p>联系站长:CommyLeung(at)gmail.com Power by wordpress 47 queries in 0.324 sec</p></div>
             </div>
         </div>

     </div>
	</body>
	</html>