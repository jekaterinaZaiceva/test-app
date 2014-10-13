<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<html>

<%! private boolean loggedIn; %>

<%
    String login = request.getParameter("login");
    String password = request.getParameter("password");

    if ("user".equals(login) && "1234".equals(password))
       loggedIn = true;
    else
       loggedIn = false;

    if ( ! loggedIn) {
%>

     <form action="/login" method="GET">
        <input type="text" name="login"><br>
        <input type="text" name="password"><br>
        <button>Send</button>
     </form>

<% } else { %>

     <h1>You are already logged in!</h1>

<% } %>

</html>