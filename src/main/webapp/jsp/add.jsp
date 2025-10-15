<%@ page import="dao.StudentDAO" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>添加学生</title>
</head>
<body>
<h2>添加学生</h2>
<form method="post" action="add.jsp">
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

  <input type="submit" value="添加">
</form>

<%--todo: 性别输入只能是M或F，没有用户提示--%>

<%
  request.setCharacterEncoding("utf-8");
  String name = request.getParameter("name");
  // 必须至少要有名字才允许添加记录
  if (name != null && !name.isEmpty()) {
    String gender = request.getParameter("gender");
    int age = Integer.parseInt(request.getParameter("age"));
    double weight = Double.parseDouble(request.getParameter("weight"));
    double height = Double.parseDouble(request.getParameter("height"));

    StudentDAO dao = new StudentDAO();
    boolean ok = dao.addStudent(name, gender, age, weight, height);

    if (ok) {
        System.out.println("添加成功");
    } else {
        System.out.println("添加失败");
    }
  }
%>
</body>
</html>
