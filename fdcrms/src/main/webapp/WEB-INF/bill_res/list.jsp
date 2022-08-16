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
		<title>账单列表页面</title>
<%--		设置整个网页的基础路径是：http://localhost:8080/fdcrms/--%>
<%--			注意：1、<base>标签是html的标签，对js代码不一定有效--%>
<%--				 2、在当前的整个html页面中，<base href="http://localhost:8080/fdcrms/">只对路径前没有/的起作用--%>

<%--		<base href="http://localhost:8081/oa/">--%>
		<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
	</head>
	<body>

	<%--显示一个登录名--%>
	<h3>欢迎${username}</h3>

	<%--安全退出--%>
	<a href="user/exit">退出系统</a>

<script type="text/javascript">
	function del(billNo){
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
			document.location.href = "${pageContext.request.contextPath}/bill/delete?billNo=" + billNo;
		}
	}
</script>

		<h1 align="center">账单列表</h1>
		<hr >
		<table border="1px" align="center" width="50%">
			<tr>
<%--				<th>序号</th>--%>
				<th>账单编号</th>
				<th>账单日期</th>
				<th>消费类型</th>
				<th>消费金额</th>
				<th>消费人</th>
				<th>操作</th>
			</tr>

<%--			<%
				// 翻译到service方法体当中
				List<Dept> deptList = (List<Dept>)request.getAttribute("deptList");
				// 循环遍历
				int i = 0;
				for (Dept dept : deptList) {
					//String deptno = dept.getDeptno();
					//String dname = dept.getDname();
					//String loc = dept.getLoc();

					// 在后台输出
					// 把账单名输出到浏览器
			%>

			<tr>
				<td><%=++i%></td>
				<td><%=dept.getDeptno()%></td>
				<td><%=dept.getDname()%></td>
				<td>
					<!--href后面设置为 javascript:void(0) 表示：仍然保留住超链接的样子-->
					<!--点击此超链接之后，不进行页面的跳转。-->
					<!--我只是希望用户点击该超链接的时候执行一段JS代码，不进行页面的跳转。-->
					<a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)" >删除</a>
					<a href="<%=request.getContextPath()%>/dept/detail?f=edit&dno=<%=dept.getDeptno()%>">修改</a>
					<a href="<%=request.getContextPath()%>/dept/detail?f=detail&dno=<%=dept.getDeptno()%>">详情</a>
				</td>
			</tr>

			<%
				}
			%>--%>

			<c:forEach items="${billMap.data}" var="billLists" varStatus="billStatus">
				<tr>
<%--					<td>${billStatus.count}</td>--%>
					<td>${billLists.billNo}</td>
					<td>${billLists.billDate}</td>
					<td>${billLists.billType}</td>
					<td>${billLists.billMoney}</td>
					<td>${billLists.billConsumer}</td>
					<td>
						<a href="javascript:void(0)" onclick="del(${billLists.billNo})" >删除</a>
						<a href="bill/detail?f=edit&billNo=${billLists.billNo}">修改</a>
						<a href="bill/detail?f=detail&billNo=${billLists.billNo}">详情</a>
					</td>
				</tr>
			</c:forEach>


		</table>
		
		<hr >
		<a href="${pageContext.request.contextPath}/page/billAdd">新增账单</a> <br>
	<a href="${pageContext.request.contextPath}/member/list" style="text-align: center">查看家庭成员列表</a>
		
	</body>
</html>
