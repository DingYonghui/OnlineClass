<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/YMain.css"/>
     <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/t-course.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/js/t-course.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
	 <script charset="utf-8" src="${pageContext.request.contextPath}/editor/kindeditor.js"></script>
	 <script charset="utf-8" src="${pageContext.request.contextPath}/editor/lang/zh_CN.js"></script>
	<script type="text/javascript">
	
$(document).ready(function(){
$("#delectSection").click(function()
	{
	if(confirm("确定要删除？")){
	var selectIndexS=$('option:selected', '#aabb').index();
	
			$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"delectSection",LId:$("#ab").val(),selectIndexS:selectIndexS},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
					$("#aabb option:selected").remove();
					alert(data.msg);
					$('#aabb')[0].selectedIndex =-1;
					}
					else{
					alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
		}
	});
//
$("#delectPart").click(function()
	{
	if(confirm("确定要删除？")){
	var selectIndexS=$('option:selected', '#aabb').index();
	var selectIndexP=$('option:selected', '#AABB').index();
			$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"delectPart",LId:$("#ab").val(),selectIndexS:selectIndexS,selectIndexP:selectIndexP},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
					$("#AABB option:selected").remove();
					alert(data.msg);
					$('#AABB')[0].selectedIndex =-1;
					}
					else{
					alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
		}
	});

