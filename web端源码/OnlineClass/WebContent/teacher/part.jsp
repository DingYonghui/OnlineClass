<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
     <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/t-part.css"/>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/webuploader.css">
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/webuploader.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/t-part.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
   
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
			               <li><a   href="${pageContext.request.contextPath}/teacher/logined.jsp" class="tip"><span>首页</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadStudentMyClass" class="tip"><span>我的学生</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadBuildClass" class="tip"><span>课程建设</span></a></li>
			<li><a  class="cur" href="${pageContext.request.contextPath}/LessonControl?method=loadMyClass" class="tip"><span>我的课程</span></a></li>
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
                 		<li class="login-btn">登录</li>
                 		<li class="regist-btn">注册</li>
                 	</ul>
                 </div>

		</div>

            <!--路径-->
		<div class="pic">
		   <div class="pic-tip">
			<div class="path">  
                  <a>我的课程</a>
                  <i >\</i>
                  <a >XX课程</a>
                  <i>\</i>
                  <a >XX章</a>
                  <i>\</i>
                 <span>XX节</span>
            </div>
            <h2>${nowPart.PName}</h2>
          </div>
         </div>

         <!--节的上传内容-->
         <div class="content">
         	    <div class="resource">
         		<ul><li class="cur" id="li01"><p>视频</p></li>
         			<li id="li02"><p>文档</p></li>
         			<li id="li03"><p>更多</p></li>
         			</ul>
         		</div>
                <!--视频面-->
         		<div class="li01">
         		<div> 
         			<div class="upload">
         			<br/>
         			<center><font color="red">${requestScope.msg }</font></center>
         				<br/>
         			<form  name="form1" id="formBase" action="${pageContext.request.contextPath}/LessonControl?method=uploadVideo" method="post"
			enctype="multipart/form-data"   >
		  			
		     	     
         			<input type="file" name="PVideo" />
         			  <a href="javascript:form1.submit();">
  <img id="upVideo" src="${pageContext.request.contextPath}/image/upload.png" />
  </a>
         			</form>
         			
         			<br/>
         			<font color="red">视频资源路径：${nowPart.PVideoPath}</font>
         			</div>
         			
         			
         			
         		</div>
         	  </div>
              <!--文档面-->
         	  <div class="li02">
         		<div> 
         			<div class="upload"><img src="${pageContext.request.contextPath}/image/upload.png" /></div>
         			<div class="nothing">
                    
         			</div>
         		</div>
         	  </div>
              <!--更多面-->
         	  <div class="li03">
         		<div> 
         			<div class="upload"><img src="${pageContext.request.contextPath}/image/upload.png" /></div>
         			<div class="nothing">
                   
         			</div>
         		</div>
         	  </div>

         	</div>
	</div>
	</body>
	</html>