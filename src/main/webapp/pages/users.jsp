<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="wanx" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>user center</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="user,center"/>
	<meta name="description" content="user center"/>
	<link href="../static/css/bootstrap.css" rel="stylesheet">
	<link href="../static/css/site.css" rel="stylesheet">
    <link href="../static/css/bootstrap-responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="../static/js/html5.js"></script>
    <![endif]-->
<body>
    <div class="navbar navbar-fixed-top">
    	<%@include file="/pages/common/header.jsp"%>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
           <%@include file="/pages/common/menu.jsp"%>
        </div>
        <div class="span9">
		  <div class="row-fluid">
			<div class="page-header">
				<h1>用户管理 <small>所有用户</small></h1>
			</div>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th style="width:45px;"><input name="userIds" type="checkbox" class="select-all"/> 全选</th>
						<th>Id</th>
						<th>userName</th>
						<th>E-mail</th>
						<th>mobile</th>
						<th>account</th>
						<th>status</th>
						<th>operate</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${userPage.results}" var="user" varStatus="s">
				<tr class="list-users">
					<td>
						<input name="userId" type="checkbox" class="userId" value="${user.userId}" /> 
					</td>
					<td>${s.index+1}</td>
					<td>${user.userName}</td>
					<td>${user.email}</td>
					<td>${user.mobile}</td>
					<td>${user.account}</td>
					<td>
						<c:if test="${user.status eq 1}">
							<span class="label label-success">Active</span>
						</c:if>
						<c:if test="${user.status eq 0}">
							<span class="label label-important">Inactive</span>
						</c:if>
					</td>
					<td>
						<div>
							<a href="editUser.do?userId=${user.userId}"><i class="icon-pencil"></i>Edit</a>
							<a href="deleteUser.do?userId=${user.userId}"><i class="icon-trash"></i>Delete</a>
							<a href="detailUser.do?userId=${user.userId}"><i class="icon-user"></i>Details</a>
							<a href="javascript:;" class="J_ResetPassword" data-value="${user.userId}" title="默认密码是123456"><i class="icon-lock"></i> Reset pwd</a>
						</div>
					</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="pagination">
				<wanx:tabPage pageInfo="${userPage}" startUrl="?userPage.currentPage=" />
			</div>
			<a href="newUser.do" class="btn btn-success">添加用户</a>
		  </div>
        </div>
      </div>

      <hr>

      <footer class="well">
        &copy; Strass - More Templates <a href="http://www.mycodes.net/" target="_blank">源码之家</a>
      </footer>

    </div>

    <%@include file="/pages/common/footer.jsp"%>
	<script>
	$(document).ready(function() {
		$('.dropdown-menu li a').hover(
		function() {
			$(this).children('i').addClass('icon-white');
		},
		function() {
			$(this).children('i').removeClass('icon-white');
		});
	});
	//自定义artDialog
	artDialog.tips = function (content, time) {
	    return artDialog({
	        id: 'Tips',
	        title: false,
	        cancel: false,
	        fixed: true,
	        lock: true
	    })
	    .content('<div style="padding: 0 1em;">' + content + '</div>')
	    .time(time || 2000);
	};
	//重置密码
	$('.J_ResetPassword').on('click',function(){
		var _this = $(this);
		$.ajax({
			url:'resetPassword.do',
			data:{userId:_this.attr('data-value')},
			success:function(msg){
				$.dialog.tips(msg.info);
			}
		});
	});
	</script>
  </body>
</html>