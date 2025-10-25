<%@ page import="dao.StudentDAO, model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
  <title>修改学生</title>
</head>
<body>
<h2>修改学生信息</h2>
<form method="post" action="<c:url value='/students'/>">
  <input type="hidden" name="action" value="update">

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

<c:if test="${not empty error}">
  <p>${error}</p>
</c:if>

</body>
</html>
