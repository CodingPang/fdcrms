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


    <c:forEach items="${memberMap}" var="data" varStatus="memStatus">
        <tr>
            <td>${billStatus.count}</td>
            <td>${data.memNo}</td>
            <td>${data.memName}</td>
            <td>${data.memPhone}</td>
            <td>${data.memGender}</td>
            <td>${data.memAge}</td>
            <td>
                <a href="javascript:void(0)" onclick="del(${data.memNo})" >删除</a>
                <a href="member/detail?f=edit&memNo=${data.memNo}">修改</a>
                <a href="member/detail?f=detail&memNo=${data.memNo}">详情</a>
                <a href="member/oneMemBill?f=month&memNo=${data.memNo}">个人月费账单</a>
                <a href="member/oneMemBill?f=year&memNo=${data.memNo}">个人年费账单</a>
            </td>
        </tr>
    </c:forEach>

</table>
<input type="button" value="后退" onclick="window.history.back()"/>
</body>
</html>
