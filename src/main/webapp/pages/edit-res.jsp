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
				<h1>修改资源 <small>更新资源</small></h1>
			</div>
			<form action="updateRes.do" method="post" class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="resName">resName</label>
						<div class="controls">
							<input name="resId" type="hidden" value="${res.resId}" />
							<input name="resName" type="text" class="input-xlarge" id="resName" value="${res.resName}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="resCode">resCode</label>
						<div class="controls">
							<input name="resCode" type="text" class="input-xlarge" id="resCode" value="${res.resCode}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="resURI">resURI</label>
						<div class="controls">
							<input name="resURI" type="text" class="input-xlarge" id="resURI" value="${res.resURI}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="active">Active?</label>
						<div class="controls">
							<input id="active" name="status" type="checkbox" id="status" value="1" <c:if test="${res.status eq 1}">checked="checked"</c:if> />
						</div>
					</div>
					<div class="form-actions">
						<input type="submit" class="btn btn-success btn-large" value="保存修改" /> <a class="btn" href="res.do">取消</a>
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