<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
   <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/t-myclass.css"/>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/t-myclass.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
     <script type="text/javascript">
  $(document).ready(function(){ 
  
  $("#myclass").change(function(){ 
   $("#tree1").empty();
    $.ajax({ 
        type: "POST",   
      url: "${pageContext.request.contextPath}/LessonControl",
      data: {
        name: $("#myclass").val(), 
        method:"getLessonTree",
        selectClass:$('select').val(),
        
        
      },
      dataType: "json",
      success: function(data) {
        
      if(data.success)
      {
        for(var i=0; i<data.sd.length; i++)  
            {  
               $("#tree1").append("   <li class=\"parent_li\"><span title=\"Collapse this branch\"><i class=\"icon-calendar\"></i> "+data.sd[i].SName+"</span><ul id=\"partTree"+i+"\"></ul> </li>");
          //   $("#partTree").append("<li class=\"parent_li\"><span class=\"badge badge-success\" title=\"Collapse this branch\"><i class=\"icon-minus-sign\" ></i>"+data.sd[i].partList[0].PName+"</span>");
               
               for(var j=0;j<data.sd[i].partList.length;j++)
              {
               $("#partTree"+i).append("<li class=\"parent_li\"><span class=\"badge badge-success\" title=\"Collapse this branch\"><i class=\"icon-minus-sign\" ></i>"+data.sd[i].partList[j].PName+"</span>");
            
               }
            }  
          //局部刷新页面
           
    $.getScript("${pageContext.request.contextPath}/js/t-myclass.js");
  
          
      }
      //var str="<script type='text/javascript' src='${pageContext.request.contextPath}/js/jquery-1.11.3.min.js'>";
      //$("#container").append(str);
      
          $("#msgLabel0").html(data.msg);
        
      },
      error: function(jqXHR){     
         alert("发生错误：" + jqXHR.status);  
      },     
    });
  });
  /*
  *退出
  */
  $("#logoutMe").click(function(){
  if(confirm("是否继续")){

   location.href = "${pageContext.request.contextPath}/RegistAndLogin?method=logoutSuccess";
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
			               <li><a class="cur" href="${pageContext.request.contextPath}/teacher/logined.jsp" class="tip"><span>&nbsp;&nbsp;&nbsp;&nbsp;首页</span></a></li>
			               <li><a href="${pageContext.request.contextPath}/LessonControl?method=loadStudentMyClass" class="tip"><span>我的学生</span></a></li>
			               <li><a href="${pageContext.request.contextPath}/LessonControl?method=loadBuildClass" class="tip"><span>课程建设</span></a></li>
			               <li><a href="${pageContext.request.contextPath}/LessonControl?method=loadMyClass" class="tip cur"><span>我的课程</span></a></li>
			               <li><a href="${pageContext.request.contextPath}/teacher/myInfo.jsp" class="tip"><span>个人信息</span></a></li>			<li><a href="${pageContext.request.contextPath}/TeacherControl?method=loadMyInfo" class="tip"><span>个人建设</span></a></li>
   	                    </ul>
		             <div id="buoy" ></div>
	                 </div>
	                 <script type="text/javascript">
	                 $.nicenav(300);
	                 </script>
                 </div>

                <!--欢迎退出-->
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
            <div class="intro">教师可以在这里编辑自己的课程信息，设计章节，让学生更好地选择学习课程</div>
          </div>
         </div>


         <div class="Middle">
         	<!--左边的-->
         	<div class="Middle-left">
         		<br/><br/>
         		<h4>选择要进一步建设的课程：</h4>
         		<br/>
         	    <div class="styled-select">
               <select class="select myclass" id="myclass" name="chooseClass">
                
                        <c:forEach items="${requestScope.MyLesson}" var="lessons">
                                 <option value="<c:out value="${lessons.pk_LId}" />"  selected="selected" ><c:out value="${lessons.LName}" /></option> 
                               </c:forEach>
                     </select>
                </div>
                <span class="sure">submit</span>

                             <!--             树状图            -->
          <div class="tree" id="tree">
             <label>   <font color="red"  id="msgLabel0"></font></label>
            <ul id="tree1">
                         <li class="parent_li">
                         <img src="${pageContext.request.contextPath}/image/book1.png"/>
                         <span title="Collapse this branch"><i class="icon-calendar"></i> 第一章</span>
                          <hr noshade="noshade" width="333" size="1" color="#99999" style="position:relative;top:5px"/>
                         <ul>
                             <li class="son_li">
                             <img src="${pageContext.request.contextPath}/image/book2.png"/>
                             <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span></li>
                              <li class="son_li">
                              <img src="${pageContext.request.contextPath}/image/book2.png"/>
                              <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第二节</span></li>
                              <li class="son_li">
                              <img src="${pageContext.request.contextPath}/image/book2.png"/>
                             <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第三节</span></li>
                            
                        </ul>
                        </li>
<%-------------------%>
                        <li class="parent_li">
                          <img src="${pageContext.request.contextPath}/image/book1.png"/>
                          <span title="Collapse this branch"><i class="icon-calendar"></i> 第二章</span>
                          <hr noshade="noshade" width="333" size="1" color="#99999" style="position:relative;top:5px"/>
                          <ul>
                            <li class="son_li">
                            <img src="${pageContext.request.contextPath}/image/book2.png"/>
                            <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span>   
                          </ul>
                        </li>

                       <li class="parent_li">
                        <img src="${pageContext.request.contextPath}/image/book1.png"/>
                        <span title="Collapse this branch"><i class="icon-calendar"></i> 第三章</span>
                        <hr noshade="noshade" width="333" size="1" color="#99999" style="position:relative;top:5px"/>
                        <ul>
                        <li class="son_li">
                        <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span>   
                        </ul>
                       </li>

                       <li class="parent_li">
                        <img src="${pageContext.request.contextPath}/image/book1.png"/>
                        <span title="Collapse this branch"><i class="icon-calendar"></i> 第四章</span>
                        <hr noshade="noshade" width="333" size="1" color="#99999" style="position:relative;top:5px"/>
                        <ul>
                        <li class="son_li">
                        <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span>   
                        </ul>
                       </li>

                       <li class="parent_li">
                        <img src="${pageContext.request.contextPath}/image/book1.png"/>
                        <span title="Collapse this branch"><i class="icon-calendar"></i> 第五章</span>
                        <hr noshade="noshade" width="333" size="1" color="#99999" style="position:relative;top:5px"/>
                        <ul>
                        <li class="son_li">
                        <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span>   
                        </ul>
                       </li>
                    </ul>
          </div>

            </div>

            <!--右边的-->
         	<div class="Middle-right">
            <img class="courseimg" src="${pageContext.request.contextPath}/image/java.jpg"/>
            <div class="introduce"><h2><img class="book3" src="image/book3.png"/>java高级程序设计</h2>
              <br/>
              <div class="word">课程介绍：《Java高级程序设计》通过实例讲解了如何使用Java语言开发数据库应用程序、多媒体应用程序、网络应用程序等。这些程序典型简洁，主要功能突出，所涉及的技术可以解决同类问题。全书分为两个部分共13章，其中第一部分为Java高级编程的核心知识，介绍了Java语言基础、异常、多线程、Java图形用户界面、Java Applet、Java输入输出流、JDBC数据库编程与Java网络编程，第二部分为扩展知识，介绍了Java常用API、Java多媒体编程与Java Bean.</div>
            </div>
             <hr style="border:1px dotted #999999;width:650px;position:relative;left:50px"/>
             <br/>
             <p style="margin:0px 50px">点击左边的章节进行每章节的课程建设</p>
          </div>
         </div>
         <!--脚部-->
	     <div class="footer">
	     	 <div class="footer-write">
		      <p>友情链接: <a href="">阿里云服务器</a></P>
               <p>版权所有 COPYRIGHT©1999-2013 , SUN YAT-SEN UNIVERSITY - <a href="">联系我们</a> - <a href="">网站地图</a> - 粤ICP备05008892号</p>
               <p>联系站长:CommyLeung(at)gmail.com Power by wordpress 47 queries in 0.324 sec</p></div>
             </div>
        </div>

   


	</body>
	</html>