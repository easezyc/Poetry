<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理员登录</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/login.css" rel="stylesheet">
    <script src="./js/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/adminlogin.js"></script>
    
    
</head>
<body>
<div class="background-container">
    <img id="background" src="/zyc/images/adminbackground.jpg" />
    <div class="container login-container">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <div class="login-windows">
                    <form method="post" action="/PoetryServer/servlet/AdminLoginSvt">
                    	<div class="text-center" style="font-size:20px;font-weight:bold;">管理员登录</div>
                        	<div class="form-group">
                        	<div class="row">
                                <div class="col-xs-10 col-xs-offset-2">
                                    <input class="form-control" id="op" name="op" value="login" type="text" style="display:none"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-2 col-xs-12"><label for="userid">账号</label></div>
                                <div class="col-sm-10 col-xs-12">
                                    <input class="form-control" id="adminid" name="adminid" placeholder="账号" type="text"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-2 col-xs-12"><label for="pwd">密码</label></div>
                                <div class="col-sm-10 col-xs-12">
                                    <input class="form-control" id="pwd" name="pwd" placeholder="密码" type="password"/>
                                </div>
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-default btn-login">登录</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
