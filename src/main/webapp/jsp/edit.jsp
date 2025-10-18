<%@ page import="dao.StudentDAO, model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>修改学生</title>
</head>
<body>
<h2>修改学生信息</h2>
<form method="post" action="edit.jsp">
  <label for="name">姓名：</label>
  <input type="text" id="name" name="name"><br>

  <label for="gender">性别：</label>
  <input type="text" id="gender" name="gender"><br>

  <label for="age">年龄：</label>
  <input type="number" id="age" name="age"><br>

  <label for="weight">体重(kg)：</label>
  <input type="number" id="weight" name="weight" step="0.1"><br>

  <label for="height">身高(cm)：</label>
  <input type="number" id="height" name="height" step="0.1"><br>

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
    boolean ok = dao.update(stu);

    if (ok) System.out.println("修改成功");
    else System.out.println("修改失败");
  }
%>
</body>
</html>
