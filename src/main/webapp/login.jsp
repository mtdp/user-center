<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
<h2>Hello World Login.jsp! ${_test} ${_ctxPath}</h2>
<form action="loginAction.do" method="post">
	<input name="account" type="text"/>
	<input name="password" type="password"/>
	<img id="codeImage" alt="" src="${_ctxPath}/captcha/getImage.do?width=60&height=20"/><input name="code" type="text"/>
	<input value="submit" type="submit"/>
</form>
<script type="text/javascript" src="${_ctxPath}/static/js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$('#codeImage').on("click",function(){
			var _this = $(this);
			console.log(_this);
			_this.attr("src","${_ctxPath}/captcha/getImage.do?width=60&height=20&date="+ new Date().getTime());
		});
	});
</script>
</body>
</html>
