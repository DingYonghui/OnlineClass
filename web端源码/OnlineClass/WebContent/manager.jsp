<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/manager.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
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
<body>
	<div class="content">
	<h1>我是管理员</h1>
	<font color="red" id="logoutMe">退出</font>
	<br/>
	 <center><font color="red" >${requestScope.msg}</font></center>
	  
	<div>
		<table>
		<th class="phonenumber">老师手机</th>
		<th class="check" >审核</th>
		<th class="do">操作</th>
		<%--
		
	     <tr>
	     	<td>18826078147</td>
	     	<td>未审核</td>
	     	<td><span class="pass">通过</span><span class="delete">删除</span></td>
	     </tr>
	    </table></div>
		 --%>
		<!--第一行-->
		<c:forEach items="${requestScope.unPassTeacherList}" var="teacher">
         <tr>
	     	<td><c:out value="${teacher.pk_TPhone}"/></td>
	     	<td>未审核</td>
	     	<td><a   class="pass" href="${pageContext.request.contextPath}/TeacherControl?method=pass&t_id=${teacher.pk_TPhone}">通过</a>
	 <a   class="delect" href="${pageContext.request.contextPath}/TeacherControl?method=noPass&t_id=${teacher.pk_TPhone}">非教师用户,删除</a></td>
	     </tr>
                </c:forEach>
	    </table>
		 </div>
		
	</div>
	</body>
	</html>