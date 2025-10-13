<%@ page import="dao.StudentDAO, entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>删除学生</title>
</head>
<body>
<h2>按条件删除学生</h2>
<form method="post" action="delete.jsp">
  姓名：<input type="text" name="name"><br>
  性别：<input type="text" name="gender"><br>
  年龄：<input type="number" name="age"><br>
  <input type="submit" value="删除">
</form>

<%
  request.setCharacterEncoding("utf-8");
  String name = request.getParameter("name");
  String gender = request.getParameter("gender");
  String ageStr = request.getParameter("age");

  if (name != null || gender != null || ageStr != null) {
    Student stu = new Student();
    if (name != null && !name.isEmpty()) stu.setName(name);
    if (gender != null && !gender.isEmpty()) stu.setGender(gender);
    if (ageStr != null && !ageStr.isEmpty()) stu.setAge(Integer.parseInt(ageStr));

    StudentDAO dao = new StudentDAO();
    boolean ok = dao.delStudent(stu);
    if (ok) System.out.println("<p style='color:green;'>删除成功！</p>");
    else System.out.println("<p style='color:red;'>删除失败。</p>");
  }
%>
</body>
</html>
