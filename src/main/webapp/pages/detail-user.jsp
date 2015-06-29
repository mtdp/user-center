<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<h1>查看用户 <small>用户详细</small></h1>
			</div>
			<form action="updateUser.do" method="post" class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="name">userName</label>
						<div class="controls">
							<input name="userName" type="text" class="input-xlarge" value="${user.userName}" readonly="readonly"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">E-mail</label>
						<div class="controls">
							<input name="email" type="text" class="input-xlarge" value="${user.email}" readonly="readonly"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="pnohe">mobile</label>
						<div class="controls">
							<input name="mobile" type="text" class="input-xlarge" value="${user.mobile}" readonly="readonly"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="city">account</label>
						<div class="controls">
							<input name="account" type="text" class="input-xlarge" value="${user.account}" readonly="readonly" />
						</div>
					</div>	
					<div class="control-group">
						<label class="control-label" for="role">role</label>
						<div class="controls">
							<input type="text" class="input-xlarge" value="${user.roles[0].roleName}" readonly="readonly"/>
						</div>
					</div>	
					<div class="control-group">
						<label class="control-label" for="active">Active?</label>
						<div class="controls">
							<c:if test="${user.status eq 1}">
								<span class="label label-success">Active</span>
							</c:if>
							<c:if test="${user.status ne 1}">
								<span class="label label-important">Inactive</span>
							</c:if>
						</div>
					</div>
				</fieldset>
			</form>
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
		
		if($(window).width() > 760)
		{
			$('tr.list-users td div ul').addClass('pull-right');
		}
	});
	</script>
  </body>
</html>