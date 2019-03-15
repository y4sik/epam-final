<%--
  Created by IntelliJ IDEA.
  User: hylio
  Date: 23.10.2018
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/bootstrap4/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap4/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/style.css">

    <%--<link rel="stylesheet" justify-content-md-center href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>
</head>
<body>

<div>
    <%--<p style="color:red;">${errorString}</p>--%>
    <%--<button type="button" class="btn btn-primary" data-toggle="popover"--%>
    <%--title="Сообщение" data-content="Ура, Bootstrap 4 работает">--%>
    <%--Поднеси ко мне курсор--%>
    <%--</button>--%>
    <div class="container-fluid h-100 bg-light">
        <div class="row h-100 justify-content-center">
            <div class="col-3 align-self-center">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="login">
                    <input type="hidden" name="checkFrom" value="fromLogin">
                    <div class="form-group">
                        <label for="usr">Username:</label>
                        <input type="email" class="form-control" id="usr" name="username" placeholder="email">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" id="pwd" name="password" placeholder="password">
                    </div>
                    <div class="text-danger">${errorLogPassMessage}</div>
                    <div class="g-recaptcha" data-sitekey="6LcSOngUAAAAADBR6NZwwRtwN5mg7mgi9JX3cRNC"></div>
                    <button type="submit" class="btn btn-primary mt-2">Log In</button>
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="registration">
                    <button type="submit" class="btn btn-primary mt-2">Registration</button>
                </form>
            </div>
        </div>
    </div>

</div>


<script src='https://www.google.com/recaptcha/api.js'></script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/resources/style/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap4/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap4/js/main.js"></script>

</body>
</html>
