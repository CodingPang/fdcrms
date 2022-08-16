<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>新增家庭成员</title>
	</head>
	<body>
	<%--显示一个登录名--%>
	<h3>欢迎${username}</h3>
		<h1>新增家庭成员</h1>
		<hr >
		<form action="${pageContext.request.contextPath}/member/save" method="post">
			<%--			账单编号<input type="text" name="billNo"/><br>--%>
			姓名<input type="text" name="memName"/><br>

<%--			<c:if test="${sessionScope.msg eq '家庭成员姓名不能为空'}"> ${sessionScope.msg} </c:if> &lt;%&ndash;家庭成员姓名不能为空&ndash;%&gt;--%>
<%--			<c:if test="${sessionScope.msg eq '姓名格式不正确'}">${sessionScope.msg}</c:if> &lt;%&ndash;姓名格式不正确&ndash;%&gt;--%>

			<br>
			手机<input type="text" name="memPhone"/><br>
<%--			<c:if test="${sessionScope.msg eq '家庭成员手机号不能为空'}">${sessionScope.msg}</c:if>--%>
<%--			<c:if test="${sessionScope.msg eq '手机号格式不正确'}">${sessionScope.msg}</c:if>--%>
<%--			<c:if test="${sessionScope.msg eq '该用户已存在，请换个手机号注册！'}">${sessionScope.msg}</c:if>--%>

			<br>
			性别<input type="text" name="memGender"/><br>
<%--			<c:if test="${sessionScope.msg eq '家庭成员性别不能为空'}">${sessionScope.msg}</c:if>--%>
<%--			<c:if test="${sessionScope.msg eq '性别只能为男或为女'}">${sessionScope.msg}</c:if>--%>

			<br>
			年龄<input type="text" name="memAge"/><br>
<%--			<c:if test="${sessionScope.msg eq '年龄不能为空'}">${sessionScope.msg}</c:if>--%>
<%--			<c:if test="${sessionScope.msg eq '年龄范围只能在1到120岁'}">${sessionScope.msg}</c:if>--%>


			<br>
			密 码<input type="text" name="memPwd"/><br>
<%--			<c:if test="${sessionScope.msg eq '密码不能为空'}">${sessionScope.msg}</c:if>--%>
<%--			<c:if test="${sessionScope.msg eq '密码必须包含大小字母与数字，不能使用特殊字符且不低于8位'}">${sessionScope.msg}</c:if>--%>

			<br>
			再次输入密码<input type="text" name="reMemPwd"/><br>
<%--			<c:if test="${sessionScope.msg eq '再次确定密码不能为空'}">${sessionScope.msg}</c:if>--%>
<%--			<c:if test="${sessionScope.msg eq '两次输入密码不一致'}">${sessionScope.msg}</c:if>--%>

			<br>
			<input type="submit" value="保存"/><br>
<%--			<c:if test="${sessionScope.msg eq '家庭成员新增失败，系统异常！'}">${sessionScope.msg}</c:if>--%>
				<p>${sessionScope.msg}</p>

		</form>
	</body>
</html>
