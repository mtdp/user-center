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
				<h1>修改角色 <small>更新角色</small></h1>
			</div>
			<form action="updateRole.do" method="post" class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="roleName">roleName</label>
						<div class="controls">
							<input name="roleId" type="hidden" id="roleId" value="${role.roleId}" />
							<input name="roleName" type="text" class="input-xlarge" id="roleName" value="${role.roleName}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="roleCode">roleCode</label>
						<div class="controls">
							<input name="roleCode" type="text" class="input-xlarge" id="roleCode" value="${role.roleCode}" />
						</div>
					</div>
					<!-- 暂时隐藏
					<div class="control-group">
						<label class="control-label" for="chooseRes">chooseRes</label>
						<div class="controls">
							<input name="chooseResBtn" type="button" class="input-xlarge" id="J_choose_res_btn_id" value="choose res"/>
							<input name="chooseResIds" type="hidden" id="J_choose_res_ids" value="" />
						</div>
					</div>
					 -->
					<div class="control-group">
						<label class="control-label" for="active">Active?</label>
						<div class="controls">
							<input id="active" name="status" type="checkbox" id="status" value="1" <c:if test="${role.status eq 1}">checked="checked"</c:if> />
						</div>
					</div>
					<div class="form-actions">
						<input type="submit" class="btn btn-success btn-large" value="保存修改" /> <a class="btn" href="roles.do">取消</a>
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
		
		if($(window).width() > 760)
		{
			$('tr.list-users td div ul').addClass('pull-right');
		}
	});
	//显示资源tree dialog
	$('#J_choose_res_btn_id').on('click',function(){
		var dialog = $.artDialog({
			id:'edit-role-id',
			lock:true,
			title:'所有资源',
			okValue:'确认',
			ok:function(){
				var zTree = $.fn.zTree.getZTreeObj("tree");
				//获取选中的菜单
				var checkedMenu = zTree.getCheckedNodes(true);
				if(checkedMenu != "" && checkedMenu.length>0){
					var values = "";
					checkedMenu.forEach(function(c){
						values += c.id+",";
					});
					$('#J_choose_res_ids').val(values.substring(0,values.length-1));
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
			data:{resIds:$('#J_choose_res_ids').val(),roleId:$('#roleId').val()},
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