<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/test-mvn-app/resources/css/reset.css"/>
        <link rel="stylesheet" href="/test-mvn-app/resources/css/main.css"/>
            <title>Document</title>
    </head>

    <body>
    <jsp:include page="toolbar.jsp"/>
    <jsp:include page="header.jsp"/>

    <div class="background">
        <div class="username">
            <span class="my_name">Я - ${user.name}</span>
        </div>
        <div class="my_blogs"><a href = "/test-mvn-app/user/${blog.userId}" class="back_text">${blog.name}</a></div>
        <div class="back"><a href = "/test-mvn-app/user/${blog.userId}" class="back_text">Вернутся</a></div>
        <div class="side_bar">
                    <span class="add_blog">Добавить текст блога:</span>
                    <form action="" method="POST">
                            <label class="formname">Текст блога:</label>
                                <input type="blogText" name="blogText" class="form_text"><br>
                                <input type="submit" value="Отправить" class="formbutton">
                    </form>
         </div>
         <div class="content">
            <div class="blog_text">
                <c:forEach var="blog" items="${blog.textList}">
                                <li>
                                        ${blog}
                                </li>
                </c:forEach>
            </div>


         </div>
        </div>
    </body>
</html>
