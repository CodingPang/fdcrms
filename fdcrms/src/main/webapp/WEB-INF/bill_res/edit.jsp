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
		<title>修改部门</title>
	</head>
	<body>
	<%--显示一个登录名--%>
	<h3>欢迎${username}</h3>
		<h1>修改部门</h1>
		<hr >
		<form action="${pageContext.request.contextPath}/bill/modify" method="post">

			账单编号<input type="text" name="billNo" value="${data.billNo}" readonly /><br>
			账单日期<input type="datetime-local" name="billDate" value="${data.billDate}"/><br>
			消费类型<input type="text" name="billType" value="${data.billType}" /><br>
			消费金额<input type="text" name="billMoney" value="${data.billMoney}" /><br>
			消费人  <input type="text" name="billConsumer" value="${data.billConsumer}" /><br>

			<input type="submit" value="修改"/><br>
		</form>
	</body>
</html>
