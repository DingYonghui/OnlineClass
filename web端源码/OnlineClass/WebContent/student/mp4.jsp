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
               $("#tree1").append("   <li class=\"parent_li\">"+ "<img src=\"${pageContext.request.contextPath}/image/book1.png\"/>"+"<span title=\"Collapse this branch\"><i class=\"icon-calendar\"></i> "+data.sd[i].SName+"</span>"+"         <hr noshade=\"noshade\" width=\"333\" size=\"1\" color=\"#99999\" style=\"position:relative;top:5px\"/>"+"<ul id=\"partTree"+i+"\"></ul> </li>");
          //   $("#partTree").append("<li class=\"parent_li\"><span class=\"badge badge-success\" title=\"Collapse this branch\"><i class=\"icon-minus-sign\" ></i>"+data.sd[i].partList[0].PName+"</span>");
           
               for(var j=0;j<data.sd[i].partList.length;j++)
              {
             
               $("#partTree"+i).append("<li id=\""+i+"-"+j+"\" class=\"son_li\">"+"<img src=\"${pageContext.request.contextPath}/image/book2.png\"/>"+"<span  class=\"badge badge-success\" title=\"Collapse this branch\"><i class=\"icon-minus-sign\" ></i>"+"<font >"+data.sd[i].partList[j].PName+"</font>"+"</span>");
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
            
              /*  $.ajax({ 
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
          <ul>
      	   <li class="back"><img src="${pageContext.request.contextPath}/image/back.png"/></li>
      	   <li class="co"><a>XXXXXXXXXXX内容</a></li>
      	   <li class="collect"><p><img src="${pageContext.request.contextPath}/image/collect.png" style="position:relative;top:5px;left:-5px;"/>收藏</p></li>
            <li> <input type="hidden" id="PIDVALUE" value="C00001_01_01" /></li><!-- 初始化的PId参数 -->
      	   <!--欢迎退出-->
               
           <li class="exit" id="logoutMe">退出</li>
           <li class="welcome"><a>欢迎用户${studentUserInfo.SName }</a></li>
            </ul>
      	</div>
      	<div class="video-box">
          <video  class="video" width="100%" height="396" controls="controls">
                  <source src="${pageContext.request.contextPath}${nowVPath}" type="video/mp4" />
                  <source src="movie.ogg" type="video/ogg" />
                  <source src="movie.webm" type="video/webm" />
                 
                  
              </video>
      	</div>
      	
        <div class="Middle">
        <!--节-->
        <div class="Middle-left">
        <div class="Content">
              <div class="Content-box">
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
                         <img src="${pageContext.request.contextPath}/image/lie.png"/>
                         <span title="Collapse this branch"><i class="icon-calendar"></i> 第一章</span>
                          <hr noshade="noshade" width="630" size="1" color="#d9d6c3" style="position:relative;top:5px"/>
                         <ul>
                             <li class="son_li">
                             <img src="${pageContext.request.contextPath}/image/video.png"/>
                             <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span></li>
                              <li class="son_li">
                              <img src="${pageContext.request.contextPath}/image/video.png"/>
                              <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第二节</span></li>
                              <li class="son_li">
                              <img src="${pageContext.request.contextPath}/image/video.png"/>
                             <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第三节</span></li>
                            
                        </ul>
                        </li>

                        <li class="parent_li">
                          <img src="${pageContext.request.contextPath}/image/lie.png"/>
                          <span title="Collapse this branch"><i class="icon-calendar"></i> 第二章</span>
                          <hr noshade="noshade" width="630" size="1" color="#d9d6c3" style="position:relative;top:5px"/>
                          <ul>
                            <li class="son_li">
                            <img src="${pageContext.request.contextPath}/image/video.png"/>
                            <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span>   
                          </ul>
                        </li>

                       <li class="parent_li">
                        <img src="${pageContext.request.contextPath}/image/lie.png"/>
                        <span title="Collapse this branch"><i class="icon-calendar"></i> 第三章</span>
                        <hr noshade="noshade" width="630" size="1" color="#d9d6c3" style="position:relative;top:5px"/>
                        <ul>
                        <li class="son_li">
                           <img src="${pageContext.request.contextPath}/image/video.png"/>
                        <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span>   
                        </ul>
                       </li>

                       <li class="parent_li">
                        <img src="${pageContext.request.contextPath}/image/lie.png"/>
                        <span title="Collapse this branch"><i class="icon-calendar"></i> 第四章</span>
                        <hr noshade="noshade" width="630" size="1" color="#d9d6c3" style="position:relative;top:5px"/>
                        <ul>
                        <li class="son_li">
                           <img src="${pageContext.request.contextPath}/image/video.png"/>
                        <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span>   
                        </ul>
                       </li>

                       <li class="parent_li">
                        <img src="${pageContext.request.contextPath}/image/lie.png"/>
                        <span title="Collapse this branch"><i class="icon-calendar"></i> 第五章</span>
                        <hr noshade="noshade" width="630" size="1" color="#d9d6c3" style="position:relative;top:5px"/>
                        <ul>
                        <li class="son_li">
                           <img src="${pageContext.request.contextPath}/image/video.png"/>
                        <span class="badge badge-success" title="Collapse this branch"><i class="icon-minus-sign"></i>第一节</span>   
                        </ul>
                       </li>
                    </ul>
          </div>
                
              
             </div>
            </div>
              <!--问答面-->
            <div class="li02">
              <textarea class="writing" id="content"></textarea>
               <span class="course-btn" id="submitExchange"> <img class="pen" src="${pageContext.request.contextPath}/image/pen.png"/>发表评论</span>
               <c:if test="${not empty requestScope.partExchangeList}"> 
                 <c:forEach items="${requestScope.partExchangeList}" var="partexchange">
                <hr noshade="noshade" width="650" size="1" color="#99999" style="position:relative;top:100px"/>
               <div class="qu-an">
                <div class="qu">${partexchange.PEByWho}同学：${partexchange.PEContent}</div>
                <div class="time">${partexchange.PETime}</div>
                 <a class="answer" href="${pageContext.request.contextPath}/PartExchangeControl?method=loadResponse&PEId=${partexchange.pk_PEId}">去回复</a></div>
               </c:forEach>
                </c:if>
                <!--
                <div class="qu-an">
                <div class="qu">xx同学：XXXX</div>
                <div class="time">XX月XX日</div>
                <span class="answer">回复</span></div>  -->

            </div>
              <!--测试面-->
            <div class="li03">
            <div> 
         
              <div class="nothing">
                   
              </div>
            </div>
            </div>
          </div>
        </div>
        <div class="Middle-right">
              <p>选择课程：</p>
              <br/>
              <div class="styled-select">
               <select class="select" id="myclass" name="chooseClass">
                        <c:forEach items="${requestScope.MyLesson}" var="lessons">
                                 <option value="<c:out value="${lessons.pk_LId}" />"  selected="selected" ><c:out value="${lessons.LName}" /></option> 
                               </c:forEach>
                     </select>
                </div>
                <br/><br/><br/>
                <span class="sure">submit</span>
                <br/><br/>
                <p>老师提示：</p>
                 <hr noshade="noshade" width="200" size="1" color="#99999" style="position:relative;top:10px"/>
                 <div class="teacher-tip"></div>
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
      </div>

	</body>
	</html>