//
	$("#delectButton").click(function()
	{
	if(confirm("确定要删除？")){
	
			$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"delectLesson",LId:$("#ab").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
					$("#ab option:selected").remove();
					alert(data.msg);
					$('#ab')[0].selectedIndex =-1;
					}
					else{
					alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
		}
	});

	$("#logoutMe").click(function(){
	if(confirm("是否继续")){

   location.href = "${pageContext.request.contextPath}/RegistAndLogin?method=logoutSuccess";
   }
	});
	
	
	/*
	改变章节 发送请求 LId+_选项下标就是SId  发送请求 获取part
	*/
	$("#aabb").change(function(){
		$("#addPart").removeAttr("disabled");
			$("#changePart").removeAttr("disabled");
		$("#delectPart").removeAttr("disabled");
	});
	$("#ccdd").blur(function(){
		$("#addPart").attr('disabled',"true");
		$("#changePart").attr('disabled',"true");
			$("#delectPart").attr('disabled',"true");
	});
	/*
	读取显示已有课程相应内容
	*/
	$("#ab").change(function(){
	
		$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"LoadMyClass",LId:$("#ab").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					//alert(data.lessonLoaded.LName);
				if(data.success)
				{
				//MyLIcon显示
				$("#MyLIcon").attr("src",data.lessonLoaded.LIcon);
				$("#LName").attr("value","");
				//$("#LIcon").attr("value","");
			
				$("#LName").attr("value",data.lessonLoaded.LName);
				//$("#LIcon").attr("value",data.lessonLoaded.LIcon);
				
				
				//章列表清空然后显示新增option
				$("#aabb").empty();
				$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"returnSectionArray",LId:$("#ab").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
					//为select循环添加项目
					// <option value="<c:out value="${lessons.pk_LId}" />"  selected="selected" ><c:out value="${lessons.pk_LId}" /></option> 
					//alert(data.sectionList[0].SName+"？");
					
					for(var i=0; i<data.sectionList.length; i++)  
					{
					$("#aabb").append("<option value=\""+data.sectionList[i].SName+"\">"+data.sectionList[i].SName+"</option>");
					}
					  $('#aabb')[0].selectedIndex =-1;
					}
					else{
					alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
				}
				else{
			$("#LName").attr("value","");
				$("#LIcon").attr("value","");
			
				$("#LName").attr("value",data.msg);
				$("#LIcon").attr("value",data.msg);
			
				}
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
		});
		/*
		AJAXLOAD节
		*/
		$("#aabb").change(function()
		{
		$("#AABB").empty();
		//
		var selectIndex=$('option:selected', '#aabb').index();
			$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"LoadPart",selectIndex:selectIndex,LId:$("#ab").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
					alert(data.partList[0].PName);
					for(var i=0; i<data.partList.length; i++)  
						{
					$("#AABB").append("<option value=\""+data.partList[i].PName+"\">"+data.partList[i].PName+"</option>");
						}
						$('#AABB')[0].selectedIndex =-1;
					
					}
					else{
					$("#CCDD").attr("value",data.msg);
					//alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
		});
	$("#add_btn").click(function()
		{
		
				$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"ajaxAddLesson",selectIndex:selectIndex,LId:$("#ab").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
						$("#MyLIcon").attr("src",data.lessonLoaded.LIcon);
					}
					else{
					
					//alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
		});
	
		/*
		AJAX添加节记录
		*/
		/*
		AJAX更新章记录？？？？？
		*/
		$("#changeSection").click(function()
		{
		
				$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"changeSection",selectIndex:selectIndex,LId:$("#ab").val()},
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
					//改变某一项option的值
					}
					else{
					
					//alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){     
			   alert("发生错误：" + jqXHR.status);  
			},     
		});
		});
		/*
		ADD jie addPart
		*/
		$("#addPart").click(function(){
		var selectLength = $("select[name=\"AABB\"] option").size()+1;//$('option:selected', '#aabb').index();
		var selectIndexS=$('option:selected', '#aabb').index()+1;
		if(selectLength>10)
		{
		alert("最多10节");
		return;
		}
				$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"addPart",PName:$("#CCDD").val(),PId:$("#cd").val()+"_"+selectIndexS+"_"+selectLength,SId:$("#cd").val()+"_"+selectIndexS},//SId:$("#cd").val()+"_"+,
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
					//alert(selectLength);
					$("#AABB").append("<option value=\""+data.msg.PName+"\">"+data.msg.PName+"</option>");
					alert("成功添加一节");
					//服务器已经添加完毕 那么就在select添加一个option
					
					}
					else{
					alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){ 
			if(jqXHR.status==500)
			{
			   alert("发生错误,请确认选择了已有的章");  
			}    
			   //alert("发生错误：" + jqXHR.status);  
			},     
		});
		});
		/*
		AJAX添加章记录 
		*/
		$("#addSection").click(function(){
		var selectLength = $("select[name=\"aabb\"] option").size()+1;//$('option:selected', '#aabb').index();
		if(selectLength>10)
		{
		alert("最多10章");
		return;
		}
				$.ajax({ 
		    type: "POST", 	
			url: "${pageContext.request.contextPath}/LessonControl",
			data:{method:"addSection",SName:$("#ccdd").val(),SId:$("#cd").val()+"_"+selectLength,LId:$("#cd").val()},//SId:$("#cd").val()+"_"+,
			dataType: "json",
			success: function(data) {
			
					//局部刷新页面
					if(data.success)
					{
					//alert(selectLength);
					$("#aabb").append("<option value=\""+data.msg.SName+"\">"+data.msg.SName+"</option>");
					alert("成功添加一章");
					//服务器已经添加完毕 那么就在select添加一个option
				$('#aabb')[0].selectedIndex =-1;
					}
					else{
					alert(data.msg);
					}
					
				
			},
			error: function(jqXHR){ 
			if(jqXHR.status==500)
			{
			   alert("发生错误,请确认选择了已有的课程");  
			}    
			   //alert("发生错误：" + jqXHR.status);  
			},     
		});
		});
	});
	</script>

