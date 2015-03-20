<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>

    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/toolbar.css"/>

</head>
<body>
<div class="toolbar">

        <div class="toolbar_buttons">


            <li class="navigation"><a href = "/test-mvn-app/users"> Все пользователи </a></li>
            <li class="navigation" href="/">help</li>
            <div class="back">

                <form action="/test-mvn-app/logout" method="POST">
                <input type="submit" value="Выйти">
                </form>
            </div>
            <div class="userName">${user.name}</div>
        </div>

</div>
</body>
</html>