<%@ page import="com.fdcrms.pojo.Bill" %><%--<%@ page import="com.bjpowernode.javaweb.oa.bean.Dept" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--	// 从request域当中取出数据--%>
<%--<%	Bill data = (Bill)request.getAttribute("data"); %>--%>

<%--%>--%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>消费账单详情</title>
	</head>
	<body>
	<%--显示一个登录名--%>
<%--	<h3>欢迎${username}</h3>--%>
		<h1>消费账单详情</h1>
		<hr >
		账单编号：${data.billNo} <br>
		账单日期：${data.billDate}<br>
		消费类型：${data.billType}<br>
		消费金额：${data.billMoney}<br>
		消费人：  ${data.billConsumer}<br>

		<input type="button" value="后退" onclick="window.history.back()"/>
	</body>
</html>
