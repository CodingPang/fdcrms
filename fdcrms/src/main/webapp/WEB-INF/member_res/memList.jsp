<%--<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.javaweb.oa.bean.Dept" %>--%>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--毙掉session对象，写上这个，内置对象-就不能用了。-%>
<%--<%@page session="false" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>家庭成员列表页面</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>

<%--显示一个登录名--%>
<h3>欢迎${username}</h3>

<%--安全退出--%>
<a href="user/exit">退出系统</a>

<script type="text/javascript">
    function del(memNo){
        // 弹出确认框，用户点击确定，返回true，点击取消返回false
        var ok = window.confirm("亲，删了不可恢复哦！");
        if(ok){
            // 发送请求进行删除数据的操作。
            // 在JS代码当中如何发送请求给服务器？
            //alert("正在删除数据，请稍后...")

            //document.location.href = "请求路径"
            //document.location = "请求路径"
            //window.location.href = "请求路径"
            //window.location = "请求路径"
            // 注意html的base标签可能对JS代码不起作用。所以JS代码最好前面写上"/oa"
            document.location.href = "${pageContext.request.contextPath}/member/delete?memNo=" + memNo;
        }
    }
</script>

<h1 align="center">家庭成员列表</h1>
<hr >
<table border="1px" align="center" width="50%">
    <tr>
        <%--				<th>序号</th>--%>
        <th>编号</th>
        <th>姓名</th>
        <th>手机号</th>
        <th>性别</th>
        <th>年龄</th>
        <th>操作</th>
    </tr>


    <c:forEach items="${memberMap}" var="data" varStatus="memStatus">
        <tr>
                <%--					<td>${billStatus.count}</td>--%>
            <td>${data.memNo}</td>
            <td>${data.memName}</td>
            <td>${data.memPhone}</td>
            <td>${data.memGender}</td>
            <td>${data.memAge}</td>
            <td>
                <a href="javascript:void(0)" onclick="del(${data.memNo})" >删除</a>
                <a href="member/detail?f=edit&memNo=${data.memNo}">修改</a>
                <a href="member/detail?f=detail&memNo=${data.memNo}">详情</a>
                <a href="statistical/oneBillByMonth?f=oneBillByMonth&memNo=${data.memNo}">个人月费账单</a>
                <a href="statistical/oneBillByYear?f=oneBillByYear&memNo=${data.memNo}">个人年费账单</a>
            </td>
        </tr>
    </c:forEach>


</table>

<hr >
<a href="${pageContext.request.contextPath}/page/memAdd">新增家庭成员</a> <br>
<a href="${pageContext.request.contextPath}/bill/list" style="text-align: center">查看消费账单列表</a> <br>
<a href="${pageContext.request.contextPath}/statistical/allBillByMonth?f=allBillByMonth">家庭月费账单</a> <br>
<a href="${pageContext.request.contextPath}/statistical/typeBillByMonth?f=typeBillByMonth">家庭各项支出月费总账单</a> <br>
<a href="${pageContext.request.contextPath}/statistical/allBillByYear?f=allBillByYear">家庭年费总账单</a> <br>
<a href="${pageContext.request.contextPath}/statistical/typeBillByYear?f=typeBillByYear">家庭各项支出年费总账单</a> <br>
</body>
</html>
