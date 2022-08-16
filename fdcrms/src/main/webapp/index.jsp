
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--访问jsp的时候不生成session对象。--%>
<%--<%@page session="false" %>--%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>欢迎使用家庭消费记账系统</title>
</head>
<body>
<%--前端发送请求路径的时候，如果请求路径是绝对路径，要以/开始，加项目名。--%>
<%--以下这样写代码，家庭消费系统项目名写死了。这种设计显然是不好的。--%>
<%--<a href="<%=request.getContextPath()%>/list.jsp">查看账单列表</a>--%>

<%--注意空格的问题。--%>
<%--<a href="<%=request.getContextPath()%> /list.jsp">查看账单列表</a>--%>

<%--执行一个Servlet，查询数据库，收集数据。--%>
<%--<a href="<%=request.getContextPath() %>/dept/list">查看账单列表</a>--%>


<%--<hr>--%>
<%--调用哪个对象的哪个方法，可以动态的获取一个应用的根路径。--%>
<%--<%=request.getContextPath()%> &lt;%&ndash; out.print(request.getContextPath()); &ndash;%&gt;--%>

<h1>家庭消费记账系统登录页面</h1>
<hr>
<form action="${pageContext.request.contextPath}/user/login" method="post">
  username: <input type="text" name="phoneNum" placeholder="<c:choose> <c:when test="${not empty requestScope.get('phone')}">${phone}</c:when><c:otherwise>请输入您的手机号</c:otherwise></c:choose>"> <br>
  password: <input type="password" name="password" placeholder="<c:choose> <c:when test="${not empty requestScope.get('pwd')}">${pwd}</c:when><c:otherwise>请输入您的密码</c:otherwise></c:choose>"> <br>
  <p>${msg}</p>
  <input type="submit" value="login">
  <a href="${pageContext.request.contextPath}/page/register">还没有注册？去注册！</a>
</form>

<%--<h1 style="text-align: center">欢迎使用家庭消费记账系统</h1>
<a href="<%=request.getContextPath()%>/bill/list">查看账单列表</a>--%>

</body>
</html>
