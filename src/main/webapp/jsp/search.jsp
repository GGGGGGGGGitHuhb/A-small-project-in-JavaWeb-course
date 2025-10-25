<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
  <title>查询学生</title>
</head>
<style>
    table { border-collapse: collapse; width: 100%; margin-top: 10px; }
    th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
    th { background: #f4f4f4; }
</style>
<body>
<h2>查询学生</h2>

<form method="get" action="<c:url value='/students'/>">
  <input type="hidden" name="action" value="search">

  <label for="id">ID：</label>
  <input type="text" id="id" name="id"><br>
  <input type="submit" value="查询">
</form>

<c:if test="${not empty student}">
  <table class="student-table">
    <tr>
      <th>ID</th><th>姓名</th><th>性别</th><th>年龄</th><th>体重</th><th>身高</th>
    </tr>
    <tr>
      <td>${student.id}</td>
      <td>${student.name}</td>
      <td>${student.gender}</td>
      <td>${student.age}</td>
      <td>${student.weight}</td>
      <td>${student.height}</td>
    </tr>
  </table>
</c:if>

<c:if test="${empty student}">
  <p>无结果</p>
</c:if>

</body>
</html>
