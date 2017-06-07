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
    <title>添加普通题目</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
	    <script>
    	$(document).ready(function(){
    		$("#btn-submit").click(function(){
    			$.ajax({
            	url:"/PoetryServer/servlet/InsertFillblankSvt",
            	data:{poetryid:$("#poetryid").val(),start:$("#start").val(),length:$("#length").val()},
            	dataType:"json",
            	type:"POST",
            	success:function(data){
            		if(data.backnews=="T"){
            			alert("提交成功");
            		}	
            	},
            	error:function(){
                	alert("请求失败");
            	}
        	});
        	return false;
    		});
    		$("#btn-search").click(function(){
    			$.ajax({
            	url:"/PoetryServer/servlet/ShowAllpoetrySvt",
            	data:{word:$("#search-content").val()},
            	dataType:"json",
            	type:"POST",
            	success:function(data){
            		$("#show-table").empty();
            		for(var i=0;i<data.length;i++)
            		{
            			$("#show-table").append("<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].author+"</td><td>"+data[i].content+"</td></tr>")
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
    <div class="container" style="margin-top:150px;">
    	<div class="row">
    		<div class="form-inline text-center">
  				<div class="form-group">
    				<p class="form-control-static">输入诗id</p>
  				</div>
  				<div class="form-group">
    				<input type="text" class="form-control" name="poetryid" id="poetryid">
  				</div>
  				<div class="form-group">
    				<p class="form-control-static">start</p>
  				</div>
  				<div class="form-group">
    				<input type="text" class="form-control" name="start" id="start">
  				</div>
  				<div class="form-group">
    				<p class="form-control-static">length</p>
  				</div>
  				<div class="form-group">
    				<input type="text" class="form-control" name="length" id="length">
  				</div>
  				<button class="btn btn-default" id="btn-submit">提交</button>
			</div>
    	</div>
    	<br>
    	<hr>
    	<div class="row">
    		<div class="form-inline text-center">
  				<div class="form-group">
    				<p class="form-control-static">输入搜索内容</p>
  				</div>
  				<div class="form-group">
    				<input class="form-control" name="search-content" id="search-content">
  				</div>
  				<button class="btn btn-default" id="btn-search">搜索</button>
			</div>
    	</div>
    	<br>
    	<table class="table table-striped" id="show-table">
			<thead>
			    <tr>
			      <th>诗词id</th>
			      <th>诗名</th>
			      <th>作者</th>
			      <th>内容</th>
			    </tr>
			  </thead>

		</table>
    </div>
  </body>
</html>
