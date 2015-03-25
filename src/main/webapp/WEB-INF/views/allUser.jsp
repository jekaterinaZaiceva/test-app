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
    <jsp:include page="toolbar.jsp"/>
    <jsp:include page="header.jsp"/>

    <div class="background">
        <div class="username">
            <span class="my_name">Все пользователи</span>
        </div>

            <div class="users">
                <c:forEach var="user" items="${users}">
                        <a href = "/test-mvn-app/user/${user.id}">
                            ${user.name}
                         </a>

                    <div class="delete">
                        <form action="" method="POST">
                              <input type="hidden" name="userId" value = "${user.id}"><br>
                              <input type="submit" value="Удалить" class="formbutton">
                        </form>
                    </div>

                </c:forEach>
            </div>
        </div>

    </body>
</html>