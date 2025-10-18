<%@ page import="dao.StudentDAO, model.Student, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>查询学生</title>
</head>
<body>
<h2>按条件查询学生</h2>
<form method="get" action="search.jsp">
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

  <input type="submit" value="查询">
</form>

<%--todo: 连续查找时上一次记录仍然显示--%>

<%
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
    String ageStr = request.getParameter("age");
    String weightStr = request.getParameter("weight");
    String heightStr = request.getParameter("height");

    // 字符串直接处理，数字需要判断合法性
    Student stu = new Student();

    if (name != null && !name.isEmpty()) stu.setName(name);
    if (gender != null && !gender.isEmpty()) stu.setGender(gender);

    try {
        if (ageStr != null && !ageStr.isEmpty()) {
          int age = Integer.parseInt(ageStr);
          stu.setAge(age);
        }
        if (weightStr != null && !weightStr.isEmpty()) {
          double weight = Double.parseDouble(weightStr);
          stu.setWeight(weight);
        }
        if (heightStr != null && !heightStr.isEmpty()) {
          double height = Double.parseDouble(heightStr);
          stu.setHeight(height);
        }
    } catch (NumberFormatException e) {
      System.out.println("参数非法：" + e.getMessage());
    }

    // 至少有一项被设置
    if (stu.getName() != null || stu.getGender() != null || stu.getAge() != null && stu.getAge() >= 0 || stu.getWeight() != null && stu.getWeight() >= 0 || stu.getHeight() != null && stu.getHeight() >= 0) {
      StudentDAO dao = new StudentDAO();
      List<Map<String, Object>> list = null;
      try {
        list = dao.selectByConditions(stu);
      } catch (java.sql.SQLException throwables) {
        throw new RuntimeException(throwables);
      }

      if (list.isEmpty()) {
        System.out.println("未查询到结果");
      } else {
%>

<table class="student-table">
  <tr>
    <th>ID</th>
    <th>姓名</th>
    <th>性别</th>
    <th>年龄</th>
    <th>体重</th>
    <th>身高</th>
  </tr>

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
