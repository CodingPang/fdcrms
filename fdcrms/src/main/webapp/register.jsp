<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="text-align: center">注册账号</h1>
<hr>
<%--style="text-align: center"--%>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    <%--			账单编号<input type="text" name="billNo"/><br>--%>
    姓名<input type="text" name="memName"/><br>

    <c:if test="${sessionScope.msg eq '家庭成员姓名不能为空'}">${sessionScope.msg}</c:if> <%--家庭成员姓名不能为空--%>
    <c:if test="${sessionScope.msg eq '姓名格式不正确'}">${sessionScope.msg}</c:if> <%--姓名格式不正确--%>

    <br>
    手机<input type="text" name="memPhone"/><br>
    <c:if test="${sessionScope.msg eq '家庭成员手机号不能为空'}">${sessionScope.msg}</c:if>
    <c:if test="${sessionScope.msg eq '手机号格式不正确'}">${sessionScope.msg}</c:if>
    <c:if test="${sessionScope.msg eq '该用户已存在，请换个手机号注册！'}">${sessionScope.msg}</c:if>

    <br>
    性别<input type="text" name="memGender"/><br>
    <c:if test="${sessionScope.msg eq '家庭成员性别不能为空'}">${sessionScope.msg}</c:if>
    <c:if test="${sessionScope.msg eq '性别只能为男或为女'}">${sessionScope.msg}</c:if>

    <br>
    年龄<input type="text" name="memAge"/><br>
    <c:if test="${sessionScope.msg eq '年龄不能为空'}">${sessionScope.msg}</c:if>
    <c:if test="${sessionScope.msg eq '年龄范围只能在1到120岁'}">${sessionScope.msg}</c:if>


    <br>
    密 码<input type="password" name="memPwd"/><br>
    <c:if test="${sessionScope.msg eq '密码不能为空'}">${sessionScope.msg}</c:if>
    <c:if test="${sessionScope.msg eq '密码必须包含大小字母与数字，不能使用特殊字符，且不低于8位'}">${sessionScope.msg}</c:if>

    <br>
    再次输入密码<input type="password" name="reMemPwd"/><br>
    <c:if test="${sessionScope.msg eq '再次确定密码不能为空'}">${sessionScope.msg}</c:if>
    <c:if test="${sessionScope.msg eq '两次输入密码不一致'}">${sessionScope.msg}</c:if>

    <br>
    <input type="submit" value="保存"/><br>
    <c:if test="${sessionScope.msg eq '家庭成员新增失败，系统异常！'}">${sessionScope.msg}</c:if>

</form>

<a href="${pageContext.request.contextPath}/page/login" style="text-align: center">已有账号？去登录</a>
</body>
</html>
