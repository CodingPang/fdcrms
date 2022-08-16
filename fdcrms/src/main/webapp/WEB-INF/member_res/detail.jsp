<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--%>--%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>家庭成员详情</title>
	</head>
	<body>
	<%--显示一个登录名--%>
<%--	<h3>欢迎${username}</h3>--%>
		<h1>家庭成员详情</h1>
		<hr >
	编号：${data.memNo} <br>
	姓名：${data.memName}<br>
	手机号：${data.memPhone}<br>
	性别：${data.memGender}<br>
	年龄：${data.memAge}<br>

		<input type="button" value="后退" onclick="window.history.back()"/>
	</body>
</html>
