<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="wanx" tagdir="/WEB-INF/tags" %>
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
    <link rel="stylesheet" href="../static/plugin/zTree/css/zTreeStyle/zTreeStyle.css">
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
				<h1>角色管理 <small>所有角色</small></h1>
			</div>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th style="width:45px;">
							<label class="control-label" for="roleIds"></label>
							<div class="controls"><input id="roleIds" name="roleIds" type="checkbox" class="select-all"/> 全选</div>
						</th>
						<th>Id</th>
						<th>roleName</th>
						<th>roleCode</th>
						<th>status</th>
						<th>operate</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${rolePage.results}" var="role" varStatus="s">
				<tr class="list-roles">
					<td>
						<input name="roleId" type="checkbox" class="roleId" value="${role.roleId}" /> 
					</td>
					<td>${s.index+1}</td>
					<td>${role.roleName}</td>
					<td>${role.roleCode}</td>
					<td>
						<c:if test="${role.status eq 1}">
							<span class="label label-success">Active</span>
						</c:if>
						<c:if test="${role.status eq 0}">
							<span class="label label-important">Inactive</span>
						</c:if>
					</td>
					<td>
						<div>
							<input name="chooseResIds" type="hidden" value="" />
							<a href="javascript:;" class="J_choose_res" data-value="${role.roleId}"><i class="icon-pencil"></i>Allocate Res</a>
							<a href="editRole.do?roleId=${role.roleId}"><i class="icon-pencil"></i>Edit</a>
							<a href="deleteRole.do?roleId=${role.roleId}"><i class="icon-trash"></i>Delete</a>
						</div>
					</td>
				</tr>	
				</c:forEach>			
			    </tbody>
			</table>
			<div class="pagination">
				<wanx:tabPage pageInfo="${rolePage}" startUrl="?currentPage=" />
			</div>
			<a href="newRole.do" class="btn btn-success">添加角色</a>
		  </div>
        </div>
      </div>

      <hr>

      <footer class="well">
        &copy; Strass - More Templates <a href="http://www.mycodes.net/" target="_blank">源码之家</a>
      </footer>

    </div>
	<div id="treeDiv" class="zTreeDemoBackground left" style="display: none;">
		<ul id="tree" class="ztree"></ul>
	</div>
    <%@include file="/pages/common/footer.jsp"%>
    <script type="text/javascript" src="../static/plugin/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="../static/plugin/zTree/js/jquery.ztree.excheck-3.5.js"></script>
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
		//显示资源tree dialog
		$('.J_choose_res').on('click',function(){
			var _this = $(this);
			var dialog = $.artDialog({
				id:'roles-id',
				lock:true,
				title:'所有资源',
				okValue:'确认保存',
				ok:function(){
					var zTree = $.fn.zTree.getZTreeObj("tree");
					//获取选中的菜单
					var checkedMenu = zTree.getCheckedNodes(true);
					if(checkedMenu != "" && checkedMenu.length>0){
						this.title("正在提交保存");
						var values = "";
						checkedMenu.forEach(function(c){
							values += c.id+",";
						});
						//将变更的check赋值到隐藏的input中
						_this.siblings('input').val(values.substring(0,values.length-1));
						//ajax保存变更的资源
						$.ajax({
							url:'ajaxSaveRoleRes.do',
							type:'post',
							async:true,
							data:{resIds:_this.siblings('input').val(),roleId:_this.attr('data-value')},
							success:function(msg){
								$.dialog.tips(msg.info);
							}
						});
					}
					return true;
				},
				cancelValue:'取消',
				cancel:true
			});
			var setting = {
					view: {selectedMulti: false},
					check: {enable: true},
					data: {
						simpleData: {
							enable: true,
							idKey: "id",
							pIdKey: "pId",
							rootPId: 0
							}
					}
			};
			//请求trre
			$.ajax({
				url:'ajaxMenu.do',
				type:'post',
				data:{resIds:_this.siblings('input').val(),roleId:_this.attr('data-value')},
				success:function(data){
					var zNodes = data;
					$.fn.zTree.init($("#tree"), setting, zNodes);
					dialog.content(document.getElementById("treeDiv"));
				}
			});
		});
		
	</script>
  </body>
</html>