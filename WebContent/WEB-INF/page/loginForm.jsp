<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<title>登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    
    <link rel="stylesheet" href="${ctx}/public/css/login.css">
</head>
<body class="login-bg">
    <div class="login layui-anim layui-anim-up">
        <div class="message">差旅团建管理系统</div>
        ${requestScope.message}
        <form method="post" class="layui-form" action="${ctx}/login">
            <input name="id" lay-verify="required" placeholder="员工号" value="${id}" type="text"  class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <select class="layui-input" name="tag" >
                <option value="1">管理员</option>
                <option value="2">经理</option>
                <option value="3">财务</option>
                <option value="4">普通用户</option>
            </select>
            <hr class="hr15">
            <center><input value="登录" lay-submit lay-filter="login" style="width:40%;" type="submit" ></center>
            <hr class="hr20" >
        </form>
    </div>
</body>
</html>