<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
   <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">Strass 后台管理</a>
          <div class="btn-group pull-right">
			<a class="btn" href="#">
				<i class="icon-user"></i> 
				<shiro:user><shiro:principal /></shiro:user>
				<shiro:guest>游客</shiro:guest>
			</a>
            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
			  <li><a href="#">我的</a></li>
              <li class="divider"></li>
              <li><a href="${_ctxPath}/logout">退出</a></li>
            </ul>
          </div>
          <div class="nav-collapse">
            <ul class="nav">
			<li><a href="${_ctxPath}/pages/index.jsp">首页</a></li>
              <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">用户 <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="${_ctxPath}/user/newUser.do">添加用户</a></li>
					<li class="divider"></li>
					<li><a href="${_ctxPath}/user/users.do">用户管理</a></li>
				</ul>
			  </li>
              <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">角色 <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="${_ctxPath}/role/newRole.do">添加角色</a></li>
					<li class="divider"></li>
					<li><a href="${_ctxPath}/role/roles.do">角色管理</a></li>
				</ul>
			  </li>
			  <li><a href="#">统计</a></li>
			  <li>
			  	<a href="#">
			  	<shiro:hasPermission name="user:menu">test user:menu permission</shiro:hasPermission>
			  	</a>
		  	  </li>
            </ul>
          </div>
        </div>
      </div>