</head>
<body>
	   <div class="container">
	          <div class="head"><img src="${pageContext.request.contextPath}/image/logo.png" style="margin:20px 0px 0px 90px">
	                           <div class="ex"><p>智能学习|智能管理</p></div>
                         
	                     <!--  <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="regist">注册</span>
                               <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="login">登录</span>  -->
                            
                               <span class="he-block" id="logoutMe" >退出登录</span>
                        <span class="he-block">欢迎用户${teacherUser.TName}</span>
                           </div>
                           

                           <!--导航栏-->
   <div id="bg">
	<div id="container">
		<ul id="nav">
			<li><a  href="${pageContext.request.contextPath}/teacher/logined.jsp" class="tip"><span>首页</span></a></li>
			<li><a  href="${pageContext.request.contextPath}/LessonControl?method=loadStudentMyClass" class="tip"><span>我的学生</span></a></li>
			<li><a class="cur" href="${pageContext.request.contextPath}/LessonControl?method=loadBuildClass" class="tip"><span>课程建设</span></a></li>
			<li><a  href="${pageContext.request.contextPath}/LessonControl?method=loadMyClass" class="tip"><span>我的课程</span></a></li>
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
		         		<div>
		     	<h4>基本：</h4>
		     	<center>  <label>   <font color="red"  id="msgLabel1">${requestScope.LessonBuildMsg} </font></label></center>
		     	<form  name="form3" id="formBase" action="${pageContext.request.contextPath}/LessonControl?method=buildBase" method="post"
			enctype="multipart/form-data"  target="hidden_frame" >
		  
		     	    <p>课程ID：<!--&nbsp; &nbsp;<input id=""　type="text" name="pk_LId" /> -->

		     	     <!--select标签和input外面的span标签的格式是为了使两个位置在同一位置，控制位置--> 
                                <span style="position:absolute; margin:12px; border:1pt solid #c1c1c1;  overflow:hidden;  width:188px;  height:22px;  clip:rect(-1px 190px 190px 170px); margin:-90px；"> 
                                
                                 <select  name="ab" id="ab"  style="width:190px; height:24px;  margin:-3px;" 
                                onChange="javascript:document.getElementById('cd').text=document.getElementById('ab').options[document.getElementById('ab').selectedIndex].text;javascript:document.getElementById('cd').value=document.getElementById('ab').options[document.getElementById('ab').selectedIndex].value"> 
                                  <!--下面的option的样式是为了使字体为灰色，只是视觉问题，看起来像是注释一样--> 
                              
                              <c:if test="${not empty requestScope.MyLesson}"> 
                               <c:forEach items="${requestScope.MyLesson}" var="lessons">
                                 <option value="<c:out value="${lessons.pk_LId}" />"  selected="selected" ><c:out value="${lessons.pk_LId}" /></option> 
                               </c:forEach>
                               	
                               </c:if>
                                <c:if test="${ empty requestScope.MyLesson}"> 
                                <option value="pleaseEdit"  selected="selected" >---请编辑---</option> 
                                      </c:if>                       
                                 </select> 
                                
                                 </span> 
                                   <span style="position:absolute; margin:12px; border-top:1pt solid #c1c1c1;border-left:1pt solid #c1c1c1;border-bottom:1pt solid #c1c1c1;width:170px;height:22px;"> 
                                   <input type="text" name="pk_LId" id="cd" value="请编辑" style="width:170px;height:22px;border:0pt;"  onkeyup="upchange()"> 
                                </span> 

                                 
                                   <button type="button" id="delectButton"  style="position:relative;left:240px;top:4px" >删除</button>
                                  
                             	
                               </p>
                             <center>(ID格式为手机号+课程ID)</center>  
		     		<p>课程名称： <input type="text" name="LName" id="LName"/></p>
		     		<p>课程简介：<span>+</span>

		     		</p>
		     		<p>课程图标： <input type="file" name="LIcon" style="position:relative;left:10px;width:210px"/>
		     		<img  id="MyLIcon" style="position:absolute;width:100px;height:100px;"/> 
		     	    </P>
		     	    <input type="hidden" name="FK_TId" value="${teacherUser.pk_TPhone}"/>
                    
		     	  <center>
		     	   <input id="add_btn" type="submit"  value="新增">
		     	     
		     	  </center>
                    <iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>
      
		     		<center><font color="red">支持jpg等图片文件的上传</font></center>                 
		     		</form>
		     	</div>	
           
               <div class="box_below">
		     	<h4>详细：</h4>
		     	 <form  name="form1" action="" method="get">
		     	    <p>章：
                                 <!--select标签和input外面的span标签的格式是为了使两个位置在同一位置，控制位置--> 
                                <span style="position:absolute; margin:15px; border:1pt solid #c1c1c1;  overflow:hidden;  width:188px;  height:19px;  clip:rect(-1px 190px 190px 170px); margin:-90px；"> 
                                
                                 <select name="aabb" id="aabb" style="width:190px; height:24px;  margin:-2px;" 
                                onChange="javascript:document.getElementById('ccdd').text=document.getElementById('aabb').options[document.getElementById('aabb').selectedIndex].text;javascript:document.getElementById('ccdd').value=document.getElementById('aabb').options[document.getElementById('aabb').selectedIndex].value"> 
                                  <!--下面的option的样式是为了使字体为灰色，只是视觉问题，看起来像是注释一样--> 
                               
                                                           
                                 </select> 
                                
                                 </span> 
                                   <span style="position:absolute; margin:15px; border-top:1pt solid #c1c1c1;border-left:1pt solid #c1c1c1;border-bottom:1pt solid #c1c1c1;width:170px;height:22px;"> 
                                   <input type="text" name="ccdd" id="ccdd" value="请编辑" style="width:170px;height:22px;border:0pt;"  onkeyup="upchange()"> 
                                </span> 
                               <button type="button" id="addSection"  style="position:relative;left:210px;top:4px" >添加</button>
                               <button type="button" id="delectSection"  style="position:relative;left:210px;top:4px" >删底</button>
                               
                               </P> </form>
                                                              
                              
                   <form name="form2" action="form_action.asp" method="get">
		     		<p>节：  <!--select标签和input外面的span标签的格式是为了使两个位置在同一位置，控制位置--> 
                                <span style="position:absolute; margin:15px; border:1pt solid #c1c1c1;  overflow:hidden;  width:188px;  height:19px;  clip:rect(-1px 190px 190px 170px); margin:-90px；"> 
                                
                                 <select name="AABB" id="AABB" style="width:190px; height:24px;  margin:-2px;" 
                                onChange="javascript:document.getElementById('CCDD').text=document.getElementById('AABB').options[document.getElementById('AABB').selectedIndex].text;javascript:document.getElementById('CCDD').value=document.getElementById('AABB').options[document.getElementById('AABB').selectedIndex].value"
                               > 
                                 
                                                                  
                                 </select> 
                                
                                 </span> 
                                   <span style="position:absolute; margin:15px; border-top:1pt solid #c1c1c1;border-left:1pt solid #c1c1c1;border-bottom:1pt solid #c1c1c1;width:170px;height:22px;"> 
                                   <input type="text" name="CCDD" id="CCDD" value="请编辑" style="width:170px;height:22px;border:0pt;" > 
                                </span> 
                               <button type="button" id="addPart"  style="position:relative;left:210px;top:4px" disabled="disabled">添加</button>
                               <button type="button" id="delectPart"  style="position:relative;left:210px;top:4px" disabled="disabled" >删底</button>
                               
                               </P> </form>
                                  </div>
		               </div>
		       </div>
		     </div>



		     <div class="footer"></div>
       </div>
	  <div class="bottom">
		     	<dl>
                    <dt>联系/Contact</dt>
                    <hr style=" height:0px;width:198px;border:none;border-top:1px dotted white;"/>
                    <dd><a href="${pageContext.request.contextPath}/image/weichatOld.jpg">广告合作</a></dd>
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
                    <dd><a href="${pageContext.request.contextPath}/image/weichatOld.jpg">微信</a></dd>
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