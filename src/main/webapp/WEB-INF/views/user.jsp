<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/test-mvn-app/resources/css/user.css"/>
</head>
<body>
<div class="username"><span class="my_name">Я - ${user}</span></div>

<div class="my_blogs">Мои блоги:</div>

<ul class="blogName">
<c:forEach var="blog" items="${blogs}">
<a href = "http://localhost:9090/test-mvn-app/user/${blog.userId}/blog/${blog.blogId}">
    <li>${blog.name}</li></a>
</c:forEach>
</ul>




</body>
</html>
