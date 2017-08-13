<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
   <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
     <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/t-mystudent.css"/>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/t-mystudent.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
<script type="text/javascript">
	
$(document).ready(function(){

$("#putStudent").click(function(){
	
	var totalLength=$("#customers td").length;//总input数
	var rowLength=totalLength/3;//行数
	var nowClass=$("#Mlesson").val();
	var allData=[];
	for(var i=0;i<rowLength;i++)
	{
	
		for(var j=1;j<4;j++)
		{
		
		allData.push($("#mc"+i+j).val());
		
		
		
		}
	
			
			
			
		
	}
	$.ajax({ 
		    type: "POST", 	
		    traditional: true,
			url: "${pageContext.request.contextPath}/StudentControl",
			data:{method:"putStudent",allData:allData,nowClass:nowClass,LId:$("#Mlesson").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
					$("#msgLabel").html("");
					$("#msgLabel").html(data.msg);
					alert(data.msg);
					}
					else{
					$("#msgLabel").html("");
					$("#msgLabel").html(data.msg);
					alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
});

	$("#logoutMe").click(function(){
	if(confirm("是否继续")){

   location.href = "${pageContext.request.contextPath}/RegistAndLogin?method=logoutSuccess";
   }
	});
	
		$("#OutStudent").click(function(){
			
			$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/StudentControl",
			data:{method:"OutStudent",LId:$("#Mlesson").val()},
			dataType: "json",
			success: function(data) {
			alert($("#Mlesson").val());
					//局部刷新页面
					if(data.success)
					{
					$("#msgLabel").html("");
					$("#msgLabel").html(data.msg);
					alert(data.msg);
					
					}
					else{
					$("#msgLabel").html("");
					$("#msgLabel").html(data.msg);
					alert(data.msg);
					}
					
				
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
		<div class="head">
			
                 <!--logo-->
                 <div class="logo"><img src="${pageContext.request.contextPath}/image/logo.png" style="margin:0px 30px "></div>

                 <!--导航栏-->
                 <div id="bg">
	                 <div id="container">
		                 <ul id="nav">
			               <li><a href="${pageContext.request.contextPath}/teacher/logined.jsp" class="tip"><span>&nbsp;&nbsp;&nbsp;&nbsp;首页</span></a></li>
			               <li><a  href="${pageContext.request.contextPath}/LessonControl?method=loadStudentMyClass" class="tip cur"><span>我的学生</span></a></li>
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
                 <div class="message">
                 	<ul class="message-li">
                 		<li id="logoutMe">退出登录</li>
                 		<li >欢迎用户${teacherUser.TName}</li>
                 	</ul>
                 </div>
		</div>


         <!--路径-->
        <div class="pic">
           <div class="pic-tip">
            <div class="path">  
                  <a href="">我的学生</a>
                  <i >\</i> 
            </div>
            <h2>导入或导出学生</h2>
            <div class="intro">教师可以在这里导入或者导出学习自己课程的学生的账号等信息</div>
            <center><label>   <font color="red"  id="msgLabel">${requestScope.msg} </font></label></center>
          </div>
         </div>


        <div class="Middle">
            <div class="select-box">
                <div class="styled-select">
               <select class="select" id="Mlesson">
                
                        <c:forEach items="${requestScope.MyLesson}" var="lessons">
                                 <option value="<c:out value="${lessons.pk_LId}" />"  selected="selected" ><c:out value="${lessons.LName}" /></option> 
                               </c:forEach>
                     </select>
                </div>
                     <div class="btn-group">
                      <span tyle="text" hidefocus="true" data-dismiss="modal" aria-role="button" id="ru" class="btn-stu">导入学生</span>
                        <span  hidefocus="true" data-dismiss="modal" aria-role="button" id="OutStudent" class="btn-stu">导出学生</span>

                      </div>
                  </div>

                  <!--导入导出数目框-->
                <div id="mask"></div>           
                    <div class="number">
                        <input type="text" value="" class="num" id="num" placeholder="请输入要添加的学生数量（如5)">
                        <span id="get" tyle="text" hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block">提交</span>
                        <span id="false" hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block">取消</span>                
                    </div>

                    <!--列表框-->
                    <div class="conmentbox">               
                     <div class="box">
                        <table id="customers">
                             <tr>
                                <th>学生帐号</th>
                                <th>登陆密码</th>
                                <th>学生姓名</th>
                             </tr>
                                                
                          </table>
                      </div>
                      <button type="button" id="putStudent"  hidefocus="true" data-dismiss="modal" aria-role="button" class="submit" >提交</button>
   
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
	</body>
</html>

