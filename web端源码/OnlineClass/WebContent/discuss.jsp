<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/mp4.css"/>
     <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/discuss.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/discuss.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
    <script type="text/javascript">
     $(document).ready(function(){
     	$("#submitResponse").click(function(){
     		var content=$(".answer").val();
     		
     	alert(content);
     		//location.href="${pageContext.request.contextPath}/PartExchangeControl?method=addResponse&content="+content+"&len="+${requestScope.ResponseNum}+"&bywho="+"${requestScope.PE.PEByWho}";
     	});
     	$("#posTA").click(function(){
     	
     	var content=$("#answer1").val();
     	alert(content);
     		location.href="${pageContext.request.contextPath}/PartExchangeControl?method=addResponse&content="+content+"&len="+${requestScope.ResponseNum}+"&bywho="+"${partresponse.PR_ByWho_Name}";
     	});
     });
    </script>
	 
	
</head>
<body>
	<div class="container">
      	<div class="column">
      	   <li class="back"><img src="${pageContext.request.contextPath}/image/back.png"/></li>
      	   <li class="co"><a>XXXXXXXXXXX内容</a></li>
      	   <li class="collect"><p><img src="${pageContext.request.contextPath}/image/collect.png"/>收藏</p></li>
      	   <li></li>
      	</div>
      	   <div class="Middle">
      	   	<br/>
      	   	<div class="an-qu">
      	   	<p>${requestScope.partName}</p>
      	     <hr noshade="noshade" width="650" size="1" color="#99999" style="position:relative;top:0px"/>

      	     <div>
      	     	<p>${requestScope.PE.PEByWho}：</p>
      	     	<p>${requestScope.PE.PEContent}</p>
      	     	${requestScope.PE.PETime}
      	        <input  type="button"  value="回复"/>
      	        
      	        <!--回复评论文本框-->
      	      <textarea id="answer"></textarea></div>
      	      <button id="submitResponse">发表</button>
      	  
      	      <%
      	      request.getSession().setAttribute("xxi", 0);
      	      
      	       %>
    <c:if test="${not empty requestScope.responseList}"> 
                 <c:forEach items="${requestScope.responseList}" var="partresponse">
      	     <hr noshade="noshade" width="650" size="1" color="#99999" style="position:relative;top:0px"/>
      	     <div>
      	           	      <%
      	      String i=""+request.getSession().getAttribute("xxi");
      	      int j=Integer.parseInt(i);
      	      j++;
      	      i=Integer.toString(j);
      	       %>
      	     <span>(${partresponse.PRTime})${partresponse.PR_ByWho_Name}回复${partresponse.PR_ToWho}:${partresponse.PRContent }</span>
      	      <input type="button"  id="post${xxi}" value="评论">
      	      <br/>
      	      <!--回复评论文本框-->
      	      <textarea id="answer${xxi}"></textarea>
      	  </div>
      	     
                 </c:forEach>
                 </c:if>

      	     <hr noshade="noshade" width="650" size="1" color="#99999" style="position:relative;top:0px"/>
      	    
      	     <button class="return" onclick="window.location.href='RegistAndLogin?method=loginStudentSuccess'">返回</button>
      	 </div>
      	</div>
      </div>
</body>
</html>