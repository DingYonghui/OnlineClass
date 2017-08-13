<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%int i=0; %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
     <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/discuss.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/discuss.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
    <script type="text/javascript">
          var bywho1 = "${requestScope.PE.PEByWho}";
    
     $(document).ready(function(){
      $("#submitResponse").click(function(){
         var content=$("#answer").val();
      alert(content);
     // alert(c);
         location.href="${pageContext.request.contextPath}/PartExchangeControl?method=addResponse&content="+content+"&len="+${requestScope.ResponseNum}+"&bywho="+bywho1;
      });
     /// $("#posTA").click(function(){
      
     // var content=$("#answer1").val();
     // alert(content);
        // location.href="${pageContext.request.contextPath}/PartExchangeControl?method=addResponse&content="+content+"&len="+${requestScope.ResponseNum}+"&bywho="+"${partresponse.PR_ByWho_Name}";
     // });
    // $("#plun").click(function(){
     	
     //});
     });
    </script>
</head>
<body>
	<div class="container">
      	<div class="column">
      	   <li class="back"><img src="${pageContext.request.contextPath}/image/back.png"/></li>
      	   <li class="co"><a>XXXXXXXXXXX内容</a></li>
      	   <li class="collect"><p><img src="${pageContext.request.contextPath}/image/collect.png"/>&nbsp;收藏</p></li>
      	   <li></li>
      	</div>
      	   <div class="Middle">
             <div class="Middle-left">
      	   	<br/>
      	   	<div class="an-qu">
      	   	<h2><img src="${pageContext.request.contextPath}/image/write.png"/>&nbsp;${requestScope.partName}课程问答</h2>
               <br/>
      	      <hr noshade="noshade" width="650" size="1" color="#99999" style="position:relative;top:0px"/>

      	     <div>
               <br/>
      	     	<p style="margin:0px 0px 0px 10px">${requestScope.PE.PEByWho}：</p>
      	     	<p style="margin:0px 0px 0px 10px">${requestScope.PE.PEContent}</p>
               <br/>
      	     	<p style="margin:0px 0px 0px 10px">${requestScope.PE.PETime}</p>
      	      <input  class="btn-answer" type="button"  value="回复"/>
      	      <!--回复评论文本框-->
      	      <textarea id="answer" class="answer" ></textarea>
               <input id="submitResponse" type="button"  value="发表"/>
               <input id="false" type="button"  value="取消"/>      

              <hr noshade="noshade" width="650" size="1" color="#99999" style="position:relative;top:0px"/>
             
               <c:if test="${not empty requestScope.responseList}">
                   <c:forEach items="${requestScope.responseList}" var="partresponse">
                  <span>(${partresponse.PRTime})${partresponse.PR_ByWho_Name}回复${partresponse.PR_ToWho}:${partresponse.PRContent }</span>
                    <input class="btn-answer answerbtn" sid="${partresponse.PR_ByWho_Name}" type="button" id="plun" value="评论" >
                    <hr noshade="noshade" width="650" size="1" color="#99999" style="position:relative;top:0px"/>
                    <br/>
             
                   </c:forEach>
                </c:if> 
      	    
              
                  <br/>
                  </div>

      	     
      	     

                
      	    

 
      	      <hr noshade="noshade" width="650" size="1" color="#99999" style="position:relative;top:0px"/>
      	      <input id="return" type="button"  onclick="window.location.href='RegistAndLogin?method=loginStudentSuccess'" value="返回"/>
      	 </div>
      </div>

            <div class="Middle-right">
                  <p><img src="${pageContext.request.contextPath}/image/head.png"/>&nbsp;用户名</p>
                  <br/>
                  <hr noshade="noshade" width="250" size="1" color="#d9d6c3" style="position:relative;top:0px"/>
                  <ul class="person">
                        <li class="line">提问</li>
                        <li class="line">回复</li>
                        <li>关注</li></ul>
                  <hr noshade="noshade" width="250" size="1" color="#d9d6c3" style="position:relative;top:0px"/>
                  <br/><br/>
                  <h4>相关问题</h4>
                  <br/>
                  <hr noshade="noshade" width="250" size="1" color="#d9d6c3" style="position:relative;top:0px"/>
                  <br/>
                  <div class="question"></div>
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
      <script>
      $(".answerbtn").click(function(){
        //var content=$("#answer").val();
			 bywho1=$(this).attr("sid");      
      //location.href="${pageContext.request.contextPath}/PartExchangeControl?method=addResponse&content="+content+"&len="+${requestScope.ResponseNum}+"&bywho="+bywho1;
      });
</script>
</body>
</html>
