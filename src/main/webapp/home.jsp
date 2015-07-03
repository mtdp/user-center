<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<body>
<shiro:guest>
	欢迎游客访问，<a href="login.jsp">登录</a>
</shiro:guest>
<shiro:user>
	欢迎[<shiro:principal />],<a href="<%=request.getContextPath()%>/logout">退出</a>
</shiro:user>
<shiro:authenticated>
         用户[<shiro:principal/>]已身份验证通过
</shiro:authenticated>
<h2>Hello World home.jsp!</h2>
<h2> <a href="<%=request.getContextPath()%>/user/users.do">进入管理页面</a>  </h2>
</body>
</html>
