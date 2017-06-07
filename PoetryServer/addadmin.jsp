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
    <title>添加管理员</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <style>
    	
    </style>
    <script>
    	$(document).ready(function(){
    		$(".btn").click(function(){
    			$.ajax({
            	url:"/PoetryServer/servlet/AdminRegisterSvt",
            	data:{id:$("#id").val(),pwd:$("#pwd").val()},
            	dataType:"json",
            	type:"POST",
            	success:function(data){
            		alert(data.backnews);
            	},
            	error:function(){
                	alert("请求失败");
            	}
        	});
        	return false;
    		});
    	});
    </script>
  </head>
  
  <body>
    <div class="header"><jsp:include page="adminheader.jsp" flush="true"></jsp:include></div>
    <div class="container" style="margin-top:100px;">
    	<div class="text-center" style="font-size:30px;color:black;margin-bottom:30px;">添加管理员</div>
    	<div class="row">
    		<div class="form-group col-md-6 col-sm-6 col-sm-offset-3 col-xs-12">
    			<label for="id" class="col-sm-2 control-label">管理员账户</label>
    			<div class="col-sm-10">
      			<input type="text" class="form-control" name="id" id="id" placeholder="管理员账户">
    			</div>
  			</div>
  			<div class="form-group col-md-6 col-sm-6 col-sm-offset-3 col-xs-12">
    			<label for="pwd" class="col-sm-2 control-label">密码</label>
    			<div class="col-sm-10">
      			<input type="text" class="form-control" name="pwd" id="pwd" placeholder="密码">
    			</div>
  			</div>
    	</div>
    	<div class="row text-center">
    		<button class="btn btn-default">提交</button>
    	</div>
    </div>
  </body>
</html>
