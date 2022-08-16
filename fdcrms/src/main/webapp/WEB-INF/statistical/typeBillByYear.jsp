<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>按年为单位，统计某种消费类型的消费总额</title>
</head>
<body>
<h1 align="center">本家庭各项消费年总账单</h1>
<hr >
<table border="1px" align="center" width="50%">
    <tr>
        <th>编号</th>
        <th>消费类型</th>
        <th>年份</th>
        <th>年消费总额</th>
    </tr>


    <c:forEach items="${data}" var="data" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <%-- 切记el表达式中通过key取特殊值  用 []  --%>
            <td>${data['bill_type']}</td>
            <td>${data['inYear']}</td>
            <td>${data['allConsume']}</td>
        </tr>
    </c:forEach>

</table>
<input type="button" value="后退" onclick="window.history.back()"/>
</body>
</html>
