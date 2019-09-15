<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>投票详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
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
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a>
          <cite>投票详情</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${ctx }/job/list" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    
    <div class="x-body layui-anim layui-anim-up">
          <fieldset class="layui-elem-field">
            <legend>投票详细信息如下</legend>
            <div class="layui-field-box">
                <table class="layui-table" >
                    <tbody>
                        <tr>
                            <td>&nbsp &nbsp投票事由</td>
                            <td>${votecontent.vote_start.item }</td>
                        </tr>
                        <tr>
                            <td>&nbsp &nbsp投票状态</td>
                            <td>
                            <c:choose>
					        	<c:when test="${votecontent.vote_start.status == 0 }">已结束</c:when>
					        	<c:when test="${votecontent.vote_start.status == 1 }">未结束</c:when>
			                 </c:choose>
							</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
      </div>
      
     <div class="x-body layui-anim layui-anim-up">
          <fieldset class="layui-elem-field">
            <legend>员工投票结果如下</legend>
            <div class="layui-field-box">
                <table class="layui-table" >
                    <tbody>
                        <c:forEach var="vote"   items="${ votecontent.votes}"   varStatus="stat" begin="0"  step="1">
                            <tr>
                               <td>&nbsp &nbsp选项：&nbsp &nbsp &nbsp ${vote.name }</td>
                               <td>${vote.sum }人</td>
                            </tr>
                        </c:forEach>
                    
                      
<!--                         <tr> -->
<!--                             <td>&nbsp &nbsp选项一：&nbsp &nbsp &nbsp ${votecontent.votes[0].name }</td> -->
<%--                             <td>${votecontent.votes[0].sum }人</td> --%>
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td>&nbsp &nbsp选项二：&nbsp &nbsp &nbsp ${votecontent.votes[1].name }</td> -->
<%--                             <td>${votecontent.votes[1].sum }人</td> --%>
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td>&nbsp &nbsp选项三：&nbsp &nbsp &nbsp ${votecontent.votes[2].name }</td> -->
<%--                             <td>${votecontent.votes[2].sum }人</td> --%>
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td>&nbsp &nbsp选项四：&nbsp &nbsp &nbsp ${votecontent.votes[3].name }</td> -->
<%--                             <td>${votecontent.votes[3].sum }人</td> --%>
<!--                         </tr> -->
                       
                    </tbody>
                </table>
            </div>
        </fieldset>
      </div> 
      
 
	
    
    <script>
    
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });

       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              //等以后再使用异步，这里先使用
              $.get("${ctx}/job/delete?id="+id);
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }



      function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>

</html>