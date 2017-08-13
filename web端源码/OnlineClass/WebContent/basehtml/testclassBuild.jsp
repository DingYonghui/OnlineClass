<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/Main.css"/>
     <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/t-course.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/t-course.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
	 <script charset="utf-8" src="${pageContext.request.contextPath}/editor/kindeditor.js"></script>
	 <script charset="utf-8" src="${pageContext.request.contextPath}/editor/lang/zh_CN.js"></script>


</head>
<body>
	   <div class="container">
	         <div class="head"><img src="${pageContext.request.contextPath}/image/logo.png" style="margin:20px 0px 0px 90px">
	                           <div class="ex"><p>智能学习|智能管理</p></div>
                         
	                     <!--  <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="regist">注册</span>
                               <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="login">登录</span>  -->
                            
                            <span class="he-block">退出登录</span><span class="he-block">欢迎XXX用户</span>
                           </div>

                           <!--导航栏-->
   <div id="bg">
	<div id="container">
		<ul id="nav">
			<li><a class="cur" href="#" class="tip"><span>首页</span></a></li>
			<li><a href="#" class="tip"><span>我的学生</span></a></li>
			<li><a href="${pageContext.request.contextPath}/teacher/testclassBuild.jsp" class="tip"><span>课程建设</span></a></li>
			<li><a href="${pageContext.request.contextPath}/teacher/test3.jsp" class="tip"><span>我的课程</span></a></li>
			<li><a href="${pageContext.request.contextPath}/teacher/myInfo.jsp" class="tip"><span>个人建设</span></a></li>
			
			
		</ul>
		<div id="buoy" ></div>
	</div>
  
  <script type="text/javascript">
  $.nicenav(300);
  </script>
</div>

		     			<!--课程建设内容-->
		    
		     <div class="Middle">
		     　<div class="content" >
		         	<div class="box">
		     	<h4>基本：</h4>
		     	<center>  <label>   <font color="red"  id="msgLabel1">${requestScope.LessonBuildMsg} </font></label></center>
		     	<form id="formBase" action="${pageContext.request.contextPath}/LessonControl?method=buildBase" method="post"
			enctype="multipart/form-data"  target="hidden_frame" >
		  
		     	    <p>课程ID：&nbsp; &nbsp;<input type="text" name="pk_LId" /></p>
		     		<p>课程名称： <input type="text" name="LName" /></p>
		     		<p>课程简介：<span>+</span>

		     		</p>
		     		<p>课程图标：
                                 
                              <input type="file" name="LIcon"/> 
		     	    </P>
		     	        <center><input type="submit" id="button1" value="提交"></input></center>
		     		
		     		<center><font color="red">支持jpg等图片文件的上传</font></center>                 
    <iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe> 
		     		</form>
		     		


		     	<h4>详细：</h4>
		     	 <form  name="form1" action="" method="get">
		     	    <p>章：
                                 <!--select标签和input外面的span标签的格式是为了使两个位置在同一位置，控制位置--> 
                                <span style="position:absolute; margin:15px; border:1pt solid #c1c1c1;  overflow:hidden;  width:188px;  height:19px;  clip:rect(-1px 190px 190px 170px); margin:-90px；"> 
                                
                                 <select name="aabb" id="aabb" style="width:190px; height:20px;  margin:-2px;" 
                                onChange="javascript:document.getElementById('ccdd').value=document.getElementById('aabb').options[document.getElementById('aabb').selectedIndex].value;"> 
                                  <!--下面的option的样式是为了使字体为灰色，只是视觉问题，看起来像是注释一样--> 
                                 <option value="" style="color:#c2c2c2;" selected="selected" >---请编辑---</option> 
                                                                  
                                 </select> 
                                
                                 </span> 
                                   <span style="position:absolute; margin:15px; border-top:1pt solid #c1c1c1;border-left:1pt solid #c1c1c1;border-bottom:1pt solid #c1c1c1;width:170px;height:19px;"> 
                                   <input type="text" name="ccdd" id="ccdd" value="请编辑" style="width:170px;height:15px;border:0pt;"  onkeyup="upchange()"> 
                                </span> 
                               <button type="button" onclick="add_opt1()"  style="position:relative;left:210px;top:4px" >添加</button>
                               <button type="button" onclick="change()"  style="position:relative;left:240px;top:4px" >更改</button>
                               </P> </form>
                                                              
                              
                   <form name="form2" action="form_action.asp" method="get">
		     		<p>节：  <!--select标签和input外面的span标签的格式是为了使两个位置在同一位置，控制位置--> 
                                <span style="position:absolute; margin:15px; border:1pt solid #c1c1c1;  overflow:hidden;  width:188px;  height:19px;  clip:rect(-1px 190px 190px 170px); margin:-90px；"> 
                                
                                 <select name="AABB" id="AABB" style="width:190px; height:20px;  margin:-2px;" 
                                onChange="javascript:document.getElementById('CCDD').value=document.getElementById('AABB').options[document.getElementById('AABB').selectedIndex].value;"
                               > 
                                  <!--下面的option的样式是为了使字体为灰色，只是视觉问题，看起来像是注释一样--> 
                                 <option value="" style="color:#c2c2c2;" >---请编辑---</option> 
                                                                  
                                 </select> 
                                
                                 </span> 
                                   <span style="position:absolute; margin:15px; border-top:1pt solid #c1c1c1;border-left:1pt solid #c1c1c1;border-bottom:1pt solid #c1c1c1;width:170px;height:19px;"> 
                                   <input type="text" name="CCDD" id="CCDD" value="请编辑" style="width:170px;height:15px;border:0pt;" disabled="disabled"> 
                                </span> 
                               <button type="button" id="add" onclick="add_opt2()" style="position:relative;left:210px;top:4px" disabled="disabled">添加</button>
                               <button type="button" onclick="change()"  style="position:relative;left:240px;top:4px" >更改</button>
                               </P> </form>
                                  </div>
		
		       </div>
		     </div>



		     <div class="footer"></div>
       </div>
	


</body>
</html>