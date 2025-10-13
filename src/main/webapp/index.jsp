<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>学生身体体质信息管理系统</title>
</head>
<link rel="stylesheet" href="./index.css" />
<body>
<h1>学生身体体质信息管理系统</h1>
<div class="wrapper">
    <div class="left" id="navMenu">
        <a class="nav-item" data-url="./jsp/list.jsp">列出全部学生</a>
        <a class="nav-item" data-url="./jsp/search.jsp">按条件查询学生</a>
        <a class="nav-item" data-url="./jsp/add.jsp">新添加学生</a>
        <a class="nav-item" data-url="./jsp/delete.jsp">按条件删除学生</a>
        <a class="nav-item" data-url="./jsp/edit.jsp">按条件修改学生</a>
    </div>
    <div class="right" id="contentArea">
        <iframe
                src="./jsp/list.jsp"
                id="contentFrame"
                name="contentFrame"
        ></iframe>
    </div>
</div>
</body>
<script src="./index.js"></script>
</html>
