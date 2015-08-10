<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="well sidebar-nav">
   <ul class="nav nav-list" id="J_change_active">
     <li class="nav-header"><i class="icon-wrench"></i> 系统管理</li>
     <li id="J_user">
     	<a href="${_ctxPath}/user/users.do">用户管理</a>
   	 </li>
     <li id="J_role">
     	<a href="${_ctxPath}/role/roles.do">角色管理</a>
   	 </li>
     <li id="J_res">
     	<a href="${_ctxPath}/res/res.do">资源管理</a>
   	 </li>
     <li class="nav-header"><i class="icon-signal"></i> 系统统计</li>
     <li>
     	<a href="#">统计</a>
     </li>
     <li>
     	<a href="#">用户统计</a>
     </li>
     <li>
     	<a href="#">访问统计</a>
     </li>
     <li class="nav-header"><i class="icon-user"></i> 我的设置</li>
     <li>
     	<a href="#">我的</a>
     </li>
     <li>
     	<a href="#">设置</a>
     </li>
 	 <li>
 	 	<a href="${_ctxPath}/logout">退出</a>
 	 </li> 
   </ul>
</div>