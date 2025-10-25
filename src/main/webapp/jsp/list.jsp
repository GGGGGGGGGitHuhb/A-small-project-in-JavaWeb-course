<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.StudentDAO, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
  <title>学生列表</title>
  <style>
      table { border-collapse: collapse; width: 100%; margin-top: 10px; }
      th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
      th { background: #f4f4f4; }
  </style>
</head>
<body>
<h2>所有学生信息</h2>

<c:if test="${not empty students}">
  <table>
    <tr>
      <th>ID</th><th>姓名</th><th>性别</th><th>年龄</th><th>体重</th><th>身高</th>
    </tr>
    <c:forEach var="s" items="${students}">
      <tr>
        <td>${s.id}</td>
        <td>${s.name}</td>
        <td>${s.gender}</td>
        <td>${s.age}</td>
        <td>${s.weight}</td>
        <td>${s.height}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<c:if test="${empty students}">
  <p>无结果</p>
</c:if>

</body>
</html>
