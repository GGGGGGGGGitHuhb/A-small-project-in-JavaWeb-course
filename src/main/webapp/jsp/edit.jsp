<%@ page import="dao.StudentDAO, entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>修改学生</title>
</head>
<body>
<h2>修改学生信息</h2>
<form method="post" action="edit.jsp">
  ID（必填）：<input type="number" name="id"><br>
  姓名：<input type="text" name="name"><br>
  性别：<input type="text" name="gender"><br>
  年龄：<input type="number" name="age"><br>
  体重(kg)：<input type="number" name="weight" step="0.1"><br>
  身高(cm)：<input type="number" name="height" step="0.1"><br>
  <input type="submit" value="修改">
</form>

<%
  request.setCharacterEncoding("utf-8");
  String idStr = request.getParameter("id");
  if (idStr != null && !idStr.isEmpty()) {
    Student stu = new Student();
    stu.setId(Integer.parseInt(idStr));
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
    String ageStr = request.getParameter("age");
    String weightStr = request.getParameter("weight");
    String heightStr = request.getParameter("height");

    if (name != null && !name.isEmpty()) stu.setName(name);
    if (gender != null && !gender.isEmpty()) stu.setGender(gender);
    if (ageStr != null && !ageStr.isEmpty()) stu.setAge(Integer.parseInt(ageStr));
    if (weightStr != null && !weightStr.isEmpty()) stu.setWeight(Double.parseDouble(weightStr));
    if (heightStr != null && !heightStr.isEmpty()) stu.setHeight(Double.parseDouble(heightStr));

    StudentDAO dao = new StudentDAO();
    boolean ok = dao.updateStudent(stu);

    if (ok) System.out.println("<p style='color:green;'>修改成功！</p>");
    else System.out.println("<p style='color:red;'>修改失败（请检查ID或输入）。</p>");
  }
%>
</body>
</html>
