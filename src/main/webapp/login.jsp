<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
<h2>Hello World Login.jsp! ${_test} ${_ctxPath}</h2>
<form action="loginAction.do" method="post">
	<input name="account" type="text"/>
	<input name="password" type="password"/>
	<input value="submit" type="submit"/>
</form>
</body>
</html>
