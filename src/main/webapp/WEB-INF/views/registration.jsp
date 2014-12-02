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
            <span class="my_name">Приветствуем, Вас следует зарегестрироватся</span>
    </div>

     <div class="content">
        <form:form method="POST" action="registration" role="form">
            <div class="login-form">
                <label for="userName">Логин</label>
                <input type="text" name="userName" value="" id="userName"/>
            </div>
            <div class="login-form">
                <label for="password">Пароль</label>
                <input type="password" name="password" value="" id="password"/>
            </div>
            <input class="btn btn-default" type="submit" value="Зарегестрироватся"/>
        </form:form>
    </div>
   <c:if test="${not empty error}">
        <div>Такой логин уже существует. Попробуйте другой</div>
   </c:if>


</body>
</html>