<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<!DOCTYPE html>
<html>
        <head>
            <link rel="stylesheet" href="/test-mvn-app/resources/css/reset.css"/>
            <link rel="stylesheet" href="/test-mvn-app/resources/css/createBlog.css"/>
        </head>
    <body>
    <jsp:include page="toolbar.jsp"/>
    <jsp:include page="header.jsp"/>

        <div class="background">



                    <a href="#" onclick="openPopUp(); return false;"><div class="button">Создать новую тему</div></a>

        <div class="content">
            <div class="my_blogs">Мои блоги:</div>
            <ul class="blogName">
                <c:forEach var="blog" items="${blogs}">
                        <a href = "/test-mvn-app/user/${blog.userId}/blog/${blog.blogId}">
                            ${blog.name}
                         </a>

                        <div class="edit">
                            <form action="/test-mvn-app/user/${blog.userId}/edit/${blog.blogId}" method="POST">
                                <input type="hidden" name="blogId" value = "${blog.blogId}"><br>
                                <input type="text" name="blogName" value = "${blog.name}"><br>
                                <input type="submit" value="Редактировать" class="formbutton">
                            </form>
                        </div>
                </c:forEach>
            </ul>

            </div>

            <div class="popUp_w __close" id="popUp">
                <div class="popUp">
                    <div class="popUp_cnt">
                        <div class="popUp_actions">
                            <a href="#" onclick="closePopUp(); return false;"><img src="/test-mvn-app/resources/images/close.png"></a>
                        </div>
                        <div class="popUp_t"><h1>Добавить блог:</h1></div>
                        <div class="popUp_tx">
                            <form action="" method="POST">
                                Название блога: <input type="text" maxlength=150 name="blogName"><br>

                                <input type="submit" value="Создать"/></form>
                        </div>
                        <div class="popUp_error">
                            <c:if test="${not empty error}">
                                <p>${error}</p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
    </div>
    <script src="/test-mvn-app/resources/js/script.js"></script>
    </body>
</html>
