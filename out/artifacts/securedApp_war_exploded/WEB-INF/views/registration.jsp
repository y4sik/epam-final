<%--
  Created by IntelliJ IDEA.
  User: hylio
  Date: 19.12.2018
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/bootstrap4/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap4/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/style.css">
</head>
<body>
<div class="container col-4 mt-5">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="registration">
        <input type="hidden" name="checkFrom" value="fromRegistration">
        <div class="form-group">
            <label for="name1">Name</label>
            <input type="text" class="form-control" id="name1" name="name" placeholder="Name">
        </div>
        <div class="form-group">
            <label for="surname1">Surname</label>
            <input type="text" class="form-control" id="surname1" name="surname" placeholder="Surname">
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="gender" id="exampleRadios1" checked>
            <label class="form-check-label" for="exampleRadios1">
                Man
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="gender" id="exampleRadios2">
            <label class="form-check-label" for="exampleRadios2">
                Woman
            </label>
        </div>
        <div class="form-group">
            <label for="date1">Date of birthday</label>
            <input type="date" class="form-control" id="date1" name="date" placeholder="Date of birthday">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp"
                   placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="password1">Password</label>
            <input type="password" class="form-control" id="password1" name="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="phone1">Phone number</label>
            <input type="text" class="form-control" id="phone1" name="phone" placeholder="Phone number">
        </div>
        <div class="text-danger">${errorRegMessage}</div>
        <button type="submit" class="btn btn-primary">Registration</button>
    </form>
</div>


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
