<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
        <head>
            <link rel="stylesheet" href="/test-mvn-app/resources/css/reset.css"/>
            <link rel="stylesheet" href="/test-mvn-app/resources/css/main.css"/>
        </head>
<body>
    <div class="username">
            <span class="my_name">Приветствуем, Вас следует залогинится</span>
    </div>

     <div class="content">
        <form:form method="POST" action="login" modelAttribute="loginInfo" role="form">
            <div class="login-form">
                <label for="username">Логин</label>
                <form:input placeholder="username" path="username" id="username"/>
            </div>
            <div class="login-form">
                <label for="password">Пароль</label>
                <form:input placeholder="password" type="password" path="password" id="password"/>
            </div>
            <input class="btn btn-default" type="submit" value="Логин"/>
        </form:form>
    </div>

</body>
</html>