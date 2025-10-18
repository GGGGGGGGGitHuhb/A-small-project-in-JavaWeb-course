<%@ page import="dao.StudentDAO, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>学生列表</title>
  <style>
      table { border-collapse: collapse; width: 100%; margin-top: 10px; }
      th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
      th { background: #f4f4f4; }
  </style>
</head>
<body>
<h2>所有学生信息</h2>

<%
    StudentDAO dao = new StudentDAO();
    List<Map<String, Object>> list = dao.selectAll();
%>

<table>
  <tr>
    <th>ID</th><th>姓名</th><th>性别</th><th>年龄</th><th>体重</th><th>身高</th>
  </tr>
  <%
    for (Map<String, Object> stu : list) {
  %>
  <tr>
    <td><%= stu.get("id") %></td>
    <td><%= stu.get("name") %></td>
    <td><%= stu.get("gender") %></td>
    <td><%= stu.get("age") %></td>
    <td><%= stu.get("weight") %></td>
    <td><%= stu.get("height") %></td>
  </tr>
  <%
    }
  %>
</table>
</body>
</html>
