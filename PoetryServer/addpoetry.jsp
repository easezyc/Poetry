<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加诗词</title>
	 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script>
    	$(document).ready(function(){
    		$(".btn").click(function(){
    			$.ajax({
            	url:"/PoetryServer/servlet/InsertPoetrySvt",
            	data:{poetryname:$("#poetry-name").val(),poetryauthor:$("#poetry-author").val(),poetrycontent:$("#poetry-content").val()},
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
    <div class="container" style="margin-top:200px;">
    	<div class="row">
    		<div class="col-sm-6 col-sm-offset-3">
    			<input type="text" class="form-control" name="poetry-name" id="poetry-name" placeholder="诗题"/>
    		</div>
    	</div>
    	<div class="row">
    		<div class="col-sm-6 col-sm-offset-3">
    			<input type="text" class="form-control" name="poetry-author" id="poetry-author" placeholder="诗人"/>
    		</div>
    	</div>
    	<div class="row">
    		<div class="col-sm-6 col-sm-offset-3">
    			<textarea class="form-control" placeholder="诗的内容" rows="5" name="comment" id="poetry-content"></textarea>	
    		</div>
    	</div>
    	<div class="row">
    		<div class="col-sm-6 col-sm-offset-3 text-center">
    			<button type="submit" type="button" class="btn btn-default"  id="ok">提交</button>
    		</div>
    	</div>
    </div>
  </body>
</html>
