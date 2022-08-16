<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>新增账单</title>
	</head>
	<body>
	<%--显示一个登录名--%>
	<h3>欢迎${username}</h3>
		<h1>新增账单</h1>
		<hr >
		<form action="${pageContext.request.contextPath}/bill/save" method="post">
<%--			账单编号<input type="text" name="billNo"/><br>--%>
			账单日期<input type="datetime-local" name="billDate"/><br>
			消费类型<input type="text" name="billType"/><br>
			消费金额<input type="text" name="billMoney"/><br>
			消费人  <input type="text" name="billConsumer"/><br>
			<input type="submit" value="保存"/><br>
			<p>${msg}</p>
		</form>
	</body>
</html>
