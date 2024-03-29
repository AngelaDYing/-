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
  
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form" method="POST" id="vote_start"  action="${ctx}/manager/activity/update">
        <input type="hidden" name="id" id="id" value="${votecontent.vote_start.id }" >
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>发起日期
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="date" name="vote_start.date" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${votecontent.vote_start.date }">
              </div>
         </div>
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
                  <span class="x-red">*</span>投票状态
              </label>
      	        <div class="layui-input-inline">
                  <input type="text" id="status" name="vote_start.status" required="" lay-verify="required"
                  autocomplete="off" placeholder="0表示已结束，1表示未结束" class="layui-input" value="${votecontent.vote_start.status }">
              </div>
          </div>
           
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>选项
              </label>
              <div class="layui-input-inline">
	    <c:forEach var="votes" items="${votecontent.votes}"   varStatus="stat" begin="0"  step="1">
                      
                             <input class="layui-input" type="text" name="votes.name" id="votes.name" autocomplete="off" value="${votes.name }">
                               <br>
              
                    </c:forEach>
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
            	document.getElementById('vote_start').submit();
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