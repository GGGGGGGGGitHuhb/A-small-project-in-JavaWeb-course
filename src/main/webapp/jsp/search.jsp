<%@ page import="dao.StudentDAO, entity.Student, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>查询学生</title>
</head>
<body>
<h2>按条件查询学生</h2>
<form method="get" action="search.jsp">
  姓名：<input type="text" name="name">
  性别：<input type="text" name="gender">
  <input type="submit" value="查询">
</form>

<%
  String name = request.getParameter("name");
  String gender = request.getParameter("gender");

  if ((name != null && !name.isEmpty()) || (gender != null && !gender.isEmpty())) {
    Student stu = new Student();
    if (name != null && !name.isEmpty()) stu.setName(name);
    if (gender != null && !gender.isEmpty()) stu.setGender(gender);

    StudentDAO dao = new StudentDAO();
    List<Map<String, Object>> list = dao.getStudents(stu);

    if (list.isEmpty()) {
      System.out.println("<p>未查询到结果。</p>");
    } else {
%>
<table border="1" cellspacing="0" cellpadding="6">
  <tr><th>ID</th><th>姓名</th><th>性别</th><th>年龄</th><th>体重</th><th>身高</th></tr>
  <%
    for (Map<String, Object> row : list) {
  %>
  <tr>
    <td><%= row.get("id") %></td>
    <td><%= row.get("name") %></td>
    <td><%= row.get("gender") %></td>
    <td><%= row.get("age") %></td>
    <td><%= row.get("weight") %></td>
    <td><%= row.get("height") %></td>
  </tr>
  <%
    }
  %>
</table>
<%
    }
  }
%>
</body>
</html>
