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
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/mp4.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/mp4.js"></script>
	  <script type="text/javascript">
	 
	 $(document).ready(function(){
	 
	 
	 
	  $("#myclass").val("${nowLid}");//默认
	  
	//初次加载树状
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
					   
					     $("#partTree"+i).append("<li class=\"parent_li\"><span  class=\"badge badge-success\" title=\"Collapse this branch\"><i  class=\"icon-minus-sign\" ></i>"+"<font id=\""+i+"-"+j+"\">"+data.sd[i].partList[j].PName+"</font>"+"</span>");
				//alert(i+""+j);
					 
					     
					      	/*
					      	*/
					 	 $("#"+i+"-"+j).click(
					 	
					 	//////PID有问题
					 	 function(){
					 	 //z这里面ij无效 点击的时候发送
					      	 //alert(i);
					  var idm=$(this).attr("id");
					      	 var myi=idm.substr(0,1);
					      	 var myj=idm.substr(2,1);
					      	var path=data.sd[myi].partList[myj].PVideoPath;
					 var PId=data.sd[myi].partList[myj].pk_PId;
					  
					 
					alert(data.sd[myi].partList[myj].PName);
					  location.href="${pageContext.request.contextPath}/PartExchangeControl?method=changePid&nPid="+PId+"&nPath="+path; 	 
					  	 
					  	 
					  });
					  
					    /*	$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/PartExchangeControl?method=changePid",
			data: {
				nPid: $("#partTreeItem"+j).val(), 
				nPath:path,
				
				
			},
			});*/
					  
					  //alert(path);
					 // $("video").prop("src","${pageContext.request.contextPath}"+path);
					  	// $("#video video")[0].load;
					  	 //设置视频  之后 设置评论的节 PId 
					  	
					  	
					  	
					  	
					  	 //AJAX
					  	
				
					  
					 
					  
					     }
					     
					     
					  }  
					//局部刷新页面
					 
    $.getScript("${pageContext.request.contextPath}/js/mp4.js");
  
					
			}
			//var str="<script type='text/javascript' src='${pageContext.request.contextPath}/js/jquery-1.11.3.min.js'>";
			//$("#container").append(str);
			
					$("#msgLabel0").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
	
	
	 
   	$("#submitExchange").click(function(){
   	var content=$("#content").val();
   	alert(content);
   	location.href="${pageContext.request.contextPath}/PartExchangeControl?method=addExchange&content="+content+"&pid="+$("#PIDVALUE").val()+"&len="+${requestScope.ExchangeNum};
   	});
   	
   	 $("#myclass").change(function(){ 
	     	/*
	     	$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl?method=changeLid",
			data: {
				nLid: $("#myclass").val(), 
				
				
				
			},
			*/
	 	location.href="${pageContext.request.contextPath}/LessonControl?method=changeLid&nLid="+$("#myclass").val();
	 
	 //改变lid 重新加载页面
	  /*
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
					     $("#partTree"+i).append("<li class=\"parent_li\"><span id=\"partTreeItem"+j+"\" class=\"badge badge-success\" title=\"Collapse this branch\"><i class=\"icon-minus-sign\" ></i>"+data.sd[i].partList[j].PName+"</span>");
					 var path=data.sd[i].partList[j].PVideoPath;
					 var PId=data.sd[i].partList[j].pk_PId;
					  $("#partTreeItem"+j).click(function(){
					  //alert(path);
					  $("video").prop("src","${pageContext.request.contextPath}"+path);
					  	// $("#video video")[0].load;
					  	 //设置视频  之后 设置评论的节 PId 
					  	
					
					  	
					  	
					  	 //AJAX
					  	 //设置现在的Lid
					  	 
					  	 
					  	 
					  });
					  
					 
					  
					     }
					  }  
					//局部刷新页面
					 
    $.getScript("${pageContext.request.contextPath}/js/mp4.js");
  
					
			}
			//var str="<script type='text/javascript' src='${pageContext.request.contextPath}/js/jquery-1.11.3.min.js'>";
			//$("#container").append(str);
			
					$("#msgLabel0").html(data.msg);
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
	  */
		//});
	});
	
	
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
      	<div class="column">
      	   <li class="back"><img src="${pageContext.request.contextPath}/image/back.png"/></li>
      	   <li class="co"><a>欢迎用户${studentUserInfo.SName }</a><li class="" id="logoutMe">退出</li></li>
      	   <li class="collect"><p><img src="image/collect.png"/>收藏</p></li>
      	  <li> <input type="hidden" id="PIDVALUE" value="C00000_1_1" /></li><!-- 初始化的PId参数 -->
      	</div>
      	<div class="video">
          <video  class="video" width="950" height="900" controls="controls">
                  <source id="MR" src="${pageContext.request.contextPath}${nowVPath}" type="video/mp4"/>
                  <source src="movie.ogg" type="video/ogg" />
                  <source src="movie.webm" type="video/webm" />
                  
                  <object data="movie.mp4" width="950" height="450">
                    <embed src="movie.swf" width="950" height="450" />
                  </object>
              </video>
      	</div>
      	
        <!--节-->
        <div class="Content">
              <div class="Content-box">
              <center><font color="red">${requestScope.msg }</font></center>
            <ul><li class="cur" id="li01"><p>视频</p></li>
              <li id="li02"><p>问答</p></li>
              <li id="li03"><p>测试</p></li>
              </ul>
            </div>
                <!--文档面-->
            <div class="li01">
            <div> 
                                           <!--             树状图            -->
 <div class="tree">
  <label>   <font color="red"  id="msgLabel0"></font></label>
    <ul id="tree1">
        <li class="parent_li">
            <span title="Collapse this branch"><i class="icon-calendar"></i> 第一章</span>
            <ul>
                <li class="parent_li">
                  <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span>
                    <ul>
                        <li style="display: list-item;">
                          <a href=""><span><i class="icon-time">视频</i> </span></a>
                        </li>
                        <li style="display: list-item;">
                          <a href=""><span><i class="icon-time">文档</i> </span></a>
                        </li>
                        <li style="display: list-item;">
                          <a href=""><span><i class="icon-time">讨论</i> </span></a>
                        </li>
                    </ul>
                   </ul>
               </li>


              

           
       </ul>
          </div>
                
              
             </div>
            </div>
              <!--问答面-->
            <div class="li02">
              <textarea class="writing" id="content"></textarea>
               <input type="button" id="submitExchange" class="course-btn" value="发表评论">
                <c:if test="${not empty requestScope.partExchangeList}"> 
                 <c:forEach items="${requestScope.partExchangeList}" var="partexchange">
               <div class="qu-an">
                <div class="qu">${partexchange.PEByWho}同学：${partexchange.PEContent}</div>
                <div class="time">${partexchange.PETime}</div>
                <a class="answer" href="${pageContext.request.contextPath}/PartExchangeControl?method=loadResponse&PEId=${partexchange.pk_PEId}">去回复</a>
                
                </div>
                </c:forEach>
                </c:if>
                <!--
                   <div class="qu-an">
                <div class="qu">xx同学：XXXX</div>
                <div class="time">XX月XX日</div>
                <span class="answer">回复</span></div>
                  -->
             
                
            </div>
            <div><select class="myclass" id="myclass" name="chooseClass" style="position:relative;top:-500px;left:100px;width:350px;">
                         <c:if test="${not empty requestScope.MyLesson}"> 
                               <c:forEach items="${requestScope.MyLesson}" var="lessons">
                                 <option value="<c:out value="${lessons.pk_LId }" />"   ><c:out value="${lessons.LName}" /></option> 
                               </c:forEach>
                       </c:if>
                   </select></div>
              <!--测试面-->
            <div class="li03">
            <div> 
         
             
            </div>
            </div>

          </div>
          
           
          
      </div>
          

	</body>
	</html>