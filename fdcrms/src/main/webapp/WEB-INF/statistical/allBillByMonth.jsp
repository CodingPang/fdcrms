<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>按月为单位，统计整个家庭的消费总额</title>
</head>
<body>
<h1 align="center">本家庭每月消费月总账单</h1>
<hr >
<table border="1px" align="center" width="50%">
    <tr>
        <th>编号</th>
        <th>月份</th>
        <th>月消费总额</th>
    </tr>


    <c:forEach items="${data}" var="data" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <%-- 切记el表达式中通过key取特殊值  用 []  --%>
            <td>${data['inMonth']}</td>
            <td>${data['allConsume']}</td>
        </tr>
    </c:forEach>

</table>
<input type="button" value="后退" onclick="window.history.back()"/>
</body>
</html>
