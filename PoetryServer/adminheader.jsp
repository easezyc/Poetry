<%@ page language="java" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/zyc/index.jsp">
                <img class="mini-logo" style="width:32px;height:32px;float:left;margin-top:-6px;" src="/zyc/images/logo.png">
                诗词大会
            </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
            	<li class="nav-home"><a href="./addadmin.jsp">添加管理员</a></li>
            	<li class="nav-home"><a href="./modpwd.jsp">修改密码</a></li>
                <li class="nav-home"><a href="./addpoetry.jsp">添加诗词</a></li>
                <li class="nav-travel"><a href="./AddProblem.jsp">添加普通题目</a></li>
                <li class="nav-travel"><a href="./ConstructPoetry.jsp">添加组句题目</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

