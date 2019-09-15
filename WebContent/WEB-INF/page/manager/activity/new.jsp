<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/index.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form" method="POST" id="votecontentForm"  action="${ctx}/manager/activity/new">
      
         <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>投票事由
              </label>
      	        <div class="layui-input-inline">
                  <input type="text" id="item" name="vote_start.item" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${votecontent.vote_start.item }">
              </div>
          </div>
           
         <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>选项
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="votes[0].name" name="votes[0].name" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${votecontent.votes[0].name }">
              </div>
          </div>
         
         
          
          
        <div id="InputsWrapper"> 
        </div> 
     <div class="layui-form-item">
             <label class="layui-form-label"></label> 
            <div class="layui-input-inline"> 
                <a id="AddMoreFileBox" style="color:blue;"><span ></span>添加选项</a> 
             </div> 
        </div>
          
          
         <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input type="submit" value=" 提交" class="layui-btn" lay-filter="add" lay-submit=""/>
          </div>  
          
          
      </form>
    </div>
    <script>
    
    
     $(document).ready(function() {
         /*---------------addinput-----------*/
         var MaxInputs       = 12; //maximum input boxes allowed  
         var InputsWrapper   = $("#InputsWrapper"); //Input boxes wrapper ID  
         var AddButton       = $("#AddMoreFileBox"); //Add button ID  
          
         var x = $("#InputsWrapper .option").length; //initlal text box count  
         var FieldCount=1; //to keep track of text box added  
         var i=1;
         $(AddButton).click(function (e)  //on add input button click  
         {  
           x = $("#InputsWrapper .option").length;
           FieldCount = x + 1;
                if(x <= MaxInputs) //max input box allowed  
                {  
                    FieldCount++; //text box added increment  
                    //add input box  
                    var optionnum =FieldCount+1; 
                   // var i=4;
                    $(InputsWrapper).append('<div class="layui-form-item"><label class="layui-form-label"></label><div class="layui-input-inline"><input class="layui-input" type="text" name="votes['+i+'].name" id="field_'+ FieldCount +'" value="${votecontent.votes['+i+'].name}"  maxlength="25"  /></div><div class="col-sm-1 vote-del"><button class="layui-btn">删除</button></div></div>');  
                    i++;
                    //x++; //text box increment 
                     if(x == 12) 
                         //添加按钮
                         $("#AddMoreFileBox").css("display","none");
                     else
                     {   //添加按钮消失
                         $("#AddMoreFileBox").css("display","block");}
                } 
                
         return false;  
         });  
          
         $("body").on("click",".vote-del", function(e){ //user click on remove text 
             if( x >= 0 ) {  
                 $(this).parent('div').remove(); //remove text box  
                 x--; //decrement textbox  
                  //添加按钮出现
                     $("#AddMoreFileBox").css("display","block");
                 var i = 3;
                 $("#InputsWrapper .option").each(function(){
                 $(this).find('input').attr('placeholder','投票选项'+i);
                 i++;
                 })
             }  
         return false;  
         })   
   });
    
    
    
    
    
    
    
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer;
        
          //自定义验证规则
          form.verify({
            nikename: function(value){
              if(value.length < 5){
                return '昵称至少得5个字符啊';
              }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#L_pass').val()!=$('#L_repass').val()){
                    return '两次密码不一致';
                }
            }
          });

          //监听提交
          form.on('submit(add)', function(data){
        	  
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("增加成功", {icon: 6},function () {
            	document.getElementById('votecontentForm').submit();
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
               
            });
            return false;
          });
          
          
        });
       
    </script>
    
  </body>

</html>