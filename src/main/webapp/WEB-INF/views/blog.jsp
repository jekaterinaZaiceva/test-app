<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/test-mvn-app/resources/css/blog.css"/>
<meta cherser="UTF-8">
<title>Document</title>
</head>

<body>
<div class="username"><span class="my_name">Я - ${user}</span></div>
<div class="back"><a href = "http://localhost:9090/test-mvn-app/user/${userId}"><span class="back_text">Вернутся</span></a></div>
<span class="blog_text"> ${blog}  </span>
</body>
</html>
