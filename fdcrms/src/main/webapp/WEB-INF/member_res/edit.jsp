<%--<%@ page import="com.bjpowernode.javaweb.oa.bean.Dept" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--	// 从request域当中取出数据--%>
<%--	Dept dept = (Dept)request.getAttribute("dept");--%>

<%--%>--%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>修改家庭成员个人信息</title>
	</head>
	<body>
	<%--显示一个登录名--%>
	<h3>欢迎${username}</h3>
		<h1>修改个人信息</h1>
		<hr >
		<form action="${pageContext.request.contextPath}/member/modify" method="post">

			家庭成员编号<input type="text" name="memNo" value="${data.memNo}" readonly /><br>
			家庭成员姓名<input type="text" name="memName" value="${data.memName}"/><br>
			家庭成员手机号<input type="text" name="memPhone" value="${data.memPhone}" /><br>
			家庭成员性别<input type="text" name="memGender" value="${data.memGender}" /><br>
			家庭成员年龄<input type="text" name="memAge" value="${data.memAge}" /><br>

			<input type="submit" value="修改"/><br>
			<p>${msg}</p>
		</form>
	</body>
</html>
