$(document).ready(function() {
      $('#ab')[0].selectedIndex = -1;
        $('#aabb')[0].selectedIndex = -1;
         $('#AABB')[0].selectedIndex = -1;

});

//课程编辑框
function add_opt3(){
//opt表示现有可选项的数目
    if (document.getElementById("cd").value!="") {
    var opt=ab.options.length;
    form3.ab.options[opt]=new Option(ok=(form3.ab.options[opt])?
    form3.ab.options[opt].innerText+String.fromCharCode(event.keyCode): document.getElementById("cd").value,ok)
    form3.ab.selectedIndex=opt;    
    document.getElementById("cd").value="";
     
       }    
}

//删除选中项 

function move() {
   var obj=document.getElementById('ab');
   //index,要删除选项的序号，这里取当前选中选项的序号
    var index=obj.selectedIndex;
    obj.options.remove(index);
    document.getElementById("cd").value="";
}

//章节编辑框
function add_opt1(){
//opt表示现有可选项的数目
    if (document.getElementById("ccdd").value!="") {
    var opt=aabb.options.length;
    form1.aabb.options[opt]=new Option(ok=(form1.aabb.options[opt])?
    form1.aabb.options[opt].innerText+String.fromCharCode(event.keyCode): document.getElementById("ccdd").value,ok)
    form1.aabb.selectedIndex=opt;
    document.getElementById("ccdd").value="";
    $('#aabb')[0].selectedIndex = -1;

       }    
}


function change() {  
    var options=$("#aabb option:selected");
    var x=document.getElementById("ccdd").value;
    options.val(x);
    options.text(x);    
}

//章文本框变化前节文本框不可用
function upchange()  {
    $('#CCDD').attr("disabled",false); 
      $('#add').attr("disabled",false); 
}

function add_opt2(){
//opt表示现有可选项的数目
   if (document.getElementById("CCDD").value!="") {
    var opt=AABB.options.length;
    form2.AABB.options[opt]=new Option(ok=(form2.AABB.options[opt])?
    form2.AABB.options[opt].innerText+String.fromCharCode(event.keyCode): document.getElementById("CCDD").value,ok)
    form2.AABB.selectedIndex=opt;
    document.getElementById("CCDD").value="";
    $('#AABB')[0].selectedIndex = -1;
    }
}




//联动





