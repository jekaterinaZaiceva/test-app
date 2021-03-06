<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/test-mvn-app/resources/css/reset.css"/>
</head>
<body>
<script src="http://code.jquery.com/jquery-1.11.2.min.js">
</script>
<jsp:include page="toolbar.jsp"/>
<jsp:include page="header.jsp"/>


<script>

    function onDataLoaded(data) {
        var html = "";
        for (var i = 0; i < data.users.length; i++) {
            html += "<span>";
            html += data.users[i].name;
            html += "</span>";
            html += "<br>";
        }

        $("#users").html(html);

        setTimeout(refresh,5000);
    }


    function refresh() {
        $.get( "http://localhost:9090/test-mvn-app/usersJson",
                onDataLoaded
        );
    }

    function clearDiv() {
        $("#users").html("");
    }

    function addUser() {
        var userName = $("#userName").val();
        var userPassword = $("#password").val();
        $.post("http://localhost:9090/test-mvn-app/addUser",
                {userName : userName,
                 password : userPassword})
                .done(function (data) {
                    $("#userName").val("");
                    $("#password").val("");
                    refresh();
                })
                .fail(function(xhr){
                    alert("Fail with" +xhr.status);
                })
    }

</script>

<div class="background">

    <div id="users">

    </div>


    <div id="calculator">
        <div class="keys">
            <input type="button" onclick="refresh();" value="refresh"/>

            <input type="button" onclick="clearDiv();" value="clear"/>

        </div>
    </div>

    <div>
        <p><input id="userName" type="text" value=""></p>
        <p>Ваш пароль  - defaultPass. Пожалуйста, зарегестрируйтесь через форму регистрации.</p>
        <input type="button" onclick="addUser();" value="addUser"/>
    </div>



</div>

</div>
</body>
</html>
