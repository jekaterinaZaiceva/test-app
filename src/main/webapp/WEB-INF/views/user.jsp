<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<!DOCTYPE html>
<html>
<body>

<h1>My Blogs Page</h1>
<h1>${user}</h1>
<ul>

<c:forEach var="blog" items="${blogs}">
<a href = "http://localhost:9090/test-mvn-app/user/${blog.userId}/blog/${blog.blogId}">
    <li>${blog.name}</li></a>
</c:forEach>
</ul>




</body>
</html>
