
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎使用家庭账单管理系统</title>
    <style>
        a {
            display: block;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<h1 style="text-align: center">欢迎使用家庭账单管理系统</h1>
<!--前端超链接发送请求的时候，请求路径以”/”开始，并且要带着项目名-->
<a href="${pageContext.request.contextPath}/bill/list" style="text-align: center">查看消费账单列表</a> <br>
<a href="${pageContext.request.contextPath}/member/list" style="text-align: center">查看家庭成员列表</a>
</body>
</html>
