<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改密码</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/adminmodpwd.css" rel="stylesheet">
    <script src="./js/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/adminmodpwd.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <div class="header"><jsp:include page="adminheader.jsp" flush="true"></jsp:include></div>
    <div class="container modpwd-container">
    	<div class="row check">
    	<div class="col-sm-8 col-sm-offset-2">
    		<div class="form-inline">
  				<div class="form-group">
    				<p class="form-control-static">输入账号</p>
  				</div>
  				<div class="form-group">
    				<input type="text" class="form-control" name="adminid" id="adminid">
  				</div>
			</div>
			<div class="form-inline">
  				<div class="form-group">
    				<p class="form-control-static">输入旧密码</p>
  				</div>
  				<div class="form-group">
    				<input type="password" class="form-control" name="oldpwd" id="old-pwd">
  				</div>
			</div>
			<div class="form-inline">
  				<div class="form-group">
    				<p class="form-control-static">输入新密码</p>
  				</div>
  				<div class="form-group">
    				<input type="password" class="form-control" name="newpwd" id="new-pwd">
  				</div>
			</div>
			<button type="submit" class="btn btn-default btn-check">确认</button>
    	</div>
    	</div>
    </div>
  </body>
</html>
