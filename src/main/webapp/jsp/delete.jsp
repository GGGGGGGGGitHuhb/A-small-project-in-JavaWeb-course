<%@ page import="dao.StudentDAO, model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>删除学生</title>
</head>
<body>
<h2>按条件删除学生</h2>
<form method="post" action="delete.jsp">
  <label for="name">姓名：</label>
  <input type="text" id="name" name="name"><br>

  <label for="gender">性别(M或F)：</label>
  <input type="text" id="gender" name="gender"><br>

  <label for="age">年龄：</label>
  <input type="number" id="age" name="age"><br>

  <label for="weight">体重(kg)：</label>
  <input type="number" id="weight" name="weight" step="0.1"><br>

  <label for="height">身高(cm)：</label>
  <input type="number" id="height" name="height" step="0.1"><br>

  <input type="submit" value="删除">
</form>

<%--todo: 删除前显示要删除的记录并询问用户确定与否--%>

<%
  request.setCharacterEncoding("utf-8");
  String name = request.getParameter("name");
  String gender = request.getParameter("gender");
  String ageStr = request.getParameter("age");
  String weightStr = request.getParameter("weight");
  String heightStr = request.getParameter("height");

  if (name != null || gender != null || ageStr != null || weightStr != null || heightStr != null) {
    Student stu = new Student();
    if (name != null && !name.isEmpty()) stu.setName(name);
    if (gender != null && !gender.isEmpty()) stu.setGender(gender);
    if (ageStr != null && !ageStr.isEmpty()) stu.setAge(Integer.parseInt(ageStr));
    if (weightStr != null && !weightStr.isEmpty()) stu.setWeight(Double.parseDouble(weightStr));
    if (heightStr != null && !heightStr.isEmpty()) stu.setHeight(Double.parseDouble(heightStr));

    StudentDAO dao = new StudentDAO();
    int id = dao.selectByConditions(stu);
    boolean ok = dao.delete(stu);
    if (ok) System.out.println("删除成功");
    else System.out.println("删除失败");
  }
%>
</body>
</html>
