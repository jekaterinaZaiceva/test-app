<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<!DOCTYPE html>
<html>
        <head>
            <link rel="stylesheet" href="/test-mvn-app/resources/css/reset.css"/>
            <link rel="stylesheet" href="/test-mvn-app/resources/css/main.css"/>
        </head>
    <body>
        <div class="username">
            <span class="my_name">Все пользователи</span>
        </div>
        <div class="side_bar">
        </div>
        <div class="content">
            <ul class="blogName">
                <c:forEach var="user" items="${users}">
                    <li>
                        <a href = "/test-mvn-app/user/${user.value.id}">
                            ${user.value.name}
                         </a>
                    </li>
                </c:forEach>
            </ul>
         </div>

    </body>
</html>