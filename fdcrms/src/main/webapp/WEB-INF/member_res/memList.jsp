<%--<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.javaweb.oa.bean.Dept" %>--%>
<%@page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--毙掉session对象，写上这个，内置对象-就不能用了。-%>
<%--<%@page session="false" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>家庭成员列表页面</title>
    <%--  采用cdn  引入jQuery--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"
            integrity="sha512-nhY06wKras39lb9lRO76J4397CH1XpRSLfLJSftTeo3+q2vP7PaebILH9TqH+GRpnOhfAGjuYMVmVTOZJ+682w=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <%--    <script>
            if(typeof jQuery!='undefined'){
                alert("jquery加载成功")
            }else {
                alert("jquery加载失败")
            }
        </script>--%>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>

<%--显示一个登录名--%>
<h3>欢迎${username}</h3>

<%--安全退出--%>
<a href="user/exit">退出系统</a>

<script type="text/javascript">
    // 删除操作
    function del(memNo) {
        // 弹出确认框，用户点击确定，返回true，点击取消返回false
        let ok = window.confirm("亲，删了不可恢复哦！");
        if (ok) {
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

    // 按月为单位，统计某个家庭成员某种消费类型的消费总额。
    function selTypeByMonth(memNo, memName) { // 查询该成员所有消费类型
        $.ajax({
            url: "${pageContext.request.contextPath}/statistical/OneMemAllType",
            type: "post",
            dataType: "json",
            data: {"memNo": memNo},
            success: function (data) {
                // $('#selType[name="'+ memName +'"] option[index!="0"]').html(""); // 注意js拼接变量有坑，采用这种方式能正常使用
                // $('#selType[name="'+ memName +'"]').find("option").remove(); // 每次点击后清空原来的option
                $('#selTypeByMonth[name="' + memName + '"] option[value!="0"]').remove(); // 每次点击后清空原来的option
                for (var i = 0; i < data.length; i++) {
                    $('#selTypeByMonth[name="' + memName + '"]').append('<option value="' + (i + 1) + '">' + data[i].bill_type + '</option>');
                }
            }
        })
    }

    function selOneMemAllTypeByMonth(memNo,name) {
        console.log(name)
        let options = $("#selTypeByMonth[name='"+name+"'] option:selected"); //获取选中的项
        let oneType = options.text(); //拿到选中项的文本
        window.location.href = "${pageContext.request.contextPath}/statistical/OneMemOneTypeByMonth?memNo="+memNo + "&billType=" + oneType;
    }

    // 按年为单位，统计某个家庭成员某种消费类型的消费总额。
    function selTypeByYear(memNo, memName) { // 查询该成员所有消费类型
        $.ajax({
            url: "${pageContext.request.contextPath}/statistical/OneMemAllType",
            type: "post",
            dataType: "json",
            data: {"memNo": memNo},
            success: function (data) {
                // $('#selType[name="'+ memName +'"] option[index!="0"]').html(""); // 注意js拼接变量有坑，采用这种方式能正常使用
                // $('#selType[name="'+ memName +'"]').find("option").remove(); // 每次点击后清空原来的option
                $('#selTypeByYear[name="' + memName + '"] option[value!="0"]').remove(); // 每次点击后清空原来的option
                for (var i = 0; i < data.length; i++) {
                    $('#selTypeByYear[name="' + memName + '"]').append('<option value="' + (i + 1) + '">' + data[i].bill_type + '</option>');
                }
            }
        })
    }

    function selOneMemAllTypeByYear(memNo,name) {
        console.log(name)
        let options = $("#selTypeByYear[name='"+name+"'] option:selected"); //获取选中的项
        let oneType = options.text(); //拿到选中项的文本
        window.location.href = "${pageContext.request.contextPath}/statistical/OneMemOneTypeByYear?memNo="+memNo + "&billType=" + oneType;
    }
</script>

<h1 align="center">家庭成员列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>

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
                <a href="javascript:void(0)" onclick="del(${data.memNo})">删除</a>
                <a href="member/detail?f=edit&memNo=${data.memNo}">修改</a>
                <a href="member/detail?f=detail&memNo=${data.memNo}">详情</a>
                <a href="statistical/oneBillByMonth?f=oneBillByMonth&memNo=${data.memNo}">个人月费账单</a>
                <a href="statistical/oneBillByYear?f=oneBillByYear&memNo=${data.memNo}">个人年费账单</a>
                <select name="${data.memName}" id="selTypeByMonth" onclick="selTypeByMonth(${data.memNo},'${data.memName}')"
                        onchange="selOneMemAllTypeByMonth(${data.memNo},'${data.memName}')">
                    <option value="0">---个人各项支出月费账单---</option>
                </select>
                <select name="${data.memName}" id="selTypeByYear" onclick="selTypeByYear(${data.memNo},'${data.memName}')"
                        onchange="selOneMemAllTypeByYear(${data.memNo},'${data.memName}')">
                    <option value="0">---个人各项支出年费账单---</option>
                </select>
            </td>
        </tr>
    </c:forEach>


</table>

<hr>
<a href="${pageContext.request.contextPath}/page/memAdd">新增家庭成员</a> <br>
<a href="${pageContext.request.contextPath}/bill/list" style="text-align: center">查看消费账单列表</a> <br>
<a href="${pageContext.request.contextPath}/statistical/allBillByMonth?f=allBillByMonth">家庭月费账单</a> <br>
<a href="${pageContext.request.contextPath}/statistical/typeBillByMonth?f=typeBillByMonth">家庭各项支出月费总账单</a> <br>
<a href="${pageContext.request.contextPath}/statistical/allBillByYear?f=allBillByYear">家庭年费总账单</a> <br>
<a href="${pageContext.request.contextPath}/statistical/typeBillByYear?f=typeBillByYear">家庭各项支出年费总账单</a> <br>
</body>

</html>
