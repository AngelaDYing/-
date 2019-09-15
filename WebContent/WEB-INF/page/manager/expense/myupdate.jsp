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
        <form class="layui-form" method="POST" id="update"  action="${ctx}/manager/expense/myupdate" enctype="multipart/form-data" >
        <input type="hidden" name="id" id="id" value="${expense.id }" >
         <input type="hidden" name="date" id="date" value="${expense.date }" >   
          <%-- <div class="layui-form-item" > 
              <label class="layui-form-label">
                  <span class="x-red">*</span>工号
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="id" name="id" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${expense_account.id }">
              </div>             
          </div> --%>
          
          <div class="layui-form-item" >
              <label class="layui-form-label">
                  <span class="x-red">*</span>报销事项
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="item" name="item" placeholder="选填差旅或团建" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${expense.item }">
              </div>
             
          </div>
          <div class="layui-form-item" >
              <label class="layui-form-label">
                  <span class="x-red">*</span>活动人数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="member" name="member" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${expense.member }">
              </div>
          </div>
         
          
           <div class="layui-form-item">
              <label class="layui-form-label">
                  <span class="x-red">*</span>报销金额
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="money" name="money" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${expense.money }">
              </div>
          </div>
          
          <div class="layui-form-item layui-form-text">
               <label for="desc" class="layui-form-label">
                   <span class="x-red">*</span> 详细信息
               </label>
               <div class="layui-input-block">
                   <textarea placeholder="请输入内容" id="detail" name="detail" class="layui-textarea">${expense.detail }</textarea>
               </div>
            </div>
           
          <div class="layui-form-item">
              <label for="phone" class="layui-form-label">
                  <span class="x-red">*</span>报销单
              </label>
              <div class="layui-input-inline">
                  <input type="file" id="file" name="file" >
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
            layer.alert("修改成功", {icon: 6},function () {
            	document.getElementById('update').submit();
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