<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
          <div class="well hero-unit">
            <h1>Welcome, Admin</h1>
            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Etiam eget ligula eu lectus lobortis condimentum. Aliquam nonummy auctor massa. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. </p>
            <p><a class="btn btn-success btn-large" href="users.html">Manage Users &raquo;</a></p>
          </div>
          <div class="row-fluid">
            <div class="span3">
              <h3>Total Users</h3>
              <p><a href="users.html" class="badge badge-inverse">563</a></p>
            </div>
            <div class="span3">
              <h3>New Users Today</h3>
              <p><a href="users.html" class="badge badge-inverse">8</a></p>
            </div>
            <div class="span3">
              <h3>Pending</h3>
			  <p><a href="users.html" class="badge badge-inverse">2</a></p>
            </div>
            <div class="span3">
              <h3>Roles</h3>
			  <p><a href="roles.html" class="badge badge-inverse">3</a></p>
            </div>
          </div>
		  <br />
		  <div class="row-fluid">
			<div class="page-header">
				<h1>Pending Users <small>Approve or Reject</small></h1>
			</div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>E-mail</th>
						<th>Phone</th>
						<th>City</th>
						<th>Role</th>
						<th>Status</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				<tr class="pending-user">
					<td>564</td>
					<td>John S. Schwab</td>
					<td>johnschwab@provider.com</td>
					<td>402-xxx-xxxx</td>
					<td>Bassett, NE</td>
					<td>User</td>
					<td><span class="label label-important">Inactive</span></td>
					<td><span class="user-actions"><a href="javascript:void(0);" class="label label-success">Approve</a> <a href="javascript:void(0);" class="label label-important">Reject</a></span></td>
				</tr>
				<tr class="pending-user">
					<td>565</td>
					<td>Juliana M. Sheffield</td>
					<td>julianasheffield@provider.com</td>
					<td>803-xxx-xxxx</td>
					<td>Columbia, SC</td>
					<td>User</td>
					<td><span class="label label-important">Inactive</span></td>
					<td><span class="user-actions"><a href="javascript:void(0);" class="label label-success">Approve</a> <a href="javascript:void(0);" class="label label-important">Reject</a></span></td>
				</tr>
				</tbody>
			</table>
		  </div>
        </div>
      </div>
      <hr>
      <footer class="well">
        &copy; Strass - More Templates <a href="http://www.mycodes.net/" target="_blank">源码之家</a> <a href="/user-center/druid/index.html" target="_blank">druid</a>
      </footer>

    </div>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>