<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>学生身体体质信息管理系统</title>
    <link rel="stylesheet" href="./index.css" />
</head>
<body>
<h1>学生身体体质信息管理系统</h1>

<div class="wrapper">
    <!-- 左侧导航 -->
    <div class="left" id="navMenu">
        <a class="nav-item" href="${pageContext.request.contextPath}/students?action=list">列出全部学生</a>
        <a class="nav-item" href="${pageContext.request.contextPath}/jsp/search.jsp">查询学生</a>
        <a class="nav-item" href="${pageContext.request.contextPath}/jsp/add.jsp">新添加学生</a>
        <a class="nav-item" href="${pageContext.request.contextPath}/jsp/delete.jsp">按条件删除学生</a>
        <a class="nav-item" href="${pageContext.request.contextPath}/jsp/edit.jsp">按条件修改学生</a>
    </div>

    <!-- 右侧内容区 -->
    <div class="right" id="contentArea">
        <p>欢迎使用学生身体体质信息管理系统，请从左侧选择功能。</p>
    </div>
</div>
</body>
</html>
