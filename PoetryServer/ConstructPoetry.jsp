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
    <title>添加组句题</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
	<script>
    	$(document).ready(function(){
    		$(".btn").click(function(){
    			$.ajax({
            	url:"/PoetryServer/servlet/InsertSentence",
            	data:{sen1:$("#first-poetry").val(),sen2:$("#second-poetry").val()},
            	dataType:"json",
            	type:"POST",
            	success:function(data){
            		
            		if(data.backnews=="T"){
            			alert("添加成功");
            		}	
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
    <div class="container" style="margin-top:200px">
    	<div class="row">
    		<label for="id" class="col-sm-2 control-label">第一句诗</label>
    			<div class="col-sm-10">
      			<input type="text" class="form-control" name="first-poetry" id="first-poetry" placeholder="第一句诗">
    		</div>
    	</div>
    	</br>
    	<div class="row">
    		<label for="id" class="col-sm-2 control-label">第二句诗</label>
    		<div class="col-sm-10">
      			<input type="text" class="form-control" name="second-poetry" id="second-poetry" placeholder="第二句诗">
    		</div>
    	</div>
    	<br>
    	<div class="row">
    		<div class="col-sm-6 col-sm-offset-3 text-center">
    			<button type="submit" type="button" class="btn btn-default"  id="ok">提交</button>
    		</div>
    	</div>
    </div>
  </body>
</html>
