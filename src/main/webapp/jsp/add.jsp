<%@ page import="dao.StudentDAO" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>添加学生</title>
</head>
<body>
<h2>添加学生</h2>
<form method="post" action="add.jsp">
  姓名：<input type="text" name="name"><br>
  性别：<input type="text" name="gender"><br>
  年龄：<input type="number" name="age"><br>
  体重(kg)：<input type="number" name="weight" step="0.1"><br>
  身高(cm)：<input type="number" name="height" step="0.1"><br>
  <input type="submit" value="添加">
</form>

<%
  request.setCharacterEncoding("utf-8");
  String name = request.getParameter("name");
  if (name != null && !name.isEmpty()) {
    String gender = request.getParameter("gender");
    int age = Integer.parseInt(request.getParameter("age"));
    double weight = Double.parseDouble(request.getParameter("weight"));
    double height = Double.parseDouble(request.getParameter("height"));

    StudentDAO dao = new StudentDAO();
    boolean ok = dao.addStudent(name, gender, age, weight, height);

    if (ok) System.out.println("<p style='color:green;'>添加成功！</p>");
    else System.out.println("<p style='color:red;'>添加失败。</p>");
  }
%>
</body>
</html>
