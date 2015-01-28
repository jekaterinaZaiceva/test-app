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
            <link rel="stylesheet" href="/test-mvn-app/resources/css/login.css"/>
        </head>
<body>
    <div class="container">
    	<section id="content">
            <form:form method="POST" action="login" role="form">
                <h1>Форма логина</h1>
                <div>
                    <label for="userName">Логин</label>
                    <input type="text" name="userName" value="" id="userName"/>
                </div>
                <div>
                    <label for="password">Пароль</label>
                    <input type="password" name="password" value="" id="password"/>
                </div>
                <div>
                    <input type="submit" value="Логин"/>
                </div>
            </form:form>
         </section>
    </div>
   <c:if test="${not empty error}">
        <div>${error}</div>
   </c:if>
</body>
</html>