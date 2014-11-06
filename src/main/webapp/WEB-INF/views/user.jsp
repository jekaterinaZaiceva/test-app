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
        <div class="username"><span class="my_name">Я - ${user.name}</span></div>
        <div class="my_blogs"><a href = "/test-mvn-app/users" class="back_text">Все пользователи</a></div>
        <div class="side_bar">
            <span class="add_blog">Добавить блог:</span>
            <form action="" method="POST">
                    <label class="formname">Название блога:</label>
                        <input type="blogName" name="blogName" class="form_text"><br>
                        <input type="submit" value="Отправить" class="formbutton">
            </form>
        </div>
        <div class="content">
            <div class="my_blogs">Мои блоги:</div>
            <ul class="blogName">
                <c:forEach var="blog" items="${blogs}">
                    <li>
                        <a href = "/test-mvn-app/user/${blog.userId}/blog/${blog.blogId}">
                            ${blog.name}
                         </a>
                    </li>
                </c:forEach>
            </ul>
         </div>

    </body>
</html>
