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
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/YMain.css"/>
	 <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/t-myclass.css"/>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/t-myclass.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nicenav.js"></script>
	 	 <script type="text/javascript">
	$(document).ready(function(){ 
	$('#myclass')[0].selectedIndex =-1;
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
					     $("#partTree"+i).append("<li class=\"parent_li\"><span class=\"badge badge-success\" title=\"Collapse this branch\"><i class=\"icon-minus-sign\" ></i>"+"<font id=\""+i+"-"+j+"\">"+data.sd[i].partList[j].PName+"</font>"+"</span>");
					  
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
					  
					 
					  alert(PId);
					  location.href="${pageContext.request.contextPath}/LessonControl?method=changePid&nPid="+PId; 	 
					  	 
					  	 
					  });
					  
					  
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
	           <div class="head"><img src="${pageContext.request.contextPath}/image/logo.png" style="margin:20px 0px 0px 90px">
                             <div class="ex"><p>智能学习|智能管理</p></div>
                         
                       <!--  <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="regist">注册</span>
                               <span hidefocus="true" data-dismiss="modal" aria-role="button" class=" btn-block" id="login">登录</span>  -->
                            
                             <span class="he-block" id="logoutMe" >退出登录</span><span class="he-block">欢迎用户${teacherUser.TName}</span> 
                           </div>

                           <!--导航栏-->
	  <div id="bg">
  <div id="container">
    <ul id="nav">
     <li><a  href="${pageContext.request.contextPath}/teacher/logined.jsp" class="tip"><span>首页</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadStudentMyClass" class="tip"><span>我的学生</span></a></li>
			<li><a href="${pageContext.request.contextPath}/LessonControl?method=loadBuildClass" class="tip"><span>课程建设</span></a></li>
			<li><a class="cur" href="${pageContext.request.contextPath}/LessonControl?method=loadMyClass" class="tip"><span>我的课程</span></a></li>
			<li><a href="${pageContext.request.contextPath}/TeacherControl?method=loadMyInfo" class="tip"><span>个人建设</span></a></li>
      
    </ul>
    <div id="buoy" ></div>
  </div>
  
  <script type="text/javascript">
  $.nicenav(300);
  </script>
</div>
   <div class="Middle">

		     	<!--选课程代码-->
		     	<div class="Middle_left">
		     		<select class="myclass" id="myclass" name="chooseClass">
                      <!--  <option class="selected" value ="1">测试用的小课程</option>
                       <option value ="C3069">小情书</option>
                       <option value="13531188587CC">CC</option>
                       <option value="C9999">高级java程序设计</option> -->
                        <c:forEach items="${requestScope.MyLesson}" var="lessons">
                                 <option value="<c:out value="${lessons.pk_LId}" />"  selected="selected" ><c:out value="${lessons.LName}" /></option> 
                               </c:forEach>
                   </select>


              <!--             树状图            -->
 <div class="tree" id="tree">
    
    <label>   <font color="red"  id="msgLabel0"></font></label>
    <ul id="tree1">
   
        <li class="parent_li" >
            <span title="Collapse this branch"><i class="icon-calendar"></i> 第一章</span>
            <ul>
                <li class="parent_li">
                	<span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign" ></i>第一节</span>
                  </li>
                   </ul>
               </li>
                 <li class="parent_li" >
            <span title="Collapse this branch"><i class="icon-calendar"></i> 第一章</span>
            <ul>
                <li class="parent_li">
                	<span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign" ></i>第一节</span>
                  </li>
                   </ul>
               </li>
 <!-- <c:forEach items="${requestScope.lesson}" var="sd">
							<li class="parent_li"><span title="Collapse this branch"><i
									class="icon-calendar"></i> <c:out value="${sd.SName}" /></span>
								
								<ul>
								<c:forEach items="${sd.partList}" var="part">
									<li class="parent_li"><span class="badge badge-success"
										title="Collapse this branch"><i class="icon-minus-sign"></i>
										<c:out value="${part.PName}" /></span>
									</c:forEach>
								</li>
								
						</c:forEach>
  -->
              
       </ul>
		     	</div>
		     </div>


                <!--右边的内容-->
		     	<div class="Middle_right">
                    <img src="${pageContext.request.contextPath}/image/class1.jpg"/>
                    <div>介绍</div>
   
                </div>
               
               


		     	
		     </div>
		     <div class="footer"></div>
       </div>
	
  <div class="bottom">
		     	<dl>
                    <dt>联系/Contact</dt>
                    <hr style=" height:0px;width:198px;border:none;border-top:1px dotted white;"/>
                    <dd><a href="">广告合作</a></dd>
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
                    <dd><a href="">微信</a></dd>
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
