<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>管理员</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
<%--     <link rel="stylesheet" href="${ctx}/public/css/xadmin.css"> --%>
    <link rel="stylesheet" href="${ctx}/public/css/index.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
    <link rel="stylesheet" href="${ctx}/public/css/font.css">

</head>
<body>
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="./index.html">差旅团建管理系统</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>
        
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <p>欢迎 ${sessionScope.user_session.name }</p>            
          </li>
          <li class="layui-nav-item to-index"><a href="${ctx}/user/logout">退出</a></li>
        </ul>        
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>员工账户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/admin/staff_account/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加账户</cite>                            
                        </a>
                    </li>
                    <li>
                        <a _href="${ctx }/admin/staff_account/delete">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>删除账户</cite>                            
                        </a>
                    </li>                   
                </ul>
            </li>
            
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>经理账户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/admin/manager_account/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加账户</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="${ctx }/admin/manager_account/delete">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>删除账户</cite>
                        </a>
                    </li>
                </ul>
            </li>
            
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>财务账户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/admin/finance_account/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加账户</cite>
                        </a>
                    </li>
                     <li>
                        <a _href="${ctx }/admin/finance_account/delete">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>删除账户</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>管理员账户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/admin/admin_account/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加账户</cite>                            
                        </a>
                    </li>
                    <li>
                        <a _href="${ctx }/admin/admin_account/delete">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>删除账户</cite>                            
                        </a>
                    </li>                   
                </ul>
            </li>
            <li>
                <a _href="${ctx }/admin/update">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>修改个人信息</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                
            </li>         
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    
    <!-- 右侧主体开始 -->
       <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${ctx}/welcome' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <center><div class="copyright">祝您工作愉快！</div></center>  
    </div>
    <!-- 底部结束 -->
 
</body>
</html>