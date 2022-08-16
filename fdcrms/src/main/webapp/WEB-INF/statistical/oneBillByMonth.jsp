<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>按月为单位，统计某个家庭成员的消费总额</title>
</head>
<body>
<h1 align="center">${username}的每月消费月总账单</h1>
<hr >
<table border="1px" align="center" width="50%">
    <tr>
        <%--				<th>序号</th>--%>
        <th>编号</th>
        <th>消费人</th>
        <th>月份</th>
        <th>月消费总额</th>
    </tr>


    <c:forEach items="${data}" var="data" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${data.consumer}</td>
            <%-- 切记el表达式中通过key取特殊值  用 []  --%>
            <td>${data['inMonth']}</td>
            <td>${data['allConsume']}</td>
        </tr>
    </c:forEach>

</table>
<input type="button" value="后退" onclick="window.history.back()"/>
</body>
</html>
