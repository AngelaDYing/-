<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title></title>
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
          <cite>投票发起信息</cite></a>
      </span>
      <%-- <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${ctx }/staff/expense/nowlist" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a> --%>
    </div>
    <div class="x-body">
      <div class="layui-row" style="" align="center">
        <form class="layui-form layui-col-md12 x-so" method="get" action="${ctx }/staff/activity/search">
          <!-- <input class="layui-input" placeholder="开始日" name="start" id="start">
          <input class="layui-input" placeholder="截止日" name="end" id="end"> -->
          <input type="text" name="date" style="width:50%;"  placeholder="请输入您要查找的团建信息" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <%-- <xblock>
 <!--        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button> -->
        <button class="layui-btn" onclick="x_admin_show('添加用户','${ctx}/dept/add')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock> --%>
     
      
      <table class="layui-table">
        <thead>
          <tr>
            <th>投票发起人</th>
            <th>发起投票日期</th>
            <th>投票事由</th>
            <th>投票状态</th>
            <th>去投票</th>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.vote_start_list}" var="vote" varStatus="stat">
     <tr>
            
            <td>${vote.manager_id }</td>
            <td>${vote.date }</td>
            <td>${vote.item }</td>
            <td>
                      <c:choose>
					        	<c:when test="${vote.status == 1 }">未结束</c:when>
					        	<c:when test="${vote.status == 0 }">已结束</c:when>
					  </c:choose>
            </td>
            
 
            <td class="td-manage">
             <!--  <a onclick="member_stop(this,'10001')" href="javascript:;"  title="启用">
                <i class="layui-icon">&#xe601;</i>
              </a> -->
              <%-- <a title="编辑"  onclick="x_admin_show('编辑','${ctx}/job/add?id=${dept.id }');" href="javascript:;"> --%>
              <a title="投票"  href="${ctx}/staff/activity/vote?id=${vote.id }">
                <svg t="1562391403643" class="icon" viewBox="0 0 1028 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1274" width="20" height="20"><path d="M765.256602 864.876154h-63.767883v63.645449H765.256602v-63.645449z m-127.535767 0h-63.767883v63.645449h63.767883v-63.645449z m-159.419709-445.558957a31.777832 31.777832 0 0 0 22.542202-9.325415l159.419709-159.123826a31.847212 31.847212 0 1 0-45.084404-44.994619l-159.419709 159.123826a31.832928 31.832928 0 0 0 22.542202 54.320034z m541.81071 253.541105a31.553369 31.553369 0 0 0-2.181371-10.835438c-0.295883-0.693795-0.761133-1.285561-1.095788-1.95895a30.902427 30.902427 0 0 0-2.88945-5.223865l-191.303651-254.602203a31.281973 31.281973 0 0 0-3.968913-3.305727l96.816992-98.069903a31.832928 31.832928 0 0 0 0-44.729345L679.070992 14.712526a32.853214 32.853214 0 0 0-45.398652 0L265.612278 387.504675h-42.382686a31.873739 31.873739 0 0 0-25.507154 12.733171L6.418787 654.840049a31.530923 31.530923 0 0 0-2.88945 5.223865c-0.334654 0.673389-0.799904 1.265155-1.097828 1.95895a31.600302 31.600302 0 0 0-2.181372 10.835438c-0.016325 0.367303-0.216301 0.693795-0.2163 1.061098v127.290898a31.845171 31.845171 0 0 0 31.883941 31.832928H127.577766v159.123826a31.845171 31.845171 0 0 0 31.883942 31.832928h701.44672a31.845171 31.845171 0 0 0 31.883942-31.832928v-159.123826h95.651825A31.845171 31.845171 0 0 0 1020.328137 801.210298v-127.290898c0-0.367303-0.204057-0.673389-0.216301-1.061098zM656.373706 82.398309l191.644427 194.099234-235.204523 238.298031h-178.788821l-102.253077-103.62026zM239.172583 451.150125h42.501039l62.849626 63.645449H255.113533a31.832928 31.832928 0 1 0 0 63.645449h510.143069a31.832928 31.832928 0 1 0 0-63.645449h-62.951654l62.859829-63.665855c0.028568 0 0.059177 0.020406 0.091825 0.020406h15.940951l143.478759 190.956753H95.693824zM829.024486 960.334125H191.34565v-127.290899h637.678836v127.290899z m127.535767-190.936348H63.809883v-63.64545h892.75037v63.64545z" p-id="1275"></path></svg>
              </a>
              
            </td>

          </tr>
				
			</c:forEach>
        
          
          
          
        </tbody>
      </table>
      <% 
              String msg=(String)request.getAttribute("message");
              if(msg!=null)
              out.println(msg);
              %>
     <!--  <div class="page">
        <div>
          <a class="prev" href="">&lt;&lt;</a>
          <a class="num" href="">1</a>
          <span class="current">2</span>
          <a class="num" href="">3</a>
          <a class="num" href="">489</a>
          <a class="next" href="">&gt;&gt;</a>
        </div>
      </div> -->

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
              $.get("${ctx}/staff/expense/delete?date="+date);
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